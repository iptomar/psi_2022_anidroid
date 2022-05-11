package com.psi.anidroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.content.Intent;
import android.os.Bundle;
=======
>>>>>>> Stashed changes
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    private TextView textViewResult,tv_id;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    private Button btnProfile;
    private Button btnLogin;

    private Button btnProfile;
    private Button btnLogin;

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
        tv_id = findViewById(R.id.tv_id);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnLogin = (Button) findViewById(R.id.btn_login);

        //textViewResult = findViewById(R.id.text_view_result);

        //Gson gson = new GsonBuilder().serializeNulls().create(); //alterar o comportamento do gson, para meter mesmo os valores nulos no telemovel

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

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

        //DBUsers.onCreate(dbUsers.getWritableDatabase());

        tv_id = findViewById(R.id.tv_id);
        recyclerView = findViewById(R.id.recyclerView);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnLogin = (Button) findViewById(R.id.btn_login);

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

        //textViewResult = findViewById(R.id.text_view_result);

        //Gson gson = new GsonBuilder().serializeNulls().create(); //alterar o comportamento do gson, para meter mesmo os valores nulos no telemovel

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
                .addConverterFactory(GsonConverterFactory.create(/*gson*/)) //dizer que queremos usar o gson para os pedidos
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

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

    private void OpenProfile(){
        Intent intent_profile = new Intent(this, ProfileActivity.class);
        intent_profile.putExtra("id",tv_id.getText().toString());
        startActivity(intent_profile);
    }

    private void OpenLogin(){
        Intent intent_login = new Intent(this, LoginActivity.class);
        startActivity(intent_login);
    }

    private void getAllMidgetAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://midgetbee.azurewebsites.net/") //o URL base, ATENÇÃO PÔR SEMPRE O BACKSLASH
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

        ArrayList<String> idAnimeList = new ArrayList<String>();
        ArrayList<String> nomeAnimeList = new ArrayList<String>();
        ArrayList<String> qntEpisList = new ArrayList<String>();
        ArrayList<String> fotoAnimeList = new ArrayList<String>();

        MidgetAPI midgetAPI = retrofit.create(MidgetAPI.class);
        Call<List<Anime1>> call = midgetAPI.requestAllAnimes();

        call.enqueue(new Callback<List<Anime1>>() {
            @Override
            public void onResponse(Call<List<Anime1>> call, Response<List<Anime1>> response) {
                List<Anime1> animes = response.body();

                for (Anime1 anime : animes) {
                    nomeAnimeList.add(anime.getNome());
                    idAnimeList.add(anime.getIdAnime().toString());
                    qntEpisList.add(anime.getQuantEpisodios());
                    fotoAnimeList.add(anime.getLinkFoto());

                    studioAnimeList.add(anime.getEstudio());
                    ratingAnimeList.add(anime.getRating().toString());
                    sinopseAnimeList.add(anime.getSinopse());

                    String content = "";
                    content += "ID: " + anime.getIdAnime() + "\n";
                    content += "Nome: " + anime.getNome() + "\n";
                    content += "Autor: " + anime.getAutor() + "\n";
                    content += "linkFoto: " + anime.getLinkFoto() + "\n\n";



                    //textViewResult.append(content);


                }
              
                myAdapter = new MyAdapter(MainActivity.this, nomeAnimeList,qntEpisList,idAnimeList,fotoAnimeList,studioAnimeList,ratingAnimeList,sinopseAnimeList);

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