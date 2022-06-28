package com.psi.anidroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailsAnime extends AppCompatActivity {

    TextView nomeAnime, qntEpis, studioName, ratingAnime, sinopseAnime, genre1, genre2, linksAnime;
    ImageView fotoAnime,minus,plus;
    Button btnAddToFavorites, btnConfirmEps;
    EditText numEpis;

    String id, nome, epis, studio, rating, sinopse, imagem, links, user_id;

    //Vai ser caso o utilizador já tenho posto episódios vistos
    String episAtual;

    ArrayList<String> id_User = new ArrayList<String>();
    ArrayList<String> id_Anime = new ArrayList<String>();
    ArrayList<String> numEpisAtual = new ArrayList<String>();

    //dizer que o contexto é esta classe
    DatabaseFavorites database = new DatabaseFavorites(DetailsAnime.this);
    //dizer que o contexto é esta classe
    DatabaseEpisodes databaseEpis = new DatabaseEpisodes(DetailsAnime.this);

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
        numEpis = findViewById(R.id.numEpis);
        minus = findViewById(R.id.minusEp);
        plus = findViewById(R.id.plusEp);
        btnConfirmEps = findViewById(R.id.btn_confirm_eps);
        linksAnime = findViewById(R.id.linkD);


        getAndSetIntentData();



        //Se o user não está autenticado
        if (user_id.equals("30")){
            //Dita que o botão para adicionar aos favoritos fica invisivel
            btnAddToFavorites.setVisibility(View.INVISIBLE);
            minus.setVisibility(View.INVISIBLE);
            plus.setVisibility(View.INVISIBLE);
            btnConfirmEps.setVisibility(View.INVISIBLE);
            numEpis.setVisibility(View.INVISIBLE);
        }else{
            //Dita que o botão para adicionar aos favoritos fica visivel
            btnAddToFavorites.setVisibility(View.VISIBLE);
            minus.setVisibility(View.VISIBLE);
            plus.setVisibility(View.VISIBLE);
            btnConfirmEps.setVisibility(View.VISIBLE);
            numEpis.setVisibility(View.VISIBLE);
        }
        //Vai ver se o user atual tem o anime visualizado como favorito
        if(storeDatainArrays(database)){
            //Se estiver como favoritos muda o texto para o seguinte
            btnAddToFavorites.setText("Unadd to Favorites");
        }else{
            //Se não estiver como favoritos muda o texto para o seguinte
            btnAddToFavorites.setText("Add to Favorites");
        }

        if (userAnimeInEpisodes(databaseEpis)){
            numEpis.setText(episAtual);


        }

        linksAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl(links);
            }
        });

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

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMinus();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPlus();
            }
        });

        btnConfirmEps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseEpis.addToEpisodeWatchlist(id, user_id, numEpis.getText().toString(), qntEpis.getText().toString().substring(10));
            }
        });
    }


    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private boolean userAnimeInEpisodes(DatabaseEpisodes databaseEpis) {
        Cursor cursor = databaseEpis.readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                //Se o user atual é igual ao user que está na linha atual
                if (user_id.equals(cursor.getString(2)) && id.equals(cursor.getString(1))){
                    //tem como episódio
                    episAtual = cursor.getString(3);
                    return true;
                }
            }
        }
        return false;
    }

    private void checkPlus() {
        if (!qntEpis.getText().toString().substring(10).equals("?")){
            if (Integer.parseInt(numEpis.getText().toString()) >= Integer.parseInt(qntEpis.getText().toString().substring(10)) ){
                return;
            }else {
                int aux = Integer.parseInt(numEpis.getText().toString()) + 1;
                numEpis.setText(String.valueOf(aux));
            }
        }else {
            int aux = Integer.parseInt(numEpis.getText().toString()) + 1;
            numEpis.setText(String.valueOf(aux));
        }

    }

    private void checkMinus() {
        //tentar pôr ep. negativos
        if (numEpis.getText().toString().equals("1")){
            return;
        }else {
            int aux = Integer.parseInt(numEpis.getText().toString()) - 1;
            numEpis.setText(String.valueOf(aux));
        }
    }

    //Coloca nas TextViews o texto correto a cada um destes
    private void getAndSetIntentData(){
        //Verifica se foi enviado toda a informação sobre o anime
        if (getIntent().hasExtra("nome") && getIntent().hasExtra("epis") && getIntent().hasExtra("image") && getIntent().hasExtra("studio") && getIntent().hasExtra("rating") && getIntent().hasExtra("sinopse") && getIntent().hasExtra("links") && getIntent().hasExtra("id") && getIntent().hasExtra("idUser")){
            //Buscar os dados pelo Intent
            id = getIntent().getStringExtra("id");
            nome = getIntent().getStringExtra("nome");
            epis = getIntent().getStringExtra("epis");
            imagem = getIntent().getStringExtra("image");
            studio = getIntent().getStringExtra("studio");
            rating = getIntent().getStringExtra("rating");
            sinopse = getIntent().getStringExtra("sinopse");
            links = getIntent().getStringExtra("links");
            user_id = getIntent().getStringExtra("idUser");

            //Dar valores à activity
            nomeAnime.setText("Name: " + nome);
            qntEpis.setText("Episodes: " + epis);
            Glide.with(this).load(imagem).into(fotoAnime);
            studioName.setText("Studio: " + studio);
            ratingAnime.setText("Rating: " + rating);
            sinopseAnime.setText("Synopsis: " + sinopse);
            linksAnime.setText("Link: " + links);

            //categorias
            DBCategorias dbCategorias = new DBCategorias(DetailsAnime.this);
            //ir buscar as categorias com o ID do anime selecionado
            Cursor c_genres = dbCategorias.getGenre(Integer.parseInt(id));
            //se o cursor não estiver vazio
            if (c_genres.moveToFirst()){
                //genre1 = primeira categoria encontrada
                genre1.setText(c_genres.getString(0));
                c_genres.moveToNext();
                //genre2 = segunda categoria encontrada
                genre2.setText(c_genres.getString(0));
            }

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