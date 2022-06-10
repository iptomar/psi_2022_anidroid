package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBComentarios extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Comentarios.db";
    private static final String TABLE_NAME = "Comment_table";
    private static final String ID = "id";
    private static final String ANIME_ID = "anime_id";
    private static final String USER_ID = "user_id";
    private static final String COMMENT = "comment";

    public DBComentarios(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Comment_table (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "anime_id INTEGER, " + "user_id INTEGER, " +
                "comment STRING)");
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addComment(int anime_id, int user_id, String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ANIME_ID, anime_id);
        values.put(USER_ID, user_id);
        values.put(COMMENT, comment);
        long result = db.insert(TABLE_NAME,null, values);
        db.close();
        return result != -1;
    }
    public void deleteComment(int anime_id, int user_id, String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Comment_table WHERE anime_id=" + anime_id + " AND user_id=" + user_id + " AND comment='" + comment + "'");
    }
    public Cursor getCommentsbyAnime(int anime_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+COMMENT+" FROM " + TABLE_NAME + " WHERE " + ANIME_ID + "=" + anime_id, null);
        return cursor;
    }
    public Cursor getUserbyComment(int anime_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+USER_ID+" FROM " + TABLE_NAME + " WHERE " + ANIME_ID + "=" + anime_id, null);
        return cursor;
    }
}
