import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by oni on 14/05/2016.
 */
public class Secondary {

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

    Secondary() {
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

    String ynConv(char v) {
        if (v == 'y') return "yes";
        if (v == 'n') return "no";
        if (v == 'x') return "unknown";
        return "ERROR";
    }

    public String toXML() {
        String s;
        s = "<secondary>\n";
        s += "<high_blood_pressure>" + ynConv(_high_blood_pressure) + "</high_blood_pressure>\n";
        s += "<stroke>" + ynConv(_stroke) + "</stroke>\n";
        s += "<seizures>" + ynConv(_seizures) + "</seizures>\n";
        s += "<diabetes>" + ynConv(_diabetes) + "</diabetes>\n";
        s += "<cardiac>" + ynConv(_cardiac) + "</cardiac>\n";
        s += "<asthma>" + ynConv(_asthma) + "</asthma>\n";
        s += "<respiratory>" + ynConv(_respiratory) + "</respiratory>\n";
        s += "<fast>" + ynConv(_fast) + "</fast>\n";
        s += "<allergies>" + _allergies + "</allergies>\n";
        s += "<medications>" + _medications + "</medications>\n";
        s += "<history>" +  _history + "</history>\n";
        s += "</secondary>\n";

        return s;
    }

    public int dbUpdate(SQLiteDatabase db, String TABLE_PRIMARY, String id ){

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

        return db.update(TABLE_PRIMARY, values, "id = ?",
                new String[] { String.valueOf(id) });
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
