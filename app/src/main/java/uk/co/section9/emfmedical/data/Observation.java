package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.ViewDebug;

import java.util.Date;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 11/05/2016.
 */
public class Observation extends BaseData {

    protected Date _time;
    protected char _response;
    protected int _respiratory;
    protected int _pulse;
    protected int _painscore;
    protected float _o2sats;
    protected int _bp_sis;
    protected int _bp_dis;
    protected float _temperature;
    protected char _perl;
    protected char _eyes;

    protected static final String TABLE_NAME = "observations";

    public Observation() {
        _response = 'x';        // avpu or x
        _respiratory = -1;      // -1 if not used
        _pulse = -1;            // -1 if not used
        _painscore = -1;        // -1 or 1 to 10
        _o2sats = -1;           // -1 if not used
        _bp_sis = -1;           // -1 if not used
        _bp_dis = -1;           // -1 if not used
        _temperature = -1;      // -1 if not used
        _perl = 'x';            // y,n or x if unknown
        _eyes = 'x';            // c,n,u,d or x if unknown
        _time = new Date();
    }

    public static void createTable(PRFDatabase db) {
        String CREATE_TABLE_OBSERVATIONS = "CREATE TABLE \"" +TABLE_NAME + "\" (\"time\" DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "\"response\" VARCHAR, \"respiratory\" INTEGER, \"pulse\" INTEGER, \"painscore\" INTEGER, " +
                "\"o2sats\" FLOAT, \"bp_sis\" INTEGER, \"bp_dis\" INTEGER, \"temperature\" FLOAT, \"perl\"" +
                " BOOL, \"eyes\" VARCHAR, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        if (!checkTableExists("observations",db)) { db.getWritableDatabase().execSQL(CREATE_TABLE_OBSERVATIONS); }
    }

    public String toXML() {
        String s;
        s = "<observation>\n";
        s += "<time>" + Util.dateToDBString(_time) + "</time>\n";
        s += "<response>" + responseConv() + "</response>\n";
        s += "<respiratory>" + respConv() + "</respiratory>\n";
        s += "<pulse>" + pulseConv() + "</pulse>\n";
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

    protected void _set_values(ContentValues values) {
        values.put("response", respConv());
        values.put("respiratory", _respiratory);
        values.put("pulse", _pulse);
        values.put("painscore", _painscore);
        values.put("o2sats", _o2sats);
        values.put("bpsis", _bp_sis);
        values.put("bpdis", _bp_dis);
        values.put("perl", perlConv());
        values.put("eyes", eyeConv());
        values.put("temperature", _temperature);
    }

    String respConv(){
        if (_respiratory != 1) return new Integer(_respiratory).toString();
        return "unknown";
    }

    String pulseConv(){
        if (_pulse != 1) return new Integer(_pulse).toString();
        return "unknown";
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

    public int get_respiratory() {
        return _respiratory;
    }

    public void set_respiratory(int _respiratory) {
        this._respiratory = _respiratory;
    }

    public int get_pulse() {
        return _pulse;
    }

    public void set_pulse(int _pulse) {
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
        if (_perl == 'y' || _perl == 'v' || _perl == 'x')
            this._perl = _perl;
    }

    public char get_eyes() {
        return _eyes;
    }

    public void set_eyes(char _eyes) {
        if (_eyes == 'c' || _eyes == 'n' || _eyes == 'u' || _eyes == 'd' || _eyes == 'x')
            this._eyes = _eyes;
    }
}
