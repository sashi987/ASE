package com.example.shashi.giveaway;

import android.content.Context;
import android.os.AsyncTask;
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


        addBtn = (Button) findViewById(R.id.button);

         addBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                System.out.println("Inside Button click Statement");
                String itemname2= itemname1.getText().toString();
                String itemcategory2=category;//itemcategory.getSelectedItem().toString();
                String quantity2= quantity.getText().toString();
                String yearsused2=yearsused.getText().toString();
                String itemdate="04-10-2015";
                String id="1020";
                System.out.println("Inside Button click Statement");
                String item_details = "'" + itemname2 + "','" +itemcategory2 + "','" + quantity2 + "','" + itemdate +"','"+user+"','"+yearsused2+"'";


                System.out.println(item_details);

                new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/insertItemDetails/" + item_details);

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
               /* JSONObject weatherObservationItems =
                        new JSONObject(jsonObject.getString("weatherObservation"));*/

                /*Toast.makeText(getBaseContext(),
                        jsonObject.getString("email") +
                                " - " + jsonObject.getString("password"),
                        Toast.LENGTH_SHORT).show();*/
                System.out.println("JSON FEED FROM WS" + jsonObject.getString("msg")
                );
                if (jsonObject.getString("msg").equalsIgnoreCase("Inserted data")) {
                    System.out.print("before toast");
                    //  Toast.makeText(, "User Created",
                    //        Toast.LENGTH_SHORT).show();
                    //Toast.makeText(Context,"item created",3);
                    Toast.makeText(getBaseContext(),
                            "Item Added Successfully",Toast.LENGTH_LONG).show();
                    // Intent intent1 = new Intent(context, LoginActivity.class);
                    // intent1.putExtra("reg_email", username.getText().toString());
                    // startActivity(intent1);

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
}
