package com.victor.calculadorcilla;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    View rootview;
    SharedPreferences settings;
    TextView username;
    TextView score4;
    TextView score6;
    ImageView photo;

    Context context;

    peopleDB peopledb;

    public void setUpProfile() {
        String currentuser = settings.getString("CurrentUser", null);
        ProfileUser pu = peopledb.getUserProfile(currentuser);
        //c.getString(c.getColumnIndex("password"))
        username.setText(currentuser);
        int s4 = pu.getScore4();
        if (s4 == 0) {
            score4.setText("No games played yet");
        } else {
            score4.setText(Integer.toString(s4));
        }
        int s6 = pu.getScore6();
        if (s6 == 0) {
            score6.setText("No games played yet");
        } else {
            score6.setText(Integer.toString(s6));
        }
        String i_path = pu.getPhoto();
        if (i_path != "null") {
            //buscar lo del path!!!!!!!!!!!!!!!!!!!
        }
    }

    public Profile() {
        // Required empty public constructor
    }


    List<Address> addressList;
    LocationManager locationManager;
    LocationListener locationListener;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_profile, container, false);
        peopledb = new peopleDB(getActivity());
        addressList = null;
        context=this.getActivity().getApplicationContext();
        username = ((TextView) rootview.findViewById(R.id.username));
        score4 = ((TextView) rootview.findViewById(R.id.score4));
        score6 = ((TextView) rootview.findViewById(R.id.score6));
        photo = ((ImageView) rootview.findViewById(R.id.profile_photo));
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        settings = getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        setUpProfile();
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
                    addressList = gc.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < addressList.size(); ++i) {
                    TextView t = (TextView) rootview.findViewById(R.id.location);
                    if (i == 0) t.setText("");
                    t.setText(t.getText() + "\n" + addressList.get(i).getAddressLine(0));
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
}
