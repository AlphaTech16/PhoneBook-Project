package com.avash.phonebook.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avash.phonebook.R;
import com.avash.phonebook.database.PhoneBookManager;
import com.avash.phonebook.model.PhoneBookModel;

public class ContactDetailsActivity extends AppCompatActivity {

    private Button callButton, smsButton, emailButton, editButton, deleteButton;
    private TextView nameTextView, numberTextView, emailTextView, skypeTextView;
    private PhoneBookModel phoneBookModel;
    private PhoneBookManager phoneBookManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        callButton = (Button) findViewById(R.id.callButton1);
        smsButton = (Button) findViewById(R.id.smsButton1);
        emailButton = (Button) findViewById(R.id.emailButton);
        editButton = (Button) findViewById(R.id.editContactButton);
        deleteButton = (Button) findViewById(R.id.deleteContactButton);

        nameTextView = (TextView) findViewById(R.id.contactNameTextView);
        numberTextView = (TextView) findViewById(R.id.contactNumberTextView);
        skypeTextView = (TextView) findViewById(R.id.skypeTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);

        final int pid = getIntent().getIntExtra("pid", 0);

        phoneBookManager = new PhoneBookManager(this);
        phoneBookModel = phoneBookManager.getSingleContact(pid);
        nameTextView.setText(phoneBookModel.getContactName());
        numberTextView.setText(phoneBookModel.getContactNumber());
        emailTextView.setText(phoneBookModel.getEmailID());
        skypeTextView.setText(phoneBookModel.getSkypeID());

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numberTextView.getText().toString()));
                if (ActivityCompat.checkSelfPermission(ContactDetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",numberTextView.getText().toString(),null));
                startActivity(smsIntent);
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",emailTextView.getText().toString(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ContactDetailsActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure to delete '"+nameTextView.getText().toString()+"' ?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                long queryResult = phoneBookManager.deleteContact(pid);
                                if(queryResult>0){
                                    Toast.makeText(getApplicationContext(), nameTextView.getText().toString()+" " +
                                            "successfully deleted!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ContactDetailsActivity.this,ContactListActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Sorry! Try Again.", Toast.LENGTH_SHORT).show();
                                }

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

    }
}
