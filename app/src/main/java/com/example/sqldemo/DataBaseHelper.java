package com.example.sqldemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_PERSON_NAME = "PERSON_NAME";
    public static final String COLUMN_PERSON_AGE = "PERSON_AGE";
    public static final String COLUMN_PERSON_ACTIVE = "PERSON_ACTIVE";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "person.db", null, 1);
    }

    //Called first time we try to access db object
    //Code that generates tables etc.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PERSON_NAME + " TEXT, " + COLUMN_PERSON_AGE + " INT," + COLUMN_PERSON_ACTIVE + " BOOL)";

        db.execSQL(createTableStatement);
    }


    //When version number of DB changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean addOne(PersonModel person)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PERSON_NAME, person.getFirstName());
        cv.put(COLUMN_PERSON_AGE, person.getAge());
        cv.put(COLUMN_PERSON_ACTIVE, person.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if(insert >= 0) return true;
        else return false;
    }

    public boolean deleteOne(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "WHERE ID = " + id;

        int result = db.delete(CUSTOMER_TABLE, query, null);
        if(result == 1) return true;
        else return false;
    }


    public List<PersonModel> getEveryone()
    {
        List<PersonModel> returnList = new ArrayList<>();

        String queryString = "SELECT * from " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            //loop through the cursor and create new person object.
            do{
                int personId = cursor.getInt(0);
                String personName = cursor.getString(1);
                int personAge = cursor.getInt(2);
                boolean personActive = (cursor.getInt(3) == 1 ? true: false);

                returnList.add(new PersonModel(personId, personName, personAge, personActive));

            }while(cursor.moveToNext());
        }
        else{
            returnList = null;
        }

        cursor.close();
        db.close();
        return returnList;
    }
}
