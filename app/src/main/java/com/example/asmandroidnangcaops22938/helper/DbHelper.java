package com.example.asmandroidnangcaops22938.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "DANGKYMONHOC", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(id integer primary key autoincrement, username text, password text, name text)";
        sqLiteDatabase.execSQL(dbNguoiDung);

        String dbMonHoc = "CREATE TABLE MONHOC(code text primary key, name text, teacher text)";
        sqLiteDatabase.execSQL(dbMonHoc);

        String dbThongTdbThongTin = "CREATE TABLE THONGTIN(id integer primary key autoincrement, code text, date text, address text)";
        sqLiteDatabase.execSQL(dbThongTdbThongTin);

        String dbDangKy = "CREATE TABLE DANGKY(id integer, code text)";
        sqLiteDatabase.execSQL(dbDangKy);

        String insNguoiDung = "INSERT INTO NGUOIDUNG VALUES(1,'baloi','12345','nguyen ba loi'),(2,'1','1','ba loi')";

        sqLiteDatabase.execSQL(insNguoiDung);

        String insMonHoC = "INSERT INTO MONHOC VALUES('MOB201','Android nâng cao','Nguyễn Ngọc Chấn')";
        sqLiteDatabase.execSQL(insMonHoC);

        String insThongTin = "INSERT INTO THONGTIN VALUES(1, 'MOB201', 'Ca 1 - 10/10/2022', 'T1011')";
        sqLiteDatabase.execSQL(insThongTin);

        String insDangKy = "INSERT INTO DANGKY VALUES(1,'MOB201')";
        sqLiteDatabase.execSQL(insDangKy);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MONHOC");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THONGTIN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DANGKY");
            onCreate(sqLiteDatabase);
        }
    }
}

