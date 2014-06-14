package uk.co.section9.emfmedical;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.util.Set;

import javax.crypto.Cipher;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends FragmentActivity  {
	 
	 private FragmentTabHost mTabHost;
	
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        
	        setContentView(R.layout.main);

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
	   
	    }
	 

	  	// Make the back button do nothing at this point
		@Override
		public void onBackPressed() {
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
	        // Handle item selection
	       /* switch (item.getItemId()) {
	            case R.id.menuMainLocation:
	            	Intent myIntent = new Intent(RuralMain.this, RuralLocation.class);
	    			RuralMain.this.startActivity(myIntent);
	                return true;
	            case R.id.menuMainSignIn:
	    			Intent myIntent2 = new Intent(RuralMain.this, RuralIn.class);
	    			RuralMain.this.startActivity(myIntent2);
	                return true;
	            case R.id.menuMainTestConnection:
	    			
	            	RuralApp app = (RuralApp)getApplication();
	            	if (app.mData.testCryptoServer()){
	            		Support.alertbox(this,"Success", "Handshake with server completed");
	            	} else {
	            		Support.alertbox(this,"Failed", "Handshake with server failed");
	            	}
	            	
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }*/
	    	
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
