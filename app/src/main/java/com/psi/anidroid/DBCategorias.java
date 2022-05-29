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
        //inserir as categorias por anime na tabela Genre_table
        String query = "INSERT INTO Genre_table (anime_id, categoria) VALUES (1, 'Action'), (1, 'Shonen'), " +
                "(2, 'Shonen'), (2, 'Comedy'), (3, 'Action'), (3, 'Adult'), (4, 'Action'), (4, 'Shonen'), " +
                "(5, 'School life'), (5, 'Drama'), (6, 'Slice of life'), (6, 'Comedy'), (7, 'Action'), (7, 'Thriller'), " +
                "(8, 'Action'), (8, 'Comedy'), (9, 'Romance'), (9, 'Adult'), (10, 'Action'), (10, 'Dark Comedy'), " +
                "(11, 'Horror'), (11, 'Adult'), (12, 'Action'), (12, 'Drama'), (13, 'School life'), (13, 'Drama'), " +
                "(14, 'Adult'), (14, 'Psychological horror'), (15, 'Action'), (15, 'Romance'), (16, 'Action'), (16, 'Drama'), " +
                "(17, 'Shonen'), (17, 'Comedy'), (18, 'Romance'), (18, 'Adult'), (19, 'Action'), (19, 'Shonen'), " +
                "(20, 'Romance'), (20, 'Comedy'), (21, 'Romance'), (21, 'Slice of life'), (22, 'Action'), (22, 'Shonen'), " +
                "(23, 'Horror'), (23, 'Dark Comedy'), (24, 'Experimental'), (24, 'Abstract horror'), (25, 'Action'), (25, 'Drama'), " +
                "(26, 'Shonen'), (26, 'Comedy'), (27, 'Action'), (27, 'Drama'), (28, 'Romance'), (28, 'School life'), " +
                "(29, 'Action'), (29, 'Adult')";
        //criar a tabela Genre_table
        db.execSQL("CREATE TABLE Genre_table (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "anime_id INTEGER, " +
                "categoria STRING)");
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //devolve as categorias correspondentes ao ID de anime encontrado no parâmetro
    public Cursor getGenre(int id_anime){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+CATEGORIA+" FROM " + TABLE_NAME + " WHERE " + ANIME_ID + "=" + id_anime, null);
        return cursor;
    }
}
