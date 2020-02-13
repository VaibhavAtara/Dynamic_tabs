package com.example.dynamic_tabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database_test extends SQLiteOpenHelper {


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS rooms(id INTEGER PRIMARY KEY,room_name TEXT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS devices(id TEXT PRIMARY KEY,type TEXT, time TEXT,topic TEXT," +
                "start TEXT,close TEXT,command TEXT,source TEXT, watt TEXT,duty TEXT,thumbnail INTEGER )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Database_test(@Nullable Context context) {
        super(context, "main.db", null, 1);
    }

    public boolean create_room(String room_name,int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("create table if not exists "+room_name+"(id TEXT PRIMARY KEY,type TEXT,time TEXT," +
                "topic TEXT,start TEXT,close TEXT, command TEXT,source TEXT,watt TEXT,duty TEXT,thumbnail INTEGER);");

        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("room_name",room_name);
        //long result=db.insert(room_name,null,contentValues);
        long result1=db.insert("rooms",null,contentValues);
        if(result1==-1)
            return false;
        else
            return true;


    }

    public Cursor fetch_room_name(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from rooms where id="+id+";",null);
        return result;

    }
    public Cursor fetch_all_rooms()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from rooms;",null);
        return result;

    }
   /* public void delete_tables()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("drop table if exists rooms;");


    }*/

    public Cursor get_room_data(String room_name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+room_name+";",null);
        return result;
    }


    public boolean insert_in_room(DeviceObject deviceObject,String room_name)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("id",deviceObject.getId());

        contentValues.put("type",deviceObject.getTopic());

        contentValues.put("time",deviceObject.getTime());

        contentValues.put("topic",deviceObject.getTopic());

        contentValues.put("start",deviceObject.getStart());

        contentValues.put("close",deviceObject.getClose());

        contentValues.put("command",deviceObject.getCommand());

        contentValues.put("source",deviceObject.getSource());

        contentValues.put("watt",deviceObject.getWatt());

        contentValues.put("duty",deviceObject.getDuty());

        contentValues.put("thumbnail",deviceObject.getThumbnail());

        long result1=db.insert(room_name,null,contentValues);
        if(result1==-1)
            return false;
        else
            return true;


    }
    public Cursor fetch_devices()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from devices;",null);
        return cursor;
    }

    public Cursor fetch_device(String id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from devices where id='"+id+"'",null);
        return cursor;
    }
    public boolean insert_devices(DeviceObject deviceObject)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor result=db.rawQuery("select * from devices where id='"+deviceObject.getId()+"'",null);

        ContentValues contentValues=new ContentValues();
        contentValues.put("id",deviceObject.getId());

        contentValues.put("type",deviceObject.getType());

        contentValues.put("time",deviceObject.getTime());

        contentValues.put("topic",deviceObject.getTopic());

        contentValues.put("start",deviceObject.getStart());

        contentValues.put("close",deviceObject.getClose());

        contentValues.put("command",deviceObject.getCommand());

        contentValues.put("source",deviceObject.getSource());

        contentValues.put("watt",deviceObject.getWatt());

        contentValues.put("duty",deviceObject.getDuty());

        contentValues.put("thumbnail",deviceObject.getThumbnail());

        if(result.getCount() != 0)
        {

        // db.execSQL("UPDATE devices SET command="+"\""+deviceObject.command+"\""+"where id="+"\""+deviceObject.getId()+"\""+";");
            db.update("devices",contentValues,"id="+deviceObject.getId(),null);
            return  false;
        }
       long result1=db.insert("devices",null,contentValues);

        if(result1==-1)
            return false;
        else
            return true;

    }




}
