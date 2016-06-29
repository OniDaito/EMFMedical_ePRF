package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

/**
 * Created by oni on 18/05/2016.
 */
public class BaseData {

    protected static String TABLE_NAME = "none";

    // Not too sure about these methods referring to the DB in the way they do

    public static String createTable(){
        return "";
    }
    public static String deleteTable(){
        return ("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        return values;
    }

    protected void setValues(  ContentValues values ) {
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    // Overridden in subclasses. Called to see if fields have been filled in
    public boolean isValid(Vector<String> errormessages){
        return true;
    }


}
