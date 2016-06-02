package uk.co.section9.emfmedical;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

import uk.co.section9.emfmedical.data.Incident;


// The actual PRFDatabase Activity itself


public class PRFActivity extends FragmentActivity  {
	 
	private FragmentTabHost _tabhost;
    private static EMFMedicalApp _app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String prf_id = intent.getStringExtra(MainActivity.PRF_ID);

		super.onCreate(savedInstanceState);

        _app = (EMFMedicalApp) getApplication();
        // Deal with our PRF - either load one if we've passed in an ID
        // TODO - oncreate might have to call a saved state PRF - not sure - held in the app?
        // TODO - We may be able to do this setup one class up from here.

        if (prf_id.equals("new") || prf_id.equals("")) {
            EMFMedicalApp.newPRF();
        } else {
            // TODO - graceful fallback on failure needed here
            EMFMedicalApp.loadPRF(prf_id);
        }


        // Create the view and add all the tabs
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
		setContentView(R.layout.prf);

		_tabhost = (FragmentTabHost)findViewById(R.id.tabhost);
	    _tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        _tabhost.addTab(_tabhost.newTabSpec("incident").setIndicator("1"),
				IncidentActivity.IncidentFragment.class, null);
	        
	    _tabhost.addTab(_tabhost.newTabSpec("primary_survey").setIndicator("2"),
	    		PrimarySurveyActivity.PrimarySurveyFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("history").setIndicator("3"),
				SecondarySurveyActivity.SecondarySurveyFragment.class, null);

		_tabhost.addTab(_tabhost.newTabSpec("observations").setIndicator("4"),
				ObservationsActivity.ObservationsFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("treatment").setIndicator("5"),
				TreatmentActivity.TreatmentFragment.class, null);
	        
		_tabhost.addTab(_tabhost.newTabSpec("serious").setIndicator("6"),
                SeriousActivity.SeriousFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("notes").setIndicator("7"),
                NotesActivity.NotesFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("outcome").setIndicator("8"),
                DischargeActivity.OutcomeFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("sign").setIndicator("9"),
                SignActivity.SignFragment.class, null);

        _tabhost.addTab(_tabhost.newTabSpec("refused").setIndicator("9"),
                RefusedActivity.RefusedFragment.class, null);  	   // Setup the cancel button

        Button cancelButton = (Button) findViewById(R.id.prf_activity_cancel);

        final Activity prfactivity = this;

        // TODO - We could replace both these buttons with just one 'back' button and always save?
        // Cancel button
        cancelButton.setOnClickListener(new OnClickListener() {
     
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

        // Save button
        Button saveButton = (Button) findViewById(R.id.prf_activity_save);
        saveButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(prfactivity);

                // set title
                alertDialogBuilder.setTitle("");

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setMessage("This will postpone the Patient Report Form, saving to a temporary database.")
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                        {

                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity but save the current state, including the
                                // activity we are on so it is saved to the db
                                postponeForm();
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

        }
    }


    // Make the back button do nothing at this point - could possibly hid the keyboard?
    @Override
    public void onBackPressed() {
    }

    // Set the database but also quit and go back to the main screen
    public void postponeForm() {

        // We need to find which fragment is active and call it' set method
        String tag = _tabhost.getCurrentTabTag();

        if (tag.equals("incident")) { IncidentActivity.IncidentFragment.setCurrentPRF(); }
        else if (tag.equals("outcome")) { DischargeActivity.OutcomeFragment.setCurrentPRF(); }
        else if (tag.equals("notes")) { NotesActivity.NotesFragment.setCurrentPRF(); }
        else if (tag.equals("primary_survey")) { PrimarySurveyActivity.PrimarySurveyFragment.setCurrentPRF(); }
        else if (tag.equals("observations")) { ObservationsActivity.ObservationsFragment.setCurrentPRF(); }
        else if (tag.equals("refused")) { RefusedActivity.RefusedFragment.setCurrentPRF(); }
        else if (tag.equals("history")) { SecondarySurveyActivity.SecondarySurveyFragment.setCurrentPRF(); }
        else if (tag.equals("serious")) { SeriousActivity.SeriousFragment.setCurrentPRF(); }
        else if (tag.equals("sign")) { SignActivity.SignFragment.setCurrentPRF(); }
        else if (tag.equals("treatment")) { TreatmentActivity.TreatmentFragment.setCurrentPRF(); }

        // We need to decide here if we are updating or starting anew
        EMFMedicalApp.commitPRF();

        // Finish destroys the form but that means we write to memory so finish happens first
        finish();
    }

    // Form is completed! Grab all data and encrypt
    public void completeForm(){
        ((EMFMedicalApp)getApplication()).completePRF();
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

        Log.d("OUTPUT", "keycode: " + keyCode);

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
                // Move tab right
                int ctab = _tabhost.getCurrentTab();
                _tabhost.setCurrentTab(ctab+1);
                return true;
            }
        }

        // Looks like back and forward are the new side keys now :S
        case KeyEvent.KEYCODE_BACK: {
            if (event.getAction() == KeyEvent.ACTION_UP){
                // Move tab right
                int ctab = _tabhost.getCurrentTab();
                _tabhost.setCurrentTab(ctab-1);
                return true;
            }
        }

        default:
            return super.dispatchKeyEvent(event);
        }
    }

	

}
