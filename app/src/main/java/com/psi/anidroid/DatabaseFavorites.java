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
    //contexto da classe
    private final Context context;
    //nome da base de dados
    private static final String DATABASE_NAME = "Animes.db";
    //versão da base de dados
    private static final int DATABASE_VER = 1;
    //nome da base de dados
    private static final String TABLE_NAME = "favorites";
    //nome de uma coluna da base de dados
    private static final String FAV_ID = "fav_id";
    //nome de uma coluna da base de dados
    private static final String ANIME_ID = "anime_id";
    //nome de uma coluna da base de dados
    private static final String USER_ID = "user_id";
    //id do user atual
    private static String user_id;

    //id dos users dentro da base de dados
    ArrayList<String> id_User = new ArrayList<String>();
    //id's dos animes que o user deu fav
    ArrayList<String> id_Anime = new ArrayList<String>();
    //contador para ver o índice
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
        DatabaseFavorites.user_id = user_id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //insere dentro da base de dados os valores enviados em parâmetro
        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);
        //Vai verificar se já tem os valores dentro  da base de dados
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
        //Vai armazenar dentro dos arrayLists os valores dentro da base de dados
        storeDatainArrays();
        for (String string : id_Anime) {
            int aux = contador;
            //Se o user atual e o anime atual já estão na base de dados, quer dizer que o utilizador quer retirar
            if (anime_id.equals(string) && user_id.equals(id_User.get(aux))){
                id_Anime.remove(contador);
                id_User.remove(contador);
                eraseDataandPopulate();
                return true; //se já estiver na lista de favoritos
            }
            contador++;
        }
        return false; //se ainda não estiver na lista de favoritos
    }

    //Apaga a base de dados e coloca nesta todos os valores posteriormente a apagar a linha
    //Esta linha vai ser a referida que foi verificada no método checkFavorite, ao qual é
    //Se e o user atual e o anime atual já estão na base de dados, quer dizer que o utilizador quer retirar
    //Apaga-se esses valores dos Arrays, e coloca-se a nova informação dentro da base de dados
    private void eraseDataandPopulate() {
        //Apaga e cria uma nova tabela
        deleteAndCreateDB();
        //Vai adicionar na tabela os novos valores dos Arrays
        for (int i = 0; i < id_User.size(); i++) {
            addToNewFavorites(id_Anime.get(i), id_User.get(i));
        }
    }

    private void addToNewFavorites(String anime_id, String user_id) {
        //O this.getWritableDatabase(); faz com que tenhamos a base de dados criada, dentro de uma variável
        //this.getWritableDatabase() obtém a base de dados criada (do extends SQLiteOpenHelper)
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //Adiciona na base de dados
        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);
        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1){
            //Apresenta mensagem de erro
            //Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            //Aprensenta que conseguiu adicionar
            //Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    //Vai ler todos os valores da base de dados
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //Vai armazenar dentro dos arrays todos os valores da base de dadods
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

}
