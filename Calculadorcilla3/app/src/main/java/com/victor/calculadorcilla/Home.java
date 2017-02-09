package com.victor.calculadorcilla;


import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    Context context;
    SharedPreferences settings;
    String not;

    String appid="59c89b5cecab0b9fd0a25c25bef43f45";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    WeatherInterface service = retrofit.create(WeatherInterface.class);

    double longi;
    double lati;

    View rootview;

    public Home() {
        // Required empty public constructor
    }


    List<Address> addressList;
    LocationManager locationManager;
    LocationListener locationListener;

    public void getWeather () {
        service.getWeather(valueOf(lati), valueOf(longi), appid).enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful()) {
                    Gson gson=new Gson();
                    JSONObject obj=new JSONObject();
//                    WeatherMessage weatherMessage=gson.fromJson(String.valueOf(response),WeatherMessage.class);
                //    ((TextView)rootview.findViewById(R.id.city)).setText(weatherMessage.getName());
                    //String temp = response.body().getMain().getTemp();
                    //((TextView)rootview.findViewById(R.id.temperature)).setText(temp);
                } else {
                    Log.d("ERROR",response.code()+response.message());
                }

            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                not=settings.getString("notifications","Toast");
                Log.d("REQUEST",t.getMessage());
                if (not.equals("Toast")) {
                    Toast.makeText(context,"Error on request",Toast.LENGTH_LONG).show();
                } else {
                    //NOTIFICACION DE BARRA
                    //Entero que nos permite identificar la notificación
                    int mId = 1;
                    //Instanciamos Notification Manager
                    NotificationManager mNotificationManager =
                            (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                    // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
                    // un Builder/contructor.
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getActivity().getApplicationContext())
                                    .setSmallIcon(R.drawable.icono)
                                    .setContentTitle("Error")
                                    .setContentText("Request failed");

                    // mId nos permite actualizar las notificaciones en un futuro
                    // Notificamos
                    mNotificationManager.notify(mId, mBuilder.build());
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_home, container, false);
        settings=getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        addressList = null;
        context=this.getActivity().getApplicationContext();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                Geocoder gc = new Geocoder(context);
                try {
                    //5 mxresults
                    longi=location.getLongitude();
                    lati=location.getLatitude();
                    addressList = gc.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 5);
                    getWeather();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        //provider, tiempo, distancia, listener
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }


        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locationManager = null;
        locationListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("curr_fragment","Home");
        editor.apply();
    }


}
