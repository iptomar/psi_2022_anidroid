package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public abstract class DBUsers extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "User_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";


    public DBUsers(Context context){
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create TABLE " + TABLE_NAME + "(ID INT PRIMARY KEY AUTOINCREMENT, Username TEXT, Email TEXT, Password TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, username);
        values.put(COL_3, email);
        values.put(COL_4, password);
        long result = db.insert(TABLE_NAME,null, values);
        if (result==-1){
            return false;
        }
        return true;
    }
    public boolean updateUser(String username, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, username);
        values.put(COL_3, email);
        long result = db.update(TABLE_NAME,values,"name?", new String[]{username});
        if (result==-1){
            return false;
        }
        return true;
    }
    public Cursor getUsername(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT " + COL_2 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);
            return cursor;
    }
    public Cursor getEmail(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);
        return cursor;
    }
    public Cursor getPassword(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_4 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);
        return cursor;
    }
}
