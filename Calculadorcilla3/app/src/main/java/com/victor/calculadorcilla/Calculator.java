package com.victor.calculadorcilla;


import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Calculator extends Fragment implements View.OnClickListener {



    String not;

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button buttons;
    Button buttonr;
    Button buttonm;
    Button buttond;
    Button buttoni;
    Button buttonC;
    TextView textView;
    View rootview;

    SharedPreferences settings;

    int fase=0;
    /*FASE=0 : ESPERANDO OPERANDO1
    FASE=1 : ESPERANDO EL SEGUNDO*/
    String operando1= new String();
    String operando2= new String();
    char operacion='N';
    int reiniciar=0;
    int usarr=0;
    int r=0;

    public Calculator() {
        // Required empty public constructor
    }


    public void imprimir(String s) {
        textView.setText(s);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="1";
                    imprimir(operando1);
                }
                else {
                    operando2+="1";
                    imprimir(operando2);
                }
                break;
            case R.id.button2:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="2";
                    imprimir(operando1);
                }
                else {
                    operando2+="2";
                    imprimir(operando2);
                }
                break;
            case R.id.button3:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="3";
                    imprimir(operando1);
                }
                else {
                    operando2+="3";
                    imprimir(operando2);
                }
                break;
            case R.id.button4:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="4";
                    imprimir(operando1);
                }
                else {
                    operando2+="4";
                    imprimir(operando2);
                }
                break;
            case R.id.button5:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="5";
                    imprimir(operando1);
                }
                else {
                    operando2+="5";
                    imprimir(operando2);
                }
                break;
            case R.id.button6:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="6";
                    imprimir(operando1);
                }
                else {
                    operando2+="6";
                    imprimir(operando2);
                }
                break;
            case R.id.button7:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="7";
                    imprimir(operando1);
                }
                else {
                    operando2+="7";
                    imprimir(operando2);
                }
                break;
            case R.id.button8:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="8";
                    imprimir(operando1);
                }
                else {
                    operando2+="8";
                    imprimir(operando2);
                }
                break;
            case R.id.button9:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="9";
                    imprimir(operando1);
                }
                else {
                    operando2+="9";
                    imprimir(operando2);
                }
                break;
            case R.id.button0:
                if (fase==0) {
                    if (usarr==1) {
                        usarr=0;
                    }
                    operando1+="0";
                    imprimir(operando1);
                }
                else {
                    operando2+="0";
                    imprimir(operando2);
                }
                break;
            case R.id.buttons:
                if (usarr==1) {
                    fase=1;
                    operacion='+';
                    usarr=0;
                }
                else if (fase==0) {
                    operacion='+';
                    fase=1;
                }
                break;
            case R.id.buttonr:
                if (usarr==1) {
                    operacion='-';
                    fase=1;
                    usarr=0;
                }
                else if (fase==0) {
                    operacion='-';
                    fase=1;
                }
                break;
            case R.id.buttonm:
                if (usarr==1) {
                    operacion='*';
                    fase=1;
                    usarr=0;
                }
                else if (fase==0) {
                    operacion='*';
                    fase=1;
                }
                break;
            case R.id.buttond:
                if (usarr==1) {
                    operacion='/';
                    fase=1;
                    usarr=0;
                }
                else if (fase==0) {
                    operacion='/';
                    fase=1;
                }
                break;
            case R.id.buttoni:
                if (fase==0) {
                    imprimir(operando1);
                    operando1=new String();
                }
                else {
                    int aux=Integer.parseInt(operando1);
                    int aux2=Integer.parseInt(operando2);
                    fase=0;
                    String s=new String();
                    usarr=1;
                    switch(operacion) { //imprime cada uno
                        case '*':
                            r=aux*aux2;
                            s=Integer.toString(r);
                            imprimir(s);
                            break;
                        case '+':
                            r=aux+aux2;
                            s=Integer.toString(r);
                            imprimir(s);
                            break;
                        case '-':
                            r=aux-aux2;
                            s=Integer.toString(r);
                            imprimir(s);
                            break;
                        case '/':
                            if (aux2==0) {

                                s="0";
                                not=settings.getString("notifications","toast");
                                if (not.equals("toast")) {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "You can't divide by zero!",Toast.LENGTH_LONG).show();
                                } else {
                                    //NOTIFICACION DE BARRA
                                    //Entero que nos permite identificar la notificación
                                    int mId = 1;
                                    //Instanciamos Notification Manager
                                    NotificationManager mNotificationManager =
                                            (NotificationManager) getActivity().
                                                    getSystemService(Context.NOTIFICATION_SERVICE);


                                    // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
                                    // un Builder/contructor.
                                    NotificationCompat.Builder mBuilder =
                                            new NotificationCompat.Builder
                                                    (getActivity().getApplicationContext())
                                                    .setSmallIcon(R.drawable.ic_infinite_sign)
                                                    .setContentTitle("Math error")
                                                    .setContentText("You can't divide by zero!");

                                    // mId nos permite actualizar las notificaciones en un futuro
                                    // Notificamos
                                    mNotificationManager.notify(mId, mBuilder.build());
                                }
                                usarr=0;
                            }
                            else {
                                r=aux/aux2;
                                s=Integer.toString(r);

                                usarr=1;
                            }
                            imprimir(s);
                            break;
                    }
                    reiniciar=0;
                    operando1=s;
                    operando2=new String();
                    fase=0;
                    operacion='N';
                }
                break;
            case R.id.buttonC:
                if (reiniciar==1) {
                    reiniciar=0;
                    operando1=new String();
                    operando2=new String();
                    fase=0;
                    imprimir("0");
                }
                else {
                    reiniciar=1;
                    if (fase==0) {
                        operando1=new String();
                    }
                    else {
                        operando2=new String();
                    }
                    imprimir("0");
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_calculator, container, false);
        setRetainInstance(true);
        textView=((TextView)rootview.findViewById(R.id.textView3));

        button1=((Button) rootview.findViewById(R.id.button1));
        button2=((Button) rootview.findViewById(R.id.button2));
        button3=((Button) rootview.findViewById(R.id.button3));
        button4=((Button) rootview.findViewById(R.id.button4));
        button5=((Button) rootview.findViewById(R.id.button5));
        button6=((Button) rootview.findViewById(R.id.button6));
        button7=((Button) rootview.findViewById(R.id.button7));
        button8=((Button) rootview.findViewById(R.id.button8));
        button9=((Button) rootview.findViewById(R.id.button9));
        button0=((Button) rootview.findViewById(R.id.button0));
        buttons=((Button) rootview.findViewById(R.id.buttons));
        buttonr=((Button) rootview.findViewById(R.id.buttonr));
        buttonm=((Button) rootview.findViewById(R.id.buttonm));
        buttond=((Button) rootview.findViewById(R.id.buttond));
        buttoni=((Button) rootview.findViewById(R.id.buttoni));
        buttonC=((Button) rootview.findViewById(R.id.buttonC));
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttons.setOnClickListener(this);
        buttonr.setOnClickListener(this);
        buttonm.setOnClickListener(this);
        buttond.setOnClickListener(this);
        buttoni.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        setHasOptionsMenu(true);
        settings=getActivity().getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
        return rootview;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("fase",fase);
        outState.putInt("reiniciar",reiniciar);
        outState.putString("operando1",operando1);
        outState.putString("operando2",operando2);
        outState.putChar("operacion",operacion);
        outState.putInt("usarr",usarr);
        outState.putCharSequence("tv", textView.getText());
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("curr_fragment","Calculator");
        editor.apply();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("NOT NULL","HOLA1");
        if (savedInstanceState!=null) {
            if (savedInstanceState.getString("fase")==null) {
                fase=0;
            } else {
                fase = savedInstanceState.getInt("fase");
            }
            if (savedInstanceState.getString("reiniciar")==null) {
                reiniciar=0;
            } else {
                reiniciar = savedInstanceState.getInt("reiniciar");
            }
            if (savedInstanceState.getString("operando1")==null) {
                operando1="";
            } else {
                operando1 = savedInstanceState.getString("operando2");
            }
            if (savedInstanceState.getString("operando2")==null) {
                operando2="";
            } else {
                operando2 = savedInstanceState.getString("operando2");
            }
            if (savedInstanceState.getString("operacion")==null) {
                operacion='N';
            } else {
                operacion = savedInstanceState.getChar("operacion");
            }
            if (savedInstanceState.getString("usarr")==null) {
                usarr=0;
            } else {
                usarr = savedInstanceState.getInt("usarr");
            }
            if (savedInstanceState.getCharSequence("tv")==null) {
                textView.setText("0");
            } else {
                Log.v("NOT NULL","HOLA2");
                textView=((TextView)rootview.findViewById(R.id.textView3));
                textView.setText("MECAGONMISMUERTOS");
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_calc, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (settings.getString("notifications","toast").equals("toast")) {
            menu.findItem(R.id.toast).setChecked(true);
        } else {
            menu.findItem(R.id.bar).setChecked(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean prueba=false;
        switch (item.getItemId()) {
            case R.id.call:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "123456789"));
                startActivity(callIntent);
                break;

            case R.id.navigator:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
                break;
            default:
                if (!item.isChecked()) {
                    item.setChecked(true);
                    switch (item.getItemId()) {
                        case R.id.toast:
                            SharedPreferences.Editor editor=settings.edit();
                            editor.putString("notifications","toast");
                            editor.apply();
                            prueba=true;
                            break;
                        case R.id.bar:
                            SharedPreferences.Editor edit=settings.edit();
                            edit.putString("notifications","bar");
                            edit.apply();
                            prueba=true;
                            break;
                    }
            }
        }
        if (prueba) {
            String s=settings.getString("notifications",null);
            if (s.equals("toast")) {
                Toast.makeText(getActivity().getApplicationContext(),"Notifications are now in Toast mode",Toast.LENGTH_LONG).show();
            } else {
                //NOTIFICACION DE BARRA
                //Entero que nos permite identificar la notificación
                int mId = 1;
                //Instanciamos Notification Manager
                NotificationManager mNotificationManager =
                        (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
                // un Builder/contructor.
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getActivity().getApplicationContext())
                                .setSmallIcon(R.drawable.icono)
                                .setContentTitle("Notification type changed")
                                .setContentText("Now using bar notifications");

                // mId nos permite actualizar las notificaciones en un futuro
                // Notificamos
                mNotificationManager.notify(mId, mBuilder.build());
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
