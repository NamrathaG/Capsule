package com.example.mahe.capsule;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Random;

public class fill_form extends Activity {
  private static final int result_load_image=1;
     int n;
    ImageView im;
  //  TextView tv;
    private EditText[] eda;
///////
DBHandler dbh;
    Intent[] intents;
    private PendingIntent[] Pintenta;
    AlarmManager[] alarmManager;
    Medicines med;
    String timess;
    String[] timesa;
   // Context mContext;
    ///


    public fill_form() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        dbh = new DBHandler(this, null, null, 1);
       // final SettingTimers st=new SettingTimers(this);
        Button submit_button=(Button)findViewById(R.id.button);
       final EditText  name=(EditText)findViewById(R.id.editText2);
         final EditText dosage=(EditText)findViewById(R.id.editText);
        final EditText nooftimes=(EditText)findViewById(R.id.editText3);
        Button b1=(Button)findViewById(R.id.testbutton);
       // EditText name_text=(EditText)findViewById(R.id.editText2);

     //   final TextView uploadimage=(TextView)findViewById(R.id.textView3);
        final DBHandler db=new DBHandler(this,null,null,1);
        im=(ImageView)findViewById(R.id.imageView);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                            // tv.setText("there");
                            Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gallery,result_load_image);


            }
        });

        //assert uploadimage != null;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,result_load_image);
            }
        });


submit_button.setOnClickListener(new View.OnClickListener()
{
    public void onClick(View v)
    {

       String name1=name.getText().toString();
        String dosage1=dosage.getText().toString();
        String nooftimes1=nooftimes.getText().toString();
        im.buildDrawingCache();
        Bitmap bmap = im.getDrawingCache();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] photo = baos.toByteArray();

        String times="";
        for(int i=0;i<eda.length;i++) {
            times = times + eda[i].getText().toString() + " ";
        }

        Medicines med1=new Medicines(name1,dosage1,nooftimes1,times,photo);
      int idd=  db.addMedicine(med1);


        Log.e("i got id",idd+"helo");
       med1.set_id(idd);


        /////
        timess = med1.get_times();
        timesa = timess.split(" ");


        intents = new Intent[timesa.length];
        Pintenta = new PendingIntent[timesa.length];
        alarmManager = new AlarmManager[timesa.length];


        for (int i = 0; i < timesa.length; i++) {

            String[] time = timesa[i].split(":");

            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            Log.e("hour time","helo"+hour);
            Log.e("minute time","helo"+minute);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, minute);

            intents[i] = new Intent(fill_form.this, AlarmReceiver.class);

            //int k=dbh.getcount();

Log.e("i set id",med1.get_id()+"helo");
            intents[i].putExtra("state",med1.get_id()+"");





          int  randomNum = 1 + (int)(Math.random() * 200);
           // Log.e("insidefillform","helo"+intents[i].getExtras().getString("state"));

            PendingIntent pi = PendingIntent.getBroadcast(fill_form.this, med1.get_id()+randomNum, intents[i], PendingIntent.FLAG_ONE_SHOT);
            alarmManager[i] = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager[i].setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);

            Pintenta[i] = pi;


        }

////////


      Intent in=new Intent(fill_form.this,medication.class);
        startActivity(in);


    }


}

);



      //  tv=(TextView)findViewById(R.id.textView);

       final  LinearLayout l1=(LinearLayout)findViewById(R.id.l1);
       final EditText ed1=(EditText)findViewById(R.id.editText3);


        ed1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

              /* n=Integer.parseInt(ed1.getText().toString());



                eda = new EditText[n];


                for (int i = 0; i < n; i++) {
                    l1.addView(createNewTextView("timer"+(i+1)));
                    l1.addView(createNewEditText(i));
                }*/

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                n=Integer.parseInt(ed1.getText().toString());



                eda = new EditText[n];


                for (int i = 0; i < n; i++) {
                    l1.addView(createNewTextView("Timer"+(i+1)));
                    l1.addView(createNewEditText(i));
                }

            }
        });










    }







    private EditText createNewEditText( int n) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //final TextView textView = new TextView(this);

        eda[n] = new EditText(this);
        // eda[n].setOnClickListener(this);

        final int k = n;

        eda[n].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(fill_form .this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        String minute_string = String.valueOf(selectedMinute);
                        String hour_string = String.valueOf(selectedHour);

                        if (selectedMinute < 10) {
                            minute_string = "0" + String.valueOf(selectedMinute);
                        }

                     //   if (selectedHour > 12) {
                        //    hour_string = String.valueOf(selectedHour - 12) ;
                      //  }

                        eda[k].setText(hour_string + ":" + minute_string);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        eda[n].setLayoutParams(lparams);

        return eda[n];
    }


    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);

        textView.setLayoutParams(lparams);
        textView.setText(text);

        return textView;
    }












    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

       super.onActivityResult(requestCode, resultCode, data);

       if(requestCode==result_load_image && data!=null) {
           //tv.setText("wow");
           Toast.makeText(getApplicationContext(), "uploaded", Toast.LENGTH_LONG).show();
           Uri selected_image=data.getData();
            im.setImageURI(selected_image);
       }

    }
}
