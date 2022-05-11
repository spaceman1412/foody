package com.example.foody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foody.SQL.FoodyDbHelper;
import com.example.foody.model.User;

public class SignUpActivity extends AppCompatActivity {

    public String username;
    public String password;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FoodyDbHelper db = new FoodyDbHelper(this);



        ((Button) findViewById(R.id.buttonSignUp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = ((EditText) findViewById(R.id.userName)).getText().toString();
                password = ((EditText) findViewById(R.id.userPassword)).getText().toString().trim();
                email = ((EditText) findViewById(R.id.userEmail)).getText().toString().trim();
                db.addUser(new User("2",username,password,email));

            }
        });
    }
}