package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseFavorites extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Animes.db";
    private static final int DATABASE_VER = 1;

    private static final String TABLE_NAME = "favorites";
    private static final String FAV_ID = "fav_id";
    private static final String ANIME_ID = "anime_id";
    private static final String USER_ID = "user_id";

    public DatabaseFavorites(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + FAV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ANIME_ID + " TEXT, " +
                USER_ID + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addToFavorites(String anime_id, String user_id){
        //O this.getWritableDatabase(); faz com que tenhamos a base de dados criada, dentro de uma variável
        //this.getWritableDatabase() obtém a base de dados criada (do extends SQLiteOpenHelper)
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);
        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1){
            //Apresenta mensagem de erro
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            //Aprensenta que conseguiu adicionar
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
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

    /*void updateData(String anime_id, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});

        if (result == -1){
            //Apresenta mensagem de erro
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            //Aprensenta que conseguiu adicionar
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }
    }*/
}
