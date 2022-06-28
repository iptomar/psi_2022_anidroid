package com.psi.anidroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBLists extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "Listas.db";
    private static final int DATABASE_VER = 1;

    private static final String TABLE_NAME = "listas";
    private static final String LISTA_ID = "lista_id";
    private static final String NOME_LISTA = "nome_lista";
    private static final String ANIME_ID = "anime_id";
    private static final String USER_ID = "user_id";

    private static String user_id;

    ArrayList<String> id_User_List = new ArrayList<String>();
    //id's dos animes que o user deu fav
    ArrayList<String> id_Anime_List = new ArrayList<String>();

    ArrayList<String> id_lista_List = new ArrayList<String>();

    ArrayList<String> nome_lista_List = new ArrayList<String>();

    int contador = 0;

    public DBLists(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + LISTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ANIME_ID + " TEXT, " +
                USER_ID + " TEXT, " +
                NOME_LISTA + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void updateList (String lista_id, String anime_id, String user_id, String nome_lista ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME +
                "SET lista_id='" + lista_id +"', nome_lista='"+ nome_lista
                + "' WHERE (anime_id='"+ anime_id + "' AND user_id='"+ user_id+"';");
    }

    public void deleteRow(String id_lista, String id_anime, String id_user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + "WHERE lista_id=" + id_lista +
                                                "AND anime_id=" + id_anime +
                                                "AND user_id=" + id_user);
    }

    public void deleteAndCreateDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addToList(String lista_id, String anime_id, String user_id, String nome_lista){
        //O this.getWritableDatabase(); faz com que tenhamos a base de dados criada, dentro de uma variável
        //this.getWritableDatabase() obtém a base de dados criada (do extends SQLiteOpenHelper)
        DBLists.user_id = user_id;


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LISTA_ID, lista_id);
        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);
        cv.put(NOME_LISTA, nome_lista);

        if (checkList(anime_id)){
            Toast.makeText(context, "Already on the list", Toast.LENGTH_SHORT).show();
        }else{
            long result = db.insert(TABLE_NAME,null, cv);
            if (result == -1){
                //Apresenta mensagem de erro
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }else{
                //Aprensenta que conseguiu adicionar
                Toast.makeText(context, "Added to the list!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected boolean checkList(String anime_id) {
        storeDatainArrays();
        for (String string : id_Anime_List) {
            int aux = contador;
            if (anime_id.equals(string) && user_id.equals(id_User_List.get(aux))){
                return true; //se já estiver na lista de favoritos
            }
            contador++;
        }
        return false; //se ainda não estiver na lista de favoritos
    }

    private void eraseDataandPopulate() {
        deleteAndCreateDB();
        for (int i = 0; i < id_User_List.size(); i++) {
            addToNewList(id_lista_List.get(i),id_Anime_List.get(i), id_User_List.get(i), nome_lista_List.get(i));
        }
    }

    private void addToNewList(String lista_id, String anime_id, String user_id, String nome_lista) {
        //O this.getWritableDatabase(); faz com que tenhamos a base de dados criada, dentro de uma variável
        //this.getWritableDatabase() obtém a base de dados criada (do extends SQLiteOpenHelper)
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LISTA_ID, lista_id);
        cv.put(ANIME_ID, anime_id);
        cv.put(USER_ID, user_id);
        cv.put(NOME_LISTA, nome_lista);

        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1){
            //Apresenta mensagem de erro
            //Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            //Aprensenta que conseguiu adicionar
            //Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
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
                id_Anime_List.add(cursor.getString(1));
                id_User_List.add(cursor.getString(2));
                nome_lista_List.add(cursor.getString(3));
            }
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "lista_id=?", new String[]{row_id});
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
