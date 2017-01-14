package com.avash.phonebook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avash.phonebook.R;
import com.avash.phonebook.database.UserManager;
import com.avash.phonebook.model.UserModel;


public class LoginActivity extends AppCompatActivity {

    EditText userET;
    EditText passET;
    Button logBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userET = (EditText) findViewById(R.id.userNameET);
        passET = (EditText) findViewById(R.id.passwordET);
        logBtn = (Button) findViewById(R.id.btnLogIn);

    }

    public void check(View view) {

        String username = userET.getText().toString();
        String password = passET.getText().toString();
        UserManager usmanager = new UserManager(this);


        UserModel user = usmanager.getUser(username, password);


        if (user == null) {

            Toast.makeText(LoginActivity.this,
                    "Invalid user id or password!",
                    Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Welcome "+user.getUserName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, ContactListActivity.class);
            intent.putExtra("uid",user.getuID());
            startActivity(intent);
            finish();

        }


    }
}