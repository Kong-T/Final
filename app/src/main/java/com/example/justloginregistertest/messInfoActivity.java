package com.example.justloginregistertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class messInfoActivity extends AppCompatActivity {

    private DBOpenHelper dbOpenHelper;
    private TextView tinfoname;
    private TextView tinfotime;
    private TextView tinfomess;
    ArrayList<HashMap<String, String>> messlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_info);
        dbOpenHelper = new DBOpenHelper(this);
        tinfomess = findViewById(R.id.infoDetail);
        tinfotime = findViewById(R.id.infotime);
        tinfoname = findViewById(R.id.infoname);


        Intent fragIntent = getIntent();
        String itemname = fragIntent.getStringExtra("itemname");
        String itemtime = fragIntent.getStringExtra("itemtime");
        String itemmess = fragIntent.getStringExtra("itemmess");

        tinfoname.setText(itemname);
        tinfotime.setText(itemtime);
        tinfomess.setText(itemmess);

        Log.i("itemname",itemname);
        Log.i("itemtime",itemtime);
        Log.i("itemmess",itemmess);


    }





}
