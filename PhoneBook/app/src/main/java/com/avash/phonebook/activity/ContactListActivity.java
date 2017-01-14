package com.avash.phonebook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.avash.phonebook.R;
import com.avash.phonebook.adapter.ContactRowAdapter;
import com.avash.phonebook.database.DatabaseHelper;
import com.avash.phonebook.database.PhoneBookManager;
import com.avash.phonebook.model.PhoneBookModel;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    private ListView contactListView;
    private ContactRowAdapter contactRowAdapter;
    private PhoneBookModel phoneBookModel;
    private PhoneBookManager phoneBookManager;
    private ArrayList<PhoneBookModel>phoneBookModels;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        databaseHelper = new DatabaseHelper(this);

        contactListView = (ListView) findViewById(R.id.contactListView);
        phoneBookModels = new ArrayList<>();
        phoneBookManager = new PhoneBookManager(this);
        phoneBookModels = phoneBookManager.getAllContacts();

        contactRowAdapter = new ContactRowAdapter(this,phoneBookModels);
        contactListView.setAdapter(contactRowAdapter);
        contactListView.setItemsCanFocus(true);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              Toast.makeText(view.getContext(),phoneBookModels.get(position).getContactName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactListActivity.this,ContactDetailsActivity.class);
                intent.putExtra("pid",phoneBookModels.get(position).getPhoneBookID());
                startActivity(intent);
            }
        });

    }
}
