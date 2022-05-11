package com.psi.anidroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    TextView username, email, password;
    Button btnLogin;
    UserModel userModel;
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
                    userModel = new UserModel(username.getText().toString(), email.getText().toString(), password.getText().toString());
                    //Toast.makeText(LoginActivity.this, "sucesso", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e) {
                    Toast.makeText(LoginActivity.this, "erro", Toast.LENGTH_SHORT).show();
                }
                boolean b = dbUsers.insertUser(userModel);
                Toast.makeText(LoginActivity.this, "resulta "+b, Toast.LENGTH_SHORT).show();
                openMain();
            }
        });
    }
    public void openMain(){
        Intent intent_main = new Intent(this, MainActivity.class);
        Cursor c_id = dbUsers.getMaxID();
        if (c_id.moveToFirst()) {
            String cid = c_id.getString(0)  ;
            intent_main.putExtra("id", cid);
        }
        startActivity(intent_main);
    }
}