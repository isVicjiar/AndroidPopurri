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

public class Memory extends BaseActivity {

    ImageView zero;
    ImageView one;
    ImageView two;
    ImageView three;
    ImageView four;
    ImageView five;
    ImageView six;
    ImageView seven;
    ImageView eight;
    ImageView nine;
    ImageView ten;
    ImageView eleven;
    ImageView twelve;
    ImageView thirteen;
    ImageView fourteen;
    ImageView fifteen;

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

    Boolean back0= true;
    Boolean back1= true;
    Boolean back2= true;
    Boolean back3= true;
    Boolean back4= true;
    Boolean back5= true;
    Boolean back6= true;
    Boolean back7= true;
    Boolean back8= true;
    Boolean back9= true;
    Boolean back10= true;
    Boolean back11= true;
    Boolean back12= true;
    Boolean back13= true;
    Boolean back14= true;
    Boolean back15= true;


    int imagesfourw[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick};

    //int imagessixw[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_emoji};

    int correspondencias[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};


    Thread th = new Thread(new Runnable() {
        public void run() {
            try {
                Thread.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (theoneflipped.getBackground()==recent.getBackground()) {
                            recent.setEnabled(false);
                            recent.setVisibility(View.GONE);
                            theoneflipped.setEnabled(false);
                            theoneflipped.setVisibility(View.GONE);
                            quedan-=2;
                        }
                        intentos+=1;
                        flipper.flipImage(back,recent);
                        flipper.flipImage(back,theoneflipped);
                        oneisflipped=false;
                        theoneflipped=null;
                        trys.setText(Integer.toString(intentos));
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    });

    void onclicked () {
        if (twoflipped) {

        }else if (oneisflipped) {
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
                                switch (r) {
                                    case 0:
                                        back0=true;
                                        break;
                                    case 1:
                                        back1=true;
                                        break;
                                    case 2:
                                        back2=true;
                                        break;
                                    case 3:
                                        back3=true;
                                        break;
                                    case 4:
                                        back4=true;
                                        break;
                                    case 5:
                                        back5=true;
                                        break;
                                    case 6:
                                        back6=true;
                                        break;
                                    case 7:
                                        back7=true;
                                        break;
                                    case 8:
                                        back8=true;
                                        break;
                                    case 9:
                                        back9=true;
                                        break;
                                    case 10:
                                        back10=true;
                                        break;
                                    case 11:
                                        back11=true;
                                        break;
                                    case 12:
                                        back12=true;
                                        break;
                                    case 13:
                                        back13=true;
                                        break;
                                    case 14:
                                        back14=true;
                                        break;
                                    case 15:
                                        back15=true;
                                        break;
                                }
                                flipper.flipImage(back,theoneflipped);
                                switch (tof) {
                                    case 0:
                                        back0=true;
                                        break;
                                    case 1:
                                        back1=true;
                                        break;
                                    case 2:
                                        back2=true;
                                        break;
                                    case 3:
                                        back3=true;
                                        break;
                                    case 4:
                                        back4=true;
                                        break;
                                    case 5:
                                        back5=true;
                                        break;
                                    case 6:
                                        back6=true;
                                        break;
                                    case 7:
                                        back7=true;
                                        break;
                                    case 8:
                                        back8=true;
                                        break;
                                    case 9:
                                        back9=true;
                                        break;
                                    case 10:
                                        back10=true;
                                        break;
                                    case 11:
                                        back11=true;
                                        break;
                                    case 12:
                                        back12=true;
                                        break;
                                    case 13:
                                        back13=true;
                                        break;
                                    case 14:
                                        back14=true;
                                        break;
                                    case 15:
                                        back15=true;
                                        break;
                                }
                                oneisflipped=false;
                                theoneflipped=null;
                                trys.setText(Integer.toString(intentos));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            th.start();
        } else {
            theoneflipped=recent;
            tof=r;
            oneisflipped=true;
        }
        if (quedan==0) {
            acaba();
        }
    }

    void acaba() {

        String user=settings.getString("user",null);
        String updateQuery ="UPDATE People SET best_score4=? WHERE best_score4 > ? OR best_score4=0 AND user = "+user;
        Cursor c= peopledb.getWritableDatabase().rawQuery(updateQuery, new String [] {String.valueOf(intentos),String.valueOf(intentos)});
        c.moveToFirst();
        c.close();
    }

    public void clicked0 (View view) {
        image=getResources().getDrawable(correspondencias[0]);
        if (!back0) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.zero)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.zero)));
            recent=zero;
            r=0;
            onclicked();
        }
        back0=!back0;
    }
    public void clicked1 (View view) {
        image=getResources().getDrawable(correspondencias[1]);
        if (!back1) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.one)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.one)));
            recent=one;
            r=1;
            onclicked();
        }
        back1=!back1;
    }
    public void clicked2 (View view) {
        image=getResources().getDrawable(correspondencias[2]);
        if (!back2) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.two)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.two)));
            recent=two;
            r=2;
            onclicked();
        }        back2=!back2;
    }
    public void clicked3 (View view) {
        image=getResources().getDrawable(correspondencias[3]);
        if (!back3) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.three)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.three)));
            recent=three;
            r=3;
            onclicked();
        }        back3=!back3;
    }
    public void clicked4 (View view) {
        image=getResources().getDrawable(correspondencias[4]);
        if (!back4) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.four)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.four)));
            recent=four;
            r=4;
            onclicked();
        }        back4=!back4;
    }
    public void clicked5 (View view) {
        image=getResources().getDrawable(correspondencias[5]);
        if (!back5) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.five)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.five)));
            recent=five;
            r=5;
            onclicked();
        }        back5=!back5;
    }
    public void clicked6 (View view) {
        image=getResources().getDrawable(correspondencias[6]);
        if (!back6) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.six)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.six)));
            recent=six;
            r=6;
            onclicked();
        }        back6=!back6;
    }
    public void clicked7 (View view) {
        image=getResources().getDrawable(correspondencias[7]);
        if (!back7) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.seven)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.seven)));
            recent=seven;
            r=7;
            onclicked();
        }        back7=!back7;
    }
    public void clicked8 (View view) {
        image=getResources().getDrawable(correspondencias[8]);
        if (!back8) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.eight)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.eight)));
            recent=eight;
            r=8;
            onclicked();
        }        back8=!back8;
    }
    public void clicked9 (View view) {
        image=getResources().getDrawable(correspondencias[9]);
        if (!back9) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.nine)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.nine)));
            recent=nine;
            r=9;
            onclicked();
        }        back9=!back9;
    }
    public void clicked10 (View view) {
        image=getResources().getDrawable(correspondencias[10]);
        if (!back10) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.ten)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.ten)));
            recent=ten;
            r=10;
            onclicked();
        }        back10=!back10;
    }
    public void clicked11 (View view) {
        image=getResources().getDrawable(correspondencias[11]);
        if (!back11) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.eleven)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.eleven)));
            recent=eleven;
            r=11;
            onclicked();
        }        back11=!back11;
    }
    public void clicked12 (View view) {
        image=getResources().getDrawable(correspondencias[12]);
        if (!back12) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.twelve)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.twelve)));
            recent=twelve;
            r=12;
            onclicked();
        }        back12=!back12;
    }
    public void clicked13 (View view) {
        image=getResources().getDrawable(correspondencias[13]);
        if (!back13) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.thirteen)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.thirteen)));
            recent=thirteen;
            r=13;
            onclicked();
        }        back13=!back13;
    }
    public void clicked14 (View view) {
        image=getResources().getDrawable(correspondencias[14]);
        if (!back14) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.fourteen)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.fourteen)));
            recent=fourteen;
            r=14;
            onclicked();
        }        back14=!back14;
    }
    public void clicked15 (View view) {
        image=getResources().getDrawable(correspondencias[15]);
        if (!back15) {
            flipper.flipImage(back,((ImageView)findViewById(R.id.fifteen)));
        }
        else  {
            flipper.flipImage(image,((ImageView)findViewById(R.id.fifteen)));
            recent=fifteen;
            r=15;
            onclicked();
        }        back15=!back15;
    }

    public void startGame() {
        intentos=0;
        quedan=16;
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

        zero=((ImageView)findViewById(R.id.zero));
        one=((ImageView)findViewById(R.id.one));
        two=((ImageView)findViewById(R.id.two));
        three=((ImageView)findViewById(R.id.three));
        four=((ImageView)findViewById(R.id.four));
        five=((ImageView)findViewById(R.id.five));
        six=((ImageView)findViewById(R.id.six));
        seven=((ImageView)findViewById(R.id.seven));
        eight=((ImageView)findViewById(R.id.eight));
        nine=((ImageView)findViewById(R.id.nine));
        ten=((ImageView)findViewById(R.id.ten));
        eleven=((ImageView)findViewById(R.id.eleven));
        twelve=((ImageView)findViewById(R.id.twelve));
        thirteen=((ImageView)findViewById(R.id.thirteen));
        fourteen=((ImageView)findViewById(R.id.fourteen));
        fifteen=((ImageView)findViewById(R.id.fifteen));

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
