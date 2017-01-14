package com.avash.phonebook.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avash.phonebook.R;
import com.avash.phonebook.activity.ContactListActivity;
import com.avash.phonebook.model.PhoneBookModel;

import java.util.ArrayList;


public class ContactRowAdapter extends ArrayAdapter<PhoneBookModel> {
    private Context context;
    private ArrayList<PhoneBookModel> phoneBookModels;
    private PhoneBookModel phoneBookModel;

    public ContactRowAdapter(Context context, ArrayList<PhoneBookModel> phoneBookModels) {
        super(context, R.layout.contact_row, phoneBookModels);
        this.context = context;
        this.phoneBookModels = phoneBookModels;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        phoneBookModel = phoneBookModels.get(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_row, parent, false);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        final TextView mobileTextView = (TextView) convertView.findViewById(R.id.numberTextView);

        Button callButton = (Button) convertView.findViewById(R.id.callButton);
        Button smsButton = (Button) convertView.findViewById(R.id.smsButton);
        nameTextView.setText(phoneBookModel.getContactName());
        mobileTextView.setText(phoneBookModel.getContactNumber());

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mobileTextView.getText()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(callIntent);
            }
        });


        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",mobileTextView.getText().toString(),null));
                context.startActivity(smsIntent);
            }
        });


        return convertView;
    }
}
