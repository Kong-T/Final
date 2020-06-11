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
public class InfoFragment extends Fragment implements Runnable{

    //用变量去存储，方便后续修改；
    private final String TAG = "InformationSearch";
    final ListView listview = (ListView) getView().findViewById(R.id.search_list);
    List<String> list;
    InfoFragment context = null;
    Handler handler;
    int k;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Thread t = new Thread((Runnable) this);
        t.start();

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 5) {
                    List<String> list = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
                    listview.setAdapter(adapter);

                }

                super.handleMessage(msg);
            }
        };


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
            Elements tbs = doc.getElementsByTag("ul");
            Element table = tbs.get(17);
            //获取网页链接
            Elements links = table.getElementsByTag("a");
            int j = 1;
            String Url;
            for (Element link : links) {
                String linkhref = link.attr("href");
                Url = "http://www.swufe.edu.cn/index/xsjz.htm/".concat(linkhref);
                Log.i(TAG, "RUN:url[" + j + "]" + Url);
                j += 2;
                editor.putString(String.valueOf(j), Url);
                editor.commit();
            }
            Elements tds = table.getElementsByTag("span");
            k = tds.size();
            //查找标题
            for (int i = 0; i < k; i += 2) {
                SharedPreferences sp = getActivity().getSharedPreferences("infoFrag", Activity.MODE_PRIVATE);
                SharedPreferences.Editor edi = sp.edit();
                Element td = tds.get(i);
                final String tdstr = td.text();
                Log.i("span", tdstr);
                edi.putString(String.valueOf(i), tdstr);
                edi.commit();
            }



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void passvalue() {
        final SharedPreferences sp = getActivity().getSharedPreferences("info", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor edi = sp.edit();
        final List<String> infoList = new ArrayList<String>();

        for (int i = 0; i <= k + 1; i++) {
            String title = sp.getString(String.valueOf(i), "");
            infoList.add(title);
        }
        Message msg = handler.obtainMessage(5);
        msg.obj = infoList;
        handler.sendMessage(msg);

    }


}