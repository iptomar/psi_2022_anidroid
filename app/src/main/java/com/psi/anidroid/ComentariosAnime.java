package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ComentariosAnime extends AppCompatActivity {
    TextView Comentario1,Comment1,Comment2,Comment3,User1,User2,User3;
    Button btnComment, btnDelete1, btnDelete2, btnDelete3;
    int user_id,anime_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios_anime2);

        btnComment = findViewById(R.id.addComment);
        btnDelete1 = findViewById(R.id.btnDeleteCom);
        btnDelete2 = findViewById(R.id.btnDeleteCom2);
        btnDelete3 = findViewById(R.id.btnDeleteCom3);
        Comentario1 = findViewById(R.id.Comentario1);
        Comment1 = findViewById(R.id.Comment1);
        Comment2 = findViewById(R.id.Comment2);
        Comment3 = findViewById(R.id.Comment3);
        User1 = findViewById(R.id.User2);
        User2 = findViewById(R.id.User3);
        User3 = findViewById(R.id.User4);

        anime_id=Integer.parseInt(getIntent().getStringExtra("Anime_ID"));
        user_id=Integer.parseInt(getIntent().getStringExtra("User_ID"));

        if (user_id == 30){
            btnComment.setVisibility(View.INVISIBLE);
            Comentario1.setVisibility(View.INVISIBLE);
        }
        if(Comment1.getText().equals("")){
            btnDelete1.setVisibility(View.INVISIBLE);
        }
        if(Comment2.getText().equals("")){
            btnDelete2.setVisibility(View.INVISIBLE);
        }
        if(Comment3.getText().equals("")){
            btnDelete3.setVisibility(View.INVISIBLE);
        }
        DBComentarios dbComentarios = new DBComentarios(ComentariosAnime.this);
        DBUsers dbusers = new DBUsers(ComentariosAnime.this);
        int user_id_aux;
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbComentarios.addComment(anime_id,user_id, Comentario1.getText().toString());
                Comment1.setText(Comentario1.getText().toString());
                Cursor c_add= dbusers.getUsername(user_id);
                if (c_add.moveToFirst()) {
                    User1.setText(c_add.getString(0));
                }
                Toast.makeText(ComentariosAnime.this, "Ação bem-sucedida", Toast.LENGTH_SHORT);
            }
        });

        btnDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbComentarios.deleteComment(anime_id,user_id, Comentario1.getText().toString());
                Comment1.setText("");
                User1.setText("");
                btnDelete1.setVisibility(View.INVISIBLE);
                Toast.makeText(ComentariosAnime.this, "Ação bem-sucedida", Toast.LENGTH_SHORT);
            }
        });
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbComentarios.deleteComment(anime_id,user_id, Comentario1.getText().toString());
                Comment2.setText("");
                User2.setText("");
                btnDelete2.setVisibility(View.INVISIBLE);
                Toast.makeText(ComentariosAnime.this, "Ação bem-sucedida", Toast.LENGTH_SHORT);
            }
        });
        btnDelete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbComentarios.deleteComment(anime_id,user_id, Comentario1.getText().toString());
                Comment3.setText("");
                User3.setText("");
                btnDelete3.setVisibility(View.INVISIBLE);
                Toast.makeText(ComentariosAnime.this, "Ação bem-sucedida", Toast.LENGTH_SHORT);
            }
        });

        //comentarios
        Cursor c_comments = dbComentarios.getCommentsbyAnime(anime_id);
        Cursor c_users = dbComentarios.getUserbyComment(anime_id);
        if (c_comments.moveToFirst()){
                Comment1.setText(c_comments.getString(0));
                if(c_comments.moveToNext()) {
                    Comment2.setText(c_comments.getString(0));
                }
                if(c_comments.moveToNext()){
                Comment3.setText(c_comments.getString(0));
                }
        }
        if (c_users.moveToFirst()){
            user_id_aux = Integer.parseInt(c_users.getString(0));
            if (user_id_aux != user_id){
                btnDelete1.setVisibility(View.INVISIBLE);
            }
            Cursor c_aux1 =dbusers.getUsername(user_id_aux);
            if (c_aux1.moveToFirst()) {
                User1.setText(c_aux1.getString(0));
            }
            if(c_users.moveToNext()) {
                user_id_aux = Integer.parseInt(c_users.getString(0));
                if (user_id_aux != user_id){
                    btnDelete2.setVisibility(View.INVISIBLE);
                }
                Cursor c_aux2=dbusers.getUsername(user_id_aux);
                if (c_aux2.moveToFirst()) {
                    User2.setText(c_aux2.getString(0));
                }
            }
            if(c_users.moveToNext()){
                user_id_aux = Integer.parseInt(c_users.getString(0));
                if (user_id_aux != user_id){
                    btnDelete3.setVisibility(View.INVISIBLE);
                }
                Cursor c_aux3=dbusers.getUsername(user_id_aux);
                if (c_aux3.moveToFirst()) {
                    User3.setText(c_aux3.getString(0));
                }
            }
        }

    }
}