package uk.co.section9.emfmedical;

import android.app.Application;

// We have this as a global holder for the database we are using

public class EMFMedicalApp extends Application {

    private static PRFDatabase _db;

    @Override
    public void onCreate() {
        super.onCreate();
        _db = new PRFDatabase( this );

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        _db.close();
        _db = null;
    }

    public static PRFDatabase getDatabase() {
        return _db;
    }
}