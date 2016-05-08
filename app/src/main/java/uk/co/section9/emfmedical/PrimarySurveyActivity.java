package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;


// Primary Survey Tab

public class PrimarySurveyActivity extends FragmentActivity {
	
	
	 public static class PrimarySurveyFragment extends Fragment {
	     
		 	static View mainView;
		 
		 	static boolean mUsed = false;
		 	
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	        	super.onCreate(savedInstanceState);
	        
	        }
	        
	       
	        public static boolean used() {
	        	return mUsed;
	        }
	        
	        // Setup some basic things for minor wound treatment
	
	        public static void populateMinorWound() {
	        	RadioButton primary_survey_capacity_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_yes);
	        	primary_survey_capacity_yes.setChecked(true);
	        	
	        	RadioButton primary_survey_consent_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_yes);
	        	primary_survey_consent_yes.setChecked(true);
	        	
	        	RadioButton primary_survey_response_alert = (RadioButton)mainView.findViewById(R.id.primary_survey_response_alert);
	        	primary_survey_response_alert.setChecked(true);
	        	
	        	RadioButton primary_survey_airway_clear = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_clear);
	        	primary_survey_airway_clear.setChecked(true);
	        	
	        	RadioButton primary_survey_breathing_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_normal);
	        	primary_survey_breathing_normal.setChecked(true);
	        	
	        	RadioButton primary_survey_circulation_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_normal);
	        	primary_survey_circulation_normal.setChecked(true);
	        	
	        	
	         	RadioButton primary_survey_consiousness_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_no);
	         	primary_survey_consiousness_no.setChecked(true);

	        }
	        
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            mainView = inflater.inflate(R.layout.primary_survey, container, false);
	            mUsed = true;
	            
	            if (PRFActivity.prePopulate.equalsIgnoreCase("MinorWoundDressed")){
	        		populateMinorWound();
	        	}
	            return mainView;
	        }
	        
	        public static String getData() {
	        	String data = new String();
	        	
	        	EditText primary_survey_problem = (EditText)mainView.findViewById(R.id.primary_survey_problem);
	        	data += "primary_survey_problem: " + primary_survey_problem.getEditableText() + "\n";
	        	
	        	RadioButton primary_survey_capacity_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_yes);
	        	RadioButton primary_survey_capacity_no = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_no);
	        	
	        	if (primary_survey_capacity_yes.isChecked()){
	        		data += "primary_survey_capacity: yes\n";
	        	}
	        	if (primary_survey_capacity_no.isChecked()){
	        		data += "primary_survey_capacity: no\n";
	        	}
	        	
	        	RadioButton primary_survey_consent_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_yes);
	        	RadioButton primary_survey_consent_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_no);
	        	
	        	if (primary_survey_consent_yes.isChecked()){
	        		data += "primary_survey_consent: yes\n";
	        	}
	        	if (primary_survey_consent_no.isChecked()){
	        		data += "primary_survey_consent: no\n";
	        	}
	        
	        	
	        	RadioButton primary_survey_response_alert = (RadioButton)mainView.findViewById(R.id.primary_survey_response_alert);
	        	RadioButton primary_survey_response_voice = (RadioButton)mainView.findViewById(R.id.primary_survey_response_voice);
	        	RadioButton primary_survey_response_pain = (RadioButton)mainView.findViewById(R.id.primary_survey_response_pain);
	        	RadioButton primary_survey_response_none = (RadioButton)mainView.findViewById(R.id.primary_survey_response_none);
	        	
	        	if (primary_survey_response_alert.isChecked()){
	        		data += "primary_survey_response: alert\n";
	        	}
	        	if (primary_survey_response_voice.isChecked()){
	        		data += "primary_survey_response: voice\n";
	        	}
	        	if (primary_survey_response_pain.isChecked()){
	        		data += "primary_survey_response: pain\n";
	        	}
	        	if (primary_survey_response_none.isChecked()){
	        		data += "primary_survey_response: none\n";
	        	}
	        	
	        	
	        	RadioButton primary_survey_airway_clear = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_clear);
	        	RadioButton primary_survey_airway_obstructed = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_obstructed);
	        	
	        	if (primary_survey_airway_clear.isChecked()){
	        		data += "primary_survey_airway: clear\n";
	        	}
	        	if (primary_survey_airway_obstructed.isChecked()){
	        		data += "primary_survey_airway: obstructed\n";
	        	}
	        	
	        	RadioButton primary_survey_breathing_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_normal);
	        	RadioButton primary_survey_breathing_shallow = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_shallow);
	        	RadioButton primary_survey_breathing_agonal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_agonal);
	        	RadioButton primary_survey_breathing_absent = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_absent);
	        	
	        	if (primary_survey_breathing_normal.isChecked()){
	        		data += "primary_survey_breathing: normal\n";
	        	}
	        	if (primary_survey_breathing_shallow.isChecked()){
	        		data += "primary_survey_breathing: shallow\n";
	        	}
	        	if (primary_survey_breathing_agonal.isChecked()){
	        		data += "primary_survey_breathing: agonal\n";
	        	}
	        	if (primary_survey_breathing_absent.isChecked()){
	        		data += "primary_survey_breathing: absent\n";
	        	}
	        	
	        	RadioButton primary_survey_circulation_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_normal);
	        	RadioButton primary_survey_circulation_pale = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_pale);
	        	RadioButton primary_survey_circulation_flushed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_flushed);
	        	RadioButton primary_survey_circulation_cyanosed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_cyanosed);
	        	
	        	if (primary_survey_circulation_normal.isChecked()){
	        		data += "primary_survey_circulation: normal\n";
	        	}
	        	if (primary_survey_circulation_pale.isChecked()){
	        		data += "primary_survey_circulation: pale\n";
	        	}
	        	if (primary_survey_circulation_flushed.isChecked()){
	        		data += "primary_survey_circulation: flushed\n";
	        	}
	        	if (primary_survey_circulation_cyanosed.isChecked()){
	        		data += "primary_survey_circulation: cyanosed\n";
	        	}
	        	
	        	RadioButton primary_survey_external_bleeding_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_yes);
	        	RadioButton primary_survey_external_bleeding_no = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_no);
	        	
	        	if (primary_survey_external_bleeding_yes.isChecked()){
	        		data += "primary_survey_external_bleeding: yes\n";
	        	}
	        	if (primary_survey_external_bleeding_no.isChecked()){
	        		data += "primary_survey_external_bleeding: no\n";
	        	}
	        	
	        	
	         	RadioButton primary_survey_consiousness_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_yes);
	        	RadioButton primary_survey_consiousness_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_no);
	        	
	        	if (primary_survey_consiousness_yes.isChecked()){
	        		data += "primary_survey_consiousness: yes\n";
	        	}
	        	if (primary_survey_consiousness_no.isChecked()){
	        		data += "primary_survey_consiousness: no\n";
	        	}
	        	
	        	return data;
	        }
	    }


}
