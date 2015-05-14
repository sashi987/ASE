package com.example.shashi.giveaway;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
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

public class ItemListing extends ActionBarActivity {
    WebServiceCall wcf=new WebServiceCall();
    ArrayList<GiveAwayItemDetails> results = new ArrayList<GiveAwayItemDetails>();
    String itemcategory;
    String username;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listing);
        itemcategory = getIntent().getStringExtra("itemcategory").toString();
        username=getIntent().getStringExtra("username").toString();
        String x="http://10.0.2.2:8871/Service1.svc/Service1.svc/retrieveItemsbyCategory/"+itemcategory;
        System.out.println(x);
        new ReadWCFServiceFeed().execute("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/Service1.svc/retrieveItemsbyCategory/"+itemcategory);

        ArrayList<GiveAwayItemDetails> image_details = results;//GetSearchResults();


        final ListView lv1 = (ListView) findViewById(R.id.listV_main2);
        lv1.setAdapter(new ItemListBase2Adapter(this, image_details));
        logout=(Button)findViewById(R.id.button25);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDefaults(ItemListing.this);
                Intent nextscreen2=new Intent(ItemListing.this,LoginActivity.class);
                startActivity(nextscreen2);

            }
        });



        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                GiveAwayItemDetails obj_itemDetails = (GiveAwayItemDetails) o;
                Intent next = new Intent(ItemListing.this, ItemSubscription.class);
                next.putExtra("itemcategory",obj_itemDetails.getitemcategory());
                next.putExtra("itemname",obj_itemDetails.getName());
                next.putExtra("itemid",obj_itemDetails.getItemid());
                next.putExtra("quantity",obj_itemDetails.getquantity());
                next.putExtra("createdby",obj_itemDetails.getCreatedby());
                next.putExtra("yearsused",obj_itemDetails.getYearsused());
                next.putExtra("username",username);
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


                for (int i = 0; i < jsonarray.length(); i++) {


                    jsonobject = jsonarray.getJSONObject(i);
                    GiveAwayItemDetails item_details = new GiveAwayItemDetails();
                    item_details.setName(jsonobject.getString("itemname"));
                    item_details.setItemid(jsonobject.getString("itemid"));
                    item_details.setitemcategory(jsonobject.getString("itemcategory"));
                    item_details.setquantity(jsonobject.getString("quantity"));
                    item_details.setYearsused(jsonobject.getString("yearsused"));
                    item_details.setCreatedby(jsonobject.getString("createdby"));
                    // item_details.setImageNumber(1);
                    results.add(item_details);






                    jsonobject = jsonarray.getJSONObject(i);
                    //itemnames.add(jsonobject.getString("itemname"));
                    //item_result.add(jsonobject.getString("itemid") + "\t" + jsonobject.getString("itemname") + "\t" + jsonobject.getString("itemcategory") + "\t" + jsonobject.getString("quantity") + "\t" + jsonobject.getString("yearsused"));
                    System.out.println("Job id is::" + jsonobject.getString("itemname"));
                    System.out.println("Object :" + i + "\t" + results);

                }

            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_listing, menu);
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
    public static void clearDefaults(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
