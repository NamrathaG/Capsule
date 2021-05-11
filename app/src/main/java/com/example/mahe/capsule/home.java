package com.example.mahe.capsule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageButton medication=(ImageButton)findViewById(R.id.imageButton);
        ImageButton refill=(ImageButton)findViewById(R.id.imageButton3);
        ImageButton reminder=(ImageButton)findViewById(R.id.imageButton2);
        medication.setOnClickListener(this);
        refill.setOnClickListener(this);
       // medication.setOnClickListener(this);

    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.imageButton:
            {

                Intent in=new Intent(this,medication.class);
                startActivity(in);

            }
            case R.id.imageButton2:
            {

                Intent in=new Intent(this,medication.class);
                startActivity(in);

            }
            case R.id.imageButton3:
            {

                Intent in=new Intent(this,Refills.class);
                startActivity(in);

            }

        }
    }



}
