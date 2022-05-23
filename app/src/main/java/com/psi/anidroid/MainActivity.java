package com.psi.anidroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult, tv_id;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    String user_id;

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
    //id do user
    ArrayList<String> id_User = new ArrayList<>();
    //id's dos animes que o user deu fav
    ArrayList<String> id_Anime = new ArrayList<>();


    //Determina se já está na view dos favoritos, 0 se não estiver, 1 se estiver
    int count = 0;

    /*private ActivityMainBinding binding;
    RecyclerView recyclerView;
    //teste
    FloatingActionButton add_button;
    String teste;

    MyDatabaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //textViewResult = findViewById(R.id.text_view_result);

        //Gson gson = new GsonBuilder().serializeNulls().create(); //alterar o comportamento do gson, para meter mesmo os valores nulos no telemovel

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(*//*gson*//*)) //dizer que queremos usar o gson para os pedidos
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);*/

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


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(gson)) //dizer que queremos usar o gson para os pedidos
                .build();*/

        //getPosts();
        //getComments();
        //createPost();
        //updatePost();
        //deletePost();

        //getSampleJsonResponse();
        //getMidgetAPI();
        getAllMidgetAPI();

        user_id = tv_id.getText().toString();
        //Se o utilizador não estiver autenticado
        if(user_id.equals("30")){
            btnFavoritos.setVisibility(View.INVISIBLE);
            btnProfile.setVisibility(View.INVISIBLE);
        }else{
            btnFavoritos.setVisibility(View.VISIBLE);
            btnProfile.setVisibility(View.VISIBLE);
        }

        //getAnime();

        /*recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, book_id, book_title, book_author, book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }*/
    }

    private void switchViewsFav() {
        storeDatainArrays(database);

        if (count == 0){
            //Ordenar os ID's
            id_Anime.sort(Comparator.comparing(Double::parseDouble));
            for (String string: id_Anime) {
                    clearArray();
                    idAnimeListF.add(string);
                    nomeAnimeListF.add(nomeAnimeList.get(Integer.parseInt(string) - 1));
                    qntEpisListF.add(qntEpisList.get(Integer.parseInt(string) - 1));
                    fotoAnimeListF.add(fotoAnimeList.get(Integer.parseInt(string) - 1));
                    studioAnimeListF.add(studioAnimeList.get(Integer.parseInt(string) - 1));
                    ratingAnimeListF.add(ratingAnimeList.get(Integer.parseInt(string) - 1));
                    sinopseAnimeListF.add(sinopseAnimeList.get(Integer.parseInt(string) - 1));
            }

            if (id_Anime.size() != idAnimeListF.size()){
                clearArray();
                for (String string: id_Anime) {
                    idAnimeListF.add(string);
                    nomeAnimeListF.add(nomeAnimeList.get(Integer.parseInt(string) - 1));
                    qntEpisListF.add(qntEpisList.get(Integer.parseInt(string) - 1));
                    fotoAnimeListF.add(fotoAnimeList.get(Integer.parseInt(string) - 1));
                    studioAnimeListF.add(studioAnimeList.get(Integer.parseInt(string) - 1));
                    ratingAnimeListF.add(ratingAnimeList.get(Integer.parseInt(string) - 1));
                    sinopseAnimeListF.add(sinopseAnimeList.get(Integer.parseInt(string) - 1));
                }
            }

            user_id = tv_id.getText().toString();
            count = 1;
            btnFavoritos.setText("Unsee Favorites");

            myAdapter = new MyAdapter(MainActivity.this, nomeAnimeListF,qntEpisListF,idAnimeListF,fotoAnimeListF,studioAnimeListF,ratingAnimeListF,sinopseAnimeListF,user_id);

        }else{
            count = 0;
            btnFavoritos.setText("See Favorites");
            myAdapter = new MyAdapter(MainActivity.this, nomeAnimeList,qntEpisList,idAnimeList,fotoAnimeList,studioAnimeList,ratingAnimeList,sinopseAnimeList, user_id);

        }
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager((MainActivity.this))));
    }

    private void clearArray() {
        idAnimeListF.clear();
        nomeAnimeListF.clear();
        qntEpisListF.clear();
        fotoAnimeListF.clear();
        studioAnimeListF.clear();
        ratingAnimeListF.clear();
        sinopseAnimeListF.clear();
    }

    private void storeDatainArrays(DatabaseFavorites database) {
        id_Anime.clear();
        id_User.clear();
        user_id = tv_id.getText().toString();
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

    private void getAllMidgetAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/rbento01/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();



        MidgetAPI midgetAPI = retrofit.create(MidgetAPI.class);
        Call<List<Anime1>> call = midgetAPI.requestAllAnimes();

        call.enqueue(new Callback<List<Anime1>>() {
            @Override
            public void onResponse(Call<List<Anime1>> call, Response<List<Anime1>> response) {
                //O retrofit automaticamente separa os objetos, para ficar um array de objetos
                List<Anime1> animes = response.body();

                for (Anime1 anime : animes) {
                    nomeAnimeList.add(anime.getNome());
                    idAnimeList.add(anime.getIdAnime().toString());
                    qntEpisList.add(anime.getQuantEpisodios());
                    fotoAnimeList.add(anime.getLinkFoto());
                    studioAnimeList.add(anime.getEstudio());
                    ratingAnimeList.add(anime.getRating().toString());
                    sinopseAnimeList.add(anime.getSinopse());/*String content = "";
                    content += "ID: " + anime.getIdAnime() + "\n";
                    content += "Nome: " + anime.getNome() + "\n";
                    content += "Autor: " + anime.getAutor() + "\n";
                    content += "linkFoto: " + anime.getLinkFoto() + "\n\n";
                    textViewResult.append(content);*/
                }
                user_id = tv_id.getText().toString();
                myAdapter = new MyAdapter(MainActivity.this, nomeAnimeList,qntEpisList,idAnimeList,fotoAnimeList,studioAnimeList,ratingAnimeList,sinopseAnimeList, user_id);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager((new LinearLayoutManager((MainActivity.this))));
            }

            @Override
            public void onFailure(Call<List<Anime1>> call, Throwable t) {

            }
        });
    }

    private void getMidgetAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://midgetbee.azurewebsites.net/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();

        MidgetAPI midgetAPI = retrofit.create((MidgetAPI.class));
        Call<Anime1> call = midgetAPI.requestAnime();

        call.enqueue(new Callback<Anime1>() {
            @Override
            public void onResponse(Call<Anime1> call, Response<Anime1> response) {
                textViewResult.setText("id: " + response.body().getIdAnime() +
                        "\nNome: " + response.body().getNome());
            }

            @Override
            public void onFailure(Call<Anime1> call, Throwable t) {

            }
        });
    }

    private void getSampleJsonResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://navneet7k.github.io/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<SampleResponse> call = requestInterface.getSampleResponse();

        call.enqueue(new Callback<SampleResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SampleResponse> call, Response<SampleResponse> response) {
                if (response.isSuccessful()){
                    textViewResult.setText("ID: " + response.body().getId() + "\nName: "
                            + response.body().getName() + "\nDesc: "
                            + response.body().getDescription());
                }
            }

            @Override
            public void onFailure(Call<SampleResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getAnime() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimeAPI animeAPI = retrofit.create(AnimeAPI.class);
        Call<Anime> call = animeAPI.requestAnime();

        call.enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                textViewResult.setText("id: " + response.body().getMalId());
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {

            }
        });
    }

    private void getPosts(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");


        //Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{2,3,6}, "id", "desc"); //o retrofit cria a implementação destes métodos

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters); //o retrofit cria a implementação destes métodos

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response);
                    return; //não fazer nada com null, parar com a app
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments("https://jsonplaceholder.typicode.com/posts/3/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response);
                    return; //não fazer nada com null, parar com a app
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost(){
        Post post = new Post(23, "New Title", "New Text");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "New Title");

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response);
                    return; //não fazer nada com null, parar com a app
                }

                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost(){
        Post post = new Post(12, null, "New Text");

        Call<Post> call = jsonPlaceHolderApi.patchPost(5, post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response);
                    return; //não fazer nada com null, parar com a app
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getUserId() + "\n";
                content += "Title: " + postResponse.getTitle() + "\n";
                content += "Text: " + postResponse.getText() + "\n\n";

                textViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void deletePost(){
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: " + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        //SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        //ViewPager viewPager = binding.viewPager;
        //viewPager.setAdapter(sectionsPagerAdapter);
        //TabLayout tabs = binding.tabs;
        //tabs.setupWithViewPager(viewPager);
        //FloatingActionButton fab = binding.fab;

        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
}
