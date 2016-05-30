package uk.co.section9.emfmedical;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import uk.co.section9.emfmedical.data.Discharge;
import uk.co.section9.emfmedical.data.PRF;


// Finished? Then you need to sign the form

public class SignActivity extends FragmentActivity {
	public static class SignFragment extends Fragment {
	     
		static View mainView;
		
		static boolean mUsed = false;
		
		
	   public static boolean used() {
	    	return mUsed;
	    }
	
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
        }
        
        
        public static DrawingView getSignatureView() {
        	return (DrawingView) mainView.findViewById(R.id.sign_signature);
        }

       
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            mainView = inflater.inflate(R.layout.signature, container, false);
            mUsed = true;
            Button button = (Button) mainView.findViewById(R.id.signature_complete_form);
              
    		button.setOnClickListener(new OnClickListener() {
     
    			@Override
    			public void onClick(View arg0) {
    				final PRFActivity ma = (PRFActivity) getActivity();
    				
    				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    		 
					// set title
					alertDialogBuilder.setTitle("Complete Form");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("This will commit the Patient report form and encrypt it.")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity - Also set the seenby in the current PRF
								PRF prf = EMFMedicalApp.getCurrentPRF();
								Discharge ds = prf.get_discharge();
								EditText sign_name = (EditText)mainView.findViewById(R.id.sign_name);
								ds.set_seen_by(""+ sign_name.getEditableText() );

								ma.completeForm();
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
            
            
            return mainView;
        }
        
        
        public static void setCurrentPRF() {
			PRF prf = EMFMedicalApp.getCurrentPRF();
			Discharge ds = prf.get_discharge();
			EditText sign_name = (EditText)mainView.findViewById(R.id.sign_name);
			ds.set_seen_by(""+ sign_name.getEditableText() );
		}
    }
}
