package com.psi.anidroid;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DetailsAnime extends AppCompatActivity {

    String[] WatchListCategories = {"No List", "Watching", "Completed", "To Watch"};

    TextView nomeAnime, qntEpis, studioName, ratingAnime, sinopseAnime, genre1, genre2;
    ImageView fotoAnime;
    Button btnAddToFavorites;
    Spinner spinner;

    String id, nome, epis, studio, rating, sinopse, imagem, user_id;

    ArrayList<String> id_User = new ArrayList<String>();
    ArrayList<String> id_Anime = new ArrayList<String>();

    ArrayList<String> id_User1 = new ArrayList<String>();
    ArrayList<String> id_Anime1 = new ArrayList<String>();

    ArrayList<String> id_UserL = new ArrayList<String>();
    ArrayList<String> id_AnimeL = new ArrayList<String>();

    //dizer que o contexto é esta classe
    DatabaseFavorites database = new DatabaseFavorites(DetailsAnime.this);

    DBLists databaseLists = new DBLists(DetailsAnime.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_anime);

        nomeAnime = findViewById(R.id.nomeAnimeD);
        qntEpis = findViewById(R.id.qntEpisD);
        studioName = findViewById(R.id.nomeStudioD);
        ratingAnime = findViewById(R.id.ratingAnimeD);
        sinopseAnime = findViewById(R.id.sinopseAnimeD);
        fotoAnime = findViewById(R.id.fotoAnimeD);
        btnAddToFavorites = findViewById(R.id.add_favoritos);
        genre1 = findViewById(R.id.Genre1);
        genre2 = findViewById(R.id.Genre2);

        spinner = findViewById(R.id.spinnerList);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, WatchListCategories);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        getAndSetIntentData();

        if (user_id.equals("30")){
            btnAddToFavorites.setVisibility(View.INVISIBLE);
        }else{
            btnAddToFavorites.setVisibility(View.VISIBLE);
        }
        displayData();

        if(storeDatainArrays(database)){
            btnAddToFavorites.setText("Unadd to Favorites");
        }else{
            btnAddToFavorites.setText("Add to Favorites");
        }



        btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnAddToFavorites.getText().toString().equals("Unadd to Favorites")){
                    btnAddToFavorites.setText("Add to Favorites");
                }else{
                    btnAddToFavorites.setText("Unadd to Favorites");
                }

                database.addToFavorites(id, user_id);
                displayData();
            }
        });

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long ID) {

                        String id_lista = String.valueOf(pos);
                        Object item = parent.getItemAtPosition(pos);

                        if(item == "No List"){
                            if(databaseLists.checkList(id)){
                                databaseLists.deleteRow(id_lista, id, user_id);
                            }
                            //databaseLists.addToList(id_lista, id, user_id, "No List");
                        }else if(item == "Watching"){
                            if(databaseLists.checkList(id)){
                                databaseLists.deleteRow(id_lista, id, user_id);
                            }
                            databaseLists.addToList(id_lista, id, user_id, "Watching");
                        }else if(item == "Completed"){
                            if(databaseLists.checkList(id)){
                                databaseLists.deleteRow(id_lista, id, user_id);
                            }
                            databaseLists.addToList(id_lista, id, user_id, "Completed");
                        }else {
                            if(databaseLists.checkList(id)){
                                databaseLists.deleteRow(id_lista, id, user_id);
                            }
                            databaseLists.addToList(id_lista, id, user_id, "To Watch");
                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    private void displayData() {
        id_Anime1.clear();
        id_User1.clear();
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "1st Favorite", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id_Anime1.add(cursor.getString(1));
                id_User1.add(cursor.getString(2));
            }
        }
    }


    private void getAndSetIntentData(){
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("epis") && getIntent().hasExtra("image") && getIntent().hasExtra("studio") && getIntent().hasExtra("rating") && getIntent().hasExtra("sinopse") && getIntent().hasExtra("id") && getIntent().hasExtra("idUser")){
            //Buscar os dados pelo Intent
            id = getIntent().getStringExtra("id");
            System.out.println(id);
            nome = getIntent().getStringExtra("nome");
            epis = getIntent().getStringExtra("epis");
            imagem = getIntent().getStringExtra("image");
            studio = getIntent().getStringExtra("studio");
            rating = getIntent().getStringExtra("rating");
            sinopse = getIntent().getStringExtra("sinopse");
            user_id = getIntent().getStringExtra("idUser");

            //Dar valores à activity
            nomeAnime.setText("Name: " + nome);
            qntEpis.setText("Episodes: " + epis);
            Glide.with(this).load(imagem).into(fotoAnime);
            studioName.setText("Studio: " + studio);
            ratingAnime.setText("Rating: " + rating);
            sinopseAnime.setText("Synopsis: " + sinopse);

            //categorias
            DBCategorias dbCategorias = new DBCategorias(DetailsAnime.this);
            Cursor c_genres = dbCategorias.getGenre(Integer.parseInt(id));
            if (c_genres.moveToFirst()){
                genre1.setText(c_genres.getString(0));
                c_genres.moveToNext();
                genre2.setText(c_genres.getString(0));
            }

        }else{
            Toast.makeText(this, "ERROR!!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean storeDatainArrays(DatabaseFavorites database) {
        id_Anime.clear();
        id_User.clear();
        String userid_tab;
        String animeid_tab;
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                //Se o user atual é igual ao user que está na linha atual
                if (user_id.equals(cursor.getString(2)) && id.equals(cursor.getString(1))){
                    //tem como favorito
                    return true;
                }
            }
        }
        return false;
    }
}

