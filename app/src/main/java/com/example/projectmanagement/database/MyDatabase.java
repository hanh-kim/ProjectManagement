package com.example.projectmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    InfoTable  table= new InfoTable();
    public MyDatabase(@Nullable Context context) {
        super(context, "BOOK_MANAGEMENT.DB", null, 1);
    }

//    public static final String TABLE_ACCOUNT = "ACCOUNT";
//    public static final String COL_USERNAME = "USERNAME";
//    public static final String COL_FULLNAME = "FULLNAME";
//    public static final String COL_PHONE = "PHONE";
//    public static final String COL_EMAIL = "EMAIL";
//    public static final String COL_PASSWORD = "PASSWORD";
//
//    public static final String TABLE_BOOK_TYPE = "BOOK_TYPE";
//    public static final String COL_TYPE_ID = "USERNAME";
//    public static final String COL_TYPE_NAME = "FULLNAME";
//    public static final String COL_TYPE_LOCATION = "LOCATION";
//
//    public static final String TABLE_BOOK = "BOOK";
//    public static final String COL_BOOK_ID = "ID";
//    public static final String COL_BOOK_NAME = "NAME";
//    public static final String COL_BOOK_AUTHOR = "AUTHOR";
//    public static final String COL_BOOK_PUBLISHER = "PUBLISHER";
//    public static final String COL_BOOK_IMPORT_PRICE = "IMPORT_PRICE";
//    public static final String COL_BOOK_PRICE = "PRICE";
//    public static final String COL_BOOK_NUMBER = "NUMBER";
//    public static final String COL_BOOK_DATE = "DATE";
//
//    public static final String TABLE_BILL = "BILL";
//    public static final String COL_BILL_ID = "ID";
//    public static final String COL_BILL_DATE = "DATE";
//
//    public static final String TABLE_BILL_DETAIL = "BILL_DETAIL";
//    public static final String COL_BILL_DETAIL_ID = "ID";
//
//    // TABLE USER'S ACCOUNT..............
//    public void createTableAccount(SQLiteDatabase db) {
//        //    userName NVARCHAR(50) PK Tên đăng nhập
//        //    Password NVARCHAR(50) NOT NULL Mật khẩu đăng nhập
//        //    Phone NCHAR(10) NOT NULL Số điện thoại
//        //    hoTen NVARCHAR(50) Họ tên người dùng
//        String create_table = "CREATE TABLE IF NOT EXISTS " + TABLE_ACCOUNT + "(" +
//                COL_USERNAME + " NCHAR(20) PRIMARY KEY NOT NULL ," +
//                COL_FULLNAME + " NVARCHAR(50) NOT NULL," +
//                COL_PHONE + " NCHAR(10) NOT NULL," +
//                COL_EMAIL + " NCHAR(50)," +
//                COL_PASSWORD + " NCHAR(50) NOT NULL)";
//
//
//        db.execSQL(create_table);
//    }
//
//    // BANG THE LOAI SACH.............
//    public void createTableBookType(SQLiteDatabase db) {
//        //    MaTheLoai CHAR(5) PK, NOT NULL Mã thể thoại
//        //    Tênthểloại NVARCHAR(50) NOT NULL Tên thể loại
//        //    Mô tả NVARCHAR(255) Mô tẻ chi tiết
//        //    Vị trí INT
//        String create_table = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK_TYPE + "(" +
//                COL_TYPE_ID + " NCHAR(5) PRIMARY KEY NOT NULL ," +
//                COL_TYPE_NAME + " NVARCHAR(50) NOT NULL ," +
//                COL_TYPE_LOCATION + " INTEGER)";
//        db.execSQL(create_table);
//    }
//
//    // BANG SACH....................
//    public void createTableBook(SQLiteDatabase db) {
//        //    MaSach NCHAR(5) PK, NOT NULL Mã Sách
//        //    MaTheLoai NCHAR(50) FK, NOT NULL Mã thể loại mà sách thuộc về
//        //    TacGia NVARCHAR(50) Tác giá sách
//        //    NXB NVARCHAR(50) Nhà xuất bản sách
//        //    giaBia FLOAT NOT NULL Giá bán sách
//        //    soLuong INT NOT NULL Số lượng tồn kho
//
//        String create_table = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK + "(" +
//                COL_BOOK_ID + " NCHAR(5) PRIMARY KEY NOT NULL ," +
//                COL_TYPE_ID + " NCHAR(5) NOT NULL," +
//                COL_BOOK_AUTHOR + " NVARCHAR(50) ," +
//                COL_BOOK_PUBLISHER + " NVARCHAR(50)," +
//                COL_BOOK_IMPORT_PRICE + " FLOAT NOT NULL," +
//                COL_BOOK_PRICE + " FLOAT NOT NULL," +
//                COL_BOOK_NUMBER + " INTEGER NOT NULL," +
//                COL_BOOK_DATE + " DATE NOT NULL)";
//
//        db.execSQL(create_table);
//    }
//
//    // BANG HOA DON...........
//    public void createTableBill(SQLiteDatabase db) {
//        //    MaHoaDon NCHAR(7) PK, NOT NULL Mã hoá đơn
//        //    NgayMua DATE NOT NULL Ngày mua hàng
//        String create_table = "CREATE TABLE IF NOT EXISTS " + TABLE_BILL + "(" +
//                COL_BILL_ID + " NCHAR(7) PRIMARY KEY NOT NULL ," +
//                COL_BILL_DATE + " DATE NOT NULL)";
//
//        db.execSQL(create_table);
//    }
//
//
//    // BANG HOA DON CHI TIET.............
//    public void createTableBillDetail(SQLiteDatabase db) {
//        //    MaHDCT INT PK, Tự tăng Mã hoá đơn chi tiết
//        //    MaHoaDon NCHAR(7) FK, NOT NULL Mã hoá đơn
//        //    MaSach NCHAR(5) FK, NOT NULL Mã sách được mua
//        //    SoLuongMua INT NOT NULL Số lượng mua
//        String create_table = "CREATE TABLE IF NOT EXISTS " + TABLE_BILL_DETAIL + "(" +
//                COL_BILL_DETAIL_ID + " PRIMARY KEY AUTOINCREMENT," +
//                COL_BILL_ID + " NCHAR(7) NOT NULL, " +
//                COL_BOOK_ID + " NCHAR(5) NOT NULL," +
//                COL_BOOK_NUMBER + " INTEGER NOT NULL)";
//
//        db.execSQL(create_table);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        table.createTableAccount(db);
        table.createTableBook(db);
        table.createTableBookType(db);
        table.createTableBill(db);
        table. createTableBillDetail(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + table.TABLE_ACCOUNT);
        db.execSQL("drop table if exists " + table.TABLE_BOOK);
        db.execSQL("drop table if exists " + table.TABLE_BOOK_TYPE);
        db.execSQL("drop table if exists " + table.TABLE_BILL);
        db.execSQL("drop table if exists " + table.TABLE_BILL_DETAIL);
        onCreate(db);
    }


}
