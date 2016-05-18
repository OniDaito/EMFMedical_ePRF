package uk.co.section9.emfmedical.data;


import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.Vector;

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

    protected  String _uuid;
    protected Date _createdAt;
    protected Discharge _discharge;
    protected Incident _incident;
    protected Notes _notes;
    protected Vector<Observation> _observations;
    protected Primary _primary;
    protected Secondary _secondary;
    protected Serious _serious;
    protected Treatment _treatment;


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

    public static void createTable(SQLiteDatabase db, String TABLE_PRFS) {
        String CREATE_TABLE_PRFS = "CREATE TABLE \"" + TABLE_PRFS + "\" (\"id\" VARCHAR PRIMARY KEY  NOT NULL , \"created\" DATETIME)";
        if (!checkTableExists("prfs", db)) {
            db.execSQL(CREATE_TABLE_PRFS);
        }
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
        s += _incident.toXML();

        // Potentially could have a masshoosive string
        for (Observation ob : _observations) {
            s+= ob.toXML();
        }

        s += "</xml>";
        return s;
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
