package uk.co.section9.emfmedical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    private boolean checkTableExists(String name, SQLiteDatabase db) {
        Cursor cursor = db.query("sqlite_master", new String[]{"name"}, "name=?",
                new String[]{name}, null, null, null, null);
        if(cursor != null){
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create all the tables, presumably in memory
        // We double check to see if we have any database tables already
        String CREATE_INCIDENT_TABLE = "CREATE TABLE \"incident\" (\"time\" DATETIME" +
                ", \"location\" TEXT, \"forname\" TEXT, \"surname\" TEXT, \"email\" TEXT, " +
                "\"address\" TEXT, \"postcode\" TEXT, \"dob\" DATETIME, \"age\" INTEGER, \"gender\" VARCHAR, " +
                "\"kin\" TEXT, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("incident",db)) { db.execSQL(CREATE_INCIDENT_TABLE); }

        String CREATE_PRIMARY_TABLE = "CREATE TABLE \"primary\" (\"presenting\" TEXT, \"capacity\" BOOL, " +
                "\"consent\" BOOL, \"response\" VARCHAR, \"airway\" VARCHAR, \"breathing\" VARCHAR, \"circulation\"" +
                " VARCHAR, \"external\" BOOL, \"consciousness\" BOOL, \"alcoholdrugs\" BOOL, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";

        if (!checkTableExists("primary",db)) {db.execSQL(CREATE_PRIMARY_TABLE); }

        String CREATE_SECONDARY_TABLE = "CREATE TABLE \"secondary\" (\"high_blood_pressure\" BOOL, \"stroke\" BOOL," +
                " \"seizures\" BOOL, \"diabetes\" BOOL, \"cardiac\" BOOL, \"asthma\" BOOL, \"respiratory\"" +
                " BOOL, \"allergies\" TEXT, \"medications\" TEXT, \"history\" TEXT, \"fast\" BOOL, \"id\" TEXT PRIMARY KEY  NOT NULL )";

        if (!checkTableExists("secondary",db)) { db.execSQL(CREATE_SECONDARY_TABLE); }

        String CREATE_TABLE_NOTES = "CREATE TABLE \"notes\" (\"notes\" TEXT, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("notes",db)) { db.execSQL(CREATE_TABLE_NOTES); }

        String CREATE_TABLE_DISCHARGE = "CREATE TABLE \"discharge\" (\"walking_unaided\" BOOL, \"walking_aided\" BOOL, " +
                "\"other\" TEXT, \"own_transport\" BOOL, \"public_transport\" BOOL, \"ambulance\" BOOL, \"taxi\" BOOL, " +
                "\"completed\" BOOL, \"hospital\" BOOL, \"review\" BOOL, \"advised\" BOOL, \"time_left\" DATETIME, " +
                "\"refused\" BOOL, \"seen_by\" TEXT, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("discharge",db)) { db.execSQL(CREATE_TABLE_DISCHARGE); }

        String CREATE_TABLE_OBSERVATIONS = "CREATE TABLE \"observations\" (\"time\" DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "\"response\" VARCHAR, \"respiratory\" INTEGER, \"pulse\" INTEGER, \"painscore\" INTEGER, " +
                "\"o2sats\" INTEGER, \"bp_sis\" INTEGER, \"bp_dis\" INTEGER, \"temperature\" FLOAT, \"perl\"" +
                " BOOL, \"eyes\" VARCHAR, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("observations",db)) { db.execSQL(CREATE_TABLE_OBSERVATIONS); }

        String CREATE_TABLE_SERIOUS = "CREATE TABLE \"serious\" (\"ambulance_arrived\" DATETIME, " +
                "\"ambulance_departed\" DATETIME, \"cpr\" BOOL, \"cpr_started\" DATETIME, " +
                "\"defib_used\" BOOL, \"defib_shocks\" INTEGER, \"witnessed_collapse\" BOOL," +
                " \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("serious",db)) { db.execSQL(CREATE_TABLE_SERIOUS); }

        String CREATE_TABLE_TREATMENT = "CREATE TABLE \"treatment\" (\"none\" BOOL, \"airway_opened\" BOOL, " +
                "\"wound_cleaned\" BOOL, \"wound_dressed\" BOOL, \"rice\" BOOL, \"adhesive_dressing\" BOOL, " +
                "\"sling\" BOOL, \"splint\" BOOL, \"recovery_position\" BOOL, \"other\" TEXT, \"id\"" +
                " VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("treatment",db)) { db.execSQL(CREATE_TABLE_TREATMENT); }

        String CREATE_TABLE_PRFS = "CREATE TABLE \"prfs\" (\"id\" VARCHAR PRIMARY KEY  NOT NULL , \"created\" DATETIME)";
        if (!checkTableExists("prfs",db)) { db.execSQL(CREATE_TABLE_PRFS); }
    }

    // Create a new PRF, setting the current one to this one
    public void writePRF(PRF prf){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id", prf.id());
        values.put("created", prf.getCreatedAt().toLocaleString());

        db.insert(TABLE_PRF, null, values);
        db.close(); // Closing database connection

    }

    public int updatePRF(PRF prf){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", prf.getIncident().getTime().toLocaleString());
        values.put("location", prf.getIncident().getLocation());

        return db.update(TABLE_INCIDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(prf.id()) });
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

    private String dateToDBString(Date d){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private Date dbStringToDate(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            return dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(1970,1,1);
    }

    private void copyDate(Date from, Date to){
        to.setDate(from.getDate());
        to.setTime(from.getTime());
    }

    public PRF readPRF (String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRF, new String[] { KEY_ID, "created" }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        PRF prf = new PRF(id);

        if (cursor != null) {
            cursor.moveToFirst();
            // Start with the PRF Table - should only be one!
            Date dd = dbStringToDate(cursor.getString(1));
            copyDate(prf.getCreatedAt(), dd);

            // Now copy the Incident Table
            cursor = db.query(TABLE_INCIDENT, new String[]{KEY_ID, "time", "location"}, KEY_ID + "=?",
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
