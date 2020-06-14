package com.example.justloginregistertest;

import androidx.appcompat.app.AppCompatActivity;

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

        getdbMess();


    }


    public void getdbMess() {
        ArrayList<Mess> data = dbOpenHelper.getALLmess();
        Log.i("data", data.toString());
        messlist = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < data.size(); i++) {
            Mess mess = data.get(i);
            String name = mess.getName();
            String message = mess.getMess();
            String messtime = mess.getTime();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", name);
            map.put("message", message);
            map.put("messtime", messtime);
            messlist.add(map);
            Log.i("getdbmess", "success");
        }
    }
}
