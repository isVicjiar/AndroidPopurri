package com.victor.calculadorcilla;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button log;
    private TwitterLoginButton loginButton;

    Button register;

    Toolbar toolbar;

    EditText user;
    EditText pass;

    peopleDB peopledb;
    SharedPreferences settings;


    //BUTTONS
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter:
                entertry(v);
                break;
            case R.id.register:
                addUser(v);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar=((Toolbar)findViewById(R.id.toolbar_login));
        setSupportActionBar(toolbar);
        toolbar.setTitle("Login");

        user=((EditText)findViewById(R.id.editUser));
        pass=((EditText)findViewById(R.id.editPass));

        peopledb=new peopleDB(getApplicationContext());



        log= ((Button) findViewById(R.id.enter));
        log.setOnClickListener(this);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model
                String us=session.getUserName();
                boolean exists=peopledb.existsUser(us);
                if (exists) {

                } else {
                    ContentValues valuesToStore = new ContentValues();
                    valuesToStore.put("user", us);
                    valuesToStore.put("password", "");
                    peopledb.createUser(valuesToStore, "People");
                }

                settings=getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=settings.edit();
                editor.putString("CurrentUser",us);
                editor.apply();
                Intent i=new Intent(getApplicationContext(), Select_Activity.class);
                String msg = "@" + session.getUserName() + " logged in!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


        register=((Button)findViewById(R.id.register));
        register.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }



    //REGISTER
    public void addUser(View view) {
        if (user.getText().toString().equals("")||pass.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), R.string.missing_text, Toast.LENGTH_SHORT).show();
        } else {

            /*String insertQuery ="INSERT INTO People VALUES ("+)";
            peopledb.getWritableDatabase().execSQL(insertQuery);*/


            String pa=String.valueOf(pass.getText());
            String u=user.getText().toString();
            boolean exists=peopledb.existsUser(u);
            if (exists) {
                Toast.makeText(getApplicationContext(), "That user is already in our database!", Toast.LENGTH_LONG).show();
            } else {
                ContentValues valuesToStor = new ContentValues();
                valuesToStor.put("user", u);
                valuesToStor.put("password", pa);
                peopledb.createUser(valuesToStor, "People");
                settings=getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=settings.edit();
                editor.putString("CurrentUser",u);
                editor.apply();
                Intent i=new Intent(getApplicationContext(), Select_Activity.class);
                Toast.makeText(getApplicationContext(), "Registered!", Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();
            }
        }
    }


    //ENTER
    public void entertry(View view) {
        if (user.getText().toString().equals("")||pass.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), R.string.missing_text, Toast.LENGTH_SHORT).show();
        } else {
            String u=user.getText().toString();
            String p=pass.getText().toString();
            //busca el usuario, si existe mira si el pass esta bien
            boolean exists=peopledb.existsUser(u);
            if (!exists) {
                Toast.makeText(getApplicationContext(), R.string.wrong_user, Toast.LENGTH_SHORT).show();
            }
            else {
                if (p.equals(peopledb.getPassword(u))) {
                    settings=getSharedPreferences("MYAPP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=settings.edit();
                    editor.putString("CurrentUser",u);
                    editor.apply();
                    Intent i=new Intent(this, Select_Activity.class);
                    String msg = u + " logged in!";
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),R.string.wrong_pass,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.info:
                AlertDialog.Builder builder =  new AlertDialog.Builder(Login.this);

                builder.setTitle("LogIn Info");
                builder.setMessage(R.string.loginfo);

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
    }
}
