package com.example.mahe.capsule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.sql.Blob;

/**
 * Created by mahe on 10/19/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="capsuledb.db";
    public static final String TABLE_MEDICINES="medtable";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_DOSAGE="dosage";
    public static final String COLUMN_NOOFTIMES="nooftimes";
    public static final String COLUMN_TIMES="times";
    public static final String COLUMN_IMAGE="image";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_MEDICINES +"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT ,"+COLUMN_DOSAGE+" TEXT ,"+COLUMN_NOOFTIMES+" TEXT,"+COLUMN_TIMES+" TEXT ,"+COLUMN_IMAGE+" BLOB);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MEDICINES);
        onCreate(db);


    }


    public int addMedicine(Medicines medicine)
    {
        SQLiteDatabase db=getWritableDatabase();
       ContentValues values=new ContentValues();
        values.put(COLUMN_DOSAGE,medicine.get_dosage());
        values.put(COLUMN_NAME,medicine.get_name());
        values.put(COLUMN_NOOFTIMES,medicine.get_nooftimes());
        values.put(COLUMN_TIMES,medicine.get_times());
        values.put(COLUMN_IMAGE,medicine.get_image());

      db.insert(TABLE_MEDICINES,null,values);

        Cursor c=db.rawQuery("SELECT id from "+ TABLE_MEDICINES + " WHERE 1",null);

        c.moveToLast();
        db.close();
       return (Integer.parseInt( c.getString(c.getColumnIndex(COLUMN_ID))));

    /*    SQLiteStatement p = db.compileStatement("INSERT INTO medtable VALUES('"+medicine.get_name()+"','"+
                medicine.get_dosage()+"','"+medicine.get_nooftimes()+"','"+medicine.get_times()+"',?);");


        p.bindBlob(1, medicine.get_image());
        //p.bindString(2, "1.jpg");
        p.execute();




       // db.execSQL("INSERT INTO medtable VALUES('"+medicine.get_name()+"','"+
         //       medicine.get_dosage()+"','"+medicine.get_nooftimes()+"','"+medicine.get_times()+"',               );");
*/

    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDICINES + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_NAME))+c.getString(c.getColumnIndex(COLUMN_ID));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }


    public int getcount()
    {
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDICINES + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        db.close();
        return c.getCount();
    }


    public String getname(int id)
    {

        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDICINES + " WHERE "+COLUMN_ID+"="+id;

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();db.close();
        return c.getString(c.getColumnIndex(COLUMN_NAME));



    }

    public void updatedosage(int id,String dose)
    {

        SQLiteDatabase db=getWritableDatabase();
        String query ="UPDATE "+TABLE_MEDICINES+" SET "+COLUMN_DOSAGE+"="+dose+" WHERE "+COLUMN_ID+"="+id;
        db.execSQL(query);

        Log.e("inside DBhandler","updated"+dose);
        db.close();

    }





    public Medicines[] getallmedicines()
    {

        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDICINES + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);

        Medicines[] medarray=new Medicines[c.getCount()];
        c.moveToFirst();
int i=0;
        while (!c.isAfterLast()) {
            medarray[i] = new Medicines();

            medarray[i].set_id(Integer.parseInt(c.getString(c.getColumnIndex(COLUMN_ID))));
            medarray[i].set_name(c.getString(c.getColumnIndex(COLUMN_NAME)));
            medarray[i].set_dosage(c.getString(c.getColumnIndex(COLUMN_DOSAGE)));
            medarray[i].set_nooftimes(c.getString(c.getColumnIndex(COLUMN_NOOFTIMES)));
            medarray[i].set_times(c.getString(c.getColumnIndex(COLUMN_TIMES)));
            medarray[i].set_image(c.getBlob(c.getColumnIndex(COLUMN_IMAGE)));
            i++;


            c.moveToNext();}


        db.close();
        return medarray;
    }

    public String[] getdoses()
    {
        SQLiteDatabase db =  getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDICINES + " WHERE 1";

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);

        String[] medarray=new String[c.getCount()];
        c.moveToFirst();
        int i=0;
        while (!c.isAfterLast()) {

            String a=c.getString(c.getColumnIndex(COLUMN_NAME));
            String b=c.getString(c.getColumnIndex(COLUMN_DOSAGE));

            medarray[i]=a+" -> "+b;
            i++;


            c.moveToNext();}


        db.close();
        return medarray;


    }


    public Medicines getMedicine(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEDICINES + " WHERE id=" + id;

        //Cursor point to a location in your result
        Cursor c = db.rawQuery(query, null);

        Medicines m = new Medicines();
        c.moveToFirst();
if(c!=null) {
    m.set_id(id);
    m.set_name(c.getString(c.getColumnIndex(COLUMN_NAME)));
    m.set_dosage(c.getString(c.getColumnIndex(COLUMN_DOSAGE)));
    m.set_nooftimes(c.getString(c.getColumnIndex(COLUMN_NOOFTIMES)));
    m.set_times(c.getString(c.getColumnIndex(COLUMN_TIMES)));
    m.set_image(c.getBlob(c.getColumnIndex(COLUMN_IMAGE)));
}
    return m;
   }}