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


public class Studentdashboard extends ActionBarActivity {
    String username;
    Button logout;
    Button sublist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdashboard);
        Button catalog=(Button)findViewById(R.id.button13);
        username=getIntent().getStringExtra("username").toString();
        Button search=(Button)findViewById(R.id.button12);
        sublist=(Button)findViewById(R.id.button14);
        sublist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextscreen2=new Intent(Studentdashboard.this,Sunscribeditemlist.class);
                nextscreen2.putExtra("username",username);
                startActivity(nextscreen2);

            }
        });

        logout=(Button)findViewById(R.id.button23);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDefaults(Studentdashboard.this);
                Intent nextscreen2=new Intent(Studentdashboard.this,LoginActivity.class);
                startActivity(nextscreen2);

            }
        });
        catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(Studentdashboard.this,StudentHome.class);
                next.putExtra("username",username);
                startActivity(next);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next=new Intent(Studentdashboard.this,Itemsearch.class);
                startActivity(next);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_studentdashboard, menu);
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
