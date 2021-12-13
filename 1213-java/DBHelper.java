package com.example.reviewmaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME ="jumg6120.db";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //데이터 베이스가 생성이 될 떄 호출
        db.execSQL("CREATE TABLE IF NOT EXISTS chicken(id VARCHAR(15) PRIMARY KEY, store VARCHAR(10) NOT NULL, kind VARCHAR(30) NOT NULL, re_view VARCHAR(100) NOT NULL, writeDate TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        onCreate(db);
    }

    public ArrayList<chickenItem> getchicken(){
        ArrayList<chickenItem> chickenitems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM chicken ORDER BY writeDate DESC", null);
        if(cursor.getCount() != 0) {
            //조회된 리뷰가 있을때 수행
            while (cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String store = cursor.getString(cursor.getColumnIndex("store"));
                @SuppressLint("Range") String kind = cursor.getString(cursor.getColumnIndex("kind"));
                @SuppressLint("Range") String re_view = cursor.getString(cursor.getColumnIndex("re_view"));
                @SuppressLint("Range") String writeDate = cursor.getString(cursor.getColumnIndex("writeDate"));

                chickenItem chickenitem = new chickenItem();
                chickenitem.setId(id);
                chickenitem.setStore(store);
                chickenitem.setKind(kind);
                chickenitem.setRe_view(re_view);
                chickenitem.setWriteDate(writeDate);
                chickenitems.add(chickenitem);
            }
        }
        cursor.close();

        return chickenitems;
    }

    //리뷰를 디비에 넣음
    public void InsertTodo(String _id, String _store, String _kind, String _re_view, String _writeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO chicken (id, store, kind, re_view, writeDate) VALUES('"+_id+"', '"+_store+"', '"+_kind+"', '"+_re_view+"', '"+_writeDate+"')");
    }

    public void UpdateTodo(String _id, String _store, String _kind, String _re_view, String _writeDate, String _beforeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE chicken SET id='"+_id + "', store='"+_store + "', kind='"+_kind + "', re_view='"+_re_view + "', writeDate='"+_writeDate + "' WHERE writeDate='"+_beforeDate+"'");
    }

    public void deleteTodo(String _beforeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM chicken WHERE writeDate = '"+_beforeDate+"'");
    }
}
