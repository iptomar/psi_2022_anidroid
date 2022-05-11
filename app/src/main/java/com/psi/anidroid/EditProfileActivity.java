package com.psi.anidroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    private Button btnExitEdit;
    EditText username, email;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        username = findViewById(R.id.editUser);
        email = findViewById(R.id.editEmail);

        DBUsers DB = new DBUsers(EditProfileActivity.this);
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            id = Integer.parseInt(intent.getStringExtra("ID"));
            Cursor setUser = DB.getUsername(id);
            Cursor setEmail = DB.getEmail(id);
            if (setUser.moveToFirst()) {
                String usr = setUser.getString(0);
                username.setText(usr);
            }
            if (setEmail.moveToFirst()) {
                String ml = setEmail.getString(0);
                email.setText(ml);
            }
        }


        btnExitEdit = (Button) findViewById(R.id.btnExitEdit);
        btnExitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try (DBUsers dbUsers = new DBUsers(EditProfileActivity.this)) {
                    dbUsers.updateUser(id+"", username.getText().toString(), email.getText().toString());
                }
                openProfile();
            }
        });
        //ao editar o perfil deve dar update aos atributos do utilizador na base de dados
    }
    public void openProfile(){
        Intent intent_profile = new Intent(this, ProfileActivity.class);
        intent_profile.putExtra("id", id+"");
        startActivity(intent_profile);
    }
}