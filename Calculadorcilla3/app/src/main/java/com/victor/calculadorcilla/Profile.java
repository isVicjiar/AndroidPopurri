package com.victor.calculadorcilla;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;
import static com.victor.calculadorcilla.R.drawable.ic_angry;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment{

    View rootview;
    SharedPreferences settings;
    Realm realm;
    TextView username;
    TextView score4;
    TextView score6;
    ImageView photo;

    Context context;

    public void setUpProfile() {
        String currentuser = settings.getString("CurrentUser", null);
        //c.getString(c.getColumnIndex("password"))
        RealmResults realmResults=realm.where(User.class).equalTo("name", currentuser).findAll();
        User pu= (User) realmResults.first();
        username.setText(currentuser);
        int s4 = pu.getBest_score4();
        if (s4 == 0) {
            score4.setText("No games played yet");
        } else {
            score4.setText(Integer.toString(s4));
        }
        int s6 = pu.getBest_score6();
        if (s6 == 0) {
            score6.setText("No games played yet");
        } else {
            score6.setText(Integer.toString(s6));
        }
        String i_path = pu.getPhoto();
        if (i_path != null) {
            Uri mUri = Uri.parse(i_path);
            /*try {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.MANAGE_DOCUMENTS);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.MANAGE_DOCUMENTS}, 0);
//                    photo.setImageBitmap(MediaStore.Images.Media.
getBitmap(getActivity().getContentResolver(), mUri));
                } else {
                    photo.setImageBitmap(MediaStore.Images.Media.
                    getBitmap(getActivity().getContentResolver(), mUri));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } else {
            photo.setBackgroundResource(R.drawable.ic_angry);
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
        realm= Realm.getDefaultInstance();
        addressList = null;
        context=this.getActivity().getApplicationContext();
        username = ((TextView) rootview.findViewById(R.id.username));
        score4 = ((TextView) rootview.findViewById(R.id.score4));
        score6 = ((TextView) rootview.findViewById(R.id.score6));
        photo = ((ImageView) rootview.findViewById(R.id.profile_photo));
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_angry);
        photo.setImageBitmap(bitmap);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getImageAsContent = new Intent(Intent.ACTION_GET_CONTENT, null);
                getImageAsContent.setType("image/*");
                startActivityForResult(getImageAsContent, 1);
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
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission( context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, locationListener);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, locationListener);
        }

        return rootview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Como en este caso los 3 intents hacen lo mismo, si el estado es correcto recogemos el resultado
        //Aún así comprobamos los request code. Hay que tener total control de lo que hace nuestra app.
        if(resultCode == RESULT_OK){
            if(requestCode >= 1 && requestCode <= 3){
                data.getData();
                Uri selectedImage = data.getData();
                Log.v("PICK","Selected image uri" + selectedImage);
                try {
                    String user=settings.getString("CurrentUser",null);
                    RealmResults realmResults=realm.where(User.class).equalTo("name",user).findAll();
                    User current=(User) realmResults.first();
                    realm.beginTransaction();
                    current.setPhoto(selectedImage.toString());
                    realm.copyToRealmOrUpdate(current);
                    realm.commitTransaction();
                    photo.setImageResource(android.R.color.transparent);
                    photo.setImageBitmap(MediaStore.Images.Media.
                            getBitmap(getActivity().getContentResolver(), selectedImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            Log.v("Result","Something happened");
        }
    }

    public void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Log Out");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("LogOut",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        settings=getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=settings.edit();
                        editor.putString("CurrentUser",null);
                        editor.apply();
                        Intent i=new Intent(getActivity().getApplicationContext(), Login.class);
                        startActivity(i);
                        getActivity().finish();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
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
        editor.putString("curr_fragment","Profile");
        editor.apply();
    }
}
