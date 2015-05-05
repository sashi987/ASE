package com.example.shashi.giveaway;

/**
 * Created by Shashi on 08-04-2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class delItem extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View windows = inflater.inflate(R.layout.deleteitemfragment, container, false);
        ((TextView)windows.findViewById(R.id.textView)).setText("Windows");
        return windows;
    }}