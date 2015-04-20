package com.example.shashi.hackbattle;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //  private UserLoginTask mAuthTask = null;

    final Context context = this;
    // Email, password edittext
    EditText txtUsername, txtPassword;
    // Session Manager Class

    // UI references.
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";
    SharedPreferences sharedpreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    CheckBox saveLoginCheckBox;
    RadioGroup rg;
    Button ok;
    RadioButton userStudent;
    RadioButton userDonor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.checkBox);

        // Set up the login form.
        String username=mEmailView.getText().toString();
        String password=mPasswordView.getText().toString();



        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        sharedpreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = sharedpreferences.edit();
        saveLogin = sharedpreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            mEmailView.setText(sharedpreferences.getString("username",""));
            mPasswordView.setText(sharedpreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
       // rg = (RadioGroup) findViewById(R.id.userradiogroup);
        // String value = ((RadioButton) findViewById(rg.getCheckedRadioButtonId() )).getText();
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                populateAutoComplete();



                String username=mEmailView.getText().toString();
                String password=mPasswordView.getText().toString();



                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }



                new ReadWCFServiceFeed().execute(
                        "http://10.0.2.2:8871/Service1.svc/userLoginDetails/"+mEmailView.getText().toString());
                // Intent intent = new Intent(context, login_Activity.class);
                //startActivity(intent);
            }
        });
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration = new Intent(LoginActivity.this, Register_Activity.class);

                startActivity(registration);
            }
        });
        Button forgotpswd = (Button) findViewById(R.id.button6);
        forgotpswd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpwd = new Intent(LoginActivity.this, ForgotPassword.class);

                startActivity(forgotpwd);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
      /*  if (mAuthTask != null) {
            return;
        }*/

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.


        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            // showProgress(true);
            //Dashboard screen

            /*String[] userDetails= new String[2];
            userDetails[0]=email;
            userDetails[1]=password;

            Boolean valid= ValidateUser(userDetails);*/

            Boolean isValidUser=false;
            //mAuthTask = new UserLoginTask(email, password);
            try {
                // UserLoginTask task = (UserLoginTask) new UserLoginTask().execute(email, password);
                //isValidUser = task.get();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

            //mAuthTask.execute((Void) null);


        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */


    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }
    private class ReadWCFServiceFeed extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... details) {

            return readJSONFeed(details[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String username=jsonObject.getString("username");
                String pwd= mPasswordView.getText().toString();
                if(!(pwd).equals(jsonObject.getString("password"))){

                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    Intent errorPwd = new Intent(LoginActivity.this, incorrectPassword.class);

                    startActivity(errorPwd);
                }
                else{
                    Intent home;
                    home = new Intent(LoginActivity.this, dashboard.class);
                        home.putExtra("username", username);
                        startActivity(home);
                }
               /* JSONObject jsonObject = new JSONObject(result);
                String username=jsonObject.getString("username");

                if(!(mPasswordView.getText().toString()).equals(jsonObject.getString("password"))){
                   // rtr=false;
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    Intent errorPwd = new Intent(LoginActivity.this, incorrectPassword.class);

                    startActivity(errorPwd);
                }
                else{
                    Intent home = new Intent(LoginActivity.this, Donorhome2.class);
                    home.putExtra("username", username);

                    startActivity(home);
                }*/

                Toast.makeText(getBaseContext(),
                        jsonObject.getString("password") +
                                " - " + jsonObject.getString("username"),
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }
        }
    }
    public class UserLoginTask extends AsyncTask<String, Void, Boolean> {

        //private final String mEmail;
        //private final String mPassword;

       /* UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;

            String url="http://10.0.2.2:56120/Service1.svc/GetPwd/"+email;
            //String op=readJSONFeed(url);
        }*/

        @Override
        protected Boolean doInBackground(String... details) {
            // TODO: attempt authentication against a network service.
            String email=details[0];
            String passwd=details[1];
            email=email.substring(0,email.length()-4);
            String pwd=details[1];
            String url="http://10.0.2.2:8871/Service1.svc/userLoginDetails/"+email;
            Boolean rtr=true;
            StringBuilder stringBuilder = new StringBuilder();
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String user=line;

                       /* String[] splittedDetails= user.split("\"");
                       */

                        try {
                            //JSONArray jsonArray = new JSONArray(result)
                            JSONObject jsonObject = new JSONObject(line);

                            if(!passwd.equals(jsonObject.getString("pwd"))){
                                rtr=false;
                                mPasswordView.setError(getString(R.string.error_invalid_password));
                                Intent errorPwd = new Intent(LoginActivity.this, incorrectPassword.class);

                                startActivity(errorPwd);
                            }
                            else{

                                Intent home;

                                setDefaults(email,passwd,LoginActivity.this);
                                    home = new Intent(LoginActivity.this, dashboard.class);
                                    home.putExtra("email", email);

                                    startActivity(home);



                            }

                        } catch (Exception e) {
                            Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
                        }

                        stringBuilder.append(line);
                    }
                    inputStream.close();
                } else {
                    Log.d("JSON", "Failed to download file");
                }
            } catch (Exception e) {
                Log.d("readJSONFeed", e.getLocalizedMessage());
            }
            return rtr;

            //return readJSONFeed(url);


        }

        @Override
        protected void onCancelled() {
            //mAuthTask = null;
            showProgress(false);
        }



        public String readJSONFeed(String URL) {
            StringBuilder stringBuilder = new StringBuilder();
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(URL);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream inputStream = entity.getContent();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                } else {
                    Log.d("JSON", "Failed to download file");
                }
            } catch (Exception e) {
                Log.d("readJSONFeed", e.getLocalizedMessage());
            }
            return stringBuilder.toString();
        }

    }



    protected void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);


            Toast.makeText(getBaseContext(),
                    jsonObject.getString("password") +
                            " - " + jsonObject.getString("username"),
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
        }
    }
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
}



