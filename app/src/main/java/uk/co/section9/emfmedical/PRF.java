package uk.co.section9.emfmedical;


import java.util.Date;

/**
 * Created by oni on 08/05/2016.
 */
public class PRF {

    protected  String uuid;
    protected  Incident _incident;
    protected Date _createdAt;

    public PRF () {
        uuid = java.util.UUID.randomUUID().toString();
        _incident = new Incident();
    }

    String id() {
        return uuid;
    }

    Date getCreatedAt() {
        return _createdAt;
    }

    Incident getIncident() {
        return _incident;
    }

}
