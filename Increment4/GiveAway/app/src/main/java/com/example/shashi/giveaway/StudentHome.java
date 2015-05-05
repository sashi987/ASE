package com.example.shashi.giveaway;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;

import external.WebServiceCall;


public class StudentHome extends ActionBarActivity {

    WebServiceCall wcf=new WebServiceCall();
    ArrayList<GiveAwayItemDetails> results = new ArrayList<GiveAwayItemDetails>();
    ArrayList<Categorydetails> categoryresults = new ArrayList<Categorydetails>();
    Categorydetails householdcategory=new Categorydetails();
    Categorydetails sportscategory=new Categorydetails();
    Categorydetails furniturecategory=new Categorydetails();
    Categorydetails electronicscategory=new Categorydetails();
    Categorydetails utiitiescategory=new Categorydetails();
    Categorydetails stationarycategory=new Categorydetails();
    ListView lv1;
    String username;
    Button logout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        username = getIntent().getStringExtra("username");
        logout=(Button)findViewById(R.id.button26);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDefaults(StudentHome.this);
                Intent nextscreen2=new Intent(StudentHome.this,LoginActivity.class);
                startActivity(nextscreen2);
            }
        });
        new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/retrieveAllItemDetails/");
        householdcategory.setItemcategory("Household");
        categoryresults.add(householdcategory);
        sportscategory.setItemcategory("Sports");
        categoryresults.add(sportscategory);
        furniturecategory.setItemcategory("Furniture");
        categoryresults.add(furniturecategory);
        electronicscategory.setItemcategory("Electronics");
        categoryresults.add(electronicscategory);
        utiitiescategory.setItemcategory("Utilities");
        categoryresults.add(utiitiescategory);
        stationarycategory.setItemcategory("Stationary");
        categoryresults.add(stationarycategory);
        ArrayList<Categorydetails> image_details = new ArrayList<Categorydetails>();//categoryresults;//GetSearchResults();
        lv1 = (ListView) findViewById(R.id.listV_main);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Categorydetails obj_itemDetails = (Categorydetails) o;

                Intent next = new Intent(StudentHome.this, ItemListing.class);
                next.putExtra("itemcategory",obj_itemDetails.getItemcategory());
                next.putExtra("username", username);
              /*  next.putExtra("itemname",obj_itemDetails.getName());
                next.putExtra("quantity",obj_itemDetails.getquantity());
                next.putExtra("createdby",obj_itemDetails.getCreatedby());
                next.putExtra("yearsused",obj_itemDetails.getYearsused());*/
                startActivity(next);
                // Toast.makeText(StudentHome.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class ReadWCFServiceFeed extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            wcf = new WebServiceCall();
            return wcf.readWCFServiceData(urls[0]);
        }

        protected void onPostExecute(String result) {
            String serviceresults=result;

            System.out.println("In try block result is: " + serviceresults);

            try {

                final JSONArray jsonarray = new JSONArray(serviceresults);
                // jsonarray=serviceresults;
                JSONObject jsonobject = new JSONObject();
                int n = jsonarray.length();
                System.out.println("array length: " + n);
                //  String[] values = new String[n];
                // item_result = new ArrayList<>();
                int householdcount=0;
                int sportscount=0;
                int furniturecount=0;
                int electronicscount=0;
                int utilitycount=0;
                int stationarycount=0;

                for (int i = 0; i < jsonarray.length(); i++) {

                    jsonobject = jsonarray.getJSONObject(i);
                    if((jsonobject.getString("itemcategory").equalsIgnoreCase("household")))
                    {householdcount++;
                    }else
                    if((jsonobject.getString("itemcategory").equalsIgnoreCase("sports")))
                    {sportscount++;
                    }else
                    if((jsonobject.getString("itemcategory").equalsIgnoreCase("furniture")))
                    {furniturecount++;
                    }else
                    if((jsonobject.getString("itemcategory").equalsIgnoreCase("electronics")))
                    {electronicscount++;
                    }else
                    if((jsonobject.getString("itemcategory").equalsIgnoreCase("utilities")))
                    {utilitycount++;
                    }else
                    if((jsonobject.getString("itemcategory").equalsIgnoreCase("stationary")))
                    {stationarycount++;
                    }
                    householdcategory.setCount(householdcount);
                    sportscategory.setCount(sportscount);
                    furniturecategory.setCount(furniturecount);
                    electronicscategory.setCount(electronicscount);
                    utiitiescategory.setCount(utilitycount);
                    stationarycategory.setCount(sportscount);
                }
                ArrayList<Categorydetails> image_details = categoryresults;
                lv1.setAdapter(new ItemListBaseAdapter(StudentHome.this, image_details));


            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }
    public static void clearDefaults(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
