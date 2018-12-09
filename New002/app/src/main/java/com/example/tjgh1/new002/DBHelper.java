package com.example.tjgh1.new002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public DBHelper(Context context) {
        super(context, "Shelter", null, 1);

        this.context = context;
    }

    public boolean testDB() {
        SQLiteDatabase db = getReadableDatabase();

        return  db.isOpen();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE Shelter ( " );
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, " );
        sb.append(" Title TEXT, " );
        sb.append(" UserName TEXT,  " );
        sb.append(" Comment TEXT, " );
        sb.append(" RegDate TEXT,  " );
        sb.append(" ImageUrl TEXT ); " );


        db.execSQL(sb.toString());

        //Toast.makeText(context, "Table 생성됨" , Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if ( oldVersion == 1 && newVersion == 2 ){
            StringBuffer sb = new StringBuffer();
            //sb.append(" ALTER TABLE DeliveryHistory ADD ADDRESS TEXT ");
            db.execSQL(sb.toString());

            //Toast.makeText(context, "Version 올라간다." , Toast.LENGTH_SHORT).show();
        }
//        else if ( oldVersion == 1 && newVersion == 3 ) {
//
//        }
    }


    public void UpdateMemory(MemoryModel info) {
        // 1. 쓰기 가능한 DB 객체를 가져온다.
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Title", info.Title);
        values.put("UserName", info.UserName);
        values.put("Comment", info.Comment);
        values.put("RegDate", info.RegDate);
        values.put("ImageUrl", info.ImageUrl);



        db.update("Shelter", values, "_ID=?", new String[]{info.ID} );
        db.close();
    }

    public void AddMemory(MemoryModel info) {
        // 1. 쓰기 가능한 DB 객체를 가져온다.
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Title", info.Title);
        values.put("UserName", info.UserName);
        values.put("Comment", info.Comment);
        values.put("RegDate", info.RegDate);
        values.put("ImageUrl", info.ImageUrl);


        db.insert("Shelter", null, values);

        db.close();

    }

    public List<MemoryModel> GetAllMemory(){

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, Title, UserName, Comment, RegDate, ImageUrl FROM Shelter order by _ID desc ");

        // 읽기 전용 DB 객체를 가져온다.
        SQLiteDatabase db = getReadableDatabase();

        // Select 해 온다.
        Cursor cursor = db.rawQuery(sb.toString(), null);

        List<MemoryModel> memoryList = new ArrayList<MemoryModel>();

        Log.d("RESULT", cursor.getCount() + "");

        MemoryModel history = null;
        while ( cursor.moveToNext() ) {
            history = new MemoryModel();
            history.ID = String.valueOf(cursor.getInt(0));
            history.Title = cursor.getString(1);
            history.UserName = cursor.getString(2);
            history.Comment = cursor.getString(3);
            history.RegDate = cursor.getString(4);
            history.ImageUrl = cursor.getString(5);
            memoryList.add(history);
        }

        db.close();
        return memoryList;

    }

    public List<MemoryModel> GetMemory(String keyword){

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, Title, UserName, Comment, RegDate, ImageUrl FROM Shelter where title LIKE '%" + keyword + "%' " +
                "or username LIKE '%" + keyword + "%' or Comment LIKE '%" +keyword  +"%' order by _ID desc ");

        // 읽기 전용 DB 객체를 가져온다.
        SQLiteDatabase db = getReadableDatabase();

        // Select 해 온다.
        Cursor cursor = db.rawQuery(sb.toString(), null);

        List<MemoryModel> memoryList = new ArrayList<MemoryModel>();

        Log.d("RESULT", cursor.getCount() + "");

        MemoryModel history = null;
        while ( cursor.moveToNext() ) {
            history = new MemoryModel();
            history.ID = String.valueOf(cursor.getInt(0));
            history.Title = cursor.getString(1);
            history.UserName = cursor.getString(2);
            history.Comment = cursor.getString(3);
            history.RegDate = cursor.getString(4);
            history.ImageUrl = cursor.getString(5);
            memoryList.add(history);
        }

        db.close();
        return memoryList;

    }

    public List<MemoryModel> GetAllMemory(String sort){
//sort == asc or desc

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, Title, UserName, Comment, RegDate, ImageUrl FROM Shelter order by _ID  " + sort);

        // 읽기 전용 DB 객체를 가져온다.
        SQLiteDatabase db = getReadableDatabase();

        // Select 해 온다.
        Cursor cursor = db.rawQuery(sb.toString(), null);

        List<MemoryModel> memoryList = new ArrayList<MemoryModel>();

        Log.d("RESULT", cursor.getCount() + "");

        MemoryModel history = null;
        while ( cursor.moveToNext() ) {
            history = new MemoryModel();
            history.ID = String.valueOf(cursor.getInt(0));
            history.Title = cursor.getString(1);
            history.UserName = cursor.getString(2);
            history.Comment = cursor.getString(3);
            history.RegDate = cursor.getString(4);
            history.ImageUrl = cursor.getString(5);
            memoryList.add(history);
        }

        db.close();
        return memoryList;

    }



    public boolean  DeleteMemory(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        try {

            db.delete("Shelter", "_ID=?", new String[]{id});

            Log.i("db", id + "정상적으로 삭제되었습니다.");
        }
        catch (Exception e)
        {
            Log.i("db", e.toString());
            return  false;
        }
        finally {
            db.close();
        }

        return  true;
    }


}
