package uk.co.section9.emfmedical.data;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Date;
import java.util.Vector;

import uk.co.section9.emfmedical.Util;
import uk.co.section9.emfmedical.data.Discharge;
import uk.co.section9.emfmedical.data.Incident;
import uk.co.section9.emfmedical.data.Notes;
import uk.co.section9.emfmedical.data.Observation;
import uk.co.section9.emfmedical.data.Primary;
import uk.co.section9.emfmedical.data.Secondary;
import uk.co.section9.emfmedical.data.Serious;
import uk.co.section9.emfmedical.data.Treatment;

/**
 * Created by oni on 08/05/2016.
 */
public class PRF extends BaseData {

    protected String _uuid;
    protected Date _createdAt;
    protected Discharge _discharge;
    protected Incident _incident;
    protected Notes _notes;
    protected Vector<Observation> _observations;
    protected Primary _primary;
    protected Secondary _secondary;
    protected Serious _serious;
    protected Treatment _treatment;

    protected static String TABLE_NAME = "prfs";

    public PRF () {
        _uuid = java.util.UUID.randomUUID().toString();
        _incident = new Incident();
        _createdAt = new Date();
        _createdAt = new Date();
        _discharge = new Discharge();
        _incident = new Incident();
        _notes = new Notes();
        _observations = new Vector<Observation>();
        _primary = new Primary();
        _secondary = new Secondary();
        _serious = new Serious();
        _treatment = new Treatment();
        _incident = new Incident();
    }

    public PRF (String uuid) {
        _uuid = uuid;
        _createdAt = new Date();
        _discharge = new Discharge();
        _incident = new Incident();
        _notes = new Notes();
        _observations = new Vector<Observation>();
        _primary = new Primary();
        _secondary = new Secondary();
        _serious = new Serious();
        _treatment = new Treatment();
        _incident = new Incident();

        // Load from the DB
    }


    public static String createTable() {
        String CREATE_TABLE_PRFS = "CREATE TABLE \"" + TABLE_NAME + "\" (\"id\" GUID PRIMARY KEY  NOT NULL , \"createdat\" DATETIME);";
        return CREATE_TABLE_PRFS;
    }

    // Set the basics for the minor wound dressing
    public void setMinorWound() {
        _incident.set_location("EMF First Aid Point");
        _primary.set_response('a');
        _primary.set_consciousness('c');
        _primary.set_airway('c');
        _primary.set_breathing('n');
        _primary.set_circulation('n');
        _serious.set_defib_used('n');
        _serious.set_defib_shocks(0);
        _serious.set_cpr('n');
        _treatment.set_wound_dressed('y');
        _treatment.set_wound_cleaned('y');
        _discharge.set_walking_unaided('y');
    }

    public String toXML() {
        String s;
        s = "<xml>\n";
        s += "<createdat>" + _createdAt.toString() + "</createdat>\n";
        s += "<uuid>" + _uuid + "</uuid>\n";
        s += _discharge.toXML();
        s += _incident.toXML();
        s += _notes.toXML();
        s += _primary.toXML();
        s += _secondary.toXML();
        s += _serious.toXML();
        s += _treatment.toXML();

        // Potentially could have a masshoosive string
        for (Observation ob : _observations) {
            s+= ob.toXML();
        }

        s += "</xml>";
        return s;
    }

    public boolean isValid(Vector<String> errormessages){
        // Could cheat and just add up :P
        boolean v0 = _discharge.isValid(errormessages);
        boolean v1 = _incident.isValid(errormessages);
        boolean v2 = _notes.isValid(errormessages);
        boolean v3 = _primary.isValid(errormessages);
        boolean v4 = _secondary.isValid(errormessages);
        boolean v5 = _serious.isValid(errormessages);
        boolean v6 = _treatment.isValid(errormessages);

        boolean valid = true;
        if (!(v0 && v1 && v2 && v3 && v4 && v5 && v6)) {
            valid = false;
        }

        return valid;

    }

    public ContentValues getValues(){
        ContentValues values = new ContentValues();
        values.put("createdat", Util.dateToDBString(_createdAt));
        values.put("id", _uuid);
        return values;
    }

    protected void setValues( ContentValues values ) {
        _createdAt = Util.dbStringToDate((String) values.get("createdat"));
        _uuid = (String)values.get("id");
    }

    public void addObservation(Observation ob){
        _observations.add(ob);
    }

    public static String get_table_name() {
        return TABLE_NAME;
    }

    String id() {
        return _uuid;
    }

    Date getCreatedAt() {
        return _createdAt;
    }

    Incident getIncident() {return _incident;}

    public String get_uuid() {
        return _uuid;
    }

    public Date get_createdAt() {
        return _createdAt;
    }

    public Discharge get_discharge() {
        return _discharge;
    }

    public Incident get_incident() {
        return _incident;
    }

    public Notes get_notes() {
        return _notes;
    }

    public Vector<Observation> get_observations() {
        return _observations;
    }

    public Primary get_primary() {
        return _primary;
    }

    public Secondary get_secondary() {
        return _secondary;
    }

    public Serious get_serious() {
        return _serious;
    }

    public Treatment get_treatment() {
        return _treatment;
    }

}
