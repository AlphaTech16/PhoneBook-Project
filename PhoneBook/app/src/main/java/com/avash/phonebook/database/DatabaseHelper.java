package com.avash.phonebook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PhoneBook";
    public static final int DATABASE_VERSION = 1;
    public static final String PHONE_BOOK_TABLE_NAME = "PhoneBooks";
    public static final String USER_TABLE_NAME = "Users";

    public static final String USER_TABLE_ID = "uid";
    public static final String USER_TABLE_USER_ID = "user_id";
    public static final String USER_TABLE_USER_NAME = "user_full_name";
    public static final String USER_TABLE_USER_PASSWORD = "user_password";
    public static final String USER_TABLE_USER_ADDRESS = "user_address";

    public static final String PHONE_BOOK_TABLE_ID = "pid";
    public static final String PHONE_BOOK_TABLE_USER_ID = "uid";
    public static final String PHONE_BOOK_TABLE_CONTACT_NAME = "contact_name";
    public static final String PHONE_BOOK_TABLE_CONTACT_NUMBER = "contact_number";
    public static final String PHONE_BOOK_TABLE_SKYPE_ID = "skype_id";
    public static final String PHONE_BOOK_TABLE_EMAIL_ID = "email_id";
    public static final String PHONE_BOOK_TABLE_IMAGE = "image";

    private static final String userTable = "CREATE TABLE "+USER_TABLE_NAME+" ("+
            USER_TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            USER_TABLE_USER_NAME+" TEXT NOT NULL,"+
            USER_TABLE_USER_ID+" TEXT NOT NULL UNIQUE,"+
            USER_TABLE_USER_PASSWORD+" TEXT NOT NULL,"+
            USER_TABLE_USER_ADDRESS+" TEXT )";

    private static final String insertUserTable = "INSERT INTO "+USER_TABLE_NAME+
            " ("+USER_TABLE_USER_NAME+","+
            USER_TABLE_USER_ID+","+
            USER_TABLE_USER_PASSWORD+","+
            USER_TABLE_USER_ADDRESS+") VALUES ("+
            "'Alpha Team', 'admin','admin123','Dhaka, Bangladesh')";
    private static final String insertUserTable2 = "INSERT INTO "+USER_TABLE_NAME+
            " ("+USER_TABLE_USER_NAME+","+
            USER_TABLE_USER_ID+","+
            USER_TABLE_USER_PASSWORD+","+
            USER_TABLE_USER_ADDRESS+") VALUES ("+
            "'Shariful Haque', 'avash','avash123','Dhaka, Bangladesh')";
    private static final String insertUserTable3 = "INSERT INTO "+USER_TABLE_NAME+
            " ("+USER_TABLE_USER_NAME+","+
            USER_TABLE_USER_ID+","+
            USER_TABLE_USER_PASSWORD+","+
            USER_TABLE_USER_ADDRESS+") VALUES ("+
            "'Harun Ur Rashid', 'harun','harun123','Dhaka, Bangladesh')";
    private static final String insertUserTable4 = "INSERT INTO "+USER_TABLE_NAME+
            " ("+USER_TABLE_USER_NAME+","+
            USER_TABLE_USER_ID+","+
            USER_TABLE_USER_PASSWORD+","+
            USER_TABLE_USER_ADDRESS+") VALUES ("+
            "'Hasna Hena', 'hasna','hasna123','Dhaka, Bangladesh')";
    private static final String insertUserTable5 = "INSERT INTO "+USER_TABLE_NAME+
            " ("+USER_TABLE_USER_NAME+","+
            USER_TABLE_USER_ID+","+
            USER_TABLE_USER_PASSWORD+","+
            USER_TABLE_USER_ADDRESS+") VALUES ("+
            "'Washim Akram', 'akram','akram123','Dhaka, Bangladesh')";


    private static final String phoneBookTable = "CREATE TABLE "+PHONE_BOOK_TABLE_NAME+" ("+
            PHONE_BOOK_TABLE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            PHONE_BOOK_TABLE_USER_ID+" INTEGER NOT NULL,"+
            PHONE_BOOK_TABLE_CONTACT_NAME+" TEXT NOT NULL,"+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+" TEXT NOT NULL UNIQUE,"+
            PHONE_BOOK_TABLE_SKYPE_ID+" TEXT,"+
            PHONE_BOOK_TABLE_EMAIL_ID+" TEXT,"+
            PHONE_BOOK_TABLE_IMAGE+" TEXT," +
            " FOREIGN KEY ("+PHONE_BOOK_TABLE_USER_ID+") REFERENCES "+USER_TABLE_NAME+"("+USER_TABLE_ID+"))";

    private static final String insertPhoneTable = "INSERT INTO "+PHONE_BOOK_TABLE_NAME+
            " ("+PHONE_BOOK_TABLE_IMAGE+","+
            PHONE_BOOK_TABLE_USER_ID+","+
            PHONE_BOOK_TABLE_CONTACT_NAME+","+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+","+
            PHONE_BOOK_TABLE_SKYPE_ID+","+
            PHONE_BOOK_TABLE_EMAIL_ID+") VALUES ("+
            "'test', 1,'Avash','01912867747', 'avashcmt','avashdiu@gmail.com')";
    private static final String insertPhoneTable2 = "INSERT INTO "+PHONE_BOOK_TABLE_NAME+
            " ("+PHONE_BOOK_TABLE_IMAGE+","+
            PHONE_BOOK_TABLE_USER_ID+","+
            PHONE_BOOK_TABLE_CONTACT_NAME+","+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+","+
            PHONE_BOOK_TABLE_SKYPE_ID+","+
            PHONE_BOOK_TABLE_EMAIL_ID+") VALUES ("+
            "'image', 1,'Monirul Islam','01915807981', 'monir','monir9656@yahoo.com')";
    private static final String insertPhoneTable3 = "INSERT INTO "+PHONE_BOOK_TABLE_NAME+
            " ("+PHONE_BOOK_TABLE_IMAGE+","+
            PHONE_BOOK_TABLE_USER_ID+","+
            PHONE_BOOK_TABLE_CONTACT_NAME+","+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+","+
            PHONE_BOOK_TABLE_SKYPE_ID+","+
            PHONE_BOOK_TABLE_EMAIL_ID+") VALUES ("+
            "'image', 2,'Washim Akram','123', 'Bangladesh','washim')";
    private static final String insertPhoneTable4 = "INSERT INTO "+PHONE_BOOK_TABLE_NAME+
            " ("+PHONE_BOOK_TABLE_IMAGE+","+
            PHONE_BOOK_TABLE_USER_ID+","+
            PHONE_BOOK_TABLE_CONTACT_NAME+","+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+","+
            PHONE_BOOK_TABLE_SKYPE_ID+","+
            PHONE_BOOK_TABLE_EMAIL_ID+") VALUES ("+
            "'image', 2,'Atiqur Rahman','01916014315', 'Bangladesh','')";
    private static final String insertPhoneTable5 = "INSERT INTO "+PHONE_BOOK_TABLE_NAME+
            " ("+PHONE_BOOK_TABLE_IMAGE+","+
            PHONE_BOOK_TABLE_USER_ID+","+
            PHONE_BOOK_TABLE_CONTACT_NAME+","+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+","+
            PHONE_BOOK_TABLE_SKYPE_ID+","+
            PHONE_BOOK_TABLE_EMAIL_ID+") VALUES ("+
            "'image', 2,'Shariful Haque','01723454786', 'Bangladesh','')";
    private static final String insertPhoneTable6 = "INSERT INTO "+PHONE_BOOK_TABLE_NAME+
            " ("+PHONE_BOOK_TABLE_IMAGE+","+
            PHONE_BOOK_TABLE_USER_ID+","+
            PHONE_BOOK_TABLE_CONTACT_NAME+","+
            PHONE_BOOK_TABLE_CONTACT_NUMBER+","+
            PHONE_BOOK_TABLE_SKYPE_ID+","+
            PHONE_BOOK_TABLE_EMAIL_ID+") VALUES ("+
            "'image', 2,'Rahul Das','01911867747', 'Bangladesh','avashcmt')";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(userTable);
            db.execSQL(insertUserTable);
            db.execSQL(insertUserTable2);
            db.execSQL(insertUserTable3);
            db.execSQL(insertUserTable4);
            db.execSQL(insertUserTable5);

            db.execSQL(phoneBookTable);
            db.execSQL(insertPhoneTable);
            db.execSQL(insertPhoneTable2);
            db.execSQL(insertPhoneTable3);
            db.execSQL(insertPhoneTable4);
            db.execSQL(insertPhoneTable5);
            db.execSQL(insertPhoneTable6);


        }catch (Exception e){
            Log.e("DatabaseHelper","--------------------"+e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+PHONE_BOOK_TABLE_NAME+" IF EXISTS");
        db.execSQL("DROP TABLE "+USER_TABLE_NAME+" IF EXISTS");
        onCreate(db);

    }
}
