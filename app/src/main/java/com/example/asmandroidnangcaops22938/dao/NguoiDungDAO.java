package com.example.asmandroidnangcaops22938.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmandroidnangcaops22938.helper.DbHelper;
import com.example.asmandroidnangcaops22938.service.KiemTraDangNhapService;

import java.util.ArrayList;

public class NguoiDungDAO {
    DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);



    }

    //kiểm tra thông tin đăng nhgaapjh
    public boolean KiemTraDangNhap(String user, String pass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE username = ? AND password = ?", new String[]{user, pass});
        if(cursor.getCount() != 0){
            //lưu lại id người dung khi đang nhập thành công id vi tri so 0
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id", cursor.getInt(0));
            editor.apply();
            return true;
        }
        return false;
    }
}
