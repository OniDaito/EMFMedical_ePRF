package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 11/05/2016.
 */
public class Primary extends BaseData {

    protected String _presenting;
    protected char _capacity;
    protected char _consent;
    protected char _response;
    protected char _airway;
    protected char _breathing;
    protected char _circulation;
    protected char _external;
    protected char _consciousness;
    protected char _alcoholdrugs;

    protected static final String TABLE_NAME = "primarysurvey";

    public Primary() {
        _presenting = "";
        _capacity = 'x';
        _consent = 'x';
        _response = 'x';
        _airway = 'x';
        _breathing = 'x';
        _circulation = 'x';
        _external = 'x';
        _consciousness = 'x';
        _alcoholdrugs = 'x';
    }

    public static String createTable() {
        String CREATE_PRIMARY_TABLE = "CREATE TABLE \"" + TABLE_NAME + "\" (\"presenting\" TEXT, \"capacity\" VARCHAR, " +
                "\"consent\" VARCHAR, \"response\" VARCHAR, \"airway\" VARCHAR, \"breathing\" VARCHAR, \"circulation\"" +
                " VARCHAR, \"external\" VARCHAR, \"consciousness\" VARCHAR, \"alcoholdrugs\" VARCHAR, \"id\" GUID PRIMARY KEY NOT NULL )";
        return CREATE_PRIMARY_TABLE;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public String toXML() {
        String s;
        s = "<primary>\n";
        s += "<presenting>" + _presenting + "</presenting>\n";
        s += "<response>" + responseConv() + "</response>\n";
        s += "<capacity>" + ynConv(_response) + "</capacity>\n";
        s += "<consent>" + ynConv(_response) + "</consent>\n";
        s += "<airway>" + airwayConv() + "</airway>\n";
        s += "<breathing>" + breathingConv() + "</breathing>\n";
        s += "<circulation>" + circulationConv() + "</circulation>\n";
        s += "<external>" + ynConv(_external) + "</external>\n";
        s += "<consciousness>" + ynConv(_consciousness) + "</consciousness>\n";
        s += "<alcohol_drugs>" + ynConv(_alcoholdrugs) + "</alcohol_drugs>\n";
        s += "</primary>\n";

        return s;
    }

    public ContentValues getValues () {
        ContentValues values = new ContentValues();
        if (_presenting != "")
            values.put("presenting", _presenting);
        values.put("capacity", ""+_capacity);
        values.put("consent", ""+_consent);
        values.put("response", ""+_response);
        values.put("airway", ""+_airway);
        values.put("breathing", ""+_breathing);
        values.put("circulation", ""+_circulation);
        values.put("external", ""+_external);
        values.put("consciousness", ""+_consciousness);
        values.put("alcoholdrugs", ""+_alcoholdrugs);
        return values;
    }

    public void setValues(ContentValues values) {
        _presenting = ((String) values.get("presenting"));
        if(Util.isValidCharValue(values,"response")) { _response = ((String) values.get("response")).charAt(0); }
        if(Util.isValidCharValue(values,"capacity")) { _capacity = ((String) values.get("capacity")).charAt(0); }
        if(Util.isValidCharValue(values,"consent")) { _consent = ((String) values.get("consent")).charAt(0); }
        if(Util.isValidCharValue(values,"airway")) { _airway = ((String) values.get("airway")).charAt(0); }
        if(Util.isValidCharValue(values,"breathing")) { _breathing = ((String) values.get("breathing")).charAt(0); }
        if(Util.isValidCharValue(values,"circulation")) { _circulation = ((String) values.get("circulation")).charAt(0); }
        if(Util.isValidCharValue(values,"external")) { _external = ((String) values.get("external")).charAt(0); }
        if(Util.isValidCharValue(values,"consciousness")) { _consciousness = ((String) values.get("consciousness")).charAt(0); }
        if(Util.isValidCharValue(values,"alcoholdrugs")) { _alcoholdrugs = ((String) values.get("alcoholdrugs")).charAt(0); }
    }

    String responseConv() {
        if (_response == 'a') return "alert";
        if (_response == 'v') return "voice";
        if (_response == 'p') return "pain";
        if (_response == 'u') return "unconscious";
        if (_response == 'x') return "unknown";
        return "ERROR";
    }

    String airwayConv() {
        if (_airway == 'c') return "clear";
        if (_airway == 'o') return "obstructed";
        if (_airway == 'x') return "unknown";
        return "ERROR";
    }

    String ynConv(char v) {
        if (v == 'y') return "yes";
        if (v == 'n') return "no";
        if (v == 'x') return "unknown";
        return "ERROR";
    }

    String breathingConv() {
        if (_breathing == 'n') return "normal";
        if (_breathing == 's') return "shallow";
        if (_breathing == 'r') return "rapid";
        if (_breathing == 'a') return "agonal";
        if (_breathing == 'b') return "absent";
        if (_breathing == 'x') return "unknown";
        return "ERROR";
    }

    String circulationConv() {
        if (_circulation == 'n') return "normal";
        if (_circulation == 'p') return "pale";
        if (_circulation == 'f') return "flushed";
        if (_circulation == 'c') return "cyanosed";
        if (_circulation == 'x') return "unknown";
        return "ERROR";
    }

    public String get_presenting() {
        return _presenting;
    }

    public void set_presenting(String _presenting) {
        this._presenting = _presenting;
    }

    public char get_capacity() {
        return _capacity;
    }

    public void set_capacity(char _capacity) {
        if (_capacity == 'y' || _capacity == 'n' || _capacity == 'x' )
            this._capacity = _capacity;
    }

    public char get_consent() {
        return _consent;
    }

    public void set_consent(char _consent) {
        if (_consent == 'y' || _consent == 'n' || _consent == 'x' )
            this._consent = _consent;
    }

    public char get_response() {
        return _response;
    }

    public void set_response(char _response) {
        if (_response == 'a' || _response == 'v' || _response == 'p' || _response == 'u' || _response == 'x' )
            this._response = _response;
    }

    public char get_airway() {
        return _airway;
    }

    public void set_airway(char _airway) {
        if (_airway == 'o' || _airway == 'c' || _airway == 'x' )
            this._airway = _airway;
    }

    public char get_breathing() {
        return _breathing;
    }

    public void set_breathing(char _breathing) {
        if (_breathing == 'n' || _breathing == 's' || _breathing == 'r' || _breathing == 'a' || _breathing == 'b' || _breathing == 'x')
            this._breathing = _breathing;
    }

    public char get_circulation() {
        return _circulation;
    }

    public void set_circulation(char _circulation) {
        if (_circulation == 'n' || _circulation == 'p' || _circulation == 'f' || _circulation == 'c' || _circulation == 'x')
            this._circulation = _circulation;
    }

    public char get_external() {
        return _external;
    }

    public void set_external(char _external) {
        if (_external == 'y' || _external == 'n' || _external == 'x' )
            this._external = _external;
    }

    public char get_consciousness() {
        return _consciousness;
    }

    public void set_consciousness(char _consciousness) {
        if (_consciousness == 'y' || _consciousness == 'n' || _consciousness == 'x' )
            this._consciousness = _consciousness;
    }

    public char get_alcoholdrugs() {
        return _alcoholdrugs;
    }

    public void set_alcoholdrugs(char _alcoholdrugs) {
        if (_alcoholdrugs == 'y' || _alcoholdrugs == 'n' || _alcoholdrugs == 'x' )
            this._alcoholdrugs = _alcoholdrugs;
    }
}
