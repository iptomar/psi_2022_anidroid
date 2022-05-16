package com.psi.anidroid;

import android.content.Context;
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

public class DetailsAnime extends AppCompatActivity {

    TextView nomeAnime, qntEpis, studioName, ratingAnime, sinopseAnime;
    ImageView fotoAnime;
    Button btnAddToFavorites;

    String id, nome, epis, studio, rating, sinopse, imagem;

    ArrayList<String> id_User = new ArrayList<String>();
    ArrayList<String> id_Anime = new ArrayList<String>();

    //Para verificar se o anime está com fav ou não, 0 se não, 1 se sim
    int count;

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

        getAndSetIntentData();

        btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 0){
                    btnAddToFavorites.setText("Unadd to Favorites");
                    count = 1;
                }else{
                    btnAddToFavorites.setText("Add to Favorites");
                    count = 0;
                }

                database.addToFavorites(id, "teste");
                displayData();
            }
        });
    }

    private void displayData() {
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id_Anime.add(cursor.getString(1));
                id_User.add(cursor.getString(2));
            }
        }
    }


    private void getAndSetIntentData(){
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("epis") && getIntent().hasExtra("image") && getIntent().hasExtra("studio") && getIntent().hasExtra("rating") && getIntent().hasExtra("sinopse") && getIntent().hasExtra("id")){
            //Buscar os dados pelo Intent
            id = getIntent().getStringExtra("id");
            System.out.println(id);
            nome = getIntent().getStringExtra("nome");
            epis = getIntent().getStringExtra("epis");
            imagem = getIntent().getStringExtra("image");
            studio = getIntent().getStringExtra("studio");
            rating = getIntent().getStringExtra("rating");
            sinopse = getIntent().getStringExtra("sinopse");

            //Dar valores à activity
            nomeAnime.setText("Name: " + nome);
            qntEpis.setText("Episodes: " + epis);
            Glide.with(this).load(imagem).into(fotoAnime);
            studioName.setText("Studio: " + studio);
            ratingAnime.setText("Rating: " + rating);
            sinopseAnime.setText("Synopsis: " + sinopse);

        }else{
            Toast.makeText(this, "ERROR!!!!", Toast.LENGTH_SHORT).show();
        }
    }
}

