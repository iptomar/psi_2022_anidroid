package com.psi.anidroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPassActivity extends AppCompatActivity {
    private Button btnSubmit;
    EditText oldpass,newpass;
    DBUsers DB;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        oldpass = findViewById(R.id.oldpass);
        newpass = findViewById(R.id.newpass);
        DB = new DBUsers(NewPassActivity.this);

        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            id = Integer.parseInt(intent.getStringExtra("ID"));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur_pass = DB.getPassword(id);
                String getpass="";
                if (cur_pass.moveToFirst()) {
                    getpass = cur_pass.getString(0);
                }
                if(oldpass.getText().toString().equals(getpass)){
                    DB.updatePass(id+"", newpass.getText().toString());
                    openMain();
                } else Toast.makeText(NewPassActivity.this, "Antiga passe está errada ", Toast.LENGTH_SHORT).show();
        }
            //Old password tem de coincidir com a passe do utilizador
        //New password irá substituir a passe do utilizador (talvez precise de limitações - pelo menos 8 caracteres por exemplo)
    });
    }
    public void openMain(){
        Intent intent_main = new Intent(this, MainActivity.class);
        startActivity(intent_main);
    }
}