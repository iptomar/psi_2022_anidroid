package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    private Button btnLogout;
    private Button btnChngPass;
    private Button btnBack;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setBackgroundColor(Color.TRANSPARENT);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnChngPass = (Button) findViewById(R.id.btnChngPass);

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
    }
    public void openMain() {
        Intent intent_main = new Intent(this, MainActivity.class);
        startActivity(intent_main);
    }

    public void openEdit() {
        Intent intent_edit = new Intent(this, EditProfileActivity.class);
        startActivity(intent_edit);
    }

    public void openChngPass() {
        Intent intent_pass = new Intent(this, NewPassActivity.class);
        startActivity(intent_pass);
    }
}