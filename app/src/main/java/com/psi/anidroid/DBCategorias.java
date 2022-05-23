package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBCategorias extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Categorias.db";
    private static final String TABLE_NAME = "Genre_table";
    private static final String ID = "id";
    private static final String ANIME_ID = "anime_id";
    private static final String CATEGORIA = "categoria";

    public DBCategorias(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Genre_table (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "anime_id INTEGER, " +
                "categoria STRING)");
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Cursor getGenre(int id_anime){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+CATEGORIA+" FROM " + TABLE_NAME + " WHERE " + ANIME_ID + "=" + id_anime, null);
        return cursor;
    }
}
