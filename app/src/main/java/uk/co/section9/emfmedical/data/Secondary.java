package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 14/05/2016.
 */
public class Secondary extends BaseData{

    protected char _high_blood_pressure;
    protected char _stroke;
    protected char _seizures;
    protected char _diabetes;
    protected char _cardiac;
    protected char _asthma;
    protected char _respiratory;
    protected char _fast;
    protected String _allergies;
    protected String _medications;
    protected String _history;

    protected static final String TABLE_NAME = "secondary";

    public Secondary() {
        _high_blood_pressure = 'x';
        _stroke = 'x';
        _seizures = 'x';
        _diabetes = 'x';
        _cardiac = 'x';
        _asthma = 'x';
        _respiratory = 'x';
        _fast = 'x';

        _allergies = "";
        _medications = "";
        _history = "";
    }

    public static String createTable() {
        String CREATE_SECONDARY_TABLE = "CREATE TABLE \"" + TABLE_NAME + "\" (\"high_blood_pressure\" BOOL, \"stroke\" BOOL," +
                " \"seizures\" BOOL, \"diabetes\" BOOL, \"cardiac\" BOOL, \"asthma\" BOOL, \"respiratory\"" +
                " BOOL, \"allergies\" TEXT, \"medications\" TEXT, \"history\" TEXT, \"fast\" BOOL, \"id\" TEXT PRIMARY KEY  NOT NULL )";

        return CREATE_SECONDARY_TABLE;
    }

    public static void deleteTable(SQLiteDatabase db){

    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public String toXML() {
        String s;
        s = "<secondary>\n";
        s += "<high_blood_pressure>" + Util.ynConv(_high_blood_pressure) + "</high_blood_pressure>\n";
        s += "<stroke>" + Util.ynConv(_stroke) + "</stroke>\n";
        s += "<seizures>" + Util.ynConv(_seizures) + "</seizures>\n";
        s += "<diabetes>" + Util.ynConv(_diabetes) + "</diabetes>\n";
        s += "<cardiac>" + Util.ynConv(_cardiac) + "</cardiac>\n";
        s += "<asthma>" + Util.ynConv(_asthma) + "</asthma>\n";
        s += "<respiratory>" + Util.ynConv(_respiratory) + "</respiratory>\n";
        s += "<fast>" + Util.ynConv(_fast) + "</fast>\n";
        s += "<allergies>" + _allergies + "</allergies>\n";
        s += "<medications>" + _medications + "</medications>\n";
        s += "<history>" +  _history + "</history>\n";
        s += "</secondary>\n";

        return s;
    }

    public ContentValues getValues () {
        ContentValues values = new ContentValues();
        values.put("high_blood_pressure", ""+_high_blood_pressure);
        values.put("stroke", ""+_stroke);
        values.put("seizures", ""+_seizures);
        values.put("diabetes", ""+_diabetes);
        values.put("cardiac", ""+_cardiac);
        values.put("asthma", ""+_asthma);
        values.put("respiratory", ""+_respiratory);
        values.put("fast", ""+_fast);
        values.put("allergies", _allergies);
        values.put("medications", _medications);
        values.put("history", _history);
        return values;
    }

    public void setValues(ContentValues values) {
        _high_blood_pressure = ((String) values.get("high_blood_pressure")).charAt(0);
        _stroke = ((String) values.get("stroke")).charAt(0);
        _seizures = ((String) values.get("seizures")).charAt(0);
        _diabetes = ((String) values.get("diabetes")).charAt(0);
        _cardiac = ((String) values.get("cardiac")).charAt(0);
        _asthma = ((String) values.get("asthma")).charAt(0);
        _respiratory = ((String) values.get("respiratory")).charAt(0);
        _fast = ((String) values.get("fast")).charAt(0);
        _allergies = ((String) values.get("allergies"));
        _medications = ((String) values.get("medications"));
        _history = ((String) values.get("history"));
    }

    public char get_high_blood_pressure() {
        return _high_blood_pressure;
    }

    public void set_high_blood_pressure(char _high_blood_pressure) {
        if (_high_blood_pressure == 'y' || _high_blood_pressure == 'n' || _high_blood_pressure == 'x' )
            this._high_blood_pressure = _high_blood_pressure;
    }

    public char get_stroke() {
        return _stroke;
    }

    public void set_stroke(char _stroke) {
        if (_stroke == 'y' || _stroke == 'n' || _stroke == 'x' )
            this._stroke = _stroke;
    }

    public char get_seizures() {
        return _seizures;
    }

    public void set_seizures(char _seizures) {
        if (_seizures == 'y' || _seizures == 'n' || _seizures == 'x' )
            this._seizures = _seizures;
    }

    public char get_diabetes() {
        return _diabetes;
    }

    public void set_diabetes(char _diabetes) {
        if (_diabetes == 'y' || _diabetes == 'n' || _diabetes == 'x' )
            this._diabetes = _diabetes;
    }

    public char get_cardiac() {
        return _cardiac;
    }

    public void set_cardiac(char _cardiac) {
        if (_cardiac == 'y' || _cardiac == 'n' || _cardiac == 'x' )
            this._cardiac = _cardiac;
    }

    public char get_asthma() {
        return _asthma;
    }

    public void set_asthma(char _asthma) {
        if (_asthma == 'y' || _asthma == 'n' || _asthma == 'x' )
            this._asthma = _asthma;
    }

    public char get_respiratory() {
        return _respiratory;
    }

    public void set_respiratory(char _respiratory) {
        if (_respiratory == 'y' || _respiratory == 'n' || _respiratory == 'x' )
            this._respiratory = _respiratory;
    }

    public String get_allergies() {
        return _allergies;
    }

    public void set_allergies(String _allergies) {
        this._allergies = _allergies;
    }

    public char get_fast() {
        return _fast;
    }

    public void set_fast(char _fast) {
        if (_fast == 'y' || _fast == 'n' || _fast == 'x' )
            this._fast = _fast;
    }

    public String get_medications() {
        return _medications;
    }

    public void set_medications(String _medications) {
        this._medications = _medications;
    }

    public String get_history() {
        return _history;
    }

    public void set_history(String _history) {
        this._history = _history;
    }

}
