package com.example.mahe.capsule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Taken extends AppCompatActivity {

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
        setContentView(R.layout.activity_taken);

         Intent i=getIntent();

        TextView medname=(TextView)findViewById(R.id.medNameTv);
        TextView dosage=(TextView)findViewById(R.id.dosageTv);
        TextView timers=(TextView)findViewById(R.id.TimersTv);
        ImageView image=(ImageView)findViewById(R.id.imageView3);
        DbBitmapUtility dbu=new DbBitmapUtility();
        Button b2=(Button)findViewById(R.id.takenButton);


        String ids = i.getStringExtra("state");

        /// String j=getIntent().getExtras().getString("state");


     final    int id=Integer.parseInt(ids);



        Log.e("in the taken","hello"+ids);

        dbh = new DBHandler(this, null, null, 1);

       final Medicines m=dbh.getMedicine(id);
        medname.setText(m.get_name());
        dosage.setText(m.get_dosage());
        timers.setText(m.get_times());
        int t=Integer.parseInt(m.get_nooftimes());








        image.setImageBitmap( dbu.getImage(m.get_image()));

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("inside Taken","yes clicked");


                dbh.updatedosage(id,String.valueOf(Integer.parseInt(m.get_dosage())-1));

                Intent in=new Intent(Taken.this,med_profile.class);
                in.putExtra("medId",id+"");
                startActivity(in);

 //m.set_dosage(String.valueOf(Integer.parseInt(m.get_dosage())-1));

            }
        });

    }















}






