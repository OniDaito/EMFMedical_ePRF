package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ObjectStreamException;
import java.security.SecurityPermission;
import java.util.Date;
import java.util.Vector;

import javax.crypto.SealedObject;

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

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = \""+ tablename +"\"", null);
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

    // Update the values in the table for the current PRF
    public void updatePRF(PRF prf) {

        ContentValues values;
        SQLiteDatabase db = getWritableDatabase();

        values = prf.getValues();
        db.update(PRF.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        values = prf.get_discharge().getValues();
        db.update(Discharge.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        values = prf.get_incident().getValues();
        db.update(Incident.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        values = prf.get_notes().getValues();
        db.update(Notes.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        Vector<Observation> obs = prf.get_observations();
        for (Observation ob : obs) {
            values = ob.getValues();
            db.update(Observation.get_table_name(), values, "id = ?", new String[]{prf.get_uuid()});
        }

        values = prf.get_primary().getValues();
        db.update(Primary.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        values = prf.get_secondary().getValues();
        db.update(Secondary.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        values = prf.get_serious().getValues();
        db.update(Serious.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

        values = prf.get_treatment().getValues();
        db.update(Treatment.get_table_name(), values, "id = ?", new String[] {prf.get_uuid()});

    }

    public void writePRF(PRF prf) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;

        values = prf.getValues();
        db.insert(PRF.get_table_name(), null, values);

        values = prf.get_discharge().getValues();
        values.put("id", prf.get_uuid());
        db.insert(Discharge.get_table_name(), null, values);

        values = prf.get_incident().getValues();
        values.put("id", prf.get_uuid());
        db.insert(Incident.get_table_name(), null, values);

        values = prf.get_notes().getValues();
        values.put("id", prf.get_uuid());
        db.insert(Notes.get_table_name(), null, values);

        Vector<Observation> obs = prf.get_observations();
        for (Observation ob : obs) {
            values = ob.getValues();
            values.put("id", prf.get_uuid());
            db.insert(Observation.get_table_name(), null, values);
        }

        values = prf.get_primary().getValues();
        values.put("id",  prf.get_uuid());
        db.insert(Primary.get_table_name(), null, values);

        values = prf.get_secondary().getValues();
        values.put("id", prf.get_uuid());
        db.insert(Secondary.get_table_name(), null, values);

        values = prf.get_serious().getValues();
        values.put("id", prf.get_uuid());
        db.insert(Serious.get_table_name(), null, values);

        values = prf.get_treatment().getValues();
        values.put("id", prf.get_uuid());
        db.insert(Treatment.get_table_name(), null, values);

        db.close();
    }

    public void deletePRF(PRF prf) {
        SQLiteDatabase db = getWritableDatabase();
        String id = prf.get_uuid();
        db.delete(Discharge.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Incident.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Notes.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Observation.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(PRF.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Primary.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Secondary.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Serious.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
        db.delete(Treatment.get_table_name(), "id = ?", new String[] { String.valueOf(id)});
    }

    // Get the number of rows for a uuid
    private int _getNumRows(String tablename, String uuid){
        int result = 0;
        Cursor cursor = getReadableDatabase().rawQuery("select count(*) from \"" + tablename + "\" where id = '" + uuid + "';", null);
        if (cursor != null) {
            cursor.moveToFirst();
            result = cursor.getInt(0);
        }
        cursor.close();
        return result;
    }

    // reads the first cursor
    private ContentValues _readRow(String tablename, String  uuid, int rownumber) {
        int result = 0;
        ContentValues values = new ContentValues();
        Cursor cursor = getReadableDatabase().rawQuery("select * from \"" + tablename + "\" where id = \"" + uuid + "\"", null);

        cursor.moveToPosition(rownumber);

        for (int i = 0; i < cursor.getColumnCount(); i++){
            values.put(cursor.getColumnName(i),cursor.getString(i));
        }


        cursor.close();
        return values;
    }

    public PRF readPRF(String uuid) {
        PRF prf = new PRF(uuid);

        // Should only be one row for the all of the fields bar obs
        ContentValues values;
        values = _readRow(PRF.get_table_name(),uuid,0);
        prf.setValues(values);

        values = _readRow(Discharge.get_table_name(), uuid, 0);
        prf.get_discharge().setValues(values);

        values = _readRow(Incident.get_table_name(), uuid, 0);
        prf.get_incident().setValues(values);

        values = _readRow(Notes.get_table_name(), uuid, 0);
        prf.get_notes().setValues(values);

        // Obs
        int numrows = _getNumRows(Observation.get_table_name(),uuid);

        for (int i =0; i < numrows; i++){
            values = _readRow(Observation.get_table_name(), uuid, i);
            Observation ob = new Observation();
            ob.setValues(values);
            prf.get_observations().add(ob);
        }

        values = _readRow(Primary.get_table_name(), uuid, 0);
        prf.get_primary().setValues(values);

        values = _readRow(Secondary.get_table_name(), uuid, 0);
        prf.get_secondary().setValues(values);

        values = _readRow(Serious.get_table_name(), uuid, 0);
        prf.get_serious().setValues(values);

        values = _readRow(Treatment.get_table_name(), uuid, 0);
        prf.get_treatment().setValues(values);

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
