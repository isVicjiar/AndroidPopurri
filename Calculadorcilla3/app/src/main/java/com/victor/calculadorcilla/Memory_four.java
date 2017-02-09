package com.victor.calculadorcilla;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class Memory_four extends Fragment implements View.OnClickListener {

    int GameMode;
    String ImagePack;
    SharedPreferences settings;
    TextView trys4;
    View rootview;
    Realm realm;

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

    int imagesfourp[]={R.drawable.ic_abra,R.drawable.ic_bellsprout,R.drawable.ic_bullbasaur,R.drawable.ic_caterpie,R.drawable.ic_charmander,R.drawable.ic_dratini,R.drawable.ic_eevee,R.drawable.ic_jigglypuff,R.drawable.ic_abra,R.drawable.ic_bellsprout,R.drawable.ic_bullbasaur,R.drawable.ic_caterpie,R.drawable.ic_charmander,R.drawable.ic_dratini,R.drawable.ic_eevee,R.drawable.ic_jigglypuff};
    int imagesfoure[]={R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick,R.drawable.ic_angry,R.drawable.ic_angel,R.drawable.ic_cool,R.drawable.ic_crying_1,R.drawable.ic_cute,R.drawable.ic_happy_4,R.drawable.ic_in_love,R.drawable.ic_sick};

    int imagesfouraux[];
    int correspondencias4[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int correspondencias4_aux[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean back_vector4[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
    boolean back_vector_aux4[]={true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};

    @Override
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
            image=getResources().getDrawable(correspondencias4[which]);
            if (back_vector4[which]) {
                flipper.flipImage(image,((ImageView)rootview.findViewById(v.getId())));
                recent=((ImageView)rootview.findViewById(v.getId()));
                r=which;
                onclicked4();
                back_vector4[which]=!back_vector4[which];
            }
        }
    }

    void onclicked4 () {
        if (oneisflipped) {
            Thread th = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
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

    void acaba4() {
        String user=settings.getString("CurrentUser",null);
        RealmResults realmResults=realm.where(User.class).equalTo("name",user).findAll();
        User current=(User) realmResults.first();
        if (current.getBest_score4()>intentos||current.getBest_score4()==0) {
            realm.beginTransaction();
            current.setBest_score4(intentos);
            realm.copyToRealmOrUpdate(current);
            realm.commitTransaction();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("You won!");
        builder.setMessage("You finally won, GG! You only needed " + String.valueOf(intentos)+" tries (impressive, you f***ing N00B). Do you want to try for a better score?");

        builder.setPositiveButton("Replay!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
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
        ImageView aux;
        aux=((ImageView)rootview.findViewById(R.id.zero));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.one));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.three));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.four));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.five));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.six));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.seven));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.eight));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.nine));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.ten));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.eleven));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.twelve));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.thirteen));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.fourteen));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);
        aux=((ImageView)rootview.findViewById(R.id.fifteen));
        aux.setEnabled(true);
        flipper.flipImage(back,aux);
        aux.setVisibility(View.VISIBLE);

        trys4=((TextView)rootview.findViewById(R.id.trys4));
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

    protected void Start() {
        intentos=0;
        oneisflipped=false;
        twoflipped=false;
        settings=null;
        settings=getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        ImagePack=settings.getString("Images_pack","emojis");
        GameMode=4;
        startGame4();
    }


    public Memory_four() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        rootview=inflater.inflate(R.layout.fragment_memory4, container, false);
        realm= Realm.getDefaultInstance();
        ((ImageView)rootview.findViewById(R.id.zero)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.one)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.two)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.three)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.four)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.five)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.six)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.seven)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.eight)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.nine)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.ten)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.eleven)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.twelve)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.thirteen)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.fourteen)).setOnClickListener(this);
        ((ImageView)rootview.findViewById(R.id.fifteen)).setOnClickListener(this);
        flipper= new CoolImageFlipper(getActivity());
        back=getResources().getDrawable(R.drawable.ic_interr);
        Start();
        return rootview;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.four:
                if (GameMode==6) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                    builder.setTitle("Changing difficulty to hard");
                    builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                    builder.setPositiveButton("Restart",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                    SharedPreferences.Editor edit=settings.edit();
                                    edit.putInt("GameMode",6);
                                    edit.apply();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                                    if (GameMode==4) {
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
                                    } else {
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                                    if (GameMode==4) {
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
                                    } else {
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Restart");
                builder.setMessage("Are you sure? All progress will be lost and will count as a failed try!");

                builder.setPositiveButton("Restart",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //poner un intento más!!!!!!!!!!!!!!!!!
                                if (GameMode==4) {
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_four(),"MEMORY_FRAGMENT").commit();
                                } else {
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,new Memory_six(),"MEMORY_FRAGMENT").commit();
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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("correspondencias4",correspondencias4);
        outState.putBooleanArray("back_vector4",back_vector4);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null) {
            correspondencias4=savedInstanceState.getIntArray("correspondencias4");
            back_vector4=savedInstanceState.getBooleanArray("back_vector4");
        }
    }
}
