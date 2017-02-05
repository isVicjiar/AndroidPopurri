package com.victor.calculadorcilla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by inlab on 27/01/2017.
 */

public class peopleDB extends SQLiteOpenHelper {


        SQLiteDatabase db;

        //Declaracion del nombre de la base de datos
        public static final int DATABASE_VERSION = 1;

        //Declaracion global de la version de la base de datos
        public static final String DATABASE_NAME = "peopleDB";

        //Declaracion del nombre de la tabla
        public static final String PEOPLE_TABLE ="People";

        //sentencia global de cracion de la base de datos
        public static final String PEOPLE_TABLE_CREATE = "CREATE TABLE " + PEOPLE_TABLE + " (user TEXT PRIMARY KEY UNIQUE, password TEXT, best_score4 INTEGER DEFAULT 0, best_score6 INTEGER DEFAULT 0, photo TEXT DEFAULT NULL);";



        public peopleDB (Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {
        db.execSQL(PEOPLE_TABLE_CREATE);

    }


        //obtener una lista de users
    public Cursor getAllusers4() {
        db = this.getReadableDatabase();
        String[] columns = {"user","best_score4","best_score6","photo"};
        Cursor c = db.query(
                PEOPLE_TABLE,          // The table to query
                columns,            // The columns to return
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                "best_score4 DESC"               // The sort order
        );
        return c;
    }

    public Cursor getAllusers6() {
        db = this.getReadableDatabase();
        String[] columns = {"user","best_score4","best_score6","photo"};
        Cursor c = db.query(
                PEOPLE_TABLE,          // The table to query
                columns,            // The columns to return
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                "best_score6 DESC"               // The sort order
        );
        return c;
    }

    public ArrayList<Player> getRanking (int game_mode) {
        ArrayList<Player> a=new ArrayList<>();
        Cursor c;
        if (game_mode==4) {
            c=getAllusers4();
        } else {
            c=getAllusers6();
        }

        String photo= new String();
        if (c.moveToFirst()) {
            if (game_mode==4) {
                do {
                    if (0!=c.getInt(c.getColumnIndex("best_score4"))) {
                        photo = c.getString(c.getColumnIndex("photo"));
                        a.add(new Player(photo,c.getString(c.getColumnIndex("user")),c.getInt(c.getColumnIndex("best_score4"))));
                    }
                } while (c.moveToNext());
            }
            else {
                do {
                    if (0!=c.getInt(c.getColumnIndex("best_score6"))) {
                        photo = c.getString(c.getColumnIndex("photo"));
                        a.add(new Player(photo,c.getString(c.getColumnIndex("user")),c.getInt(c.getColumnIndex("best_score6"))));
                    }
                } while (c.moveToNext());
            }
        }
        c.close();
        return a;
    }


    public String getPassword(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"password"};
        String[] where = {user};
        Cursor c = db.query(
                PEOPLE_TABLE,  // The table to query
                columns,         // The columns to return
                "user=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        String s=new String();
        if (c.moveToFirst()) {
            s=c.getString(c.getColumnIndex("password"));
        }
        return s;
    }

    public boolean existsUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"user","password"};
        String[] where = {user};
        Cursor c = db.query(
                PEOPLE_TABLE,  // The table to query
                columns,         // The columns to return
                "user=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        if (c.moveToFirst()) {
            if (user.equals(c.getString(c.getColumnIndex("user")))) {
                Log.v("HOLA","ESTOY EN EL MOVE TO FIRST DEL EXISTSUSER TRUE");
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }

    public ProfileUser getUserProfile (String user) {
        ProfileUser pu=new ProfileUser();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"user","photo","best_score4","best_score6"};
        String[] where = {user};
        Cursor c = db.query(
                PEOPLE_TABLE,  // The table to query
                columns,         // The columns to return
                "user=?",        // The columns for the WHERE clause
                where,           // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                null             // The sort order
        );
        c.moveToFirst();
        int s4=c.getInt(c.getColumnIndex("best_score4"));
        int s6=c.getInt(c.getColumnIndex("best_score6"));
        String i_path=c.getString(c.getColumnIndex("photo"));
        if (i_path!=null) {
            i_path="null";
            //buscar lo del path!!!!!!!!!!!!!!!!!!!
        }
        pu.setPhoto(i_path);
        pu.setScore4(s4);
        pu.setScore6(s6);
        c.close();
        return pu;
    }


    public void createUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
