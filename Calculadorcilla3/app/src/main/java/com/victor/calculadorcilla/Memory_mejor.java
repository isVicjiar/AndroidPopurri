package com.victor.calculadorcilla;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.graphics.Color.WHITE;

/**
 * Created by inlab on 30/01/2017.
 */

public class Memory_mejor extends BaseActivity implements View.OnClickListener{

    peopleDB peopledb;
    SharedPreferences settings;
    TextView trys;

    Boolean oneisflipped=false;
    ImageView theoneflipped;
    int tof;
    int r;
    ImageView recent;
    Boolean twoflipped=false;

    int quedan=16;
    int intentos=0;
    CoolImageFlipper flipper;

    Drawable back;
    Drawable image;


    int imagesfourw[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick};

    //int imagessixw[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_emoji};

    int correspondencias[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean back_vector[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    boolean back_vector_aux[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};



    public void onClick(View v) {
        if (!twoflipped) {
            int which=0;
            switch (v.getId()) {
                case R.id.zero:
                    which=0;
                    break;
                case R.id.one:
                    which=1;
                    break;
                case R.id.two:
                    which=2;
                    break;
                case R.id.three:
                    which=3;
                    break;
                case R.id.four:
                    which=4;
                    break;
                case R.id.five:
                    which=5;
                    break;
                case R.id.six:
                    which=6;
                    break;
                case R.id.seven:
                    which=7;
                    break;
                case R.id.eight:
                    which=8;
                    break;
                case R.id.nine:
                    which=9;
                    break;
                case R.id.ten:
                    which=10;
                    break;
                case R.id.eleven:
                    which=11;
                    break;
                case R.id.twelve:
                    which=12;
                    break;
                case R.id.thirteen:
                    which=13;
                    break;
                case R.id.fourteen:
                    which=14;
                    break;
                case R.id.fifteen:
                    which=15;
                    break;
            }
            image=getResources().getDrawable(correspondencias[which]);
            if (back_vector[which]) {
                flipper.flipImage(image,((ImageView)findViewById(v.getId())));
                recent=((ImageView)findViewById(v.getId()));
                r=which;
                onclicked();
                back_vector[which]=!back_vector[which];
            }
        }
    }

    void onclicked () {
        if (oneisflipped) {
            Thread th = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (correspondencias[r]==correspondencias[tof]) {
                                    recent.setEnabled(false);
                                    recent.setVisibility(View.GONE);
                                    theoneflipped.setEnabled(false);
                                    theoneflipped.setVisibility(View.GONE);
                                    quedan-=2;
                                }
                                intentos+=1;
                                flipper.flipImage(back,recent);
                                back_vector[r]=true;
                                flipper.flipImage(back,theoneflipped);
                                back_vector[tof]=true;
                                oneisflipped=false;
                                theoneflipped=null;
                                trys.setText(Integer.toString(intentos));
                                twoflipped=false;
                                if (quedan==0) {
                                    acaba();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            twoflipped=true;
            th.start();
        } else {
            theoneflipped=recent;
            tof=r;
            oneisflipped=true;
        }
    }

    void acaba() {
        String user=settings.getString("CurrentUser",null);
        String updateQuery ="UPDATE People SET best_score4="+String.valueOf(intentos)+ " WHERE (best_score4 > "+String.valueOf(intentos)+" OR best_score4=0) AND user = \""+user+"\"";
        peopledb.getWritableDatabase().execSQL(updateQuery);
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory_mejor.this);

        builder.setTitle("You won!");
        builder.setMessage("You finally won, GG! You only needed " + String.valueOf(intentos)+" tries (impressive, you f***ing N00B). Do you want to try for a better score?");

        builder.setPositiveButton("Replay!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        startGame();
                    }
                });
        builder.setNegativeButton("Back",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void startGame() {
        intentos=0;
        quedan=16;
        back_vector=back_vector_aux;
        int imagesfourwaux[]=imagesfourw;
        Random r=new Random();
        int n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[0]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[1]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[2]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[3]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[4]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[5]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[6]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[7]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[8]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[9]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[10]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[11]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[12]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[13]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[14]=imagesfourwaux[n];
        imagesfourwaux[n]=-1;
        n=r.nextInt(16);
        while (imagesfourwaux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias[15]=imagesfourwaux[n];
    }

    public void setUpViews () {

        ((ImageView)findViewById(R.id.zero)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.one)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.two)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.three)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.four)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.five)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.six)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.seven)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.eight)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.nine)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.ten)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.eleven)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.twelve)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.thirteen)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.fourteen)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.fifteen)).setOnClickListener(this);

        trys=((TextView)findViewById(R.id.trys));
        trys.setText(Integer.toString(intentos));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory4);
        peopledb=new peopleDB(this);
        setUpViews();
        settings=this.getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        flipper= new CoolImageFlipper(this);
        back=getResources().getDrawable(R.drawable.ic_interr);
        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.size:
                //poner dialog list!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                return true;
            case R.id.images:
                //poner dialog list!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                return true;
            case R.id.reset:
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

                builder.setTitle("Restart");
                builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                builder.setPositiveButton("Restart",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //poner un intento más!!!!!!!!!!!!!!!!!
                                startGame();

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
