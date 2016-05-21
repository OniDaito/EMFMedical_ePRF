package uk.co.section9.emfmedical;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

import uk.co.section9.emfmedical.data.PRF;


// The actual PRFDatabase Activity itself


public class PRFActivity extends FragmentActivity  {
	 
	private FragmentTabHost _tabhost;
	private String          _prfID;
    private PRF _currentPRF;
	private EMFCrypto       _crypto;

    public static String prePopulate;
    private static EMFMedicalApp _app;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String prf_id = intent.getStringExtra(MainActivity.PRF_ID);

		super.onCreate(savedInstanceState);

        _app = (EMFMedicalApp) getApplication();
        // Deal with our PRF - either load one if we've passed in an ID
		_prfID = java.util.UUID.randomUUID().toString();

        if (prf_id.equals("new") || prf_id.equals("")) {
            _currentPRF = new PRF(_prfID);
        } else {
            // TODO - graceful fallback on failure needed here
            _currentPRF = _app.getDatabase().readPRF(_prfID);
        }

	    _crypto = new EMFCrypto();
		_crypto.init(getBaseContext());

        // Create the view and add all the tabs
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
		setContentView(R.layout.prf);

		_tabhost = (FragmentTabHost)findViewById(R.id.tabhost);
	    _tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        _tabhost.addTab(_tabhost.newTabSpec("incident").setIndicator("1.Incident"),
				IncidentActivity.IncidentFragment.class, null);
	        
	    _tabhost.addTab(_tabhost.newTabSpec("primary_survey").setIndicator("2.Primary Survey"),
	    		PrimarySurveyActivity.PrimarySurveyFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("medical_history").setIndicator("3.Medical History"),
				MedicalHistoryActivity.MedicalHistoryFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("secondary_survey").setIndicator("4.uk.co.section9.emfmedical.data.Secondary Survey"),
				SecondarySurveyActivity.SecondarySurveyFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("observations").setIndicator("5.Observations"),
				ObservationsActivity.ObservationsFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("treatment").setIndicator("6.Treatment"),
				TreatmentActivity.TreatmentFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("resuscitation").setIndicator("7.Resuscitation"),
				ResuscitationActivity.ResuscitationFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("ambulance").setIndicator("8.Ambulance"),
				ResponseActivity.ResponseFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("notes").setIndicator("9.Notes"),
                NotesActivity.NotesFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("outcome").setIndicator("10.Outcome"),
                OutcomeActivity.OutcomeFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("sign").setIndicator("11.Sign"),
                SignActivity.SignFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("refused").setIndicator("11.Refused"),
                RefusedActivity.RefusedFragment.class, null);  	   // Setup the cancel button

        Button button = (Button) findViewById(R.id.prf_activity_cancel);

        final Activity prfactivity = this;

