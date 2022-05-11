package com.psi.anidroid;

<<<<<<< Updated upstream
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
=======
import android.content.Intent;
import android.database.Cursor;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< Updated upstream
=======
import androidx.appcompat.app.AppCompatActivity;

>>>>>>> Stashed changes
public class LoginActivity extends AppCompatActivity {
    TextView username, email, password;
    Button btnLogin;
    UserModel userModel;
<<<<<<< Updated upstream
    SQLiteDatabase db;
=======
>>>>>>> Stashed changes
    DBUsers dbUsers = new DBUsers(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        username = findViewById(R.id.log_user);
        email = findViewById(R.id.log_mail);
        password = findViewById(R.id.log_pass);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
<<<<<<< Updated upstream
                    userModel = new UserModel(-1, username.getText().toString(), email.getText().toString(), password.getText().toString());
=======
                    userModel = new UserModel(username.getText().toString(), email.getText().toString(), password.getText().toString());
>>>>>>> Stashed changes
                    //Toast.makeText(LoginActivity.this, "sucesso", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(LoginActivity.this, "erro", Toast.LENGTH_SHORT).show();
                }
                boolean b = dbUsers.insertUser(userModel);
<<<<<<< Updated upstream
                Toast.makeText(LoginActivity.this, "resulta ", Toast.LENGTH_SHORT).show();
=======
                Toast.makeText(LoginActivity.this, "resulta "+b, Toast.LENGTH_SHORT).show();
>>>>>>> Stashed changes
                openMain();
            }
        });
    }
    public void openMain(){
        Intent intent_main = new Intent(this, MainActivity.class);
<<<<<<< Updated upstream
        Cursor c_id = dbUsers.getID(username.getText().toString());
        if (c_id.moveToFirst()) {
            String cid = c_id.getString(0);
=======
        Cursor c_id = dbUsers.getMaxID();
        if (c_id.moveToFirst()) {
            String cid = c_id.getString(0)  ;
>>>>>>> Stashed changes
            intent_main.putExtra("id", cid);
        }
        startActivity(intent_main);
    }
}