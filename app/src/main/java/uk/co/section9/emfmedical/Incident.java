package uk.co.section9.emfmedical;

import java.util.Date;

/**
 * Created by oni on 08/05/2016.
 */

public class Incident {
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

    public Incident () {
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

    public Incident (Date time, String location, String forname, String surname, String email,
                     String address, String postcode, Date dob, int age, String gender, String kin) {

        this._time = time;
        this._location = location;
        this._forname = forname;
        this._surname = surname;
        this._email = email;
        this._address = address;
        this._postcode = postcode;
        this._dob = dob;
        this._age = age;
        this._gender = gender;
        this._kin = kin;
    }

    Date getTime() {
        return this._time;
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

    String getLocation() {
        return this._location;
    }
}
