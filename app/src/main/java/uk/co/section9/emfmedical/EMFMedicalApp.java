package uk.co.section9.emfmedical;

import android.app.Application;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
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
    public static void loadPRF(String uuid){
        _currentPRF = _db.readPRF(uuid);
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

    // Write out the existing current prf to encrypted format and to a file
    public void completePRF() {

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        String time_date = new String();
        time_date += hour + "_" + minute + "_" + day + "_" + month;

        // TODO - Pretty much guaranteed there will be no clash but you never know. We should cx really
        String filename = new String(time_date + "_" + _currentPRF.get_uuid() + ".prf");

        try {
            File root = getFilesDir();
            FileOutputStream f = new FileOutputStream(new File(root, filename));

            byte[] text_bytes = _currentPRF.toXML().getBytes();

            byte[] divider_bytes = "***JPEG_SIGNATURE***:".getBytes();
            byte[] image_bytes = {};
            if (SignActivity.SignFragment.used()){
                ByteArrayOutputStream bs = SignActivity.SignFragment.getSignatureView().convertToByteArrayOutputStream();
                image_bytes = bs.toByteArray();
            }

            if (RefusedActivity.RefusedFragment.used()){
                ByteArrayOutputStream bs = RefusedActivity.RefusedFragment.getSignatureView().convertToByteArrayOutputStream();
                image_bytes = _crypto.encode(bs.toByteArray());
            }
            // Create one large array for encrypting
            int total_size = text_bytes.length + divider_bytes.length + image_bytes.length;

            Log.d("OUTPUT", "Total File Size: " + total_size);
            byte[] final_bytes = new byte[total_size];

            for (int i =0; i < text_bytes.length; i++){
                final_bytes[i] = text_bytes[i];
            }
            for (int i = 0; i < divider_bytes.length; i++){
                final_bytes[i + text_bytes.length ] = divider_bytes[i];
            }
            for (int i = 0; i < image_bytes.length; i++){
                final_bytes[i + text_bytes.length + divider_bytes.length] = image_bytes[i];
            }

            byte[] encrypted_data = _crypto.encode(final_bytes);
            f.write(encrypted_data);
            f.close();

        } catch (IOException e) {
            Log.d("ERROR", "Exception on Form");
            e.printStackTrace();
        }

        Log.d("OUTPUT","Completed Form: " + filename);

        // Assuming its in the db - should probably remove from memory too :S
        _db.deletePRF(_currentPRF);
    }

    public static PRFDatabase getDatabase() {
        return _db;
    }

    public static Vector<String> getAllPRFIDs() {
        Vector<String> ids = _db.listPRFS();
        return  ids;
    }
}