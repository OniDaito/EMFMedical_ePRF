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

    private static final String KEY_ID = "id";
    private static final String TABLE_PRF = "prfs";
    private static final String TABLE_INCIDENT = "incident";
    private static final String TABLE_PRIMARY = "primary";
    private static final String TABLE_SECONDARY = "secondary";
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_DISCHARGE = "discharge";
    private static final String TABLE_OBSERVATIONS = "observations";
    private static final String TABLE_SERIOUS = "serious";
    private static final String TABLE_TREATMENT = "treatment";

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
        Discharge.createTable(db, TABLE_DISCHARGE);
        Incident.createTable(db, TABLE_INCIDENT);
        Notes.createTable(db, TABLE_NOTES);
        Observation.createTable(db, TABLE_OBSERVATIONS);
        PRF.createTable(db, TABLE_PRF);
        Primary.createTable(db, TABLE_PRIMARY);
        Secondary.createTable(db, TABLE_SECONDARY);
        Serious.createTable(db, TABLE_SERIOUS);
        Treatment.createTable(db, TABLE_TREATMENT);
    }

    // Create a new PRF, setting the current one to this one
    public void writePRF(PRF prf){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", prf.id());
        values.put("created", prf.getCreatedAt().toLocaleString());

        db.insert(TABLE_PRF, null, values);

        // Write out base tables


        db.close(); // Closing database connection

    }


    public int updatePRF(PRF prf){
        int result;
        SQLiteDatabase db = this.getWritableDatabase();
        result = prf.get_discharge().dbUpdate(db,TABLE_DISCHARGE,prf.id());
        result = prf.get_incident().dbUpdate(db,TABLE_INCIDENT,prf.id());
        result = prf.get_notes().dbUpdate(db,TABLE_INCIDENT,prf.id());

        Vector<Observation> obs = prf.get_observations();
        for (Observation ob : obs) {
            result = ob.dbUpdate(db,TABLE_OBSERVATIONS,prf.id());
        }

        result = prf.get_primary().dbUpdate(db, TABLE_PRIMARY, prf.id());
        result = prf.get_secondary().dbUpdate(db, TABLE_SECONDARY, prf.id());
        result = prf.get_serious().dbUpdate(db, TABLE_SERIOUS, prf.id());
        result = prf.get_treatment().dbUpdate(db, TABLE_TREATMENT, prf.id());

        return result;
    }


    public void deletePRF(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRF, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        // Now remove from other tables
        db.delete(TABLE_DISCHARGE, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_INCIDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_OBSERVATIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_PRIMARY, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_SECONDARY, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_SERIOUS, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.delete(TABLE_TREATMENT, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});

        db.close();
    }

    public PRF readPRF (String id) {
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCIDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISCHARGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBSERVATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIMARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECONDARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIOUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREATMENT);

        // Create tables again
        onCreate(db);
    }
}
