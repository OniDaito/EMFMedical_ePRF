package uk.co.section9.emfmedical;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.util.Calendar;
import java.util.Set;

import javax.crypto.Cipher;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")

public class PRFActivity extends FragmentActivity  {
	 
	 private FragmentTabHost mTabHost;
	 private String mFormID; // UUID?
	 private EMFCrypto mCrypto;
	
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        
	        mFormID = java.util.UUID.randomUUID().toString();
	        
	        mCrypto = new EMFCrypto();
	        mCrypto.init(getBaseContext());
	       
	        
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	        setContentView(R.layout.prf);

	        mTabHost = (FragmentTabHost)findViewById(R.id.tabhost);
	        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

	        
	        mTabHost.addTab(mTabHost.newTabSpec("incident").setIndicator("1.Incident"),
	                IncidentActivity.IncidentFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("primary_survey").setIndicator("2.Primary Survey"),
	                PrimarySurveyActivity.PrimarySurveyFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("medical_history").setIndicator("3.Medical History"),
	                MedicalHistoryActivity.MedicalHistoryFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("secondary_survey").setIndicator("4.Secondary Survey"),
	                SecondarySurveyActivity.SecondarySurveyFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("observations").setIndicator("5.Observations"),
	        		ObservationsActivity.ObservationsFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("treatment").setIndicator("6.Treatment"),
	        		TreatmentActivity.TreatmentFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("resuscitation").setIndicator("7.Resuscitation"),
	        		ResuscitationActivity.ResuscitationFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("ambulance").setIndicator("8.Ambulance"),
	        		ResponseActivity.ResponseFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("items").setIndicator("9.Items"),
	        		ItemsActivity.ItemsFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("notes").setIndicator("10.Notes"),
	        		NotesActivity.NotesFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("outcome").setIndicator("11.Outcome"),
	        		OutcomeActivity.OutcomeFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("sign").setIndicator("12.Sign"),
	        		SignActivity.SignFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("refused").setIndicator("12.Refused"),
	        		RefusedActivity.RefusedFragment.class, null);
	   
	        
	        Button button = (Button) findViewById(R.id.prf_activity_cancel);
            
    		button.setOnClickListener(new OnClickListener() {
     
    			@Override
    			public void onClick(View arg0) {
    			
    				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getBaseContext());
    		 
					// set title
					alertDialogBuilder.setTitle("");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("This will cancel the Patient Report Form. All data will be lost.")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								finish();
							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
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
	        
	    }
	 

	  	// Make the back button do nothing at this point
		@Override
		public void onBackPressed() {
		}
		
	   
		public void completeForm(){
		
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
			if (ItemsActivity.ItemsFragment.used())
				total_data += ItemsActivity.ItemsFragment.getData();
			if (NotesActivity.NotesFragment.used())
			 total_data += NotesActivity.NotesFragment.getData();
			if (OutcomeActivity.OutcomeFragment.used())
				total_data += OutcomeActivity.OutcomeFragment.getData();
			
			if (SignActivity.getInstanceCount() > 0)
				total_data += SignActivity.SignFragment.getData();
			
			Calendar c = Calendar.getInstance(); 
			int hour = c.get(Calendar.HOUR);
			int minute = c.get(Calendar.MINUTE);
			int day = c.get(Calendar.DAY_OF_MONTH);
			int month = c.get(Calendar.MONTH);
			
			String time_date = new String();
			time_date += hour + "_" + minute + "_" + day + "_" + month; 
			
			String filename = new String(time_date + "_" + mFormID + ".prf");
			
			System.out.println("Data: " + total_data );
			
			try { 
				File root = getFilesDir();
				
				//File root = new File("/media/My Files"); 
				
				System.out.println("Root Dir: " + root.getAbsolutePath() );
				
	            FileOutputStream f = new FileOutputStream(new File(root, filename));
	  
				//OutputStreamWriter osw = new OutputStreamWriter(f); 

				// Write the string to the file
				
				// Encrypt!!
	            total_data += "\n***SIGNATURE_JPEG***\n";
	            total_data += SignActivity.SignFragment.getSignatureView().convertToString();
	            
				byte[] encrypted_data = mCrypto.encode(total_data);
				
				f.write(encrypted_data);

				// Write the signature to the file
	
				//osw.write("\n***SIGNATURE_JPEG***\n");
				//osw.flush();
				
				//SignActivity.SignFragment.getSignatureView().writeToFile(f);
				
				//osw.close();
				f.close();

			} catch (IOException ioe) {
				System.out.println("Exception on Form");
				ioe.printStackTrace();
			}
				
			System.out.println("Completed Form: " + filename);
			
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
	    	// Write out any PRF that isnt't already written?
	    	
	 
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
	    

		
		 

	

}
