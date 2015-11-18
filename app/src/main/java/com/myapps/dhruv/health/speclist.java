package com.myapps.dhruv.health;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class speclist extends ActionBarActivity {

public ArrayList<doctor> arrlist = new ArrayList<>() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speclist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        RVAdapter adapter = new RVAdapter(arrlist);
        rv.setAdapter(adapter);

        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);


        String btnid = "";
        Intent getbtn = getIntent();
        btnid = getbtn.getStringExtra("btnid");
        showlist(btnid);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speclist, menu);
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




    /*------------------------------------------- show Specialist list -----------------------------------------------*/

    public void showlist(String btnid) {

           Log.v("showlist","yooyoyoyo");
        try {
            JSONObject obj = new JSONObject(LoadJSON());
            JSONArray spec_arr = null;

            if (btnid.equals("doc1")) {
                spec_arr = obj.getJSONArray("dentist");

            } else if (btnid.equals("doc2")) {
                spec_arr = obj.getJSONArray("homeopath");
            }
            else if (btnid.equals("doc3")) {
                spec_arr = obj.getJSONArray("ayurveda");
            }
            else if (btnid.equals("doc4")) {
                spec_arr = obj.getJSONArray("neurologist");
            }
            else if (btnid.equals("doc5")) {
                spec_arr = obj.getJSONArray("cardiologist");
            }
            else if (btnid.equals("doc6")) {
                spec_arr = obj.getJSONArray("psychiatrist");
            }


            int i = 0;


            for (i = 0; i < spec_arr.length(); i++) {
                doctor arr = new doctor();
                JSONObject element = spec_arr.getJSONObject(i);

                arr.setname(element.getString("name"));
                arr.settime(element.getString("timings"));
                arr.setfee(element.getString("fee"));
                int id = getResources().getIdentifier(element.getString("photoid"), "drawable", getPackageName());
                arr.setPhotoid(id);
                arr.setloc(element.getString("location"));
                arr.setabout(element.getString("about"));
                arr.setnum(element.getString("num"));
                arr.seti(i);
                arrlist.add(arr);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /*--------------------------------------- LOADING JSON FILE ----------------------------*/

    public String LoadJSON() {
        String json = null;
        try {
            InputStream is = getAssets().open("speclist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

   /*---------------------------------------- onclick function ------------------------------*/

    public void call(View v) {

        Button b = (Button)v;

        for(int i=0;i<arrlist.size();i++){
            if(String.valueOf(b.getId()).equals(String.valueOf(arrlist.get(i).count))){
               Intent callnum = new Intent(v.getContext(),book.class);
                callnum.putExtra("time",arrlist.get(i).timings.toString());
                startActivity(callnum);

            }

        }
    }

}

