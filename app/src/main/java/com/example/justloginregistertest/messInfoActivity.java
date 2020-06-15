package com.example.justloginregistertest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class messInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private DBOpenHelper dbOpenHelper;
    private TextView tinfoname;
    private TextView tinfotime;
    private TextView tinfomess;
    private ImageView imessinfoback;
    ArrayList<HashMap<String, String>> messlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_info);
        dbOpenHelper = new DBOpenHelper(this);
        tinfomess = findViewById(R.id.infoDetail);
        tinfotime = findViewById(R.id.infotime);
        tinfoname = findViewById(R.id.infoname);
        imessinfoback = findViewById(R.id.messinfo_back);
        imessinfoback.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.messinfo_back) {
            Intent intent1 = new Intent(messInfoActivity.this, MainActivity.class);
            intent1.putExtra("id",1);
            startActivity(intent1);

        }
    }
}
