package com.avash.phonebook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.avash.phonebook.model.UserModel;

import java.util.ArrayList;

public class UserManager {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private UserModel userModel;

    public UserManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public UserModel getUser(String userID, String userPassword) {
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        String allUsersQuery = "SELECT * FROM " + DatabaseHelper.USER_TABLE_NAME + " WHERE " + DatabaseHelper.USER_TABLE_USER_ID +
                " = '" + userID + "' and " + DatabaseHelper.USER_TABLE_USER_PASSWORD + " = '" + userPassword + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(allUsersQuery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int uID = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_ID));
                String userId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_USER_ID));
                String userPass = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_USER_PASSWORD));
                String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_USER_NAME));
                String userAddress = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_TABLE_USER_ADDRESS));

                userModel = new UserModel(uID, userId, userPass, userName, userAddress);
            }

        } else {
            userModel = null;
        }
        sqLiteDatabase.close();
        return userModel;
    }


}
