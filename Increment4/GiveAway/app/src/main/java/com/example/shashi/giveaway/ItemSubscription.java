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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import android.app.AlertDialog;
import android.content.DialogInterface;

import external.WebServiceCall;


public class ItemSubscription extends ActionBarActivity {
    TextView itemname1;
    TextView itemcategory1;
    TextView quantity1;
    TextView yearsused1;
    TextView createdby1;
    String curitemname;
    String curitemcategory;
    String curquantity;
    String curyearsused;
    String curcreatedby;
    String curitemid;
    String subscribedetails;
    String username;
    Button logout;
    WebServiceCall wcf=new WebServiceCall();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_subscription);
        username=getIntent().getStringExtra("username");
        itemname1=(TextView) findViewById(R.id.textView4);
        itemcategory1=(TextView) findViewById(R.id.textView6);
        quantity1=(TextView) findViewById(R.id.textView8);
        yearsused1=(TextView) findViewById(R.id.textView10);
        createdby1=(TextView) findViewById(R.id.textView12);

        curitemname = getIntent().getStringExtra("itemname").toString();
        curitemid=getIntent().getStringExtra("itemid").toString();
        itemname1.setText(curitemname);
        curitemcategory=getIntent().getStringExtra("itemcategory").toString();
        itemcategory1.setText(curitemcategory);
        curquantity=getIntent().getStringExtra("quantity").toString();
        quantity1.setText(curquantity);
        curyearsused=getIntent().getStringExtra("yearsused").toString();
        yearsused1.setText(curyearsused);
        curcreatedby=getIntent().getStringExtra("createdby").toString();
        createdby1.setText(curcreatedby);
        Button subscribe = (Button) findViewById(R.id.button7);
         subscribedetails="'"+curitemid+"','"+username+"'";

        logout=(Button)findViewById(R.id.button27);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDefaults(ItemSubscription.this);
                Intent nextscreen2=new Intent(ItemSubscription.this,LoginActivity.class);
                startActivity(nextscreen2);
            }
        });

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  System.out.println("http://10.0.2.2:8871/Service1.svc/updateItembyId/"+subscribedetails);
                new ReadWCFServiceFeed().execute("hhttp://10.0.2.2:8871/Service1.svc/updateItembyId/"+subscribedetails);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ItemSubscription.this);
                alertDialog.setTitle("Subscription");
                alertDialog.setMessage("Item Subscribed Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// here you can add functions
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
            String serviceresults = result;

            System.out.println("In try block result is: " + serviceresults);


                try {

                    System.out.println("Input to onPost" + result);
                    JSONObject jsonObject = new JSONObject(result);

                    System.out.println("JSON FEED FROM WS" + jsonObject.getString("msg")
                    );
                    if (jsonObject.getString("msg").equalsIgnoreCase("Updated")) {
                        System.out.print("before toast");

                        Toast.makeText(getBaseContext(),
                                "Item Subscribed Successfully", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
                }
            }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_subscription, menu);
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
