package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class AccountsAnime extends AppCompatActivity {

    ArrayList<String> id_account = new ArrayList<String>();
    ArrayList<String> user_account = new ArrayList<String>();
    ArrayList<String> email_account = new ArrayList<String>();
    ArrayList<String> roles_account = new ArrayList<String>();

    DBUsers database = new DBUsers(AccountsAnime.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts_anime);
    }

    private void storeAccontsinArrays(DBUsers database) {
        id_account.clear();
        user_account.clear();
        email_account.clear();
        roles_account.clear();
        Cursor cursor = database.readAllData();
        if (cursor.getCount() == 0){
            //Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id_account.add(cursor.getString(1));
                user_account.add(cursor.getString(2));
                email_account.add(cursor.getString(3));
                roles_account.add(cursor.getString(4));
            }
        }
    }

    private void typeAccount(){
        storeAccontsinArrays(database);


    }
}