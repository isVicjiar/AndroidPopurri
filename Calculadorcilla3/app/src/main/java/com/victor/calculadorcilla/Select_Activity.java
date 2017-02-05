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

public class Select_Activity extends BaseActivity implements View.OnClickListener {

    SharedPreferences settings;

    public void onClick(View v) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_select);
        //((Toolbar)findViewById(R.id.toolbar)).setTitle("Home");
        settings=this.getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        String f=settings.getString("curr_fragment",null);
        Fragment fragment=new Home();
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
                case "Home":
                    getSupportActionBar().setTitle("Home");
                    fragment=new Home();
                    break;
            }
        }
        else {
            getSupportActionBar().setTitle("Home");
            fragment=new Home();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment,"MEMORY_FRAGMENT").commit();
    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                AlertDialog.Builder builder =  new AlertDialog.Builder(this);

                builder.setTitle("Quick info:");
                builder.setMessage(R.string.home_info);

                builder.setPositiveButton("Dismiss",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}