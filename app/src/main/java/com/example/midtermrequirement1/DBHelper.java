package com.example.midtermrequirement1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails (student TEXT primary key, course Text, address Text, phone Text, email Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String student, String course, String address, String phone, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("student", student);
        contentValues.put("course", course);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        long result=DB.insert("Userdetails", null, contentValues);

        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateuserdata(String student, String course, String address, String phone, String email) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("course", course);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where student = ?", new String[]{student});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "student=?", new String[]{student});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Boolean deletedata(String student) {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery( "Select * from Userdetails where student = ?", new String[]{student});
            if(cursor.getCount()>0) {
                long result = DB.delete("Userdetails", "student=?", new String[]{student});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }

            }else
            {
                return false;
            }

    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery( "Select * from Userdetails", null);
        return cursor;

    }
}
