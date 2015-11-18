package com.myapps.dhruv.health;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class specinfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specinfo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();

/*------------------------------------- Declaring Variables-----------------------*/

        String name;
        TextView info_name = (TextView)findViewById(R.id.info_name);

         String fee;
         String loc;

        Integer photo;
        ImageView info_photo = (ImageView)findViewById(R.id.info_photo);

        String time;
        TextView info_time = (TextView)findViewById(R.id.info_time);

        String about;
        TextView info_about = (TextView)findViewById(R.id.info_about);

/*------------------------------------- Getting Data-----------------------------*/

        fee = intent.getStringExtra("fee");
        loc = intent.getStringExtra("loc");
        name = intent.getStringExtra("name");
        about = intent.getStringExtra("about");
        about = "Location: "+loc+"\n"+fee+"\n\n"+about;
        photo = intent.getIntExtra("photoid",0);
        time = intent.getStringExtra("time");


/*------------------------------------------------ Setting Data-------------------------*/

        info_name.setText(name);
        info_time.setText(time);
        int id = getResources().getIdentifier(photo.toString(), "drawable", getPackageName());
        TextView text = (TextView)findViewById(R.id.info_name);
        text.setText(name);
        info_photo.setImageResource(id);
        info_about.setText(about);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_specinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void call(View view){

        Intent intent = getIntent();

        String num = intent.getStringExtra("num");
        Log.v("num",num);


        Intent callnum = new Intent(Intent.ACTION_DIAL);
        callnum.setData(Uri.parse("tel:"+num));
        startActivity(callnum);


    }


}
