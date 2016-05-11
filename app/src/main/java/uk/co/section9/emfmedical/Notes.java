package uk.co.section9.emfmedical;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by oni on 11/05/2016.
 */
public class Notes {

    protected String _notes;

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

    public int dbUpdate(SQLiteDatabase db, String TABLE_NOTES, String id ){

        ContentValues values = new ContentValues();

        values.put("notes", _notes);

        return db.update(TABLE_NOTES, values, "id = ?",
                new String[] { String.valueOf(id) });
    }

    public String get_notes() {
        return _notes;
    }

    public void set_notes(String _notes) {
        this._notes = _notes;
    }


}
