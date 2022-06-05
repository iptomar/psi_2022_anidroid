package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseEpisodes extends SQLiteOpenHelper {
    //contexto da classe
    private final Context context;
    //nome da base de dados
    private static final String DATABASE_NAME = "Episodes.db";
    //versão da base de dados
    private static final int DATABASE_VER = 1;
    //nome da base de dados
    private static final String TABLE_NAME = "Episodes";
    //nome de uma coluna da base de dados
    private static final String EPISODE_ID = "epi_id";
    //nome de uma coluna da base de dados
    private static final String ANIME_ID = "anime_id";
    //nome de uma coluna da base de dados
    private static final String USER_ID = "user_id";
    //número do episódio
    private static final String NUM_EPIS = "num_epis";
    //id do user atual
    private static String user_id;

    //id dos users dentro da base de dados
    ArrayList<String> id_User = new ArrayList<String>();
    //id's dos animes que o user deu fav
    ArrayList<String> id_Anime = new ArrayList<String>();
    //contador para ver o índice
    int contador = 0;

    public DatabaseEpisodes(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + EPISODE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ANIME_ID + " TEXT, " +
                USER_ID + " TEXT, " +
                NUM_EPIS + " TEXT);";
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

    void addToEpisodeWatchlist(String anime_id, String user_id, String num_epis, String failsafe){
        //O this.getWritableDatabase(); faz com que tenhamos a base de dados criada, dentro de uma variável
        //this.getWritableDatabase() obtém a base de dados criada (do extends SQLiteOpenHelper)
        DatabaseEpisodes.user_id = user_id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //insere dentro da base de dados os valores enviados em parâmetro
        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);
        cv.put(NUM_EPIS, num_epis);
        //Se a quantidade de episódios não for indefinida
        if (!failsafe.equals("?")){
            if (Integer.parseInt(num_epis) > Integer.parseInt(failsafe)){
                Toast.makeText(context, "EPISODE TOO HIGH", Toast.LENGTH_SHORT).show();
            }else{
                if (checkUserAnime(anime_id, num_epis)){
                    Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                }else{
                    long result = db.insert(TABLE_NAME,null, cv);
                    if (result == -1){
                        //Apresenta mensagem de erro
                        Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                    }else{
                        //Aprensenta que conseguiu adicionar
                        Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }else{
            if (checkUserAnime(anime_id, num_epis)){
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
            }else{
                long result = db.insert(TABLE_NAME,null, cv);
                if (result == -1){
                    //Apresenta mensagem de erro
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                }else{
                    //Aprensenta que conseguiu adicionar
                    Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    private boolean checkUserAnime(String anime_id, String numEpis) {
        //Vai armazenar dentro dos arrayLists os valores dentro da base de dados
        storeDatainArrays();
        for (String string : id_Anime) {
            int aux = contador;
            //Se o user atual e o anime atual já estão na base de dados, quer dizer que o utilizador quer retirar
            if (anime_id.equals(string) && user_id.equals(id_User.get(aux))){
                updateData(anime_id, user_id, numEpis);
                return true; //se já estiver na lista de favoritos
            }
            contador++;
        }
        return false; //se ainda não estiver na lista de favoritos
    }

    void updateData(String anime_id, String user_id, String numEpis) {
        SQLiteDatabase db = this.getWritableDatabase();

        String strSQL = "UPDATE " + TABLE_NAME + " SET " + NUM_EPIS + " = " + "\"" + numEpis + "\"" +  " WHERE " + ANIME_ID + " = "+ anime_id + " AND " + USER_ID + " = " + user_id;
        System.out.println(strSQL);

        db.execSQL(strSQL);
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
