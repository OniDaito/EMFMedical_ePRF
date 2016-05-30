package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 14/05/2016.
 */
public class Serious extends BaseData {
    protected Date _ambulance_arrived;
    protected Date _ambulance_departed;
    protected char _cpr;
    protected char _ambulance_called;
    protected Date _cpr_started;
    protected char _defib_used;
    protected int _defib_shocks;
    protected char _witnessed_collapse;

    protected static final String TABLE_NAME = "serious";

    public Serious() {
        _ambulance_arrived = new Date();
        _ambulance_departed = new Date();
        _cpr = 'x';
        _cpr_started = new Date();
        _defib_used = 'x';
        _defib_shocks = 0;
        _witnessed_collapse = 'x';

    }

    public static String createTable() {
        String CREATE_TABLE_SERIOUS = "CREATE TABLE \"" + TABLE_NAME +"\" (\"ambulance_arrived\" DATETIME, " +
                "\"ambulance_departed\" DATETIME, \"ambulance_called\" VARCHAR, \"cpr\" VARCHAR, \"cpr_started\" DATETIME, " +
                "\"defib_used\" VARCHAR, \"defib_shocks\" INTEGER, \"witnessed_collapse\" VARCHAR," +
                " \"id\" GUID PRIMARY KEY  NOT NULL )";
        return CREATE_TABLE_SERIOUS;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public String toXML() {
        String s;
        s = "<serious>\n";
        s += "<ambulance_called>" + Util.ynConv(_ambulance_called) + "</ambulance_called>\n";
        s += "<ambulance_arrived>" + Util.dateToDBString(_ambulance_arrived) + "</ambulance_arrived>\n";
        s += "<ambulance_departed>" + Util.dateToDBString(_ambulance_departed) + "</ambulance_departed>\n";
        s += "<cpr>" + Util.ynConv(_cpr) + "</cpr>\n";
        s += "<cpr_started>" + Util.dateToDBString(_cpr_started) + "</cpr_started>\n";
        s += "<defib_used>" + Util.ynConv(_defib_used) + "</defib_used>\n";
        s += "<defib_shocks>" + ""+_defib_shocks + "</defib_shocks>\n";
        s += "<witnessed_collapse>" + Util.ynConv(_witnessed_collapse) + "</witnessed_collapse>\n";
        s += "</serious>\n";

        return s;
    }

    public ContentValues getValues () {
        ContentValues values = new ContentValues();
        values.put("ambulance_called", ""+_ambulance_called);
        values.put("ambulance_arrived", Util.dateToDBString(_ambulance_arrived));
        values.put("ambulance_departed", Util.dateToDBString(_ambulance_departed));
        values.put("cpr", ""+_cpr);
        values.put("cpr_started", Util.dateToDBString(_cpr_started));
        values.put("defib_used", ""+_defib_used);
        values.put("defib_shocks", ""+_defib_shocks);
        values.put("witnessed_collapse", ""+_witnessed_collapse);
        return values;
    }

    // TODO - probably should use the setter and getter here
    public void setValues(ContentValues values) {
        _ambulance_arrived = Util.dbStringToDate((String) values.get("ambulance_arrived"));
        if (Util.isValidCharValue(values,"ambulance_called")){ _ambulance_called = ((String) values.get("ambulance_called")).charAt(0);}
        _ambulance_departed = Util.dbStringToDate((String) values.get("ambulance_departed"));
        if (Util.isValidCharValue(values,"cpr")){_cpr = ((String) values.get("cpr")).charAt(0);}
        _cpr_started = Util.dbStringToDate((String) values.get("cpr_started"));
        if (Util.isValidCharValue(values,"defib_used")){_defib_used = ((String) values.get("defib_used")).charAt(0);}
        if (Util.isValidCharValue(values,"defib_shocks")){_defib_shocks = ((String) values.get("defib_shocks")).charAt(0);}
        if (Util.isValidCharValue(values,"witnessed_collapse")){ _witnessed_collapse = ((String) values.get("witnessed_collapse")).charAt(0);}
    }

    public Date get_ambulance_arrived() {
        return _ambulance_arrived;
    }

    public void set_ambulance_arrived(Date _ambulance_arrived) {
        this._ambulance_arrived = _ambulance_arrived;
    }

    public char get_ambulance_called() {
        return _ambulance_called;
    }

    public void set_ambulance_called(char _ambulance_called) {
        if (_ambulance_called == 'y' || _ambulance_called == 'n' || _ambulance_called == 'x' )
            this._ambulance_called = _ambulance_called;
    }

    public Date get_ambulance_departed() {
        return _ambulance_departed;
    }

    public void set_ambulance_departed(Date _ambulance_departed) {
        this._ambulance_departed = _ambulance_departed;
    }

    public char get_cpr() {
        return _cpr;
    }

    public void set_cpr(char _cpr) {
        if (_cpr == 'y' || _cpr == 'n' || _cpr == 'x' )
            this._cpr = _cpr;
    }

    public Date get_cpr_started() {
        return _cpr_started;
    }

    public void set_cpr_started(Date _cpr_started) {
        this._cpr_started = _cpr_started;
    }

    public char get_defib_used() {
        return _defib_used;
    }

    public void set_defib_used(char _defib_used) {
        if (_defib_used == 'y' || _defib_used == 'n' || _defib_used == 'x' )
            this._defib_used = _defib_used;
    }

    public int get_defib_shocks() {
        return _defib_shocks;
    }

    public void set_defib_shocks(int _defib_shocks) {
        this._defib_shocks = _defib_shocks;
    }

    public char get_witnessed_collapse() {
        return _witnessed_collapse;
    }

    public void set_witnessed_collapse(char _witnessed_collapse) {
        if (_witnessed_collapse == 'y' || _witnessed_collapse == 'n' || _witnessed_collapse == 'x' )
            this._witnessed_collapse = _witnessed_collapse;
    }
}
