package com.psi.anidroid;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    public Button btnLogout;
    public Button btnCheckUsers;
    public Button btnChangPass;
    public Button btnBack;
    public Button btnEdit;
    public Button btnDelete;
    public TextView tvUsername;
    public TextView tvEmail;
    int id;

    DBUsers DB = new DBUsers(ProfileActivity.this);

    //id do do user
    ArrayList<String> id_User = new ArrayList<>();
    //username do user
    ArrayList<String> username = new ArrayList<>();
    //email do user
    ArrayList<String> email = new ArrayList<>();
    //role do user
    ArrayList<String> roles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setBackgroundColor(Color.TRANSPARENT);
        btnLogout = findViewById(R.id.btnLogout);
        btnEdit = findViewById(R.id.btnEdit);
        btnChangPass = findViewById(R.id.btnChngPass);
        btnDelete = findViewById(R.id.btnDelete);
        btnCheckUsers = findViewById(R.id.btnCheckUsers);
        tvUsername =  findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvUsername.setText(DBUsers.COL_2);
        tvEmail.setText(DBUsers.COL_3);
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            id = Integer.parseInt(intent.getStringExtra("id"));
            Cursor setUser = DB.getUsername(id);
            Cursor setEmail = DB.getEmail(id);
            if (setUser.moveToFirst()) {
                String usr = setUser.getString(0);
                tvUsername.setText(usr);
            }
            if (setEmail.moveToFirst()) {
                String ml = setEmail.getString(0);
                tvEmail.setText(ml);
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEdit();
            }
        });

        btnChangPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChangPass();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.deleteUser(id+"");
                openMain();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });

        btnCheckUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUsers();
            }
        });
    }
    public void openMain() {
        Intent intent_main = new Intent(this, MainActivity.class);
        intent_main.putExtra("id", id+"");
        startActivity(intent_main);
    }

    public void openEdit() {
        Intent intent_edit = new Intent(this, EditProfileActivity.class);
        intent_edit.putExtra("ID", id+"");
        startActivity(intent_edit);
    }

    public void openChangPass() {
        Intent intent_pass = new Intent(this, NewPassActivity.class);
        intent_pass.putExtra("ID", id+"");
        startActivity(intent_pass);
    }

    public void openUsers() {
        Intent intent_acc = new Intent(this, AccountsAnime.class);
        startActivity(intent_acc);
    }


}