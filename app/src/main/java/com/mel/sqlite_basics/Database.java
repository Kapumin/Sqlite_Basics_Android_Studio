package com.mel.sqlite_basics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String dbName = "student.db";
    private static final String TABLE_NAME = "student_table";
    private static final String col1 = "id";
    private static final String col2 = "firstName";
    private static final String col3 = "lastName";
    private static final String col4 = "Marks";


    public Database(@Nullable Context context) {
        super(context, dbName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME + " (id integer primary key autoincrement ,  firstName text, lastName text,Marks Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table  if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public boolean Insert(String FirstName, String LastName, String marks) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, FirstName);
        contentValues.put(col3, LastName);
        contentValues.put(col4, marks);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor ViewAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean update(String id, String fname, String lname, String mark) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, id);
        contentValues.put(col2, fname);
        contentValues.put(col3, lname);
        contentValues.put(col4, mark);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;

    }

    public Integer delete(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

}

