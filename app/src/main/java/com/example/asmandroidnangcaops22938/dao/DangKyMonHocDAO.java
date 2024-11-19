package com.example.asmandroidnangcaops22938.dao;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.asmandroidnangcaops22938.helper.DbHelper;
import com.example.asmandroidnangcaops22938.model.MonHoc;
import com.example.asmandroidnangcaops22938.model.ThongTin;

import java.util.ArrayList;

public class DangKyMonHocDAO {
    private DbHelper dbHelper;

    public DangKyMonHocDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    //lấy danh sách Môn học
    public ArrayList<MonHoc> getDSMonHoc(int id, boolean isAll) {
        ArrayList<MonHoc> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor;
        if(isAll){
            cursor  = sqLiteDatabase.rawQuery("SELECT mh.code,mh.name,mh.teacher,dk.id FROM MONHOC mh LEFT JOIN DANGKY dk ON mh.code = dk.code AND dk.id = ?", new String[]{String.valueOf(id)});
        }else{
            cursor  = sqLiteDatabase.rawQuery("SELECT mh.code,mh.name,mh.teacher,dk.id FROM MONHOC mh INNER JOIN DANGKY dk ON mh.code = dk.code WHERE dk.id = ?", new String[]{String.valueOf(id)});
        }

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new MonHoc(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), getThongTinMonHoc(cursor.getString(0))));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //đangư kí môn học
    public boolean dangKyMonHoc(int id, String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("code",code);
        long check = sqLiteDatabase.insert("DANGKY",null,contentValues);
        if(check == -1)
            return false;
        return true;

    }
    //huydangkymonhoc
    public boolean huyDangKyMonHoc(int id,String code){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        long check = sqLiteDatabase.delete("DANGKY","id =? and code = ?", new String[]{String.valueOf(id),code});
        if(check == -1)
            return false;
        return true;
    }

    //trả về danh sách thông tin
    public ArrayList<ThongTin> getThongTinMonHoc(String code){
        ArrayList<ThongTin> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT date,address FROM THONGTIN WHERE code = ?", new String[]{code});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                list.add(new ThongTin(cursor.getString(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }

        return list;
    }
}