package com.victor.calculadorcilla;

import android.app.AlertDialog;
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

    int GameMode;
    String ImagePack;
    peopleDB peopledb;
    SharedPreferences settings;
    TextView trys4;
    TextView trys6;

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


    int imagesfoure[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick};
    int imagessixe[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_shocked,R.drawable.ic_sleepy,R.drawable.ic_wink_1,R.drawable.ic_arrogant,R.drawable.ic_bored,R.drawable.ic_surprised_3,R.drawable.ic_smart,R.drawable.ic_muted,R.drawable.ic_confused,R.drawable.ic_kiss,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_shocked,R.drawable.ic_sleepy,R.drawable.ic_wink_1,R.drawable.ic_arrogant,R.drawable.ic_bored,R.drawable.ic_surprised_3,R.drawable.ic_smart,R.drawable.ic_muted,R.drawable.ic_confused,R.drawable.ic_kiss};
    int imagesfourp[]={R.drawable.ic_abra,R.drawable.ic_bellsprout,R.drawable.ic_bullbasaur,R.drawable.ic_caterpie,R.drawable.ic_charmander,R.drawable.ic_dratini,R.drawable.ic_eevee,R.drawable.ic_jigglypuff,R.drawable.ic_abra,R.drawable.ic_bellsprout,R.drawable.ic_bullbasaur,R.drawable.ic_caterpie,R.drawable.ic_charmander,R.drawable.ic_dratini,R.drawable.ic_eevee,R.drawable.ic_jigglypuff};
    int imagessixp[]={R.drawable.ic_abra,R.drawable.ic_bellsprout,R.drawable.ic_bullbasaur,R.drawable.ic_caterpie,R.drawable.ic_charmander,R.drawable.ic_dratini,R.drawable.ic_eevee,R.drawable.ic_jigglypuff,R.drawable.ic_mankey,R.drawable.ic_meowth,R.drawable.ic_mew,R.drawable.ic_pidgey,R.drawable.ic_pikachu_2,R.drawable.ic_snorlax,R.drawable.ic_psyduck,R.drawable.ic_rattata,R.drawable.ic_zubat,R.drawable.ic_squirtle,R.drawable.ic_abra,R.drawable.ic_bellsprout,R.drawable.ic_bullbasaur,R.drawable.ic_caterpie,R.drawable.ic_charmander,R.drawable.ic_dratini,R.drawable.ic_eevee,R.drawable.ic_jigglypuff,R.drawable.ic_mankey,R.drawable.ic_meowth,R.drawable.ic_mew,R.drawable.ic_pidgey,R.drawable.ic_pikachu_2,R.drawable.ic_snorlax,R.drawable.ic_psyduck,R.drawable.ic_rattata,R.drawable.ic_zubat,R.drawable.ic_squirtle};



    int imagesfouraux[];
    int imagessixaux[];
    int correspondencias4[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int correspondencias6[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int correspondencias4_aux[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int correspondencias6_aux[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean back_vector4[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    boolean back_vector_aux4[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    boolean back_vector6[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    boolean back_vector_aux6[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};



    public void onClick4(View v) {
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
            image=getResources().getDrawable(correspondencias4[which]);
            if (back_vector4[which]) {
                flipper.flipImage(image,((ImageView)findViewById(v.getId())));
                recent=((ImageView)findViewById(v.getId()));
                r=which;
                onclicked4();
                back_vector4[which]=!back_vector4[which];
            }
        }
    }

    public void onClick6(View v) {
        if (!twoflipped) {
            int which=0;
            switch (v.getId()) {
                case R.id.z0:
                    which=0;
                    break;
                case R.id.z1:
                    which=1;
                    break;
                case R.id.z2:
                    which=2;
                    break;
                case R.id.z3:
                    which=3;
                    break;
                case R.id.z4:
                    which=4;
                    break;
                case R.id.z5:
                    which=5;
                    break;
                case R.id.z6:
                    which=6;
                    break;
                case R.id.z7:
                    which=7;
                    break;
                case R.id.z8:
                    which=8;
                    break;
                case R.id.z9:
                    which=9;
                    break;
                case R.id.z10:
                    which=10;
                    break;
                case R.id.z11:
                    which=11;
                    break;
                case R.id.z12:
                    which=12;
                    break;
                case R.id.z13:
                    which=13;
                    break;
                case R.id.z14:
                    which=14;
                    break;
                case R.id.z15:
                    which=15;
                    break;
                case R.id.z16:
                    which=16;
                    break;
                case R.id.z17:
                    which=17;
                    break;
                case R.id.z18:
                    which=18;
                    break;
                case R.id.z19:
                    which=19;
                    break;
                case R.id.z20:
                    which=20;
                    break;
                case R.id.z21:
                    which=21;
                    break;
                case R.id.z22:
                    which=22;
                    break;
                case R.id.z23:
                    which=23;
                    break;
                case R.id.z24:
                    which=24;
                    break;
                case R.id.z25:
                    which=25;
                    break;
                case R.id.z26:
                    which=26;
                    break;
                case R.id.z27:
                    which=27;
                    break;
                case R.id.z28:
                    which=28;
                    break;
                case R.id.z29:
                    which=29;
                    break;
                case R.id.z30:
                    which=30;
                    break;
                case R.id.z31:
                    which=31;
                    break;
                case R.id.z32:
                    which=32;
                    break;
                case R.id.z33:
                    which=33;
                    break;
                case R.id.z34:
                    which=34;
                    break;
                case R.id.z35:
                    which=35;
                    break;
            }
            image=getResources().getDrawable(correspondencias6[which]);
            if (back_vector6[which]) {
                flipper.flipImage(image,((ImageView)findViewById(v.getId())));
                recent=((ImageView)findViewById(v.getId()));
                r=which;
                onclicked6();
                back_vector6[which]=!back_vector6[which];
            }
        }
    }

    void onclicked4 () {
        if (oneisflipped) {
            Thread th = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (correspondencias4[r]==correspondencias4[tof]) {
                                    recent.setEnabled(false);
                                    recent.setVisibility(View.GONE);
                                    theoneflipped.setEnabled(false);
                                    theoneflipped.setVisibility(View.GONE);
                                    quedan-=2;
                                }
                                intentos+=1;
                                flipper.flipImage(back,recent);
                                back_vector4[r]=true;
                                flipper.flipImage(back,theoneflipped);
                                back_vector4[tof]=true;
                                oneisflipped=false;
                                theoneflipped=null;
                                trys4.setText(Integer.toString(intentos));
                                twoflipped=false;
                                if (quedan==0) {
                                    acaba4();
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

    void onclicked6 () {
        if (oneisflipped) {
            Thread th = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (correspondencias6[r]==correspondencias6[tof]) {
                                    recent.setEnabled(false);
                                    recent.setVisibility(View.GONE);
                                    theoneflipped.setEnabled(false);
                                    theoneflipped.setVisibility(View.GONE);
                                    quedan-=2;
                                }
                                intentos+=1;
                                flipper.flipImage(back,recent);
                                back_vector6[r]=true;
                                flipper.flipImage(back,theoneflipped);
                                back_vector6[tof]=true;
                                oneisflipped=false;
                                theoneflipped=null;
                                trys6.setText(Integer.toString(intentos));
                                twoflipped=false;
                                if (quedan==0) {
                                    acaba6();
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

    void acaba4() {
        String user=settings.getString("CurrentUser",null);
        String updateQuery ="UPDATE People SET best_score4="+String.valueOf(intentos)+ " WHERE (best_score4 > "+String.valueOf(intentos)+" OR best_score4=0) AND user = \""+user+"\"";
        peopledb.getWritableDatabase().execSQL(updateQuery);
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

        builder.setTitle("You won!");
        builder.setMessage("You finally won, GG! You only needed " + String.valueOf(intentos)+" tries (impressive, you f***ing N00B). Do you want to try for a better score?");

        builder.setPositiveButton("Replay!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Start();
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

    void acaba6() {
        String user=settings.getString("CurrentUser",null);
        String updateQuery ="UPDATE People SET best_score6="+String.valueOf(intentos)+ " WHERE (best_score6 > "+String.valueOf(intentos)+" OR best_score6=0) AND user = \""+user+"\"";
        peopledb.getWritableDatabase().execSQL(updateQuery);
        AlertDialog.Builder builder = new AlertDialog.Builder(Memory.this);

        builder.setTitle("You won!");
        builder.setMessage("You finally won, GG! You only needed " + String.valueOf(intentos)+" tries (impressive, you f***ing N00B). Do you want to try for a better score?");

        builder.setPositiveButton("Replay!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Start();
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

    public void startGame4() {
        setContentView(R.layout.activity_memory4);
        ImageView aux;
        aux=((ImageView)findViewById(R.id.zero));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.one));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.three));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.four));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.five));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.six));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.seven));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.eight));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.nine));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.ten));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.eleven));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.twelve));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.thirteen));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.fourteen));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.fifteen));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);

        trys4=((TextView)findViewById(R.id.trys4));
        quedan=16;
        correspondencias4=correspondencias4_aux;
        back_vector4=back_vector_aux4;
        if (ImagePack.equals("emojis")) {
            imagesfouraux=imagesfoure;
        } else {
            imagesfouraux=imagesfourp;
        }
        Random r=new Random();
        int n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[0]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[1]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[2]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[3]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[4]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[5]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[6]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[7]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[8]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[9]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[10]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[11]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[12]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[13]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[14]=imagesfouraux[n];
        imagesfouraux[n]=-1;
        n=r.nextInt(16);
        while (imagesfouraux[n]==-1) {
            n=r.nextInt(16);
        }
        correspondencias4[15]=imagesfouraux[n];
    }

    public void startGame6() {
        setContentView(R.layout.activity_memory6);
        ImageView aux;
        aux=((ImageView)findViewById(R.id.z0));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z1));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z2));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z3));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z4));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z5));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z6));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z7));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z8));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z9));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z10));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z11));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z12));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z13));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z14));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z15));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z16));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z17));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z18));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z19));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z20));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z21));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z22));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z23));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z24));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z25));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z26));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z27));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z28));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z29));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z30));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z31));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z32));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z33));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z34));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)findViewById(R.id.z35));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        trys6=((TextView)findViewById(R.id.trys6));
        quedan=36;
        back_vector6=back_vector_aux6;
        correspondencias6=correspondencias6_aux;
        if (ImagePack.equals("emojis")) {
            imagessixaux=imagessixe;
        } else {
            imagessixaux=imagessixp;
        }
        Log.v("hey!!!","no sabes lo que he pasado111111111111");
        Random r=new Random();
        int n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[0]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[1]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[2]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[3]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[4]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[5]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[6]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[7]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[8]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[9]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[10]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[11]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[12]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[13]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[14]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[15]=imagessixaux[n];
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[16]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[17]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[18]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[19]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[20]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[21]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[22]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[23]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[24]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[25]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[26]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[27]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[28]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[29]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[30]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[31]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[32]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[33]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[34]=imagessixaux[n];
        imagessixaux[n]=-1;
        n=r.nextInt(36);
        while (imagessixaux[n]==-1) {
            n=r.nextInt(36);
        }
        correspondencias6[35]=imagessixaux[n];
    }

    protected void Start() {
        intentos=0;
        oneisflipped=false;
        twoflipped=false;
        settings=null;
        settings=this.getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        ImagePack=settings.getString("Images_pack","emojis");
        GameMode=settings.getInt("GameMode",4);
        if (GameMode==4) {
            startGame4();
        } else {
            startGame6();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        peopledb=new peopleDB(this);
        flipper= new CoolImageFlipper(this);
        back=getResources().getDrawable(R.drawable.ic_interr);
        Start();
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

                                    SharedPreferences.Editor edit=settings.edit();
                                    edit.putInt("GameMode",4);
                                    edit.apply();
                                    Start();
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

                                    SharedPreferences.Editor edit=settings.edit();
                                    edit.putInt("GameMode",4);
                                    edit.apply();
                                    Start();
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
                                    Start();
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
                                    edit.putString("Images_pack","emojis");
                                    edit.apply();
                                    Start();
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
                                Start();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("correspondencias4",correspondencias4);
        outState.putIntArray("correspondencias6",correspondencias6);
        outState.putBooleanArray("back_vector4",back_vector4);
        outState.putBooleanArray("back_vector6",back_vector6);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState==null) {
            correspondencias4=savedInstanceState.getIntArray("correspondencias4");
            correspondencias6=savedInstanceState.getIntArray("correspondencias6");
            back_vector4=savedInstanceState.getBooleanArray("back_vector4");
            back_vector6=savedInstanceState.getBooleanArray("back_vector6");
        }
    }
}
