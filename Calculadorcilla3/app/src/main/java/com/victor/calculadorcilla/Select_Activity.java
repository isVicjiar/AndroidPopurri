package com.victor.calculadorcilla;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Select_Activity extends BaseActivity implements View.OnClickListener {

    SharedPreferences settings;

    public void onClick(View v) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_select);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(getApplicationContext()).build());
        settings=this.getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        String f=settings.getString("curr_fragment",null);
        String just_started=settings.getString("just_started",null);
        Fragment fragment=new Profile();
        if (just_started!=null){
            getSupportActionBar().setTitle("Profile");
            fragment=new Profile();
            SharedPreferences.Editor editor=settings.edit();
            editor.putString("just_started",null);
            editor.apply();
        }
        else {
            if (f!=null) {
                SharedPreferences.Editor editor=settings.edit();
                editor.putString("curr_fragment",null);
                editor.apply();
                switch (f) {
                    case "Calculator":
                        getSupportActionBar().setTitle("Calculator");
                        fragment=new Calculator();
                        break;
                    case "Profile":
                        getSupportActionBar().setTitle("Profile");
                        fragment=new Profile();
                        break;
                    case "Game":
                        getSupportActionBar().setTitle("Game");
                        fragment=new Game();
                        break;
                    case "Music":
                        getSupportActionBar().setTitle("Music");
                        fragment=new Music();
                        break;
                }
            }
            else {
                getSupportActionBar().setTitle("Profile");
                fragment=new Profile();
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment,
                "MEMORY_FRAGMENT").commit();
    }
}