package com.myapps.dhruv.health;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class SearchResultsActivity extends ActionBarActivity{
    public ArrayList<doctor> doclist = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        RecyclerView rv = (RecyclerView) findViewById(R.id.srv);
        RVAdapter adapter = new RVAdapter(doclist);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);


        Log.v("search","search");
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.v("search2","search2");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.v("query",query);
            showResults(query);
        }
    }

    private void showResults(String query) {
        // Query your data set and show results
        Log.v("query",query);

        try {
            JSONObject obj = new JSONObject(LoadJSON());
            JSONArray spec_arr = null;

            int c=0;

            for(int j=0;j<obj.length();j++) {

                if (j==0) {
                    spec_arr = obj.getJSONArray("dentist");

                } else if (j==1) {
                    spec_arr = obj.getJSONArray("homeopath");
                }
                else if (j==2) {
                    spec_arr = obj.getJSONArray("ayurveda");
                }
                else if (j==3) {
                    spec_arr = obj.getJSONArray("neurologist");
                }
                else if (j==4) {
                    spec_arr = obj.getJSONArray("cardiologist");
                }
                else if (j==5) {
                    spec_arr = obj.getJSONArray("psychiatrist");
                }

                Log.v("obj", String.valueOf(obj.length()));


                for (int i = 0; i < spec_arr.length(); i++) {
                    doctor sarr = new doctor();
                    JSONObject element = spec_arr.getJSONObject(i);

                    if(query.equalsIgnoreCase(element.getString("location"))) {
                        sarr.setname(element.getString("name"));
                        sarr.settime(element.getString("timings"));
                        sarr.setfee(element.getString("fee"));
                        int id = getResources().getIdentifier(element.getString("photoid"), "drawable", getPackageName());
                        sarr.setPhotoid(id);
                        sarr.setloc(element.getString("location"));
                        sarr.setabout(element.getString("about"));
                        sarr.setnum(element.getString("num"));
                        sarr.seti(c);
                        doclist.add(sarr);
                        c++;

                        Log.v("found","found");
                    }
                }
            }

            if(doclist.size()==0){

                Toast.makeText(getApplicationContext(), "No Results Found for \" "+query+" \"", Toast.LENGTH_LONG).show();
            }

for (int i=0;i<doclist.size();i++){
    Log.v("found",doclist.get(i).name);

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
        Log.v("button","clicked");
        for(int i=0;i<doclist.size();i++){
            if(String.valueOf(b.getId()).equals(String.valueOf(doclist.get(i).count))){
                Intent callnum = new Intent(v.getContext(),book.class);
                callnum.putExtra("time",doclist.get(i).timings.toString());
                startActivity(callnum);

            }

        }
    }



}
