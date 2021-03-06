package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 18/05/2016.
 */
public class Treatment extends BaseData {
    protected char _none;
    protected char _airway_opened;
    protected char _wound_cleaned;
    protected char _wound_dressed;
    protected char _rice;
    protected char _adhesive_dressing;
    protected char _sling;
    protected char _splint;
    protected char _recovery_position;
    protected String _other;

    protected static final String TABLE_NAME = "treatment";

    public Treatment() {
        _none = 'x';
        _airway_opened = 'x';
        _wound_cleaned = 'x';
        _wound_dressed = 'x';
        _rice = 'x';
        _adhesive_dressing = 'x';
        _sling = 'x';
        _splint = 'x';
        _recovery_position = 'x';
        _other = "";
    }

    public String toXML() {
        String s;
        s = "<treatment>\n";
        s += "<none>" + Util.ynConv(_none) + "</none>\n";
        s += "<airway_opened>" + Util.ynConv(_airway_opened) + "</airway_opened>\n";
        s += "<wound_cleaned>" + Util.ynConv(_wound_cleaned) + "</wound_cleaned>\n";
        s += "<wound_dressed>" + Util.ynConv(_wound_dressed) + "</wound_dressed>\n";
        s += "<rice>" + Util.ynConv(_rice) + "</rice>\n";
        s += "<adhesive_dressing>" + Util.ynConv(_adhesive_dressing) + "</adhesive_dressing>\n";
        s += "<sling>" + Util.ynConv(_sling) + "</sling>\n";
        s += "<splint>" + Util.ynConv(_splint) + "</splint>\n";
        s += "<recovery_position>" + Util.ynConv(_recovery_position) + "</recovery_position>\n";
        s += "<other>" + _other + "</other>\n";
        s += "</treatment>\n";

        return s;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }


    public static String createTable() {
        String CREATE_TABLE_TREATMENT = "CREATE TABLE \"" + TABLE_NAME + "\" (\"none\" BOOL, \"airway_opened\" BOOL, " +
                "\"wound_cleaned\" BOOL, \"wound_dressed\" BOOL, \"rice\" BOOL, \"adhesive_dressing\" BOOL, " +
                "\"sling\" BOOL, \"splint\" BOOL, \"recovery_position\" BOOL, \"other\" TEXT, \"id\"" +
                " GUID PRIMARY KEY  NOT NULL )";
        return CREATE_TABLE_TREATMENT;
    }

    public ContentValues getValues () {
        ContentValues values = new ContentValues();
        values.put("none", ""+_none);
        values.put("airway_opened", ""+_airway_opened);
        values.put("wound_cleaned", ""+_wound_cleaned);
        values.put("wound_dressed", ""+_wound_dressed);
        values.put("rice", ""+_rice);
        values.put("adhesive_dressing", ""+_adhesive_dressing);
        values.put("sling", ""+_sling);
        values.put("splint", ""+_splint);
        values.put("recovery_position", ""+_recovery_position);
        if (_other != "")
            values.put("other", _other);
        return values;
    }



    public void setValues(ContentValues values) {
        if(Util.isValidCharValue(values,"none")) { _none = ((String) values.get("none")).charAt(0); }
        if(Util.isValidCharValue(values,"airway_opened")) { _airway_opened = ((String) values.get("airway_opened")).charAt(0); }
        if(Util.isValidCharValue(values,"wound_cleaned")) { _wound_cleaned = ((String) values.get("wound_cleaned")).charAt(0); }
        if(Util.isValidCharValue(values,"wound_dressed")) { _wound_dressed = ((String) values.get("wound_dressed")).charAt(0); }
        if(Util.isValidCharValue(values,"rice")) { _rice = ((String) values.get("rice")).charAt(0); }
        if(Util.isValidCharValue(values,"adhesive_dressing")) { _adhesive_dressing = ((String) values.get("adhesive_dressing")).charAt(0); }
        if(Util.isValidCharValue(values,"sling")) { _sling = ((String) values.get("sling")).charAt(0); }
        if(Util.isValidCharValue(values,"splint")) { _splint = ((String) values.get("splint")).charAt(0); }
        if(Util.isValidCharValue(values,"recovery_position")) { _recovery_position = ((String) values.get("recovery_position")).charAt(0); }
        _other = ((String) values.get("other"));
    }


    public char get_none() {
        return _none;
    }

    public void set_none(char _none) {
        this._none = _none;
    }

    public char get_airway_opened() {
        return _airway_opened;
    }

    public void set_airway_opened(char _airway_opened) {
        this._airway_opened = _airway_opened;
    }

    public char get_wound_cleaned() {
        return _wound_cleaned;
    }

    public void set_wound_cleaned(char _wound_cleaned) {
        this._wound_cleaned = _wound_cleaned;
    }

    public char get_wound_dressed() {
        return _wound_dressed;
    }

    public void set_wound_dressed(char _wound_dressed) {
        this._wound_dressed = _wound_dressed;
    }

    public char get_rice() {
        return _rice;
    }

    public void set_rice(char _rice) {
        this._rice = _rice;
    }

    public char get_adhesive_dressing() {
        return _adhesive_dressing;
    }

    public void set_adhesive_dressing(char _adhesive_dressing) {
        this._adhesive_dressing = _adhesive_dressing;
    }

    public char get_sling() {
        return _sling;
    }

    public void set_sling(char _sling) {
        this._sling = _sling;
    }

    public char get_splint() {
        return _splint;
    }

    public void set_splint(char _splint) {
        this._splint = _splint;
    }

    public char get_recovery_position() {
        return _recovery_position;
    }

    public void set_recovery_position(char _recovery_position) {
        this._recovery_position = _recovery_position;
    }

    public String get_other() {
        return _other;
    }

    public void set_other(String _other) {
        this._other = _other;
    }
}
