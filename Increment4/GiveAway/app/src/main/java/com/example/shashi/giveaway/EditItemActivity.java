package com.example.shashi.giveaway;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.Adapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.ArrayAdapter;

import android.widget.AdapterView;

import android.widget.AdapterView;
import android.widget.Toast;

import android.content.Context;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONObject;

import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import external.WebServiceCall;


public class EditItemActivity extends ActionBarActivity {
    String user;
    TextView text;
    Context context;
    List<String> item_result ;
    WebServiceCall wcf=new WebServiceCall();
    //String[] itemnames=new String[20];
    List<String> itemnames = new ArrayList<String>();
    Button savebtn;
    EditText newitemname;
    EditText newquantity;
    EditText newyearsused;
    Spinner newcategory;
    Spinner selectitem;
    boolean savebuttonclicked=false;
    long itemid;
    String subuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        user = getIntent().getStringExtra("user").toString();
        text = (TextView) findViewById(R.id.welcometext);
        text.setText(user);
        text.setBackgroundColor(6);
        context=this;
         selectitem = (Spinner)findViewById(R.id.spinner);
        System.out.println("Inside Button click Statement");

        System.out.println("Inside Button click Statement");
        newitemname = (EditText) findViewById(R.id.edititemname);
         newcategory = (Spinner)findViewById(R.id.spinner2);
        String[] items = new String[]{"Household", "Sports", "Furniture","Stationary","Electronics","Food","Accessories","Utilities"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        newcategory.setAdapter(adapter);
        newcategory.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
               String category1 = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Category Type : " + category1, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        newquantity = (EditText) findViewById(R.id.editquantity);
        newyearsused = (EditText) findViewById(R.id.edityearsused);
        savebtn = (Button) findViewById(R.id.button6);
        Button logout = (Button) findViewById(R.id.button11);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(EditItemActivity.this);
                Intent backtologin = new Intent(EditItemActivity.this, LoginActivity.class);

                startActivity(backtologin);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                 savebuttonclicked=true;
                System.out.println("Inside Button click Statement");
                String itemname2= newitemname.getText().toString();
                String itemcategory2=newcategory.getSelectedItem().toString();
                String quantity2= newquantity.getText().toString();
                String yearsused2=newyearsused.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                //get current date time with Date()
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                String itemdate=dateFormat.format(date);



                System.out.println("Inside Button click Statement");
                String item_details = "'"+itemid+"','" + itemname2 + "','" +itemcategory2 + "','" + quantity2 + "','" + itemdate +"','"+user+"','"+yearsused2+"','"+subuser+"'";


                System.out.println(item_details);

                new ReadWCFServiceFeed().execute("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/Service1.svc/updateItemDetails/"+item_details);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(EditItemActivity.this);
                alertDialog.setTitle("Item Updation");
                alertDialog.setMessage("Item Updated Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    }
                });
                // alertDialog.setIcon(R.drawable.icon);
                alertDialog.show();

            }
        });


        new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/extractItemDetails/"+user);
        System.out.println("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/extractItemDetails/"+user);

    }

    private class ReadWCFServiceFeed extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            wcf = new WebServiceCall();
            return wcf.readWCFServiceData(urls[0]);
        }

        protected void onPostExecute(String result) {
            String serviceresults=result;

            System.out.println("In try block result is: " + serviceresults);

            if(savebuttonclicked){
                try {

                    System.out.println("Input to onPost" + result);
                    JSONObject jsonObject = new JSONObject(result);

                    System.out.println("JSON FEED FROM WS" + jsonObject.getString("msg")
                    );
                    if (jsonObject.getString("msg").equalsIgnoreCase("Inserted data")) {
                        System.out.print("before toast");

                        Toast.makeText(EditItemActivity.this,
                                "Item Updated Successfully",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
                }
            }
            else
                try {

                    final JSONArray jsonarray = new JSONArray(serviceresults);
                   // jsonarray=serviceresults;
                    JSONObject jsonobject = new JSONObject();
                    int n = jsonarray.length();
                    System.out.println("array length: " + n);
                    //  String[] values = new String[n];
                    item_result = new ArrayList<>();

                    for (int i = 0; i < jsonarray.length(); i++) {

                        jsonobject = jsonarray.getJSONObject(i);
                        itemnames.add(jsonobject.getString("itemname"));
                        item_result.add(jsonobject.getString("itemid") + "\t" + jsonobject.getString("itemname") + "\t" + jsonobject.getString("itemcategory") + "\t" + jsonobject.getString("quantity") + "\t" + jsonobject.getString("yearsused"));
                        System.out.println("Job id is::" + jsonobject.getString("itemname"));
                        System.out.println("Object :" + i + "\t" + jsonobject);

                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_spinner_dropdown_item, itemnames);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    selectitem.setAdapter(dataAdapter);
                    selectitem.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapter, View v,
                                                   int position, long id) {
                            String val = selectitem.getSelectedItem().toString();

                            JSONObject itemobj=new JSONObject();
                            System.out.println("Value is:" + val);
                            try {

                                for(int j=0;j<jsonarray.length();j++){
                                     itemobj=jsonarray.getJSONObject(j);
                                    if (val.equalsIgnoreCase(itemobj.getString("itemname"))) {
                                        newitemname.setText(itemobj.getString("itemname"));
                                        itemid=itemobj.getLong("itemid");
                                        subuser=itemobj.getString("subscribedby");
                                        newquantity.setText(itemobj.getString("quantity"));
                                        newyearsused.setText(itemobj.getString("yearsused"));
                                    } else if (val.equalsIgnoreCase("Non-Technical")) {
                                        System.out.println("Inside nontechnical");
                                        //addItemsNonTechnicalSubCatagory();
                                    }
                                }
                            }catch(Exception e){
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                } catch (Exception e) {
                    Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
                }
            }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
