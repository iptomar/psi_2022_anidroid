package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewPassActivity extends AppCompatActivity {
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });
        //Old password tem de coincidir com a passe do utilizador
        //New password irá substituir a passe do utilizador (talvez precise de limitações - pelo menos 8 caracteres por exemplo)
    }
    public void openMain() {
        Intent intent_main = new Intent(this, MainActivity.class);
        startActivity(intent_main);
    }
}