package com.example.mahe.capsule;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class med_profile extends AppCompatActivity {
    DBHandler dbh;
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="capsuledb.db";
    public static final String TABLE_MEDICINES="medtable";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_IMAGE="image";
    public static final String COLUMN_DOSAGE="dosage";
    public static final String COLUMN_NOOFTIMES="nooftimes";
    public static final String COLUMN_TIMES="times";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_profile);

        TextView medname=(TextView)findViewById(R.id.medNameTv);
        TextView dosage=(TextView)findViewById(R.id.dosageTv);
        TextView timers=(TextView)findViewById(R.id.TimersTv);
        ImageView image=(ImageView)findViewById(R.id.imageView3);
        DbBitmapUtility dbu=new DbBitmapUtility();

      //  String id = getIntent().getStringExtra("medId");


        Bundle bundle=getIntent().getExtras();

   String j="";

        if(bundle!=null)
        {
          j =(String) bundle.get("medId");}

        int id=Integer.parseInt(j);

        dbh = new DBHandler(this, null, null,   1);


        Medicines m=dbh.getMedicine(id);
        medname.setText(m.get_name());
        dosage.setText(m.get_dosage());
        timers.setText(m.get_times());
        int t=Integer.parseInt(m.get_nooftimes());








         image.setImageBitmap( dbu.getImage(m.get_image()));

        }















    }






