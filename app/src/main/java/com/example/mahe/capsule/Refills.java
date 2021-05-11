package com.example.mahe.capsule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


    public class Refills extends AppCompatActivity  {


        DBHandler dbh;TextView tv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_refills);


            dbh = new DBHandler(this, null, null, 1);
            ListView listView1 = (ListView) findViewById(R.id.listView2);
            // TextView tv=(TextView)findViewById(R.id.textView11);
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, dbh.getdoses());
            listView1.setAdapter(adapter);








            //    assert tv != null;
            // if (tv != null) {
            //       tv.setText(dbh.databaseToString());
            //   }

        }




    }
