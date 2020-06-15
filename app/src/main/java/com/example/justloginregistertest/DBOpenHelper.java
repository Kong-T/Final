package com.example.justloginregistertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    /**
     * 写一个这个类的构造函数，参数为上下文context，所谓上下文就是这个类所在包的路径
     * 把数据库设置成可写入状态，除非内存已满，那时候会自动设置为只读模式
     */
    public DBOpenHelper(Context context){
        super(context,"db_test",null,1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS message(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "mess TEXT,"+
                "time TEXT)");
        //AUTOINCREMENT自动创建主键   ，从1开始
        //execSQL用来执行SQL语句，不返回结果
        // execSQL方法除了可执行Select语句外还可执行其它SQL语句（如Update、Insert、Delete等）
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS message");
        onCreate(db);
    }

     //自定义的增删改查方法

    //对user表
 public void add(String name,String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updata(String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    //对message表
    public void addmess(String name, String mess, String time){
        Log.i("name+mess+time",name + time + mess);
        String sql = "INSERT INTO message (name,mess,time) VALUES(?,?,?)";
        db.execSQL(sql,new Object[]{name,mess,time});
    }

    public void messagedelete(String name, String mess, String time){
        db.execSQL("DELETE FROM message WHERE name = AND mess = AND time =" + name + mess + time);
    }

    //用于个人中心的信息检索
    public ArrayList<Mess> getusermess(String username){

        ArrayList<Mess> list = new ArrayList<Mess>();
        Cursor cursor = db.query("message",null,"name = ?" , new String[]{username},null,null,"time DESC");
        while(cursor.moveToNext()){
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String mess = cursor.getString(cursor.getColumnIndex("mess"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(new Mess(name,mess,time));
        }
        return list;
    }

    //获取数据库里的留言板信息
    public ArrayList<Mess> getAllmess(){

        ArrayList<Mess> list = new ArrayList<Mess>();
        Cursor cursor = db.query("message",null,null,null,null,null,"time DESC");
        while(cursor.moveToNext()){
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String mess = cursor.getString(cursor.getColumnIndex("mess"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(new Mess(name,mess,time));
        }
        return list;
    }



    //用于登录匹配信息
    public ArrayList<User> getAllData(){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name,password));
        }
        return list;
    }
}
