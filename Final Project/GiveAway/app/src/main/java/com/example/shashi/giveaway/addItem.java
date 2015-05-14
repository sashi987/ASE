package com.example.shashi.giveaway;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import external.FieldValidation;
import external.WebServiceCall;

public class addItem extends Fragment {
    EditText itemname1;
    Spinner itemcategory;
    EditText quantity;
    EditText yearsused;
    Button  backBtn;
    Context context;
    WebServiceCall wcf;
    View additems;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        additems = inflater.inflate(R.layout.additemfragment, container, false);
        //((TextView)android.findViewById(R.id.textView)).setText("Add Items");
        itemname1 = (EditText) additems.findViewById(R.id.itemname);
      //  itemcategory = (Spinner) additems.findViewById(R.id.spinner);
        quantity = (EditText) additems.findViewById(R.id.quantity);
        yearsused = (EditText) additems.findViewById(R.id.yearsused);


        backBtn = (Button) additems.findViewById(R.id.button);

        backBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                System.out.println("Inside Button click Statement");
                String itemname2= "table";//itemname1.getText().toString();
                String itemcategory2=itemcategory.getSelectedItem().toString();
                String quantity2= "2";//quantity.getText().toString();
                String yearsused2="5";//yearsused.getText().toString();
                String itemdate="04-10-2015";
                String id="1020";
                 System.out.println("Inside Button click Statement");





// Validation for password address


            String item_details = "'" + itemname2 + "','" +itemcategory2 + "','" + quantity2 + "','" + itemdate +"','"+yearsused2+"','"+id+"'";


             System.out.println(item_details);

            new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/insertItemDetails/" + item_details);





        }
        });

return additems;
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

                   // Intent intent1 = new Intent(context, LoginActivity.class);
                   // intent1.putExtra("reg_email", username.getText().toString());
                   // startActivity(intent1);

                }

            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }

    }






