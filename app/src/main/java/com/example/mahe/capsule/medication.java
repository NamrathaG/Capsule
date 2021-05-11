package com.example.mahe.capsule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class medication extends AppCompatActivity  implements View.OnClickListener {


    DBHandler dbh;TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        ImageView i = (ImageView) findViewById(R.id.imageView2);
        i.setOnClickListener(this);

        dbh = new DBHandler(this, null, null, 1);
        ListView listView1 = (ListView) findViewById(R.id.listView);
      // TextView tv=(TextView)findViewById(R.id.textView11);
      final ArrayAdapter<Medicines> adapter = new ArrayAdapter<Medicines>(this,
                android.R.layout.simple_list_item_1, dbh.getallmedicines());
        listView1.setAdapter(adapter);



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                             @Override
                                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                                Medicines p = (Medicines) adapter.getItem(position);

                                                 Intent i=new Intent(getBaseContext(),med_profile.class);
                                                 Toast.makeText(getBaseContext(),p.get_id()+p.get_name(),Toast.LENGTH_LONG).show();
                                                 i.putExtra("medId",p.get_id()+"");
                                                 startActivity(i);


                                                 //Toast.makeText(getBaseContext(),p.get_id()+p.get_name(),Toast.LENGTH_LONG).show();
                                             }

                                         }
        );






    //    assert tv != null;
       // if (tv != null) {
     //       tv.setText(dbh.databaseToString());
     //   }

    }




    public void onClick(View v) {

        Intent in = new Intent(this, fill_form.class);
        startActivity(in);


    }
}
