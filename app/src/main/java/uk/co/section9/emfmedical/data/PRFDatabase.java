package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ObjectStreamException;
import java.util.Date;
import java.util.Vector;

import uk.co.section9.emfmedical.Util;
import uk.co.section9.emfmedical.data.PRF;

/**
 * Created by oni on 01/05/2016.
 * A class that represents the actual PRFDatabase data - including writing and reading it in
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 */

public class PRFDatabase extends SQLiteOpenHelper {

    // Data members held inside a small database which is cleared
    // then written out - must make sure memory is cleared.

    // All Static variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "prf";

    public PRFDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create all the tables, presumably in memory
        // We double check to see if we have any database tables already
        Discharge.createTable(this);
        Incident.createTable(this);
        Notes.createTable(this);
        Observation.createTable(this);
        PRF.createTable(this);
        Primary.createTable(this);
        Secondary.createTable(this);
        Serious.createTable(this);
        Treatment.createTable(this);
    }

    public Vector<String> listPRFS() {
        Vector<String> results = new Vector<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id from " + PRF.TABLE_NAME + ";", null);
        if (cursor != null) {
            cursor.moveToFirst();
            results.add(cursor.getString(0));
            while (cursor.moveToNext()){
                results.add(cursor.getString(0));
            }
        }
        return results;
    }


    public String listTables() {
        SQLiteDatabase db = this.getReadableDatabase();
        String result ="";
        Cursor cursor = db.rawQuery("select * from sqlite_master WHERE type='table'",null);
        if (cursor != null) {
            cursor.moveToFirst();
            result += cursor.getString(1) + ",";
            while (cursor.moveToNext()){
                result += cursor.getString(1) + ",";
            }
        }
        return result;

    }

    public PRF readPRF(String uuid) {
        PRF prf = new PRF(uuid);
        prf.dbRead(this);
        return prf;
    }

   /* public PRF readPRF (String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRF, new String[] { KEY_ID, "created" }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        PRF prf = new PRF(id);

        if (cursor != null) {
            cursor.moveToFirst();
            // Start with the PRF Table - should only be one!
            Date dd = Util.dbStringToDate(cursor.getString(1));
            Util.copyDate(prf.getCreatedAt(), dd);

            // Now copy the Incident Table
            cursor = db.query(TABLE_DISCHARGE, new String[]{KEY_ID, "walking_unaided", "walking_aided" , "other", "own_transport",
            "public_transport", "ambulance", "taxi", "completed", "hospital", "review", "advised", "time_left", "refused",
            "seen_by"}, KEY_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);

            cursor = db.query(TABLE_INCIDENT, new String[]{KEY_ID, "time", "location", "forname", "surname", "email", "address",
                    "postcode", "dob", "age", "gender", "kin"}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);


        }

        return prf;
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        Discharge.deleteTable(this);
        Incident.deleteTable(this);
        Notes.deleteTable(this);
        Observation.deleteTable(this);
        PRF.deleteTable(this);
        Primary.deleteTable(this);
        Secondary.deleteTable(this);
        Serious.deleteTable(this);
        Treatment.deleteTable(this);
        // Create tables again
        onCreate(db);
    }
}
