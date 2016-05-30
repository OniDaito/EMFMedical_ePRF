package uk.co.section9.emfmedical;

import android.app.Application;
import android.util.Log;

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
    public static PRF newPRF(String uuid){
        _currentPRF = _db.readPRF(uuid);
        return _currentPRF;
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
}