package com.avash.phonebook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.avash.phonebook.model.PhoneBookModel;

import java.util.ArrayList;

public class PhoneBookManager {
    private Context context;
    private DatabaseHelper databaseHelper;
    private ContentValues contentValues;
    private SQLiteDatabase sqLiteDatabase;
    private PhoneBookModel phoneBookModel;
    ArrayList<PhoneBookModel>allContacts;

    public PhoneBookManager(Context context) {
        this.context = context;
    }

    public long addNewContact(PhoneBookModel phoneBookModel) {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_USER_ID, phoneBookModel.getuID());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NAME, phoneBookModel.getContactName());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NUMBER, phoneBookModel.getContactNumber());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_SKYPE_ID, phoneBookModel.getSkypeID());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_EMAIL_ID, phoneBookModel.getEmailID());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_IMAGE, phoneBookModel.getImage());

        long queryResult = sqLiteDatabase.insert(DatabaseHelper.PHONE_BOOK_TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return queryResult;
    }

    public ArrayList<PhoneBookModel> getAllContacts() {
        databaseHelper = new DatabaseHelper(context);
        allContacts = new ArrayList<>();
        try{
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }catch (Exception e){
            Log.e("---------------","*******"+e.toString());
        }

        String sqlString = "SELECT * FROM " + DatabaseHelper.PHONE_BOOK_TABLE_NAME;
        System.out.println(sqlString);
//         + " where " + DatabaseHelper.PHONE_BOOK_TABLE_ID +" = '" + pid + "' ";
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.rawQuery(sqlString, null);
        }catch (Exception e){
            Log.e("getAllcontact","*****************"+e.toString());
        }



        if(cursor.moveToFirst()){
            do {
                int phoneBookID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_ID));
                int uID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_USER_ID));
                String contactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NUMBER));
                String skypeID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_SKYPE_ID));
                String emailID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_EMAIL_ID));
                String image = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_IMAGE));

                phoneBookModel = new PhoneBookModel(phoneBookID,uID,contactName,contactNumber,skypeID,emailID,image);
                try{
                    allContacts.add(phoneBookModel);
                }catch (Exception e){Log.e("------------------","--------"+e.toString());}
            }while (cursor.moveToNext());
        }
        else this.allContacts = null;
        sqLiteDatabase.close();

        return this.allContacts;
    }
    public PhoneBookModel getSingleContact(int pid){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        String sqlString = "SELECT * FROM " + DatabaseHelper.PHONE_BOOK_TABLE_NAME+
                " where " + DatabaseHelper.PHONE_BOOK_TABLE_ID +" = '" + pid + "' ";
        Cursor cursor = sqLiteDatabase.rawQuery(sqlString, null);

        if(cursor.moveToFirst()){
            do {
                int phoneBookID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_ID));
                int uID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_USER_ID));
                String contactName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NUMBER));
                String skypeID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_SKYPE_ID));
                String emailID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_EMAIL_ID));
                String image = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PHONE_BOOK_TABLE_IMAGE));

                phoneBookModel = new PhoneBookModel(phoneBookID,uID,contactName,contactNumber,skypeID,emailID,image);

            }while (cursor.moveToNext());
        }
        else phoneBookModel = null;
        sqLiteDatabase.close();

        return phoneBookModel;
    }

    public long updateContact (PhoneBookModel phoneBookModel){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NAME,phoneBookModel.getContactName());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_CONTACT_NUMBER,phoneBookModel.getContactNumber());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_SKYPE_ID,phoneBookModel.getSkypeID());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_EMAIL_ID,phoneBookModel.getEmailID());
        contentValues.put(DatabaseHelper.PHONE_BOOK_TABLE_IMAGE,phoneBookModel.getImage());

        long queryResult = sqLiteDatabase.update(DatabaseHelper.PHONE_BOOK_TABLE_NAME,contentValues,
                DatabaseHelper.PHONE_BOOK_TABLE_ID+" =? ",new String [] {String.valueOf(phoneBookModel.getPhoneBookID())});
        sqLiteDatabase.close();
        return queryResult;

    }

    public long deleteContact(int pid){
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        long queryResult = sqLiteDatabase.delete(DatabaseHelper.PHONE_BOOK_TABLE_NAME,DatabaseHelper.PHONE_BOOK_TABLE_ID+" =? ",
                new String[]{String.valueOf(pid)});

        sqLiteDatabase.close();
        return queryResult;
    }
}
