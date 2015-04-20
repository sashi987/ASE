package com.example.shashi.hackbattle;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import external.WebServiceCall;
import external.FieldValidation;


public class Register_Activity extends ActionBarActivity {

    View focusView = null;
    EditText firstname;
    EditText lastname;
    EditText username;
    EditText password;
    EditText email;
    EditText mobilenumber;
    EditText dob;
    EditText address;
    boolean toexecute1=false;
    boolean toexecute2=false;
    EditText zipcode;
    Context context;
    String insert_details = "";
    WebServiceCall wcf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        context=this;
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        mobilenumber = (EditText) findViewById(R.id.mobilenumber);
        dob = (EditText) findViewById(R.id.dob);
        address = (EditText) findViewById(R.id.address);
        zipcode = (EditText) findViewById(R.id.zipcode);
        Button registerbutton = (Button) findViewById(R.id.button_register);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname1=firstname.getText().toString();
                String lastname1=lastname.getText().toString();
                String username1=username.getText().toString();
                String password1=password.getText().toString();
                String email1=email.getText().toString();

                String mobile= mobilenumber.getText().toString();
                String dob1=dob.getText().toString();

                String address1=address.getText().toString();
                String zipcode1=zipcode.getText().toString();
                if (!FieldValidation.isNotNull(email1)) {
                    email.setError("This field is required");

                } /*else if (!FieldValidation.validate(email1)) {

                    email.setError("Invalid email address :(");
                }*/

// Validation for password address
                if (!FieldValidation.isNotNull(password1)) {


                    password.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(firstname1)) {


                    firstname.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(lastname1)) {


                    lastname.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(mobile)) {


                    mobilenumber.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(address1)) {


                    address.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(zipcode1)) {


                    zipcode.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(dob1)) {


                    dob.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(username1)) {


                    username.setError("This field is required");

                }
                // Validation for password address
                if (!FieldValidation.isNotNull(password1)) {


                    password.setError("This field is required");

                }


                insert_details = "'" + firstname1+ "','" + lastname1 + "','" + username1 + "','" +password1  + "','" + email1  + "','" + mobile + "','" + dob1 + "','" + address1 + "','" + zipcode1+"'";
                String user_details="'"+username1+"','"+password1+"'";
                // System.out.println("Inside Button click Statement");
                System.out.println(insert_details);

                new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/insertRegistrationDetails/" +insert_details);
                toexecute1=true;
                // new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/insertRegistrationDetails/" + insert_details);
                new ReadWCFServiceFeed().execute("http://10.0.2.2:8871/Service1.svc/insertLoginDetails/" +user_details);
                toexecute2=false;

            }
        });


    }
    private class ReadWCFServiceFeed extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            wcf = new WebServiceCall();
            return wcf.readWCFServiceData(urls[0]);
        }

        protected void onPostExecute(String result) {
            if(toexecute1&&!toexecute2)try {

               /* System.out.println("Input to onPost" + result);
                JSONObject jsonObject = new JSONObject(result);

                System.out.println("JSON FEED FROM WS" + jsonObject.getString("msg")
                );*/
               /* if (jsonObject.getString("msg").equalsIgnoreCase("Inserted data")) {
                    System.out.print("before toast");*/
                Toast.makeText(Register_Activity.this, "User Created", Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(Register_Activity.this, LoginActivity.class);
                intent1.putExtra("reg_email", username.getText().toString());
                startActivity(intent1);

                // }

            } catch (Exception e) {
                Log.d("ReadWCFServiceFeed", e.getLocalizedMessage());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_, menu);
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
