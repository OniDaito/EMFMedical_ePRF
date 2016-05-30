package uk.co.section9.emfmedical;

import android.app.Application;
import android.util.Log;

import java.util.Vector;

import uk.co.section9.emfmedical.data.PRF;
import uk.co.section9.emfmedical.data.PRFDatabase;

// We have this as a global holder for the database we are using

public class EMFMedicalApp extends Application {

    private static PRFDatabase _db;
    private static PRF _currentPRF;
    private static EMFCrypto _crypto;

    @Override
    public void onCreate() {
        super.onCreate();
        _db = new PRFDatabase( this );
        _crypto = new EMFCrypto();
        _crypto.init(getBaseContext());
    }

    @Override
    // TODO - write out all PRFS to encrypted format and delete from DB
    public void onTerminate() {
        super.onTerminate();
        _db.close();
        _db = null;
    }

    // start a new PRF afresh
    public static PRF newPRF(){
        _currentPRF = new PRF();
        return _currentPRF;
    }

    // Create a new PRF from a uuid handed in
    public static PRF loadPRF(String uuid){
        _currentPRF = _db.readPRF(uuid);
        return _currentPRF;
    }

    // Commit the current PRF to the database
    public static void commitPRF() {
        Vector<String> prfs = _db.listPRFS();
        for (String id : prfs){
            if (id.equals( _currentPRF.get_uuid())){
                _db.updatePRF(_currentPRF);
                return;
            }
        }
        _db.writePRF(_currentPRF);
    }

    public static PRF getPRFByID(String uuid) {
        return _db.readPRF(uuid);
    }

    // Return the current PRF
    public static PRF getCurrentPRF(){
        return _currentPRF;
    }

    // Write out the existing current prf to encrypted format
    public static void completePRF() {
        Log.d("OUTPUT",_currentPRF.toXML());
    }

    public static PRFDatabase getDatabase() {
        return _db;
    }

    public static Vector<String> getAllPRFIDs() {
        Vector<String> ids = _db.listPRFS();
        return  ids;
    }
}