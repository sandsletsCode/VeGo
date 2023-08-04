package com.example.vego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelperForContacts extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    public DatabaseHelperForContacts(@Nullable Context context) {
        super(context,"Contacts.db", null,1 );
        database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query;
        query = "CREATE TABLE contacts (id integer PRIMARY KEY,name TEXT, number TEXT, nick_name TEXT, address TEXT, notes TEXT)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addContact(Contact contact){
        //adds contact tot he database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();
        // adding the column name as the key along with the value
        values.put("name" , contact.getName());
        values.put("number", contact.getNumber());
        values.put("nick_name" ,contact.getNick_name());
        values.put("address" , contact.getAddress());
        values.put("notes" ,contact.getNotes());
        //inserts records and return the long value and -1 if not inserted
        return db.insert("contacts" , null,values);


    }

    // function to load all the records from the database
    public ArrayList<Contact> getAllContacts(){
        String query ="SELECT * FROM contacts";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Contact> contactArrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact(cursor.getShort(0), cursor.getString(1),cursor.getString(2)
                , cursor.getString(3), cursor.getString(4), cursor.getString(5));
                contactArrayList.add(contact);
            }while (cursor.moveToNext());

        }
        return contactArrayList;
    }

// method to get the contact info by giving id
    public Contact getContactInfor(int id){
        String query= "SELECT * FROM contacts contacts WHERE id ='"+id+"'";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(query,null);
        Contact contact=null;
        if(cursor.moveToFirst()){
            contact = new Contact(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5) );
        }
        return contact;
    }


    public int onUpgrade(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name" , contact.getName());
        values.put("number", contact.getNumber());
        values.put("nick_name" ,contact.getNick_name());
        values.put("address" , contact.getAddress());
        values.put("notes" ,contact.getNotes());
        Log.d("TAG","updateContact:"+values);
        return db.update("contacts",values,"id=?", new String[]{String.valueOf(contact.getId())});


    }
}
