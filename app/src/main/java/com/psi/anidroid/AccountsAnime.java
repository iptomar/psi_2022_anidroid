package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class AccountsAnime extends AppCompatActivity {

    RecyclerView recyclerView;
    AccountsMyAdapter myAccountAdapter;

    //id do do user
    ArrayList<String> id_User = new ArrayList<>();
    //username do user
    ArrayList<String> username = new ArrayList<>();
    //email do user
    ArrayList<String> email = new ArrayList<>();
    //role do user
    ArrayList<String> roles = new ArrayList<>();

    DBUsers database = new DBUsers(AccountsAnime.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storeDatainArrays(database);
        recyclerView = findViewById(R.id.)
        myAccountAdapter = new AccountsMyAdapter(AccountsAnime.this, id_User,username,email,roles);
        recyclerView.setAdapter(myAccountAdapter);
        recyclerView.setLayoutManager((new LinearLayoutManager((AccountsAnime.this))));
    }

    private void storeDatainArrays(DBUsers database) {
        id_User.clear();
        username.clear();
        email.clear();
        roles.clear();
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id_User.add(cursor.getString(0));
                username.add(cursor.getString(1));
                email.add(cursor.getString(2));
                roles.add(cursor.getString(4));
            }
        }
    }
}