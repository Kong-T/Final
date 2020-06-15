package com.example.justloginregistertest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initView();
        PageView();
        int id = getIntent().getIntExtra("id", 0);
        if (id == 1) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.show_fragment,new ShowFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }


    public void onClick(View view) {

    }

    //菜单项
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
        //如果返回值为真，说明当前页面是有菜单项的，如果返回值为假，说明当前页面是没有菜单项的。
    }

    //菜单点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //这里只有一个item，但如果我们有多个，就需要通过id去区分不同的item
        if(item.getItemId()==R.id.menu_set){
            //这里老师演示直接引用之前的打开新窗口的方法。
            // tip:为了避免出现两段相同代码，通过method功能形成了方法，可以直接引用
            Log.i("menu","menu");
            Intent intent = new Intent(this, loginActivity.class);
            startActivity(intent);
            finish();
        }
        if ((item.getItemId()==R.id.usercenter)){
//            Intent intent = getIntent();
//            String name = intent.getStringExtra("name");

            SharedPreferences SPlogin = getSharedPreferences ("currentuser", Activity.MODE_PRIVATE);
            String name = SPlogin.getString("curusername","");
            Intent intent_gouc = new Intent(this, usercenterActivity.class);
            intent_gouc.putExtra("usercentername",name);
            startActivity(intent_gouc);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }




    private void PageView() {
        //多个页面及tablelayout
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MypageAdapter pageAdapter = new MypageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tabLayout);
        tableLayout.setupWithViewPager(viewPager);
    }



}
