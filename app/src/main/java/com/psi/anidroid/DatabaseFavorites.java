package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseFavorites extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "Animes.db";
    private static final int DATABASE_VER = 1;

    private static final String TABLE_NAME = "favorites";
    private static final String FAV_ID = "fav_id";
    private static final String ANIME_ID = "anime_id";
    private static final String USER_ID = "user_id";

    ArrayList<String> id_User = new ArrayList<String>();
    //id's dos animes que o user deu fav
    ArrayList<String> id_Anime = new ArrayList<String>();

    int contador = 0;

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

    public void deleteAndCreateDB(){
        SQLiteDatabase db = this.getWritableDatabase();
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

        if (checkFavorite(anime_id)){
            Toast.makeText(context, "Unfavorited", Toast.LENGTH_SHORT).show();
        }else{
            long result = db.insert(TABLE_NAME,null, cv);
            if (result == -1){
                //Apresenta mensagem de erro
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else{
                //Aprensenta que conseguiu adicionar
                Toast.makeText(context, "Favorited!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkFavorite(String anime_id) {
        storeDatainArrays();
        for (String string : id_Anime) {
            contador++;
            if (anime_id.equals(string)){
                id_Anime.remove(contador-1);
                id_User.remove(contador-1);
                eraseDataandPopulate();
                return true; //se já estiver na lista de favoritos
            }
        }
        return false; //se ainda não estiver na lista de favoritos
    }

    private void eraseDataandPopulate() {
        deleteAndCreateDB();
        for (int i = 0; i < id_User.size(); i++) {
            addToNewFavorites(id_Anime.get(i), id_User.get(i));
        }
    }

    private void addToNewFavorites(String anime_id, String user_id) {
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

    void storeDatainArrays(){
        Cursor cursor = readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id_Anime.add(cursor.getString(1));
                id_User.add(cursor.getString(2));
            }
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "fav_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
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
