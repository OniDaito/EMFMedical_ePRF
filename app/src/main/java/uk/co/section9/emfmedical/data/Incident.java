package uk.co.section9.emfmedical.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import uk.co.section9.emfmedical.Util;

/**
 * Created by oni on 08/05/2016.
 */

public class Incident extends BaseData {
    protected Date _time;
    protected String _location;
    protected String _forname;
    protected String _surname;
    protected String _email;
    protected String _address;
    protected String _postcode;
    protected Date _dob;
    protected int _age;
    protected String _gender;
    protected String _kin;

    protected static final String TABLE_NAME = "incident";

    public Incident () {
        // Assuming GMT - wont work elsewhere of course :S
        _time = new Date();
        _location = "";
        _forname = "";
        _surname = "";
        _email = "";
        _address = "";
        _postcode = "";
        _dob = new Date();
        _age = 0;
        _gender = "";
        _kin = "";
    }


    public static String createTable() {
        String CREATE_INCIDENT_TABLE = "CREATE TABLE \"" + TABLE_NAME + "\" (\"time\" DATETIME" +
                ", \"location\" TEXT, \"forname\" TEXT, \"surname\" TEXT, \"email\" TEXT, " +
                "\"address\" TEXT, \"postcode\" TEXT, \"dob\" DATETIME, \"age\" INTEGER, \"gender\" VARCHAR, " +
                "\"kin\" TEXT, \"id\" GUID PRIMARY KEY  NOT NULL )";

        return CREATE_INCIDENT_TABLE;
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    public String toXML() {
        String s;
        s = "<incident>\n";
        s+= "<time>" + _time.toString() + "</time>\n";
        s+= "<location>" + _location + "</location>\n";
        s+= "<forname>" + _forname + "</forname>\n";
        s+= "<surname>" + _surname + "</surname>\n";
        s+= "<email>" + _email + "</email>\n";
        s+= "<address>" + _address + "</address>\n";
        s+= "<postcode>" + _postcode + "</postcode>\n";
        s+= "<dob>" + _dob.toString() + "</dob>\n";
        s+= "<age>" + _age + "</age>\n";
        s+= "<gender>" + _gender + "</gender>\n";
        s+= "<kin>" + _kin + "</kin>";
        s+= "</incident>\n";

        return s;
    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        values.put("time", Util.dateToDBString(get_time()));
        values.put("location", get_location());
        values.put("forname", get_forname());
        values.put("surname", get_surname());
        values.put("email", get_email());
        values.put("address", get_address());
        values.put("postcode", get_postcode());

        values.put("dob", Util.dateToDBString(get_dob()));
        values.put("age", Integer.toString(get_age()));
        values.put("gender", get_gender());
        values.put("kin", get_kin());
        return values;
    }

    public void setValues(ContentValues values) {

        _time = Util.dbStringToDate((String) values.get("time"));
        _location = ((String) values.get("location"));
        _forname = ((String) values.get("forname"));
        _surname = ((String) values.get("surname"));
        _email = ((String) values.get("email"));
        _address = ((String) values.get("address"));
        _postcode = ((String) values.get("postcode"));
        _dob = Util.dbStringToDate((String) values.get("dob"));
        _age =  Integer.parseInt((String)values.get("age")); // TODO - need a catch
        _gender = ((String) values.get("gender"));
        _kin = ((String) values.get("kin"));

    }

    // It doesnt seem quite right to have this  function here instead of in PRFDatabase but given
    // these classes can change it seems the better move


    public Date get_time() {
        return _time;
    }

    public void set_time(Date tt) {
        _time = tt;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_forname() {
        return _forname;
    }

    public void set_forname(String _forname) {
        this._forname = _forname;
    }

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_postcode() {
        return _postcode;
    }

    public void set_postcode(String _postcode) {
        this._postcode = _postcode;
    }

    public Date get_dob() {
        return _dob;
    }

    public void set_dob(Date _dob) {
        this._dob = _dob;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_kin() {
        return _kin;
    }

    public void set_kin(String _kin) {
        this._kin = _kin;
    }

}
