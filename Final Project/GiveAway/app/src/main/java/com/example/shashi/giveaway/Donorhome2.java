package com.example.shashi.giveaway;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Donorhome2 extends ActionBarActivity {


    Button btn;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donorhome2);
        user = getIntent().getStringExtra("username");
        Button btnadd = (Button) findViewById(R.id.button3);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent additem = new Intent(Donorhome2.this, addItem1.class);
                additem.putExtra("user",user);
                startActivity(additem);
            }
        });
        Button btnedit = (Button) findViewById(R.id.button4);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edititem = new Intent(Donorhome2.this, EditItemActivity.class);
                edititem.putExtra("user",user);
                startActivity(edititem);
            }
        });
        Button viewapproveditems = (Button) findViewById(R.id.button5);
        viewapproveditems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(Donorhome2.this);
                Intent subitemview = new Intent(Donorhome2.this, ViewApprovedItems.class);
                subitemview.putExtra("user",user);
                startActivity(subitemview);
            }
        });
        Button viewitems = (Button) findViewById(R.id.button16);
        viewitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(Donorhome2.this);
                Intent subitemview = new Intent(Donorhome2.this, subscribeditemDonarview.class);
                subitemview.putExtra("user",user);
                startActivity(subitemview);
            }
        });
        Button logout = (Button) findViewById(R.id.button9);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clearDefaults(Donorhome2.this);
                Intent backtologin = new Intent(Donorhome2.this, LoginActivity.class);

                startActivity(backtologin);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donorhome2, menu);
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
