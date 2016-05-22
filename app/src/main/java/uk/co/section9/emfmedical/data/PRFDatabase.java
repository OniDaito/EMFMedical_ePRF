package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    }

    protected boolean checkTableExists(String tablename, SQLiteDatabase db){

        Log.d("OUTPUT", "CHECKING " +tablename);

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+ tablename +"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public void createTable(String create_string, String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        checkTableExists(tablename,db);
        db.execSQL(create_string);
    }

    public void reset() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS \"" + Discharge.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Incident.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Notes.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Observation.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + PRF.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Primary.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Secondary.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Serious.get_table_name() + "\"");
        db.execSQL("DROP TABLE IF EXISTS \"" + Treatment.get_table_name() + "\"");

        onCreate(db);
    }

    public void deleteTable(String tablename){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
    }

    public int updatePRF(PRF prf) {
        //return getWritableDatabase().update(TABLE_NAME, values, KEY_ID + " = ?",
        //        new String[]{String.valueOf(id)});
        return -1;
    }

    public void newPRF(PRF prf) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;

        values = prf.getValues();
        db.insert(PRF.get_table_name(), null, values);

        values = prf.get_discharge().getValues();
        db.insert(Discharge.get_table_name(), null, values);

    }

    public void deletePRF() {

        //getWritableDatabase().delete(TABLE_NAME, KEY_ID + " = ?",
        //        new String[] { String.valueOf(id)});
    }

    public PRF readPRF(String uuid) {
        PRF prf = new PRF(uuid);

        ContentValues values;

        return prf;
    }

    private void _check_and_create(SQLiteDatabase db) {
        if (!checkTableExists(Discharge.get_table_name(), db)) { db.execSQL(Discharge.createTable()); }
        if (!checkTableExists(Incident.get_table_name(), db)) { db.execSQL(Incident.createTable());}
        if (!checkTableExists(Notes.get_table_name(), db)) { db.execSQL(Notes.createTable());}
        if (!checkTableExists(Observation.get_table_name(), db)) { db.execSQL(Observation.createTable()); }
        if (!checkTableExists(PRF.get_table_name(), db)) { db.execSQL(PRF.createTable());}
        if (!checkTableExists(Primary.get_table_name(), db)) { db.execSQL(Primary.createTable());}
        if (!checkTableExists(Secondary.get_table_name(), db)) { db.execSQL(Secondary.createTable());}
        if (!checkTableExists(Serious.get_table_name(), db)) { db.execSQL(Serious.createTable());}
        if (!checkTableExists(Treatment.get_table_name(), db)) { db.execSQL(Treatment.createTable());}
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (db.isReadOnly()){
            db = getWritableDatabase();
        }
        _check_and_create(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create all the tables, presumably in memory
        // We double check to see if we have any database tables already]
        _check_and_create(db);
    }

    public Vector<String> listPRFS() {
        Vector<String> results = new Vector<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id from " + PRF.TABLE_NAME + ";", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                results.add(cursor.getString(0));
                while (cursor.moveToNext()) {
                    results.add(cursor.getString(0));
                }
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
        db.execSQL("DROP TABLE IF EXISTS " + Discharge.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " +Incident.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + Notes.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + Observation.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + PRF.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + Primary.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + Secondary.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + Serious.get_table_name());
        db.execSQL("DROP TABLE IF EXISTS " + Treatment.get_table_name());
        // Create tables again
        onCreate(db);
    }
}
