package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 11/05/2016.
 */
public class Discharge extends BaseData {

    protected char _walking_unaided; // We use chars for boolean fields as we may use null, true or false
    protected char _walking_aided;
    protected String _other;
    protected char _own_transport;
    protected char _public_transport;
    protected char _ambulance;
    protected char _taxi;
    protected char _completed;
    protected char _hospital;
    protected char _review;
    protected char _advised;
    protected Date _time_left;
    protected char _refused;
    protected String _seen_by;

    protected static final String TABLE_NAME = "discharge";

    public Discharge() {
        _walking_aided = 'x';
        _walking_unaided = 'x';
        _other = "";
        _own_transport = 'x';
        _public_transport = 'x';
        _ambulance = 'x';
        _taxi = 'x';
        _completed = 'x';
        _hospital = 'x';
        _review = 'x';
        _advised = 'x';
        _time_left = new Date();
        _time_left.setDate(0);
        _refused = 'x';
        _seen_by = "";
    }

    public static String createTable() {
        String CREATE_TABLE_DISCHARGE = "CREATE TABLE \"" + TABLE_NAME + "\" (\"walking_unaided\" BOOL, \"walking_aided\" BOOL, " +
                "\"other\" TEXT, \"own_transport\" BOOL, \"public_transport\" BOOL, \"ambulance\" BOOL, \"taxi\" BOOL, " +
                "\"completed\" BOOL, \"hospital\" BOOL, \"review\" BOOL, \"advised\" BOOL, \"time_left\" DATETIME, " +
                "\"refused\" BOOL, \"seen_by\" TEXT, \"id\" VARCHAR PRIMARY KEY  NOT NULL )";
        return CREATE_TABLE_DISCHARGE;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public String toXML() {
        String s;
        s = "<discharge>\n";
        s += "<walking_aided>" + charConv(get_walking_aided()) + "</walking_aided>\n";
        s += "<walking_unaided>" + charConv(get_walking_unaided()) + "</walking_unaided>\n";
        s += "<other>" + get_other() + "</other>\n";
        s += "<own_transport>" + charConv(get_own_transport()) + "</own_transport>\n";
        s += "<public_transport>" + charConv(get_public_transport()) + "</public_transport>\n";
        s += "<ambulance>" + charConv(get_ambulance()) + "</ambulance>\n";
        s += "<taxi>" + charConv(get_taxi()) + "</taxi>\n";
        s += "<completed>" + charConv(get_completed()) + "</completed>\n";
        s += "<hospital>" + charConv(get_hospital()) + "</hospital>\n";
        s += "<review>" + charConv(get_review()) + "</review>\n";
        s += "<advised>" + charConv(get_advised()) + "</advised>\n";
        s += "<time_left>" + get_time_left().toString() + "</time_left>\n";
        s += "<refused>" + charConv(get_refused()) + "</refused>\n";
        s += "<seen_by>" + get_seen_by() + "</seen_by>\n";
        s += "</discharge>\n";

        return s;
    }

    public ContentValues getValues() {
        ContentValues values = new ContentValues();
        values.put("walking_aided", new String(""+_walking_aided));
        values.put("walking_unaided", new String(""+_walking_unaided));
        values.put("other", _other);
        values.put("own_transport", new String(""+_own_transport));
        values.put("public_transport", new String(""+_public_transport));
        values.put("ambulance", new String(""+_ambulance));
        values.put("taxi", new String(""+_taxi));
        values.put("completed", new String(""+_completed));
        values.put("hospital", new String(""+_hospital));
        values.put("review", new String(""+_review));
        values.put("advised", new String(""+_advised));
        values.put("time_left", Util.dateToDBString(_time_left));
        values.put("refused", new String(""+_refused));
        values.put("seen_by", _seen_by);
        return values;
    }

    public void setValues(ContentValues values) {
        _walking_aided = ((String) values.get("walking_aided")).charAt(0);
        _walking_unaided = ((String) values.get("walking_unaided")).charAt(0);
        _other = ((String) values.get("other"));
        _own_transport = ((String) values.get("own_transport")).charAt(0);
        _public_transport = ((String) values.get("public_transport")).charAt(0);
        _ambulance = ((String) values.get("ambulance")).charAt(0);
        _taxi = ((String) values.get("taxi")).charAt(0);
        _completed =((String) values.get("completed")).charAt(0);
        _hospital = ((String) values.get("hospital")).charAt(0);
        _review = ((String) values.get("review")).charAt(0);
        _advised =((String) values.get("advised")).charAt(0);
        _time_left = Util.dbStringToDate((String) values.get("time_left"));
        _refused = ((String) values.get("refused")).charAt(0);
        _seen_by = ((String) values.get("seen_by"));

    }

    private static String charConv(char c){
        if (c == 'x') return "unknown";
        if (c == 'y') return "yes";
        if (c == 'n') return "no";
        return "ERROR";
    }

    public String get_seen_by() {
        return _seen_by;
    }

    public void set_seen_by(String _seen_by) {
        this._seen_by = _seen_by;
    }

    public char get_walking_unaided() {
        return _walking_unaided;
    }

    public void set_walking_unaided(char _walking_unaided) {
        if (_walking_unaided == 'x' || _walking_unaided == 'y' || _walking_unaided == 'n')
            this._walking_unaided = _walking_unaided;
    }

    public char get_walking_aided() {
        return _walking_aided;
    }

    public void set_walking_aided(char _walking_aided) {
        if (_walking_aided == 'x' || _walking_aided == 'y' || _walking_aided == 'n')
            this._walking_aided = _walking_aided;
    }

    public String get_other() {
        return _other;
    }

    public void set_other(String _other) {
        this._other = _other;
    }

    public char get_own_transport() {
        return _own_transport;
    }

    public void set_own_transport(char _own_transport) {
        if (_own_transport == 'x' || _own_transport == 'y' || _own_transport == 'n')
            this._own_transport = _own_transport;
    }

    public char get_public_transport() {
        return _public_transport;
    }

    public void set_public_transport(char _public_transport) {
        if (_public_transport == 'x' || _public_transport == 'y' || _public_transport == 'n')
            this._public_transport = _public_transport;
    }

    public char get_ambulance() {
        return _ambulance;
    }

    public void set_ambulance(char _ambulance) {
        if (_ambulance == 'x' || _ambulance == 'y' || _ambulance == 'n')
            this._ambulance = _ambulance;
    }

    public char get_taxi() {
        return _taxi;
    }

    public void set_taxi(char _taxi) {
        if (_taxi == 'x' || _taxi == 'y' || _taxi == 'n')
            this._taxi = _taxi;
    }

    public char get_completed() {
        return _completed;
    }

    public void set_completed(char _completed) {
        if (_completed == 'x' || _completed == 'y' || _completed == 'n')
            this._completed = _completed;
    }

    public char get_hospital() {
        return _hospital;
    }

    public void set_hospital(char _hospital) {
        if (_hospital == 'x' || _hospital == 'y' || _hospital == 'n')
            this._hospital = _hospital;
    }

    public char get_review() {
        return _review;
    }

    public void set_review(char _review) {
        if (_review == 'x' || _review == 'y' || _review == 'n')
            this._review = _review;
    }

    public char get_advised() {
        return _advised;
    }

    public void set_advised(char _advised) {
        if (_advised == 'x' || _advised == 'y' || _advised == 'n')
            this._advised = _advised;
    }

    public Date get_time_left() {
        return _time_left;
    }

    public void set_time_left(Date _time_left) {
        this._time_left = _time_left;
    }

    public char get_refused() {
        return _refused;
    }

    public void set_refused(char _refused) {
        if (_refused == 'x' || _refused == 'y' || _refused == 'n')
            this._refused = _refused;
    }
}
