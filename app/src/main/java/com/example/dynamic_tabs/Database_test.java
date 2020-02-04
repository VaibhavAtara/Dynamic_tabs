package com.example.dynamic_tabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_test extends SQLiteOpenHelper {


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS rooms(id INTEGER PRIMARY KEY,room_name TEXT );");

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
        db.execSQL("create table if not exists "+room_name+"(title TEXT,des TEXT,cat TEXT,thumbnail INTEGER);");

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


    public boolean insert_in_room(String title,String des,String cat,int id,String room_name)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("title",title);
        contentValues.put("des",des);
        contentValues.put("cat",cat);
        contentValues.put("thumbnail",id);
        //long result=db.insert(room_name,null,contentValues);
        long result1=db.insert(room_name,null,contentValues);
        if(result1==-1)
            return false;
        else
            return true;


    }


}
