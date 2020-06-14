package com.example.justloginregistertest;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class FirstFragment extends Fragment {

    private EditText edmessage;
    private Button btenter;
    private DBOpenHelper dbOpenHelper;

    public FirstFragment() {
        // Required empty public constructor
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dbOpenHelper = new DBOpenHelper(getActivity());

        btenter =  (Button) Objects.requireNonNull(getActivity()).findViewById(R.id.enter);
        btenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMess();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        edmessage = view.findViewById(R.id.message);
        return  view;
    }



    public void ClickMess() {
        String edmess = edmessage.getText().toString();
        String curDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        Intent intent = getActivity().getIntent();
        String name = intent.getStringExtra("name");
        Log.i("curDate",curDate);
        Log.i("edmess",edmess);
        Log.i("intent_name",name);
        if(!TextUtils.isEmpty(edmess)){
            dbOpenHelper.addmess(name,edmess,curDate);
            Log.i("dbOPen","已加入");
            Toast.makeText(getActivity(),  "成功提交", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(getActivity(), MainActivity.class));

    }


}
