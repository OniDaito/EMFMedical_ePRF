package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 11/05/2016.
 */
public class Notes extends BaseData {

    protected String _notes;

    protected static final String TABLE_NAME = "notes";

    public Notes() {
        _notes = new String();
    }

    public String toXML() {
        String s;
        s = "<notes>\n";
        s += get_notes() + "\n";
        s += "</notes>\n";
        return s;
    }

    public static String createTable() {
        String CREATE_TABLE_NOTES = "CREATE TABLE \"" + TABLE_NAME + "\" (\"notes\" TEXT, \"id\" GUID PRIMARY KEY NOT NULL )";
        return CREATE_TABLE_NOTES;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        values.put("notes", get_notes());
        return values;
    }

    public void setValues(ContentValues values) {
        _notes = ((String) values.get("notes"));
    }

    public String get_notes() {
        return _notes;
    }
    public void set_notes(String _notes) {
        this._notes = _notes;
    }


}
