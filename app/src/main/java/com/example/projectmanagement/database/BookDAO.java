package com.example.projectmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectmanagement.model.Book;
import com.example.projectmanagement.model.InfoUser;

import java.util.ArrayList;
import java.util.List;

public class BookDAO  {
    Context context;
    MyDatabase myDatabase;
    SQLiteDatabase db ;
    public BookDAO(Context context, MyDatabase myDatabase) {
        this.context = context;
        this.myDatabase = myDatabase;

    }


    public long deleteData(Book book) {
        db = myDatabase.getWritableDatabase();
        return db.delete(InfoTable.TABLE_BOOK, InfoTable.COL_BOOK_ID + " = ?", new String[]{book.bookID});
    }


    public long udateData(Book book) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_BOOK_NAME, book.bookName);
        values.put(InfoTable.COL_BOOK_ID, book.bookID);
        values.put(InfoTable.COL_TYPE_ID, book.typeID);
        values.put(InfoTable.COL_BOOK_AUTHOR, book.author);
        values.put(InfoTable.COL_BOOK_AMOUNT, book.amount);
        values.put(InfoTable.COL_BOOK_IMPORT_PRICE, book.importPrice);
        values.put(InfoTable.COL_BOOK_PRICE, book.price);
        values.put(InfoTable.COL_BOOK_DATE, book.importDate);
        return db.update(InfoTable.TABLE_BOOK, values, InfoTable.COL_BOOK_ID + " = ?", new String[]{book.bookID});
    }


    public long addData(Book book) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InfoTable.COL_BOOK_NAME, book.bookName);
        values.put(InfoTable.COL_BOOK_ID, book.bookID);
        values.put(InfoTable.COL_TYPE_ID, book.typeID);
        values.put(InfoTable.COL_BOOK_AUTHOR, book.author);
        values.put(InfoTable.COL_BOOK_AMOUNT, book.amount);
        values.put(InfoTable.COL_BOOK_IMPORT_PRICE, book.importPrice);
        values.put(InfoTable.COL_BOOK_PRICE, book.price);
        values.put(InfoTable.COL_BOOK_DATE, book.importDate);
        return db.insert(InfoTable.TABLE_BOOK,null, values);
    }

    public List<Book> getAllData() {
        db = myDatabase.getWritableDatabase();
        List<Book> bookList = new ArrayList<>();
        String getData = "SELECT * FROM "+InfoTable.TABLE_BOOK;
        Cursor cursor = db.rawQuery(getData,null);
        if (cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
               Book book = new Book();
                book.bookID= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_ID));
                book.bookName= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_NAME));
                book.typeID= cursor.getString(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                book.author= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_AUTHOR));
                book.amount= cursor.getInt(cursor.getColumnIndex(InfoTable.COL_BOOK_AMOUNT));
                book.importPrice= cursor.getDouble(cursor.getColumnIndex(InfoTable.COL_BOOK_IMPORT_PRICE));
                book.price= cursor.getDouble(cursor.getColumnIndex(InfoTable.COL_BOOK_PRICE));
                book.importDate= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_DATE));
                bookList.add(book);
                cursor.moveToNext();
            }
            cursor.close();

        }



        return bookList;
    }

    public List<Book> searchData(String bookName) {
        db = myDatabase.getWritableDatabase();
        List<Book> bookList = new ArrayList<>();
        String getData = "SELECT * FROM "+InfoTable.TABLE_BOOK+" WHERE "+InfoTable.COL_BOOK_NAME+" LIKE "+bookName+"%";

        Cursor cursor = db.rawQuery(getData,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Book book = new Book();
                book.bookID= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_ID));
                book.bookName= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_NAME));
                book.typeID= cursor.getString(cursor.getColumnIndex(InfoTable.COL_TYPE_ID));
                book.author= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_AUTHOR));
                book.amount= cursor.getInt(cursor.getColumnIndex(InfoTable.COL_BOOK_AMOUNT));
                book.importPrice=cursor.getDouble(cursor.getColumnIndex(InfoTable.COL_BOOK_IMPORT_PRICE));
                book.price=cursor.getDouble(cursor.getColumnIndex(InfoTable.COL_BOOK_PRICE));
                book.importDate= cursor.getString(cursor.getColumnIndex(InfoTable.COL_BOOK_DATE));
                bookList.add(book);
                cursor.moveToNext();
            }
            cursor.close();

        }
        return bookList;
    }



}