        // Cancel button
    	button.setOnClickListener(new OnClickListener() {
     
    	    @Override
            public void onClick(View arg0) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(prfactivity);

            // set title
            alertDialogBuilder.setTitle("");

            // set dialog message
            alertDialogBuilder
                .setMessage("This will cancel the Patient Report Form. All data will be lost.")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
            {

                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        finish();
                    }
            }).setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
    					
            }
    			
        });
    		

        // Pre-populate if we have been asked to
        // TODO - move this to the PRF Class
        if(message.equalsIgnoreCase("MinorWoundDressed")){
    		prePopulate = "MinorWoundDressed";
        } else {
            prePopulate = "None";
        }
	        
    }
	 

    // Make the back button do nothing at this point
    @Override
    public void onBackPressed() {
    }


    // Form is completed! Grab all data and encrypt
    public void completeForm(){
/*
        String total_data = new String();
        if (IncidentActivity.IncidentFragment.used())
            total_data += IncidentActivity.IncidentFragment.getData();
        if (PrimarySurveyActivity.PrimarySurveyFragment.used())
            total_data += PrimarySurveyActivity.PrimarySurveyFragment.getData();
        if (MedicalHistoryActivity.MedicalHistoryFragment.used())
            total_data += MedicalHistoryActivity.MedicalHistoryFragment.getData();

        if (SecondarySurveyActivity.SecondarySurveyFragment.used())
            total_data += SecondarySurveyActivity.SecondarySurveyFragment.getData();
        if (ObservationsActivity.ObservationsFragment.used())
            total_data += ObservationsActivity.ObservationsFragment.getData();
        if (TreatmentActivity.TreatmentFragment.used())
            total_data += TreatmentActivity.TreatmentFragment.getData();
        if (ResuscitationActivity.ResuscitationFragment.used())
            total_data += ResuscitationActivity.ResuscitationFragment.getData();
        if ( ResponseActivity.ResponseFragment.used())
            total_data += ResponseActivity.ResponseFragment.getData();
        if (NotesActivity.NotesFragment.used())
         total_data += NotesActivity.NotesFragment.getData();
        if (OutcomeActivity.OutcomeFragment.used())
            total_data += OutcomeActivity.OutcomeFragment.getData();
        if (SignActivity.SignFragment.used())
            total_data += SignActivity.SignFragment.getData();

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        String time_date = new String();
        time_date += hour + "_" + minute + "_" + day + "_" + month;

        String filename = new String(time_date + "_" + _prfID + ".prf");

        //System.out.println("Data: " + total_data );

        try {
            File root = getFilesDir();

            //File root = new File("/media/My Files");
            //System.out.println("Root Dir: " + root.getAbsolutePath() );

            FileOutputStream f = new FileOutputStream(new File(root, filename));


            // Write the string to the file

            // Start with all the text then do the two jpegs seperately

            byte[] encrypted_data = _crypto.encode(total_data.getBytes());
            f.write(encrypted_data);
            // Encrypt!!
            if (SignActivity.SignFragment.used()){
                String jpg_sig ="***JPEG_SIGNATURE***:";
                byte[] encrypted_sig_header = _crypto.encode(jpg_sig.getBytes());
                f.write(encrypted_sig_header);
                ByteArrayOutputStream bs = SignActivity.SignFragment.getSignatureView().convertToByteArrayOutputStream();
                byte[] encrypted_signature = _crypto.encode(bs.toByteArray());
                f.write(encrypted_signature);
            }


            if (RefusedActivity.RefusedFragment.used()){
                String jpg_sig ="***JPEG_REFUSED***:";
                byte[] encrypted_sig_header = _crypto.encode(jpg_sig.getBytes());
                f.write(encrypted_sig_header);
                ByteArrayOutputStream bs = RefusedActivity.RefusedFragment.getSignatureView().convertToByteArrayOutputStream();
                byte[] encrypted_signature = _crypto.encode(bs.toByteArray());
                f.write(encrypted_signature);
            }

            f.close();

        } catch (IOException ioe) {
            System.out.println("Exception on Form");
            ioe.printStackTrace();
        }

        System.out.println("Completed Form: " + filename);*/

        System.out.println(_currentPRF.toXML());

        finish();

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();


    }

    @Override
    public void onDestroy() {
        // Write out any PRFDatabase that isnt't already written?


        super.onDestroy();

    }

    @Override
    protected void onNewIntent(Intent intent){
        String action = intent.getAction();

    }

    /*
     * Menu for the location viewer and signing the device back in
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.menu_rural_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }


    // Override so we can move the tabs left and right with the nook buttons
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();

        // 92 & 93 are on the left. 95 and 96 are the two on the right


        switch (keyCode) {
        case KeyEvent.KEYCODE_PAGE_UP:
        case KeyEvent.KEYCODE_PAGE_DOWN:{
            if (event.getAction() == KeyEvent.ACTION_UP){
                // Move tab left
                int ctab = _tabhost.getCurrentTab();
                _tabhost.setCurrentTab(ctab-1);
                return true;
            }
        }

        case KeyEvent.KEYCODE_PICTSYMBOLS:
        case KeyEvent.KEYCODE_SWITCH_CHARSET:{
            if (event.getAction() == KeyEvent.ACTION_UP){
                // Move tab left
                int ctab = _tabhost.getCurrentTab();
                _tabhost.setCurrentTab(ctab+1);
                return true;
            }
        }

        default:
            return super.dispatchKeyEvent(event);
        }
    }

	

}
