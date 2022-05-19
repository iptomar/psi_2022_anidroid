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
    RecyclerView recyclerView;
    AccountsMyAdapter myAccountAdapter;

    private Button btnLogout;
    private Button btnChngPass;
    private Button btnBack;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnCheckUsers;
    public TextView tvUsername;
    public TextView tvEmail;
    int id;

    //id do anime
    ArrayList<String> idUserAccount = new ArrayList<String>();
    //nome do anime
    ArrayList<String> nameUserAccount = new ArrayList<String>();
    //
    ArrayList<String> emailUserAccount = new ArrayList<String>();
    //
    ArrayList<String> roleUserAccount = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setBackgroundColor(Color.TRANSPARENT);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnChngPass = (Button) findViewById(R.id.btnChngPass);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCheckUsers = (Button) findViewById(R.id.btnCheckUsers);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvUsername.setText(DBUsers.COL_2);
        tvEmail.setText(DBUsers.COL_3);
        DBUsers DB = new DBUsers(ProfileActivity.this);
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

        btnChngPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChngPass();
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

    public void openChngPass() {
        Intent intent_pass = new Intent(this, NewPassActivity.class);
        intent_pass.putExtra("ID", id+"");
        startActivity(intent_pass);
    }

    public void openUsers() {
        myAccountAdapter = new AccountsMyAdapter(ProfileActivity.this, idUserAccount,nameUserAccount,emailUserAccount,roleUserAccount);
        recyclerView.setAdapter(myAccountAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager((ProfileActivity.this))));
    }
}