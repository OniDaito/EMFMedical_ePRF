package uk.co.section9.emfmedical;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import uk.co.section9.emfmedical.data.PRF;
import uk.co.section9.emfmedical.data.Treatment;

// Treatment Administered

public class TreatmentActivity extends FragmentActivity {

	 public static class TreatmentFragment extends Fragment {
	     
		 	static View mainView;
		 	static boolean mUsed = false;
		 
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	        }
	        
	        public static boolean used() {
		    	return mUsed;
		    }

	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            mainView = inflater.inflate(R.layout.treatment, container, false);
	            mUsed = true;

	            return mainView;
	        }
	        
	        
	        public static void setCurrentPRF() {
				PRF prf = EMFMedicalApp.getCurrentPRF();
				Treatment tt = prf.get_treatment();

	        	EditText treatment_other = (EditText)mainView.findViewById(R.id.treatment_other);
	        	tt.set_other("" + treatment_other.getEditableText());
	        	
	        	CheckBox treatment_none = (CheckBox)mainView.findViewById(R.id.treatment_none);
	        	if (treatment_none.isChecked()){
                    tt.set_none('y');
	        	}

	        	CheckBox treatment_wound_cleansed = (CheckBox)mainView.findViewById(R.id.treatment_wound_cleansed);
	        	if (treatment_wound_cleansed.isChecked()){
                    tt.set_wound_cleaned('y');
	        	}
	        	
	        	CheckBox treatment_recovery_position = (CheckBox)mainView.findViewById(R.id.treatment_recovery_position);
	        	if (treatment_recovery_position.isChecked()){
                    tt.set_recovery_position('y');
	        	}
	        	
	        	CheckBox treatment_adhesive_dressing = (CheckBox)mainView.findViewById(R.id.treatment_adhesive_dressing);
	        	if (treatment_adhesive_dressing.isChecked()){
	        		tt.set_adhesive_dressing('y');
	        	}
	        	
	        	CheckBox treatment_wound_dressed = (CheckBox)mainView.findViewById(R.id.treatment_wound_dressed);
	        	if (treatment_wound_dressed.isChecked()){
                    tt.set_wound_dressed('y');
	        	}

	        	CheckBox treatment_rice = (CheckBox)mainView.findViewById(R.id.treatment_rice);
	        	if (treatment_rice.isChecked()){
	        		tt.set_rice('y');
	        	}
	        	
	        	CheckBox treatment_sling = (CheckBox)mainView.findViewById(R.id.treatment_sling);
	        	if (treatment_sling.isChecked()){
	        		tt.set_sling('y');
	        	}
	        	
	        	CheckBox treatment_splint = (CheckBox)mainView.findViewById(R.id.treatment_splint);
	        	if (treatment_splint.isChecked()){
	        		tt.set_splint('y');
	        	}
	        	
	        }
	        
	    }

}
