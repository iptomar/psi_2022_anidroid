package com.psi.anidroid;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailsAnime extends AppCompatActivity {

    TextView nomeAnime, qntEpis, studioName, ratingAnime, sinopseAnime;
    ImageView fotoAnime;

    String nome, epis, studio, rating, sinopse, imagem;

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

        getAndSetIntentData();
    }

    private void getAndSetIntentData(){
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("epis") && getIntent().hasExtra("image") && getIntent().hasExtra("studio") && getIntent().hasExtra("rating") && getIntent().hasExtra("sinopse")){
            //Buscar os dados pelo Intent
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

