package com.psi.anidroid;

<<<<<<< Updated upstream
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
=======
import android.content.Intent;
import android.database.Cursor;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

<<<<<<< Updated upstream
public class EditProfileActivity extends AppCompatActivity {
    private Button btnExitEdit;
    DBUsers DB;
    EditText username, email;
=======
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    private Button btnExitEdit;
    EditText username, email;
    int id;
>>>>>>> Stashed changes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        username = findViewById(R.id.editUser);
        email = findViewById(R.id.editEmail);

<<<<<<< Updated upstream
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            username.setText(intent.getStringExtra("Username"));
            email.setText(intent.getStringExtra("Email"));
        }

=======
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


>>>>>>> Stashed changes
        btnExitEdit = (Button) findViewById(R.id.btnExitEdit);
        btnExitEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< Updated upstream
                /*UserModel userModel;
                try (DBUsers DBUsers = new DBUsers(EditProfileActivity.this)) {
                    //DBUsers.insertUser(userModel);
                }*/
                //DB.updateUser(username.getText().toString(), email.getText().toString());
=======
                try (DBUsers dbUsers = new DBUsers(EditProfileActivity.this)) {
                    dbUsers.updateUser(id+"", username.getText().toString(), email.getText().toString());
                }
>>>>>>> Stashed changes
                openProfile();
            }
        });
        //ao editar o perfil deve dar update aos atributos do utilizador na base de dados
    }
    public void openProfile(){
        Intent intent_profile = new Intent(this, ProfileActivity.class);
<<<<<<< Updated upstream
        //intent_profile.putExtra("Username", username.getText().toString());
        //intent_profile.putExtra("Email", email.getText().toString());
=======
        intent_profile.putExtra("id", id+"");
>>>>>>> Stashed changes
        startActivity(intent_profile);
    }
}