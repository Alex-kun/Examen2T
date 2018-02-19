package com.example.alejandroquiros.examen2t;

/**
 * Created by alejandroquiros on 19/2/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LON = "lon";
    private static final String KEY_NOMBRE = "nombre";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LAT + " DOUBLE,"
                + KEY_LON + " DOUBLE," + KEY_NOMBRE + " TEXT"
                +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }


    // Adding new contact
    public void addContact(Lugar lugar) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID, lugar.getLat()); // Contact ID
        values.put(KEY_LAT, lugar.getLat()); // Contact ID
        values.put(KEY_LON, lugar.getLon()); // Contact Name
        values.put(KEY_NOMBRE, lugar.getNombre()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    public Lugar getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_LAT, KEY_LON, KEY_NOMBRE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Lugar lugar = new Lugar(cursor.getDouble(0), cursor.getDouble(1), cursor.getString(2));
        // return contact
        return lugar;
    }

    // Getting All Contacts
    public List<Lugar> getAllContacts() {
        List<Lugar> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Lugar lugar = new Lugar();
                lugar.setLat(cursor.getDouble(0));
                lugar.setLon(cursor.getDouble(1));
                lugar.setNombre(cursor.getString(2));
                // Adding contact to list
                contactList.add(lugar);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
