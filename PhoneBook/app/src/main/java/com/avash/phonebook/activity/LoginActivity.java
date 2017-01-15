package com.avash.phonebook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userET = (EditText) findViewById(R.id.userNameET);
        passET = (EditText) findViewById(R.id.passwordET);
        logBtn = (Button) findViewById(R.id.btnLogIn);

        sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        int uid = sharedPreferences.getInt("uid",0);
        if(uid>0){
            Intent listIntent = new Intent(LoginActivity.this, ContactListActivity.class);
            startActivity(listIntent);
            finish();
        }
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
            sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("uid",user.getuID());
            editor.putString("uName",user.getUserName());
            editor.apply();
            editor.commit();
            Toast.makeText(this, "Welcome "+user.getUserName(), Toast.LENGTH_SHORT).show();
            Intent listIntent = new Intent(LoginActivity.this, ContactListActivity.class);
            startActivity(listIntent);
            finish();

        }


    }
}