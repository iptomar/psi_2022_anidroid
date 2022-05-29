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

    String id, nome, epis, studio, rating, sinopse, imagem, user_id;

    ArrayList<String> id_User = new ArrayList<String>();
    ArrayList<String> id_Anime = new ArrayList<String>();

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
        //Coloca-se nas TextViews o texto correto
        getAndSetIntentData();

        //Se o user não está autenticado
        if (user_id.equals("30")){
            //Dita que o botão para adicionar aos favoritos fica invisivel
            btnAddToFavorites.setVisibility(View.INVISIBLE);
        }else{
            //Dita que o botão para adicionar aos favoritos fica visivel
            btnAddToFavorites.setVisibility(View.VISIBLE);
        }
        //Vai ver se o user atual tem o anime visualizado como favorito
        if(storeDatainArrays(database)){
            //Se estiver como favoritos muda o texto para o seguinte
            btnAddToFavorites.setText("Unadd to Favorites");
        }else{
            //Se não estiver como favoritos muda o texto para o seguinte
            btnAddToFavorites.setText("Add to Favorites");
        }



        btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Vai alternando o valor do texto do botão cada vez que o user clique neste botão
                if (btnAddToFavorites.getText().toString().equals("Unadd to Favorites")){
                    btnAddToFavorites.setText("Add to Favorites");
                }else{
                    btnAddToFavorites.setText("Unadd to Favorites");
                }
                //Finalmente adiciona à base de dados o id do user e o id do anime
                database.addToFavorites(id, user_id);
            }
        });
    }
    //Coloca nas TextViews o texto correto a cada um destes
    private void getAndSetIntentData(){
        //Verifica se foi enviado toda a informação sobre o anime
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

        }else{
            Toast.makeText(this, "ERROR!!!!", Toast.LENGTH_SHORT).show();
        }
    }
    //Coloca dentro dos arrays os valores dentro d abase de dados
    private Boolean storeDatainArrays(DatabaseFavorites database) {
        id_Anime.clear();
        id_User.clear();
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