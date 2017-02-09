package com.victor.calculadorcilla;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences settings;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings=getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        super.setContentView(R.layout.activity_base);
        setView();
    }

    protected void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }


    protected void setView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected Toolbar getToolbar(){
        return this.toolbar;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.calculator:
                if (this instanceof Select_Activity) {
                    Fragment fi=new Calculator();
                    ((Toolbar)findViewById(R.id.toolbar)).setTitle("Calculator");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fi,"MEMORY_FRAGMENT").commit();
                } else {
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putString("curr_fragment","Calculator");
                    editor.apply();
                    Intent i=new Intent(this,Select_Activity.class);
                    startActivity(i);
                    finish();
                }
                break;

            case R.id.music:
                if (this instanceof Select_Activity) {
                    Fragment fi=new Music();
                    ((Toolbar)findViewById(R.id.toolbar)).setTitle("Music");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fi,"MEMORY_FRAGMENT").commit();
                } else {
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putString("curr_fragment","Music");
                    editor.apply();
                    Intent i=new Intent(this,Select_Activity.class);
                    startActivity(i);
                    finish();
                }
                break;


            case R.id.game:
                if (this instanceof Select_Activity) {
                    Fragment fi=new Game();
                    ((Toolbar)findViewById(R.id.toolbar)).setTitle("Game");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fi,"MEMORY_FRAGMENT").commit();
                } else {
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putString("curr_fragment","Game");
                    editor.apply();
                    Intent i=new Intent(this,Select_Activity.class);
                    startActivity(i);
                    finish();
                }
                break;

            case R.id.profile:
                if (this instanceof Select_Activity) {
                    Fragment fi=new Profile();
                    ((Toolbar)findViewById(R.id.toolbar)).setTitle("Profile");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            fi,"MEMORY_FRAGMENT").commit();
                } else {
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putString("curr_fragment","Profile");
                    editor.apply();
                    Intent i=new Intent(this,Select_Activity.class);
                    startActivity(i);
                    finish();
                }
                break;

            case R.id.loggout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Log Out");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("LogOut",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                settings=getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=settings.edit();
                                editor.putString("CurrentUser",null);
                                editor.apply();
                                Intent i=new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                                finish();
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
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {

        DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base,
                null);
        FrameLayout frameLayout = (FrameLayout) fullLayout.findViewById(R.id.frame_layout_base);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        super.setContentView(fullLayout);
        setView();
    }
}
