package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by oni on 11/05/2016.
 */
public class Notes extends BaseData {

    protected String _notes;

    protected static final String TABLE_NAME = "notes";

    public Notes() {
        _notes = "";
    }

    public String toXML() {
        String s;
        s = "<notes>\n";
        s += get_notes() + "\n";
        s += "</notes>\n";
        return s;
    }

    public static void createTable(PRFDatabase db) {
        String CREATE_TABLE_NOTES = "CREATE TABLE \"" + TABLE_NAME + "\" (\"notes\" TEXT, \"id\" VARCHAR PRIMARY KEY NOT NULL )";
        if (!checkTableExists("notes",db)) { db.getWritableDatabase().execSQL(CREATE_TABLE_NOTES); }
    }

    public String get_notes() {
        return _notes;
    }
    public void set_notes(String _notes) {
        this._notes = _notes;
    }


}
