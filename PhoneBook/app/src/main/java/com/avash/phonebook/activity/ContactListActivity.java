package com.avash.phonebook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private SharedPreferences sharedPreferences;

    private FloatingActionButton fab;

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

        fab= (FloatingActionButton) findViewById(R.id.addFloatingButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent=new Intent(ContactListActivity.this,ContactAddActivity.class);
                startActivity(addIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu:
                sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("uid");
                editor.remove(("uName"));
                editor.apply();
                editor.commit();
                Toast.makeText(ContactListActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactListActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return onOptionsItemSelected(item);
    }
}
