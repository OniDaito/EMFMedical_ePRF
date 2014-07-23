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
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
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
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")

public class PRFActivity extends FragmentActivity  {
	 
	 private FragmentTabHost mTabHost;
	 
	 private String mFormID; // UUID?
	
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        
	        mFormID = java.util.UUID.randomUUID().toString();
	        
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	        setContentView(R.layout.prf);

	        mTabHost = (FragmentTabHost)findViewById(R.id.tabhost);
	        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

	        
	        mTabHost.addTab(mTabHost.newTabSpec("incident").setIndicator("Incident"),
	                IncidentActivity.IncidentFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("primary_survey").setIndicator("Primary Survey"),
	                PrimarySurveyActivity.PrimarySurveyFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("medical_history").setIndicator("Medical History"),
	                MedicalHistoryActivity.MedicalHistoryFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("secondary_survey").setIndicator("Secondary Survey"),
	                SecondarySurveyActivity.SecondarySurveyFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("observations").setIndicator("Observations"),
	        		ObservationsActivity.ObservationsFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("treatment").setIndicator("Treatment"),
	        		TreatmentActivity.TreatmentFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("resuscitation").setIndicator("Resuscitation"),
	        		ResuscitationActivity.ResuscitationFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("response").setIndicator("Response"),
	        		ResponseActivity.ResponseFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("items").setIndicator("Items"),
	        		ItemsActivity.ItemsFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("notes").setIndicator("Notes"),
	        		NotesActivity.NotesFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("outcome").setIndicator("Outcome"),
	        		OutcomeActivity.OutcomeFragment.class, null);
	        
	        mTabHost.addTab(mTabHost.newTabSpec("sign").setIndicator("Sign"),
	        		SignActivity.SignFragment.class, null);
	   
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
	  
				OutputStreamWriter osw = new OutputStreamWriter(f); 

				// Write the string to the file
				osw.write(total_data);

				// Write the signature to the file
	
				osw.write("\n***SIGNATURE_JPEG***\n");
				osw.flush();
				
				SignActivity.SignFragment.getSignatureView().writeToFile(f);
				
				osw.close();
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
	    

		 private void testCrypto() {

		    // Original text
		    String theTestText = "This is just a simple test!";
		    //TextView tvorig = (TextView)findViewById(R.id.tvorig);
		    //tvorig.setText("\n[ORIGINAL]:\n" + theTestText + "\n");

		    // Generate key pair for 1024-bit RSA encryption and decryption
		    Key publicKey = null;
		    Key privateKey = null;
		    try {
		        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		        kpg.initialize(1024);
		        KeyPair kp = kpg.genKeyPair();
		        publicKey = kp.getPublic();
		        privateKey = kp.getPrivate();
		    } catch (Exception e) {
		        Log.e("CRYPTO", "RSA key pair error");
		    }

		    // Encode the original data with RSA private key
		    byte[] encodedBytes = null;
		    try {
		        Cipher c = Cipher.getInstance("RSA");
		        c.init(Cipher.ENCRYPT_MODE, privateKey);
		        encodedBytes = c.doFinal(theTestText.getBytes());
		    } catch (Exception e) {
		        Log.e("CRYPTO", "RSA encryption error");
		    }
		    //TextView tvencoded = (TextView)findViewById(R.id.tvencoded);
		   //tvencoded.setText("[ENCODED]:\n" + 
		    //    Base64.encodeToString(encodedBytes, Base64.DEFAULT) + "\n");

		    // Decode the encoded data with RSA public key
		    byte[] decodedBytes = null;
		    try {
		        Cipher c = Cipher.getInstance("RSA");
		        c.init(Cipher.DECRYPT_MODE, publicKey);
		        decodedBytes = c.doFinal(encodedBytes);
		    } catch (Exception e) {
		        Log.e("CRYPTO", "RSA decryption error");
		    }
		 }
		 

	

}
