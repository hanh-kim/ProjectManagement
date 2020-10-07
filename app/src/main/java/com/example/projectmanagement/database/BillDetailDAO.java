package com.example.projectmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectmanagement.model.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillDetailDAO {

    Context context;
    MyDatabase myDatabase;
    SQLiteDatabase db;
    public BillDetailDAO(Context context, MyDatabase myDatabase) {
        this.context = context;
        this.myDatabase = myDatabase;

    }



    public long deleteData(Bill bill) {
        db = myDatabase.getWritableDatabase();
        return db.delete(InfoTable.TABLE_BILL_DETAIL, InfoTable.COL_BILL_DETAIL_ID + " = ?", new String[]{bill.billID});
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
        values.put(InfoTable.COL_BOOK_NAME, bill.bookName);
        values.put(InfoTable.COL_BOOK_ID, bill.bookID);
        values.put(InfoTable.COL_BOOK_AMOUNT,bill.numberBook);
        values.put(InfoTable.COL_BOOK_PRICE,bill.bookPrice);
        return db.insert(InfoTable.TABLE_BILL_DETAIL, null, values);
    }


    public List<Bill> getAllData(String billID) {
        db = myDatabase.getWritableDatabase();
        List<Bill> billList = new ArrayList<>();
        String getData = "SELECT * FROM "+InfoTable.TABLE_BILL_DETAIL+" WHERE "+InfoTable.COL_BILL_ID+"="+billID;
        Cursor cursor = db.rawQuery(getData,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Bill bill =  new Bill();
                bill.billDetailID=cursor.getInt(cursor.getColumnIndex(InfoTable.COL_BILL_DETAIL_ID));
                bill.billID= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BILL_ID));
                bill.date= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BILL_DATE));
                bill.bookID= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_ID));
                bill.bookName= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_NAME));
                bill.numberBook= cursor.getInt(cursor.getColumnIndex(InfoTable.COL_BOOK_AMOUNT));
                bill.bookPrice= cursor.getDouble(cursor.getColumnIndex(InfoTable.COL_BOOK_PRICE));
                billList.add(bill);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return billList;
    }

}
