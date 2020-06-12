package com.example.justloginregistertest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment implements Runnable,AdapterView.OnItemClickListener {

    //用变量去存储，方便后续修改；
    private final String TAG = "InformationSearch";
    ListView listview;
    List<String> list;
    InfoFragment context = null;
    Handler handler;
    int k;
    String strtitle;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        listview = (ListView) view.findViewById(R.id.search_list);
        return  view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Thread t = new Thread((Runnable) this);


        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    List<String> list = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
                    listview.setAdapter(adapter);

                }

                super.handleMessage(msg);
            }
        };
        t.start();
        listview.setOnItemClickListener(this);


    }


    public void run() {
        //获取网络数据，放入List带回主线程
        Log.i("thread", "run......");
//        final List<String> rateList = new ArrayList<String>();
        try {

            Thread.sleep(2000);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences ("infoFrag", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Document doc = Jsoup.connect("http://www.swufe.edu.cn/index/xsjz.htm").get();
            final List<String> infoList = new ArrayList<String>();
            Elements tbs = doc.getElementsByTag("ul");
            Element table = tbs.get(8);
            //获取网页链接
            Elements links = table.getElementsByTag("a");
            int j = 1;
            int i = 0;
            String Url;
            for (Element link : links) {
                String linkhref = link.attr("href");
                String title = link.attr("title");
                Url = "http://www.swufe.edu.cn/".concat(linkhref);
                Log.i(TAG, "RUN:url[" + j + "]" + Url);
                Log.i(TAG,title);
                j += 2;
                i += 2;
                editor.putString(String.valueOf(j), Url);
                editor.putString(String.valueOf(i),title);
                editor.commit();
                String strtitle = sharedPreferences.getString(String.valueOf(i),"");
                infoList.add(strtitle);
                Log.i("strtitle",strtitle);
                Log.i("infoList",infoList.toString());
            }

            Message msg = handler.obtainMessage(5);
            msg.obj = infoList;
            handler.sendMessage(msg);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick:parents = " + parent);
        Log.i(TAG, "onItemClick:view = " + view);
        Log.i(TAG, "onItemClick:position = " + position);
        Log.i(TAG, "onItemClick:id = " + id);
        int i = 0;
        i = position*2+1;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("infoFrag", Activity.MODE_PRIVATE);
        String URL = sharedPreferences.getString(String.valueOf(i+2), "1");
        Log.i("i=",String.valueOf(i));
        Log.i("TAG", "run=" + i+sharedPreferences.getString(String.valueOf(i), ""));
        Log.i("TAG", "run=" + URL);
        Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        startActivity(web);
    }





}