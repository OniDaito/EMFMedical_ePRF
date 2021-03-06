package uk.co.section9.emfmedical.data;

import android.content.ContentValues;

import java.util.Date;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 11/05/2016.
 */
public class Observation extends BaseData {

    protected Date _time;
    protected char _response;
    protected String _respiratory;
    protected String _pulse;
    protected int _painscore;
    protected float _o2sats;
    protected int _bp_sis;
    protected int _oborder;
    protected int _bp_dis;
    protected float _temperature;
    protected char _perl;
    protected char _eyes;

    protected static final String TABLE_NAME = "observations";

    public Observation() {
        _response = 'x';        // avpu or x
        _respiratory = "";      // -1 if not used
        _pulse = "";            // -1 if not used
        _oborder = 0;
        _painscore = -1;        // -1 or 1 to 10
        _o2sats = -1;           // -1 if not used
        _bp_sis = -1;           // -1 if not used
        _bp_dis = -1;           // -1 if not used
        _temperature = -1;      // -1 if not used
        _perl = 'x';            // y,n or x if unknown
        _eyes = 'x';            // c,n,u,d or x if unknown
        _time = new Date();
    }

    public static String createTable() {
        String CREATE_TABLE_OBSERVATIONS = "CREATE TABLE \"" +TABLE_NAME + "\" (\"time\" DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "\"response\" VARCHAR, \"respiratory\" TEXT, \"pulse\" TEXT, \"painscore\" INTEGER, " +
                "\"o2sats\" FLOAT, \"bp_sis\" INTEGER, \"bp_dis\" INTEGER, \"temperature\" FLOAT, \"perl\"" +
                " VARCHAR, \"eyes\" VARCHAR, \"oborder\" INTEGER, \"id\" GUID NOT NULL )";
        return CREATE_TABLE_OBSERVATIONS;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public String toXML() {
        String s;
        s = "<observation>\n";
        s += "<time>" + Util.dateToDBString(_time) + "</time>\n";
        s += "<response>" + responseConv() + "</response>\n";
        s += "<respiratory>" + _respiratory + "</respiratory>\n";
        s += "<pulse>" + _pulse + "</pulse>\n";
        s += "<painscore>" + painConv() + "</painscore>\n";
        s += "<o2sats>" + o2Conv() + "</o2sats>\n";
        s += "<temperature>" + tempConv() + "</temperature>\n";
        s += "<bpsis>" + sisConv() + "</bpsis>\n";
        s += "<bpdis>" + disConv() + "</bpdis>\n";
        s += "<perl>" + perlConv() + "</perl>\n";
        s += "<eyes>" + eyeConv() + "</eyes>\n";
        s += "</observation>\n";

        return s;
    }

    public ContentValues getValues () {
        ContentValues values = new ContentValues();
        values.put("time", Util.dateToDBString(_time));
        values.put("response", ""+_response);
        if (!_respiratory.equals(""))
         values.put("respiratory", _respiratory);
        if (!_pulse.equals(""))
            values.put("pulse", _pulse);
        values.put("painscore",Integer.toString(_painscore));
        values.put("o2sats", Float.toString(_o2sats));
        values.put("bp_sis",  Integer.toString(_bp_sis));
        values.put("bp_dis",  Integer.toString(_bp_dis));
        values.put("perl", ""+_perl);
        values.put("oborder", Integer.toString(_oborder));
        values.put("eyes", ""+_eyes);
        values.put("temperature", Float.toString(_temperature));
        return values;
    }


    public void setValues(ContentValues values) {
        _time = Util.dbStringToDate((String) values.get("time"));
        if(Util.isValidCharValue(values,"response")) { _response = ((String) values.get("response")).charAt(0); }
        _respiratory = ((String) values.get("respiratory"));
        _pulse = ((String) values.get("pulse"));
        _painscore = (Integer.parseInt((String)values.get("painscore")));
        _o2sats = ( Float.parseFloat( (String) values.get("o2sats")));
        _bp_sis = (Integer.parseInt((String) values.get("bp_sis")));
        _bp_dis = (Integer.parseInt((String) values.get("bp_dis")));
        _oborder = (Integer.parseInt((String)values.get("oborder")));
        if(Util.isValidCharValue(values,"perl")) { _perl = ((String) values.get("perl")).charAt(0); }
        if(Util.isValidCharValue(values,"eyes")) { _eyes = ((String) values.get("eyes")).charAt(0); }
        _temperature = Float.parseFloat((String) values.get("temperature"));

    }

    String painConv(){
        if (_painscore != 1) return new Integer(_painscore).toString();
        return "unknown";
    }

    String o2Conv(){
        if (_o2sats != 1) return new Float(_o2sats).toString();
        return "unknown";
    }

    String sisConv(){
        if (_bp_sis != 1) return new Integer(_bp_sis).toString();
        return "unknown";
    }

    String disConv(){
        if (_bp_dis != 1) return new Integer(_bp_dis).toString();
        return "unknown";
    }

    String tempConv(){
        if (_temperature != 1) return new Float(_temperature).toString();
        return "unknown";
    }

    String perlConv(){
        if (_response == 'y') return "yes";
        if (_response == 'n') return "no";
        if (_response == 'x') return "unknown";
        return "ERROR";
    }

    String responseConv(){
        if (_response == 'a') return "alert";
        if (_response == 'v') return "voice";
        if (_response == 'p') return "pain";
        if (_response == 'u') return "unconscious";
        if (_response == 'x') return "unknown";
        return "ERROR";
    }

    String eyeConv(){
        if (_response == 'c') return "constricted";
        if (_response == 'n') return "normal";
        if (_response == 'u') return "unequal";
        if (_response == 'd') return "dilated";
        if (_response == 'x') return "unknown";
        return "ERROR";
    }

    public Date get_time() {
        return _time;
    }

    public void set_time(Date _time) {
        this._time = _time;
    }

    public char get_response() {
        return _response;
    }

    public void set_response(char _response) {
        if (_response == 'a' || _response == 'v' || _response == 'p' ||  _response == 'u' || _response == 'x')
            this._response = _response;
    }

    public String get_respiratory() {
        return _respiratory;
    }

    public void set_respiratory(String _respiratory) {
        this._respiratory = _respiratory;
    }

    public String get_pulse() {
        return _pulse;
    }

    public void set_pulse(String _pulse) {
        this._pulse = _pulse;
    }

    public int get_painscore() {
        return _painscore;
    }

    public void set_painscore(int _painscore) {
        this._painscore = _painscore;
    }

    public float get_o2sats() {
        return _o2sats;
    }

    public void set_o2sats(float _o2sats) {
        this._o2sats = _o2sats;
    }

    public int get_bp_sis() {
        return _bp_sis;
    }

    public void set_bp_sis(int _bp_sis) {
        this._bp_sis = _bp_sis;
    }

    public int get_bp_dis() {
        return _bp_dis;
    }

    public void set_bp_dis(int _bp_dis) {
        this._bp_dis = _bp_dis;
    }

    public float get_temperature() {
        return _temperature;
    }

    public void set_temperature(float _temperature) {
        this._temperature = _temperature;
    }

    public char get_perl() {
        return _perl;
    }

    public void set_perl(char _perl) {
        if (_perl == 'y' || _perl == 'n' || _perl == 'x')
            this._perl = _perl;
    }

    public char get_eyes() {
        return _eyes;
    }

    public void set_eyes(char _eyes) {
        if (_eyes == 'c' || _eyes == 'n' || _eyes == 'u' || _eyes == 'd' || _eyes == 'x')
            this._eyes = _eyes;
    }

    public int get_oborder() {
        return _oborder;
    }

    public void set_oborder(int _oborder) {
        this._oborder = _oborder;
    }
}
