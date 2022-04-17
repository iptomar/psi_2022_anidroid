package com.psi.anidroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public void onUpdate(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
