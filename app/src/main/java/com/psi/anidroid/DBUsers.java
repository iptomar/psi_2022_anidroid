package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBUsers extends SQLiteOpenHelper {
    public static final String DB_NAME = "User.db";
    public static final String TABLE_NAME = "User_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "Roles";


    public DBUsers(Context context){
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE User_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Username STRING UNIQUE, " +
                "Email STRING, " +
                "Password STRING,  " +
                "Roles STRING)");
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
        values.put(COL_4, UserModel.getPassword());
        values.put(COL_5, UserModel.getRoles());
        long result = db.insert(TABLE_NAME,null, values);
        db.close();
        return result != -1;
    }
    public boolean updateUser(String id, String username, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, username);
        values.put(COL_3, email);
        long result = db.update(TABLE_NAME,values,"id=?", new String[]{id});
        return result != -1;
    }

    public boolean updatePass(String id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_4, password);
        long result = db.update(TABLE_NAME,values,"id=?", new String[]{id});
        return result != -1;
    }

    public boolean deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        return result != -1;
    }



    public Cursor checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select "+COL_1+" FROM " + TABLE_NAME + " WHERE " + COL_2 + " = " + username + " AND " + COL_4 + " = " + password, null);
        return cursor;
    }
    public Cursor getIdByUserPass(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " LIKE '%" + username + "%' AND " + COL_4 + " LIKE '%" + password + "%'", null);
        return cursor;
    }

    public Cursor getMaxID(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT MAX(" + COL_1 + ") FROM " + TABLE_NAME, null);
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

    public Cursor getRoles(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_5 + " FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + id, null);
        return cursor;
    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
