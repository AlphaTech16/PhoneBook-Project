package com.avash.phonebook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avash.phonebook.R;
import com.avash.phonebook.database.PhoneBookManager;
import com.avash.phonebook.model.PhoneBookModel;

public class ContactAddActivity extends AppCompatActivity {
    private EditText nameET,phoneET,emailET,skypeET;
    PhoneBookManager phoneBookManager;
    private int contactId,userId;
    private TextView addNewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
        phoneBookManager=new PhoneBookManager(this);
        nameET= (EditText) findViewById(R.id.nameEditText);
        phoneET= (EditText) findViewById(R.id.phoneNumberEditText);
        emailET= (EditText) findViewById(R.id.emailEditText);
        skypeET= (EditText) findViewById(R.id.skypeEditText);
        addNewContact= (TextView) findViewById(R.id.addContactTextView);
        contactId=getIntent().getIntExtra("cId",0);
        userId=getIntent().getIntExtra("uId",0);
        Log.e("id","onCreate :"+contactId);

        if (contactId > 0){
            PhoneBookModel phoneBookModel=phoneBookManager.getSingleContact(contactId);
            addNewContact.setText("Update contact");
            nameET.setText(phoneBookModel.getContactName());
            phoneET.setText(phoneBookModel.getContactNumber());
            skypeET.setText(phoneBookModel.getSkypeID());
            emailET.setText(phoneBookModel.getEmailID());
        }

    }

    public void saveContact(View view) {
        String name=nameET.getText().toString();
        String phone=phoneET.getText().toString();
        String email=emailET.getText().toString();
        String skype=skypeET.getText().toString();

        PhoneBookModel phoneBookModel=new PhoneBookModel(contactId,userId,name,phone,skype,email,"");
        if (contactId >0){
            long updateResult=phoneBookManager.updateContact(phoneBookModel);
            if (updateResult >0){
                Toast.makeText(this,String.valueOf(updateResult),Toast.LENGTH_SHORT).show();
            }
        }else {


            long insertedResult = phoneBookManager.addNewContact(phoneBookModel);
            if (insertedResult > 0) {
                Toast.makeText(this, String.valueOf(insertedResult), Toast.LENGTH_SHORT).show();
            }
        }
        Intent listIntent=new Intent(ContactAddActivity.this,ContactListActivity.class);
        startActivity(listIntent);

    }
}
