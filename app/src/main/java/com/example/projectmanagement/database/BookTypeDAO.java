package com.example.projectmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectmanagement.model.BookType;

import java.util.ArrayList;
import java.util.List;

public class BookTypeDAO {

    Context context;
    MyDatabase myDatabase;
    SQLiteDatabase db;
    public BookTypeDAO(Context context, MyDatabase myDatabase) {
        this.context = context;
        this.myDatabase = myDatabase;
    }



    public long deleteData(BookType bookType) {
        db = myDatabase.getWritableDatabase();
        return db.delete(InfoTable.TABLE_BOOK_TYPE, InfoTable.COL_TYPE_ID + " = ?", new String[]{bookType.ID});
    }


    public long udateData(BookType bookType) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_TYPE_ID, bookType.ID);
        values.put(InfoTable.COL_TYPE_NAME, bookType.name);
        values.put(InfoTable.COL_TYPE_LOCATION, bookType.location);
        return db.update(InfoTable.TABLE_BOOK_TYPE, values, InfoTable.COL_TYPE_ID + " = ?", new String[]{bookType.ID});
    }


    public long addData(BookType bookType) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_TYPE_ID, bookType.ID);
        values.put(InfoTable.COL_TYPE_NAME, bookType.name);
        values.put(InfoTable.COL_TYPE_LOCATION, bookType.location);
        return db.insert(InfoTable.TABLE_ACCOUNT, null, values);
    }


    public List<BookType> getAllData() {
        db = myDatabase.getWritableDatabase();
        List<BookType> bookTypeList = new ArrayList<>();
        String getData = "SELECT * FROM " + InfoTable.TABLE_BOOK_TYPE;
        Cursor cursor = db.rawQuery(getData, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                BookType type = new BookType();
                type.ID = cursor.getString(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                type.name = cursor.getString(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                type.location = cursor.getInt(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                bookTypeList.add(type);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return bookTypeList;
    }

    public List<BookType> searchData(String typeName) {
        db = myDatabase.getWritableDatabase();
        List<BookType> bookTypeList = new ArrayList<>();
        String getData = "SELECT * FROM " + InfoTable.TABLE_BOOK_TYPE + " WHERE " + InfoTable.COL_TYPE_ID + " LIKE " + typeName + "%";
        Cursor cursor = db.rawQuery(getData, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                BookType type = new BookType();
                type.ID = cursor.getString(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                type.name = cursor.getString(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                type.location = cursor.getInt(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                bookTypeList.add(type);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return bookTypeList;
    }
}
