package com.example.projectmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectmanagement.model.InfoUser;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    MyDatabase myDatabase;
    SQLiteDatabase db;

    public UserDAO( MyDatabase myDatabase) {
        this.myDatabase = myDatabase;


    }


    public long deleteData(InfoUser user) {
        db = myDatabase.getWritableDatabase();
        return db.delete(InfoTable.TABLE_ACCOUNT, InfoTable.COL_USERNAME + " = ?", new String[]{user.username});
    }

    public long udateData(InfoUser user) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_FULLNAME, user.fullName);
        values.put(InfoTable.COL_PHONE, user.phone);
        values.put(InfoTable.COL_EMAIL, user.email);
        values.put(InfoTable.COL_PASSWORD, user.password);
        return db.update(InfoTable.TABLE_ACCOUNT, values, InfoTable.COL_USERNAME + " = ?", new String[]{user.username});
    }

    public long changePassword(String username, String password) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_PASSWORD, password);
        return db.update(InfoTable.TABLE_ACCOUNT, values, InfoTable.COL_USERNAME + " = ?", new String[]{username});
    }

    public long addData(InfoUser user) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_USERNAME, user.username);
        values.put(InfoTable.COL_FULLNAME, user.fullName);
        values.put(InfoTable.COL_PHONE, user.phone);
        values.put(InfoTable.COL_EMAIL, user.email);
        values.put(InfoTable.COL_PASSWORD, user.password);
        return db.insert(InfoTable.TABLE_ACCOUNT, null, values);


    }

    public List<InfoUser> getAllData() {
        db = myDatabase.getWritableDatabase();
        List<InfoUser> userList = new ArrayList<>();
        String getData = "SELECT * FROM " + InfoTable.TABLE_ACCOUNT;
        Cursor cursor = db.rawQuery(getData, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                InfoUser user = new InfoUser();
                user.username = cursor.getString(cursor.getColumnIndex(InfoTable.COL_USERNAME));
                user.fullName = cursor.getString(cursor.getColumnIndex(InfoTable.COL_FULLNAME));
                user.phone = cursor.getString(cursor.getColumnIndex(InfoTable.COL_PHONE));
                user.email = cursor.getString(cursor.getColumnIndex(InfoTable.COL_EMAIL));
                user.password = cursor.getString(cursor.getColumnIndex(InfoTable.COL_PASSWORD));
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();

        }

        return userList;
    }

    public List<InfoUser> searchData(String name) {
        db = myDatabase.getWritableDatabase();
        List<InfoUser> userList = new ArrayList<>();
        String getData = "SELECT * FROM " + InfoTable.TABLE_ACCOUNT + " WHERE " + InfoTable.COL_TYPE_ID + " LIKE " + name + "%";
        Cursor cursor = db.rawQuery(getData, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                InfoUser user = new InfoUser();
                user.username = cursor.getString(cursor.getColumnIndex(InfoTable.COL_USERNAME));
                user.fullName = cursor.getString(cursor.getColumnIndex(InfoTable.COL_FULLNAME));
                user.phone = cursor.getString(cursor.getColumnIndex(InfoTable.COL_PHONE));
                user.email = cursor.getString(cursor.getColumnIndex(InfoTable.COL_EMAIL));
                user.password = cursor.getString(cursor.getColumnIndex(InfoTable.COL_PASSWORD));
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return userList;
    }

    // check exist
    public boolean checkExist(InfoUser user) {
        db = myDatabase.getWritableDatabase();
        String check = "SELECT * FROM " + InfoTable.TABLE_ACCOUNT + " WHERE " + InfoTable.COL_USERNAME + "= '" + user.username+"'";
        Cursor cursor = db.rawQuery(check, null);

        if (cursor.getCount() > 0) {
            db.close();
            return true;
        } else{
            db.close();
            return false;
        }
    }

    public boolean checkExist(String username, String password) {
        db = myDatabase.getWritableDatabase();
        String check = "SELECT * FROM " + InfoTable.TABLE_ACCOUNT + " WHERE " + InfoTable.COL_USERNAME + "= '" +username+"' AND " +
                InfoTable.COL_PASSWORD+" = '"+password+"'";
        Cursor cursor = db.rawQuery(check, null);

        if (cursor.getCount() > 0) {
            db.close();
            return true;
        } else{
            db.close();
            return false;
        }
    }
}
