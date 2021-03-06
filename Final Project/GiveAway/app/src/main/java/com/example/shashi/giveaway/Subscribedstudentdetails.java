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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import external.GeocodingLocation;
import external.GeocoderHandler;
import external.WebServiceCall;

public class Subscribedstudentdetails extends ActionBarActivity {
    String subscribeduser;
    WebServiceCall wcf=new WebServiceCall();
    TextView name;
    TextView email;
    TextView mobile;
    TextView address;
    TextView zipcode;
    String useraddress="";
    String itemid;
    String createdby;
    String approvaldetails;
    Boolean approvalstatus=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribedstudentdetails);

        Button locationview = (Button) findViewById(R.id.button19);
        Button approveitem=(Button)findViewById(R.id.button17);
        Button cancelitem=(Button)findViewById(R.id.button18);

        subscribeduser=getIntent().getStringExtra("subscribedby").toString();
        createdby=getIntent().getStringExtra("createdby").toString();
        itemid=getIntent().getStringExtra("itemid").toString();
        name=(TextView)findViewById(R.id.textView5);
        email=(TextView)findViewById(R.id.textView9);
        mobile=(TextView)findViewById(R.id.textView13);
        address=(TextView)findViewById(R.id.textView15);
        zipcode=(TextView)findViewById(R.id.textView17);
        name.setText(subscribeduser);
         approvaldetails="'"+itemid+"','"+createdby+"'";

       // System.out.println("Lat and Long"+x+","+y);
        new ReadWCFServiceFeed().execute("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/Service1.svc/retrieveUserDetails/"+subscribeduser);


        cancelitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String canceled="nouser";
                approvaldetails="'"+itemid+"','"+canceled+"'";
                approvalstatus=true;

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Subscribedstudentdetails.this);
                alertDialog.setTitle("Item Cancellation");
                alertDialog.setMessage("Item Subscription Cancelled Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// here you can add functions

                    }
                });
                // alertDialog.setIcon(R.drawable.icon);
                alertDialog.show();
                new ReadWCFServiceFeed().execute("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/Service1.svc/updateItemApprovalstatus/"+approvaldetails);

            }
        });
        approveitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approvaldetails="'"+itemid+"','"+createdby+"'";
                approvalstatus=true;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Subscribedstudentdetails.this);
                alertDialog.setTitle("Item Approval");
                alertDialog.setMessage("Item Approved Successfully");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
// here you can add functions

                    }
                });
                // alertDialog.setIcon(R.drawable.icon);
                alertDialog.show();
                new ReadWCFServiceFeed().execute("http://kc-sce-cs551-3.kc.umkc.edu/aspnet_client/PG5/GiveAway/GiveAway/Service1.svc/updateItemApprovalstatus/"+approvaldetails);

            }
        });
        Button logout = (Button) findViewById(R.id.button20);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(Subscribedstudentdetails.this);
                Intent backtologin = new Intent(Subscribedstudentdetails.this, LoginActivity.class);

                startActivity(backtologin);
            }
        });

    locationview.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent nextscreen=new Intent(Subscribedstudentdetails.this,Studentlocation.class);
            nextscreen.putExtra("useraddress",useraddress);
            nextscreen.putExtra("subscribeduser",subscribeduser);
            startActivity(nextscreen);
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
           // ArrayList<GiveAwayItemDetails> results2 = new ArrayList<GiveAwayItemDetails>();
            System.out.println("In try block result is: " + serviceresults);
            if(approvalstatus){

                String serviceresults2=result;
                // ArrayList<GiveAwayItemDetails> results2 = new ArrayList<GiveAwayItemDetails>();
                System.out.println("In try block result is: " + serviceresults2);
            }

            try {

               // final JSONArray jsonarray = new JSONArray(serviceresults);
                // jsonarray=serviceresults;
                JSONObject jsonobject = new JSONObject(serviceresults);

                    email.setText(jsonobject.getString("emailaddress"));
                    mobile.setText(jsonobject.getString("mobilenumber"));
                    useraddress= jsonobject.getString("address");
                    address.setText(jsonobject.getString("address"));
                    String add=jsonobject.getString("address");
                    zipcode.setText(jsonobject.getString("zipcode"));
                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(add,
                        getApplicationContext(), new GeocoderHandler());


            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subscribedstudentdetails, menu);
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
