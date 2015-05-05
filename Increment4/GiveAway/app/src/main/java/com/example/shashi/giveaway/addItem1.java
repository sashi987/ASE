package com.example.shashi.giveaway;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import external.WebServiceCall;


public class addItem1 extends ActionBarActivity  {
    EditText itemname1;
    Spinner itemcategory;
    EditText quantity;
    EditText yearsused;
    Button addBtn;
    Context context;
    WebServiceCall wcf;
    View additems;
    Spinner spinner1;
    String category;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item1);
        user = getIntent().getStringExtra("user").toString();
        itemname1 = (EditText) findViewById(R.id.itemname);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Household", "Sports", "Furniture","Stationary","Electronics","Food","Accessories","Utilities"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                 category = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Category Type : " + category, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        quantity = (EditText) findViewById(R.id.quantity);
        yearsused = (EditText) findViewById(R.id.yearsused);

        Button logout = (Button) findViewById(R.id.button10);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(addItem1.this);
                Intent backtologin = new Intent(addItem1.this, LoginActivity.class);

                startActivity(backtologin);
            }
        });


        addBtn = (Button) findViewById(R.id.button);

         addBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                System.out.println("Inside Button click Statement");
                String itemname2= itemname1.getText().toString();
                String itemcategory2=category;//itemcategory.getSelectedItem().toString();
                String quantity2= quantity.getText().toString();
                String yearsused2=yearsused.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                //get current date time with Date()
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                String itemdate=dateFormat.format(date);
                String nouser="nouser";
                System.out.println("Inside Button click Statement");
                String item_details = "'" + itemname2 + "','" +itemcategory2 + "','" + quantity2 + "','" + itemdate +"','"+user+"','"+yearsused2+"','"+nouser+"','"+nouser+"'";


                System.out.println(item_details);

                new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/insertItemDetails/" + item_details);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(addItem1.this);
                alertDialog.setTitle("Item Addition");
                alertDialog.setMessage("Item Added Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                        itemname1.setText("");
                        quantity.setText("");
                        yearsused.setText("");
                    }
                });
                // alertDialog.setIcon(R.drawable.icon);
                alertDialog.show();

            }
        });

    }



    private class ReadWCFServiceFeed extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            wcf = new WebServiceCall();
            return wcf.readWCFServiceData(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {

                System.out.println("Input to onPost" + result);
                JSONObject jsonObject = new JSONObject(result);

                System.out.println("JSON FEED FROM WS" + jsonObject.getString("msg")
                );
                if (jsonObject.getString("msg").equalsIgnoreCase("Inserted data")) {
                    System.out.print("before toast");

                    Toast.makeText(addItem1.this,
                            "Item Added Successfully",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


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
