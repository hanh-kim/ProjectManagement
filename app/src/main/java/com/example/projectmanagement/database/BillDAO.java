package com.example.projectmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectmanagement.model.Bill;
import com.example.projectmanagement.model.InfoUser;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    Context context;
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public BillDAO(Context context, MyDatabase myDatabase) {
        this.context = context;
        this.myDatabase = myDatabase;

    }


    public long deleteData(Bill bill) {
        db = myDatabase.getWritableDatabase();
        return db.delete(InfoTable.TABLE_BILL, InfoTable.COL_BILL_ID + " = ?", new String[]{bill.billID});
    }


    public long udateData(Bill bill) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_BILL_DATE, bill.date);
        return db.update(InfoTable.TABLE_BILL, values, InfoTable.COL_BILL_ID + " = ?", new String[]{bill.billID});
    }


    public long addData(Bill bill) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_BILL_ID, bill.billID);
        values.put(InfoTable.COL_BILL_DATE, bill.date);
        return db.insert(InfoTable.TABLE_BILL, null, values);
    }


    public List<Bill> getAllData() {
        db = myDatabase.getWritableDatabase();
        List<Bill> billList = new ArrayList<>();
        String getData = "SELECT * FROM " + InfoTable.TABLE_BILL;
        Cursor cursor = db.rawQuery(getData, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Bill bill = new Bill();
                bill.billID = cursor.getString(cursor.getColumnIndex(InfoTable.COL_BILL_ID));
                bill.date = cursor.getString(cursor.getColumnIndex(InfoTable.COL_BILL_DATE));
                billList.add(bill);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return billList;
    }

    public List<Bill> searchData(String billID) {
        db = myDatabase.getWritableDatabase();
        List<Bill> billList = new ArrayList<>();
        String getData = "SELECT * FROM " + InfoTable.TABLE_BILL + " WHERE " + InfoTable.COL_TYPE_ID + " LIKE " + billID + "%";
        Cursor cursor = db.rawQuery(getData, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Bill bill = new Bill();
                bill.billID = cursor.getString(cursor.getColumnIndex(InfoTable.COL_BILL_ID));
                bill.date = cursor.getString(cursor.getColumnIndex(InfoTable.COL_BILL_DATE));
                billList.add(bill);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return billList;
    }

    public boolean checkExist(Bill bill) {
        db = myDatabase.getWritableDatabase();
        String check = "SELECT * FROM " + InfoTable.TABLE_BILL + " WHERE " + InfoTable.COL_BILL_ID + "=" + bill.billID;
        Cursor cursor = db.rawQuery(check, null);
        db.close();
        if (cursor.getCount() > 0) {
            return true;
        } else return false;
    }

}
