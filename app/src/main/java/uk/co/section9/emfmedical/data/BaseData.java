package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by oni on 18/05/2016.
 */
public class BaseData {

    protected static String TABLE_NAME = "none";
    protected static final String KEY_ID = "id";

    protected static boolean checkTableExists(String table, PRFDatabase db){
        Cursor cursor = db.getReadableDatabase().query("sqlite_master", new String[]{"name"}, "name=?",
                new String[]{table}, null, null, null, null);
        if(cursor != null){
            return true;
        }
        return false;
    }

    protected void _set_values(ContentValues values){

    }

    public static void createTable(PRFDatabase db){

    }

    public static void deleteTable(PRFDatabase db){
        db.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    protected int dbUpdate( PRFDatabase db, String id ){
        ContentValues values = new ContentValues();
        _set_values(values);
        return db.getWritableDatabase().update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // Sets the current data values to these found inside the db
    protected void dbRead(PRFDatabase db, String id){

    }

    protected void dbNew(PRFDatabase db, String id) {
        ContentValues values = new ContentValues();
        _set_values(values);
        db.getWritableDatabase().insert(TABLE_NAME, null, values); // Could return value here
    }

    protected void dbDelete(PRFDatabase db, String id){
        db.getWritableDatabase().delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});
    }


}
