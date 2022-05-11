package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DBUsers extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "User_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";


    public DBUsers(Context context){
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE User_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT UNIQUE, Email TEXT, Password TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertUser(UserModel UserModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, UserModel.getUsername());
        values.put(COL_3, UserModel.getEmail());
        values.put(COL_4, UserModel.getPassword());
        long result = db.insert(TABLE_NAME,null, values);
        db.close();
        if (result==-1){
            return false;
        }
        return true;
    }
    public boolean updateUser(String id, String username, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, username);
        values.put(COL_3, email);
        long result = db.update(TABLE_NAME,values,"id=?", new String[]{id});
        if (result==-1){
            return false;
        }
        return true;
    }

    public boolean updatePass(String id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_4, password);
        long result = db.update(TABLE_NAME,values,"id=?", new String[]{id});
        if (result==-1){
            return false;
        }
        return true;
    }
    public Cursor getID(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = " + username, null);
        return cursor;
    }

    public Cursor getUsername(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT " + COL_2 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);

        return cursor;
    }
    public Cursor getEmail(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_3 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);

        return cursor;
    }
    public Cursor getPassword(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_4 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);
        return cursor;
    }
}
