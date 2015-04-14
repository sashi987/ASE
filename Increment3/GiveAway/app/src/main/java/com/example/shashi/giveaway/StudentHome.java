package com.example.shashi.giveaway;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;

import external.WebServiceCall;


public class StudentHome extends ActionBarActivity {

    WebServiceCall wcf=new WebServiceCall();
    ArrayList<GiveAwayItemDetails> results = new ArrayList<GiveAwayItemDetails>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/retrieveAllItemDetails/");

        ArrayList<GiveAwayItemDetails> image_details = results;//GetSearchResults();


        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));



        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                GiveAwayItemDetails obj_itemDetails = (GiveAwayItemDetails) o;
                Toast.makeText(StudentHome.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
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
                        item_details.setitemcategory(jsonobject.getString("itemcategory"));
                        item_details.setquantity(jsonobject.getString("quantity"));
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

   /* private ArrayList<GiveAwayItemDetails> GetSearchResults(){
        ArrayList<GiveAwayItemDetails> results = new ArrayList<GiveAwayItemDetails>();

        GiveAwayItemDetails item_details = new GiveAwayItemDetails();
        item_details.setName("Pizza");
        item_details.setItemDescription("Spicy Chiken Pizza");
        item_details.setPrice("RS 310.00");
        item_details.setImageNumber(1);
        results.add(item_details);



        return results;
    }*/
}
