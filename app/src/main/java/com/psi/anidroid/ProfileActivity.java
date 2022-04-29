package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private Button btnLogout;
    private Button btnChngPass;
    private Button btnBack;
    private Button btnEdit;
    private Button btnDelete;
    public TextView tvUsername;
    public TextView tvEmail;

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
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvUsername.setText(DBUsers.COL_2);
        tvEmail.setText(DBUsers.COL_3);
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            int id = Integer.parseInt(intent.getStringExtra("id"));
            DBUsers DB = new DBUsers(ProfileActivity.this);
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
                //boolean del = DB.deleteUser();
                //openLogin();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openLogin();
            }
        });

        //tvUsername.setText(DBUsers.TABLE_NAME.username);
    }
    public void openMain() {
        Intent intent_main = new Intent(this, MainActivity.class);
        startActivity(intent_main);
    }

    public void openEdit() {
        Intent intent_edit = new Intent(this, EditProfileActivity.class);
        intent_edit.putExtra("Username", tvUsername.getText().toString());
        intent_edit.putExtra("Email", tvEmail.getText().toString());
        startActivity(intent_edit);
    }

    public void openChngPass() {
        Intent intent_pass = new Intent(this, NewPassActivity.class);
        startActivity(intent_pass);
    }
    public String getUser(){
        return tvUsername.getText().toString();
    }
}