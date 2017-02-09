package com.victor.calculadorcilla;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Random;

/**
 * Created by inlab on 30/01/2017.
 */

public class Memory extends BaseActivity{

    SharedPreferences settings;
    int GameMode;
    String ImagePack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_memory);
        settings=this.getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        GameMode=settings.getInt("GameMode",4);
        ImagePack=settings.getString("Images_pack","emojis");
        Fragment fragment;
        getSupportActionBar().setTitle("Memory");
        if (GameMode==4) {
            fragment=new Memory_four();
        } else {
            fragment=new Memory_six();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,fragment,"MEMORY_FRAGMENT").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.four:
                if (GameMode==6) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

                    builder.setTitle("Changing difficulty to easy");
                    builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                    builder.setPositiveButton("Restart",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    GameMode=4;
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
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
                return true;
            case R.id.six:
                if (GameMode==4) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

                    builder.setTitle("Changing difficulty to hard");
                    builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                    builder.setPositiveButton("Restart",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    GameMode=6;
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
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
                return true;
            case R.id.emojis:
                if (ImagePack.equals("pokemons")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

                    builder.setTitle("Changing images to emojis");
                    builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                    builder.setPositiveButton("Change",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {


                                    SharedPreferences.Editor edit=settings.edit();
                                    edit.putString("Images_pack","emojis");
                                    edit.apply();
                                    ImagePack="emojis";
                                    if (GameMode==4) {
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
                                    } else {
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
                                    }
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
                    dialog.show();;
                }
                return true;
            case R.id.pokemons:
                if (ImagePack.equals("emojis")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

                    builder.setTitle("Changing images to Pokemons");
                    builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                    builder.setPositiveButton("Change",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {


                                    SharedPreferences.Editor edit=settings.edit();
                                    edit.putString("Images_pack","pokemons");
                                    edit.apply();
                                    ImagePack="pokemons";
                                    if (GameMode==4) {
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
                                    } else {
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
                                    }
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
                return true;
            case R.id.restart:
                AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

                builder.setTitle("Restart");
                builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                builder.setPositiveButton("Restart",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //poner un intento más!!!!!!!!!!!!!!!!!
                                if (GameMode==4) {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
                                } else {
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
                                }
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
