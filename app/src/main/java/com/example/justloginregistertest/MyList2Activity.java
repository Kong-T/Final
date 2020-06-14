package com.example.justloginregistertest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {

    String TAG = "mylist2";
    private List<HashMap<String,String>> listItems;//存放文字、图片信息；
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();//调用方法
        this.setListAdapter(listItemAdapter);

        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);

    }

    private void initListView() {
        listItems = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("ItemTitle", "Name: = " + i);//标题文字
            map.put("ItemDetail", "Message: = " + i);//详情描述
            listItems.add(map);
        }
        //生成适配器的 Item 和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this,listItems,//listItems 数据源
                R.layout.activity_my_list2,// ListItem的xml布局实现
                new String[]{"ItemTitle","ItemDetail"},
                new int[] {R.id.itemtitle,R.id.itemDetail}
                );
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"onItemClick:parents = " +parent);
        Log.i(TAG,"onItemClick:view = " + view);
        Log.i(TAG,"onItemClick:position = " +position);
        Log.i(TAG,"onItemClick:id = " + id);
        HashMap<String,String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);
        String titleStr = map.get("ItemTitle");
        String detailStr = map.get("ItemDetail");
        Log.i(TAG,"onItemClick:titleStr = " + titleStr);
        Log.i(TAG,"onItemClick:detailStr = " + detailStr);

        TextView title  = (TextView) view.findViewById(R.id.itemtitle);
        TextView detail = (TextView) view.findViewById(R.id.itemDetail);
        String title2  = String.valueOf(title.getText());
        String detail2  = String.valueOf(detail.getText());
        Log.i(TAG,"onItemClick:title2 = " + title2);
        Log.i(TAG,"onItemClick:detail2 = " + detail2);



    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG,"onItemLongClick:长按列表项position"+position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick:对话框事件处理");
                //删除数据
                listItems.remove(position);
                listItemAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("否",null);
        builder.create().show();

        return true;
    }
}
