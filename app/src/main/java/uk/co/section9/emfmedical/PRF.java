package uk.co.section9.emfmedical;


import java.util.Date;

/**
 * Created by oni on 08/05/2016.
 */
public class PRF {

    protected  String _uuid;
    protected  Incident _incident;
    protected Date _createdAt;

    public PRF () {
        _uuid = java.util.UUID.randomUUID().toString();
        _incident = new Incident();
        _createdAt = new Date();
    }

    public PRF (String uuid) {
        _uuid = uuid;
        _incident = new Incident();
        _createdAt = new Date();
    }

    public String toXML() {
        String s;
        s = "<xml>\n";
        s += "<createdat>" + _createdAt.toString() + "</createdat>\n";
        s += "<uuid>" + _uuid + "</uuid>\n";
        s += _incident.toXML();

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

}
