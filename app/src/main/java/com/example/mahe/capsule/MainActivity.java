package com.example.mahe.capsule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl);
        rl.setOnClickListener(this);

    }

    public void onClick(View v)
    {
        Intent in=new Intent(this,home.class);
        startActivity(in);
    }


}
