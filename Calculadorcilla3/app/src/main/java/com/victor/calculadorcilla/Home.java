package com.victor.calculadorcilla;


import android.Manifest;
import android.content.Context;
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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    Context context;

    String appid="59c89b5cecab0b9fd0a25c25bef43f45";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
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
        service.getWeather(String.valueOf(lati), String.valueOf(longi), appid).enqueue(new Callback<WeatherMessage>() {
            @Override
            public void onResponse(Call<WeatherMessage> call, Response<WeatherMessage> response) {
                String weather = response.body().getMessage();
                ((TextView)rootview.findViewById(R.id.loc)).setText(weather);
            }

            @Override
            public void onFailure(Call<WeatherMessage> call, Throwable t) {
                Toast.makeText(context,"Error on request",Toast.LENGTH_LONG).show();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_home, container, false);
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

        getWeather();

        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locationManager = null;
        locationListener = null;
    }

}
