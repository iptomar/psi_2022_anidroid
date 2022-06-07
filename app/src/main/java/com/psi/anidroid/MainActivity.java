package com.psi.anidroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult, tv_id;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    String user_id;
    EditText editText;

    private Button btnProfile, btnLogin, btnFavoritos, btnRegister, btnCheckUsers;

    DatabaseFavorites database = new DatabaseFavorites(MainActivity.this);
    DBCategorias dbCategorias = new DBCategorias(MainActivity.this);
    //boolean fillGenres = dbCategorias.addData();

    //id do anime
    ArrayList<String> idAnimeList = new ArrayList<>();
    //nome do anime
    ArrayList<String> nomeAnimeList = new ArrayList<>();
    //quantidade de episodios do anime
    ArrayList<String> qntEpisList = new ArrayList<>();
    //link da foto para o anime
    ArrayList<String> fotoAnimeList = new ArrayList<>();
    //nome do estudio
    ArrayList<String> studioAnimeList = new ArrayList<>();
    //rating do anime
    ArrayList<String> ratingAnimeList = new ArrayList<>();
    //sinopse do anime
    ArrayList<String> sinopseAnimeList = new ArrayList<>();
    //links do anime
    ArrayList<String> linksAnimeList = new ArrayList<>();
    //id do anime
    ArrayList<String> idAnimeListF = new ArrayList<>();
    //nome do anime
    ArrayList<String> nomeAnimeListF = new ArrayList<>();
    //quantidade de episodios do anime
    ArrayList<String> qntEpisListF = new ArrayList<>();
    //link da foto para o anime
    ArrayList<String> fotoAnimeListF = new ArrayList<>();
    //nome do estudio
    ArrayList<String> studioAnimeListF = new ArrayList<>();
    //rating do anime
    ArrayList<String> ratingAnimeListF = new ArrayList<>();
    //sinopse do anime
    ArrayList<String> sinopseAnimeListF = new ArrayList<>();
    //links do anime
    ArrayList<String> linksAnimeListF = new ArrayList<>();
    //id do user
    ArrayList<String> id_User = new ArrayList<>();
    //id's dos animes que o user deu fav
    ArrayList<String> id_Anime = new ArrayList<>();

    //Determina se já está na view dos favoritos, 0 se não estiver, 1 se estiver
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                getFilterdAnimes(editable.toString());
            }
        });

        tv_id = findViewById(R.id.tv_id);
        recyclerView = findViewById(R.id.recyclerView);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnFavoritos = (Button) findViewById(R.id.btnFavoritos);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //ir buscar o ID definido em login ou register
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            tv_id.setText(intent.getStringExtra("id"));
        }

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenProfile();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenLogin();
            }
        });

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchViewsFav();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRegister();
            }
        });

        //Vai buscar todos os valores da API
        getAllMidgetAPI();
        //Vai buscar o id do user atual, vendo o texto de uma TextView
        user_id = tv_id.getText().toString();
        //Se o utilizador não estiver autenticado
        if(user_id.equals("30")){
            btnFavoritos.setVisibility(View.INVISIBLE);
            btnProfile.setVisibility(View.INVISIBLE);
        }else{
            btnFavoritos.setVisibility(View.VISIBLE);
            btnProfile.setVisibility(View.VISIBLE);
        }
    }

    /*
    Vai mudar as views para de todos os animes para a lista de só os favoritos
     */
    private void switchViewsFav() {
        //Vai armazenar dentro dos arrays id_Anime e id_User, os valores que se encontram dentro da base de dados
        storeDatainArrays(database);

        //Se não está na lista dos favoritos
        if (count == 0){
            //Ordenar os ID's
            id_Anime.sort(Comparator.comparing(Double::parseDouble));
            //Para todos os animes
            for (String string: id_Anime) {
                    //Limpa os arrays
                    clearArray();
                    //Adiciona aos novos arrays, todos os valores de só os animes favoritos
                    idAnimeListF.add(string);
                    nomeAnimeListF.add(nomeAnimeList.get(Integer.parseInt(string) - 1));
                    qntEpisListF.add(qntEpisList.get(Integer.parseInt(string) - 1));
                    fotoAnimeListF.add(fotoAnimeList.get(Integer.parseInt(string) - 1));
                    studioAnimeListF.add(studioAnimeList.get(Integer.parseInt(string) - 1));
                    ratingAnimeListF.add(ratingAnimeList.get(Integer.parseInt(string) - 1));
                    sinopseAnimeListF.add(sinopseAnimeList.get(Integer.parseInt(string) - 1));
                    linksAnimeListF.add(linksAnimeList.get(Integer.parseInt(string) - 1));
            }

            //Se ainda não adicionou todos
            if (id_Anime.size() != idAnimeListF.size()){
                //Limpa de novo os arrays
                clearArray();
                for (String string: id_Anime) {
                    //Adiciona aos novos arrays, todos os valores de só os animes favoritos
                    idAnimeListF.add(string);
                    nomeAnimeListF.add(nomeAnimeList.get(Integer.parseInt(string) - 1));
                    qntEpisListF.add(qntEpisList.get(Integer.parseInt(string) - 1));
                    fotoAnimeListF.add(fotoAnimeList.get(Integer.parseInt(string) - 1));
                    studioAnimeListF.add(studioAnimeList.get(Integer.parseInt(string) - 1));
                    ratingAnimeListF.add(ratingAnimeList.get(Integer.parseInt(string) - 1));
                    sinopseAnimeListF.add(sinopseAnimeList.get(Integer.parseInt(string) - 1));
                    linksAnimeListF.add(linksAnimeList.get(Integer.parseInt(string) - 1));
                }
            }
            //Passa de novo para uma variável o id do user atual extraindo o texto da TextView
            user_id = tv_id.getText().toString();
            //Quer dizer que vai mudar para a lista de todos os animes
            count = 1;
            //Muda o texto dos botões
            btnFavoritos.setText("Unsee Favorites");
            //Cria-se um novo objeto do myAdapter para mudar a nossa MainActivity para só mostrar os animes com favoritos
            myAdapter = new MyAdapter(MainActivity.this, nomeAnimeListF,qntEpisListF,idAnimeListF,fotoAnimeListF,studioAnimeListF,ratingAnimeListF,sinopseAnimeListF,linksAnimeListF , user_id);
            //Torna parte da pesquisar por animes invisivel
            editText.setVisibility(View.INVISIBLE);
        }else{
            //Quer dizer que vai mudar para a lista dos favoritos
            count = 0;
            //Muda o texto do botão
            btnFavoritos.setText("See Favorites");
            //Cria-se um novo objeto do myAdapter para mudar a nossa MainActivity para só mostrar os animes com favoritos
            myAdapter = new MyAdapter(MainActivity.this, nomeAnimeList,qntEpisList,idAnimeList,fotoAnimeList,studioAnimeList,ratingAnimeList,sinopseAnimeList, linksAnimeList , user_id);
            //Torna parte da pesquisar por animes invisivel
            editText.setVisibility(View.VISIBLE);
        }
        //Muda a recyclerView
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager((MainActivity.this))));
    }

    //Limpar todos os arrayLists
    private void clearArray() {
        idAnimeListF.clear();
        nomeAnimeListF.clear();
        qntEpisListF.clear();
        fotoAnimeListF.clear();
        studioAnimeListF.clear();
        ratingAnimeListF.clear();
        sinopseAnimeListF.clear();
        linksAnimeListF.clear();
    }

    //Vai ler todos os valores dentro da database e guardar dentro dos ArrayLists
    private void storeDatainArrays(DatabaseFavorites database) {
        //limpa o arraylist
        id_Anime.clear();
        //limpa o arraylist
        id_User.clear();
        //Vai buscar o id do user atual
        user_id = tv_id.getText().toString();
        //Vai ler os valores da database
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                //Se o user atual é igual ao user que está na linha atual
                if (user_id.equals(cursor.getString(2))){
                    //foi feito com a razão de apenas colocar nos arrays os favoritos do user atual
                    id_Anime.add(cursor.getString(1));
                    id_User.add(cursor.getString(2));
                }

            }
        }
    }

    private void getFilterdAnimes(String text) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/rbento01/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();

        //id do anime
        ArrayList<String> idAnimeList = new ArrayList<String>();
        //nome do anime
        ArrayList<String> nomeAnimeList = new ArrayList<String>();
        //quantidade de episodios do anime
        ArrayList<String> qntEpisList = new ArrayList<String>();
        //link da foto para o anime
        ArrayList<String> fotoAnimeList = new ArrayList<String>();
        //nome do estudio
        ArrayList<String> studioAnimeList = new ArrayList<String>();
        //rating do anime
        ArrayList<String> ratingAnimeList = new ArrayList<String>();
        //sinopse do anime
        ArrayList<String> sinopseAnimeList = new ArrayList<String>();
        //links do anime
        ArrayList<String> linksAnimeList = new ArrayList<>();

        MidgetAPI midgetAPI = retrofit.create(MidgetAPI.class);
        Call<List<Anime>> call = midgetAPI.requestAllAnimes();

        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                //O retrofit automaticamente separa os objetos, para ficar um array de objetos
                List<Anime> animes = response.body();

                for (Anime anime : animes) {
                    if(anime.getNome().toLowerCase().contains(text.toLowerCase())) {
                        nomeAnimeList.add(anime.getNome());
                        idAnimeList.add(anime.getIdAnime().toString());
                        qntEpisList.add(anime.getQuantEpisodios());
                        fotoAnimeList.add(anime.getLinkFoto());
                        studioAnimeList.add(anime.getEstudio());
                        ratingAnimeList.add(anime.getRating().toString());
                        sinopseAnimeList.add(anime.getSinopse());
                        linksAnimeList.add(anime.getLinks());
                    }
                }
                myAdapter = new MyAdapter(MainActivity.this, nomeAnimeList,qntEpisList,idAnimeList,fotoAnimeList,studioAnimeList,ratingAnimeList,sinopseAnimeList, linksAnimeList , user_id);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager((new LinearLayoutManager((MainActivity.this))));
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {

            }
        });
    }

    void OpenProfile(){
        Intent intent_profile = new Intent(this, ProfileActivity.class);
        intent_profile.putExtra("id",tv_id.getText().toString());
        startActivity(intent_profile);
    }

     void OpenLogin(){
        Intent intent_login = new Intent(this, LoginActivity.class);
        startActivity(intent_login);
    }
    private void OpenRegister(){
        Intent intent_register = new Intent(this, RegisterActivity.class);
        startActivity(intent_register);
    }

    //Vai buscar a API do nosso projeto
    private void getAllMidgetAPI() {
        //Usamos o retrofit para conseguirmos ir buscar a APi
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/rbento01/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();
        //Criamos o objeto de acordo com uma interface onde se tem os GET's
        MidgetAPI midgetAPI = retrofit.create(MidgetAPI.class);
        //Pedimos para ir buscar todos os animes, ao qual esta variável ("call") vai ter todos os valores dos objetos JSON
        Call<List<Anime>> call = midgetAPI.requestAllAnimes();

        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                //O retrofit automaticamente separa os objetos, para ficar um array de objetos
                //Vai ter todos os valores dos objetos JSON
                List<Anime> animes = response.body();

                for (Anime anime : animes) {
                    //Adiciona nos ArrayLists
                    nomeAnimeList.add(anime.getNome());
                    idAnimeList.add(anime.getIdAnime().toString());
                    qntEpisList.add(anime.getQuantEpisodios());
                    fotoAnimeList.add(anime.getLinkFoto());
                    studioAnimeList.add(anime.getEstudio());
                    ratingAnimeList.add(anime.getRating().toString());
                    sinopseAnimeList.add(anime.getSinopse());
                    linksAnimeList.add(anime.getLinks());
                }
                //Vai buscar o id do User
                user_id = tv_id.getText().toString();
                //Vai mudar a vista do main activity para mostrar todos os animes
                myAdapter = new MyAdapter(MainActivity.this, nomeAnimeList,qntEpisList,idAnimeList,fotoAnimeList,studioAnimeList,ratingAnimeList,sinopseAnimeList, linksAnimeList, user_id);
                //Muda para essa vista
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager((new LinearLayoutManager((MainActivity.this))));
            }
            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
            }
        });
    }
}
