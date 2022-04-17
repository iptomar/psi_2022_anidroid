package com.psi.anidroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfileActivity extends AppCompatActivity {
    private Button btnExitEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnExitEdit = (Button) findViewById(R.id.btnExitEdit);
        btnExitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });
        //ao editar o perfil deve dar update aos atributos do utilizador na base de dados
    }
    public void openProfile(){
        Intent intent_profile = new Intent(this, ProfileActivity.class);
        startActivity(intent_profile);
    }
}