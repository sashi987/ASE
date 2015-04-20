package com.example.shashi.hackbattle;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Intent;
import android.widget.Button;


public class Videosearch extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videosearch);

       /* WebView wb = new WebView(this);
        wb.loadUrl("file:///android_asset/search.html");
        setContentView(wb);*/
        WebView webView;
        webView=(WebView) findViewById(R.id.webView1);

        webView.setWebViewClient(new WebViewClient());
        Intent myIntent=getIntent();
       // url=myIntent.getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/search.html");
        Button logout2 = (Button) findViewById(R.id.button5);
        logout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(Videosearch.this);
                Intent backtologin = new Intent(Videosearch.this, LoginActivity.class);

                startActivity(backtologin);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_videosearch, menu);
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
