package com.example.justloginregistertest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

    }

//    private void initView() {
//        // 初始化控件对象
//        Button mBtMainLogout = findViewById(R.id.bt_main_logout);
//        // 绑定点击监听器
//        mBtMainLogout.setOnClickListener(this);
//    }

    public void onClick(View view) {
//        if (view.getId() == R.id.bt_main_logout) {
//            Intent intent = new Intent(this, loginActivity.class);
//            startActivity(intent);
//            finish();
//        }
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
