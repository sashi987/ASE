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
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import external.GeocoderHandler;
import external.GeocodingLocation;
import external.WebServiceCall;


public class ViewApprovedItems extends ActionBarActivity {
     String user;
    Button logout;
    WebServiceCall wcf=new WebServiceCall();
    ArrayList<GiveAwayItemDetails> results4= new ArrayList<GiveAwayItemDetails>();
    ArrayList<GiveAwayItemDetails> image_details = results4;
    ListView lv7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_approved_items);


        user=getIntent().getStringExtra("user").toString();
        new ReadWCFServiceFeed().execute("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/Service1.svc/extractItemDetails/"+user);

        logout=(Button)findViewById(R.id.button26);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDefaults(ViewApprovedItems.this);
                Intent nextscreen2=new Intent(ViewApprovedItems.this,LoginActivity.class);
                startActivity(nextscreen2);

            }
        });

        //GetSearchResults();
        lv7 = (ListView) findViewById(R.id.listV_main6);
        lv7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv7.getItemAtPosition(position);
                GiveAwayItemDetails obj_itemDetails = (GiveAwayItemDetails) o;

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
                    item_details.setSubscribedby(jsonobject.getString("subscribedby"));
                    // item_details.setImageNumber(1);
                    if(!(jsonobject.getString("approvedby").equalsIgnoreCase("nouser")))
                        results4.add(item_details);
                    jsonobject = jsonarray.getJSONObject(i);
                    //itemnames.add(jsonobject.getString("itemname"));
                    //item_result.add(jsonobject.getString("itemid") + "\t" + jsonobject.getString("itemname") + "\t" + jsonobject.getString("itemcategory") + "\t" + jsonobject.getString("quantity") + "\t" + jsonobject.getString("yearsused"));
                    System.out.println("Job id is::" + jsonobject.getString("itemname"));
                    System.out.println("Object :" + i + "\t" + results4);

                }

                lv7.setAdapter(new ItemListBase4Adapter(ViewApprovedItems.this, image_details));

            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_approved_items, menu);
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
