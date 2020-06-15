package com.example.justloginregistertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class usercenterActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener{

    private String TAG ="usercenter";
    private ImageView userback;
    private TextView usercenter_name;
    private  String name;
    private DBOpenHelper dbOpenHelper;
    private ListView listView;
    List<Map<String,String>> messlist;
    String usercentermessage;
    String usercentertime;
    String usercentername;
    SQLiteDatabase db;

    private SimpleAdapter listItemAdapter;//适配器



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercenter);

        dbOpenHelper = new DBOpenHelper(this);
        db = dbOpenHelper.getWritableDatabase();

        userback = findViewById(R.id.usercenter_back);
        usercenter_name = findViewById(R.id.usercenter_username);
        listView = findViewById(R.id.usercenter_infolist);

        userback.setOnClickListener(this);
        listView.setOnItemLongClickListener(this);


        Intent intent_gouc = getIntent();
        name = intent_gouc.getStringExtra("usercentername");
        usercenter_name.setText(name);
        Log.i("usercentername",name);

        getdbMess();
        listItemAdapter = new SimpleAdapter(this,messlist,//listItems 数据源
                R.layout.userlist,// ListItem的xml布局实现
                new String[]{"usercentermessage","usercentertime"},
                new int[] {R.id.useritemmess,R.id.useritemtime}
        );
        listView.setAdapter(listItemAdapter);




    }
    public void onClick(View v) {
        if (v.getId() == R.id.usercenter_back) {
            Intent intent_usercenter = new Intent(this, MainActivity.class);
            startActivity(intent_usercenter);
            finish();
        }
    }

    public void getdbMess() {
        ArrayList<Mess> data = dbOpenHelper.getAllmess();
        Log.i("usercenter_data",data.toString());
        messlist = new ArrayList<Map<String,String>>();
        for (int i = 0; i < data.size(); i++) {
            Mess mess = data.get(i);
            usercentername = mess.getName();
            usercentermessage = mess.getMess();
            usercentertime = mess.getTime();
            if (usercentername.equals(name)){
                Log.i("usercentername",usercentername);
                Map<String, String> map = new HashMap<String, String>();
                map.put("usercentermessage", usercentermessage);
                map.put("usercentertime", usercentertime);
                Log.i("usercenternmap",map.toString());
                Log.i("usercenternmap",String.valueOf(i)+usercentermessage+usercentertime);

                messlist.add(map);
            }

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        Log.i("onItemLongClick",messlist.get(position).toString());
        Log.i("onItemLongClick",messlist.get(position).get("usercentertime"));
        Log.i("onItemLongClick",String.valueOf(position));
        final String itemmesstime = messlist.get(position).get("usercentertime");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前信息").setPositiveButton("是",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick:对话框事件处理");
                //删除数据
                db.delete("message","time = ?",new String[]{itemmesstime});
                //dbOpenHelper.messagedelete(name,messlist.get(position).get("usercentermessage"),messlist.get(position).get("usercentertime"));
                messlist.remove(position);
                dbOpenHelper.close();
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();

        return true;
    }

}
