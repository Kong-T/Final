package com.example.justloginregistertest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListAdapterActivity extends ArrayAdapter {


    private static final String TAG = "MyAdapter";

    public MyListAdapterActivity(@NonNull Context context, int resource, ArrayList <HashMap<String,String>>List) {
        super(context, resource,List);
    }


    @Override
    // getView()有三个参数，
    // position表示将显示的是第几行，
    // covertView是从布局文件中inflate来的布局。
    // 我们用LayoutInflater的方法将定义好的image_item.xml文件提取成View实例用来显示。
    // 然后将xml文件中的各个组件实例化（简单的findViewById()方法）。
    // 这样便可以将数据对应到各个组件上了。
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_my_list2,parent,false);
            Log.i("Adapter", "convertView is null now");
        }
        Map<String,String> map = (Map<String, String>)getItem(position);
        TextView name = (TextView)itemView.findViewById(R.id.itemtitle);
        TextView mess = (TextView)itemView.findViewById(R.id.itemDetail);
        TextView time = (TextView)itemView.findViewById(R.id.itemtime);

        name.setText(map.get("name"));
        mess.setText(map.get("message"));
        time.setText(map.get("messtime"));



        return itemView;
    }


}
