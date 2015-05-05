package com.example.shashi.giveaway;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.content.Context;
import android.location.LocationManager;
import android.widget.Button;

import external.GeocoderHandler;
import external.GeocodingLocation;


public class Studentlocation extends Activity {
    public static Double x=0.00;
    public static Double y=0.00;
    LatLng loc = new LatLng( x,y);
    Button back;
    Button logout;
    private GoogleMap googleMap;
    String add = "";
    String subscribeduser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlocation);
        subscribeduser=getIntent().getStringExtra("subscribeduser").toString();

        logout=(Button)findViewById(R.id.button22);
        add=getIntent().getStringExtra("useraddress").toString();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDefaults(Studentlocation.this);
                Intent nextscreen2=new Intent(Studentlocation.this,LoginActivity.class);
                startActivity(nextscreen2);

            }
        });

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(loc).title(add));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void clearDefaults(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

}