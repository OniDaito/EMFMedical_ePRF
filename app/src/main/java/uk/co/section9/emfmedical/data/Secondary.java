package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

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
    protected Date _fast_onset;
    protected String _allergies;
    protected String _medications;
    protected String _medical_history;
    protected String _history_presenting_complaint;

    protected static final String TABLE_NAME = "secondarysurvey";

    public Secondary() {
        _high_blood_pressure = 'x';
        _stroke = 'x';
        _seizures = 'x';
        _diabetes = 'x';
        _cardiac = 'x';
        _asthma = 'x';
        _respiratory = 'x';
        _fast = 'x';
        _fast_onset = new Date();
        _allergies = "";
        _medications = "";
        _medical_history = "";
        _history_presenting_complaint = "";
    }

    public static String createTable() {
        String CREATE_SECONDARY_TABLE = "CREATE TABLE \"" + TABLE_NAME + "\" (\"high_blood_pressure\" VARCHAR, \"stroke\" VARCHAR," +
                " \"seizures\" VARCHAR, \"diabetes\" VARCHAR, \"cardiac\" VARCHAR, \"asthma\" VARCHAR, \"respiratory\""  +
                " VARCHAR, \"fast_onset\" DATETIME, \"allergies\" TEXT, \"medications\" TEXT, \"medical_history\" TEXT, \"history_presenting_complaint\" TEXT, \"fast\" VARCHAR, \"id\" GUID PRIMARY KEY  NOT NULL )";

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
        s += "<fast_onset>" + Util.dateToDBString(_fast_onset) + "</fast_onset>\n";
        s += "<allergies>" + _allergies + "</allergies>\n";
        s += "<medications>" + _medications + "</medications>\n";
        s += "<medical_history>" +  _medical_history + "</medical_history>\n";
        s += "<history_presenting_complaint>" +  _history_presenting_complaint + "</history_presenting_complaint>\n";
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
        values.put("fast_onset", Util.dateToDBString(_fast_onset));
        if (_allergies != "")
            values.put("allergies", _allergies);
        if (_medications != "")
            values.put("medications", _medications);
        if (_medical_history != "")
            values.put("medical_history", _medical_history);
        if (_history_presenting_complaint != "")
            values.put("history_presenting_complaint", _history_presenting_complaint);
        return values;
    }

    public void setValues(ContentValues values) {
        if(Util.isValidCharValue(values,"high_blood_pressure")) { _high_blood_pressure = ((String) values.get("high_blood_pressure")).charAt(0); }
        if(Util.isValidCharValue(values,"stroke")) { _stroke = ((String) values.get("stroke")).charAt(0); }
        if(Util.isValidCharValue(values,"seizures")) { _seizures = ((String) values.get("seizures")).charAt(0); }
        if(Util.isValidCharValue(values,"diabetes")) { _diabetes = ((String) values.get("diabetes")).charAt(0); }
        if(Util.isValidCharValue(values,"cardiac")) { _cardiac = ((String) values.get("cardiac")).charAt(0); }
        if(Util.isValidCharValue(values,"asthma")) { _asthma = ((String) values.get("asthma")).charAt(0); }
        if(Util.isValidCharValue(values,"respiratory")) { _respiratory = ((String) values.get("respiratory")).charAt(0); }
        if(Util.isValidCharValue(values,"fast")) { _fast = ((String) values.get("fast")).charAt(0); }
        _fast_onset = Util.dbStringToDate((String) values.get("fast_onset"));
        _allergies = ((String) values.get("allergies"));
        _medications = ((String) values.get("medications"));
        _medical_history = ((String) values.get("medical_history"));
        _history_presenting_complaint = ((String) values.get("history_presenting_complaint"));
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

    public String get_medical_history() {
        return _medical_history;
    }

    public void set_medical_history(String _history) {
        this._medical_history = _history;
    }

    public String get_history_presenting_complaint() {
        return _history_presenting_complaint;
    }

    public void set_history_presenting_complaint(String _history) {
        this._history_presenting_complaint = _history;
    }

    public void set_fast_onset(Date dd){
        _fast_onset = dd;
    }

    public Date get_fast_onset(){
        return  _fast_onset;
    }
}
