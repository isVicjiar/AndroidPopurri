package com.victor.calculadorcilla;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Created by inlab on 27/01/2017.
 */

public class DECIDELOGIN extends Activity implements View.OnClickListener{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "sVBla9s5O4YkIx14rir5tl8U1";
    private static final String TWITTER_SECRET = "zLVCloB9Mhv3hJaGWNobjoO9OCp8WuhJmZ1WfUz2cmP41j2jx8";


    SharedPreferences settings;
    Button start;

    @Override
    public void onClick (View v) {
        String currentuser = settings.getString("CurrentUser", null);

        Intent i = new Intent();
        if (currentuser==null) {
            i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        } else {
            i = new Intent(this, Select_Activity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_decidelogin);
        //Instanciamos el SharedPreferences
        settings = getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        //Consultamos

        start=((Button)findViewById(R.id.s));
        start.setOnClickListener(this);
    }
}