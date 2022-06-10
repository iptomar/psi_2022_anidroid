package com.psi.anidroid;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailsAnime extends AppCompatActivity{

    TextView nomeAnime, qntEpis, studioName, ratingAnime, sinopseAnime, genre1, genre2;
    ImageView fotoAnime;
    Button btnAddToFavorites;
    Button btnComentarios;

    String id, nome, epis, studio, rating, sinopse, imagem, user_id;

    ArrayList<String> id_User = new ArrayList<String>();
    ArrayList<String> id_Anime = new ArrayList<String>();

    ArrayList<String> id_User1 = new ArrayList<String>();
    ArrayList<String> id_Anime1 = new ArrayList<String>();

    //dizer que o contexto é esta classe
    DatabaseFavorites database = new DatabaseFavorites(DetailsAnime.this);

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
        btnComentarios = findViewById(R.id.comments);

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
        btnComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openComment();
            }
        });
    }
    private void openComment(){
        Intent intent_comment = new Intent(this, ComentariosAnime.class);
        intent_comment.putExtra("Anime_ID", id+"");
        intent_comment.putExtra("User_ID", user_id+"");
        startActivity(intent_comment);
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

