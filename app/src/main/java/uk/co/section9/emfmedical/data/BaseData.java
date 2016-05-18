package uk.co.section9.emfmedical.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by oni on 18/05/2016.
 */
public class BaseData {

    protected static boolean checkTableExists(String table, SQLiteDatabase db){
        Cursor cursor = db.query("sqlite_master", new String[]{"name"}, "name=?",
                new String[]{table}, null, null, null, null);
        if(cursor != null){
            return true;
        }
        return false;
    }

}
