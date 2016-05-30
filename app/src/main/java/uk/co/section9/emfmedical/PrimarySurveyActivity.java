package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import uk.co.section9.emfmedical.data.PRF;
import uk.co.section9.emfmedical.data.Primary;


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
	        

	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            mainView = inflater.inflate(R.layout.primary_survey, container, false);
	            mUsed = true;
	            return mainView;
	        }


	        public static void setCurrentPRF() {

				PRF prf = EMFMedicalApp.getCurrentPRF();
				Primary pp = prf.get_primary();
	        	
	        	EditText primary_survey_problem = (EditText)mainView.findViewById(R.id.primary_survey_problem);
				pp.set_presenting(""+primary_survey_problem.getEditableText());
	        	
	        	RadioButton primary_survey_capacity_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_yes);
	        	RadioButton primary_survey_capacity_no = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_no);
	        	
	        	if (primary_survey_capacity_yes.isChecked()){
					pp.set_capacity('y');
	        	}
	        	if (primary_survey_capacity_no.isChecked()){
					pp.set_capacity('n');
	        	}
	        	
	        	RadioButton primary_survey_consent_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_yes);
	        	RadioButton primary_survey_consent_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_no);
	        	
	        	if (primary_survey_consent_yes.isChecked()){
					pp.set_consent('y');
	        	}
	        	if (primary_survey_consent_no.isChecked()){
					pp.set_consent('n');
	        	}
	        	
	        	RadioButton primary_survey_response_alert = (RadioButton)mainView.findViewById(R.id.primary_survey_response_alert);
	        	RadioButton primary_survey_response_voice = (RadioButton)mainView.findViewById(R.id.primary_survey_response_voice);
	        	RadioButton primary_survey_response_pain = (RadioButton)mainView.findViewById(R.id.primary_survey_response_pain);
	        	RadioButton primary_survey_response_none = (RadioButton)mainView.findViewById(R.id.primary_survey_response_none);
	        	
	        	if (primary_survey_response_alert.isChecked()){
					pp.set_consciousness('a');
	        	}
	        	if (primary_survey_response_voice.isChecked()){
					pp.set_consciousness('v');
	        	}
	        	if (primary_survey_response_pain.isChecked()){
					pp.set_consciousness('p');
	        	}
	        	if (primary_survey_response_none.isChecked()){
					pp.set_consciousness('u');
	        	}
	        	
	        	
	        	RadioButton primary_survey_airway_clear = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_clear);
	        	RadioButton primary_survey_airway_obstructed = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_obstructed);
	        	
	        	if (primary_survey_airway_clear.isChecked()){
					pp.set_airway('y');
	        	}
	        	if (primary_survey_airway_obstructed.isChecked()){
					pp.set_airway('n');
	        	}
	        	
	        	RadioButton primary_survey_breathing_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_normal);
	        	RadioButton primary_survey_breathing_shallow = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_shallow);
	        	RadioButton primary_survey_breathing_agonal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_agonal);
	        	RadioButton primary_survey_breathing_absent = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_absent);
                RadioButton primary_survey_breathing_rapid = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_rapid);

	        	if (primary_survey_breathing_normal.isChecked()){
					pp.set_breathing('n');
	        	}
				if (primary_survey_breathing_rapid.isChecked()){
					pp.set_breathing('r');
				}
	        	if (primary_survey_breathing_shallow.isChecked()){
					pp.set_breathing('s');
	        	}
	        	if (primary_survey_breathing_agonal.isChecked()){
					pp.set_breathing('a');
	        	}
	        	if (primary_survey_breathing_absent.isChecked()){
					pp.set_breathing('b');
	        	}
	        	
	        	RadioButton primary_survey_circulation_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_normal);
	        	RadioButton primary_survey_circulation_pale = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_pale);
	        	RadioButton primary_survey_circulation_flushed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_flushed);
	        	RadioButton primary_survey_circulation_cyanosed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_cyanosed);
	        	
	        	if (primary_survey_circulation_normal.isChecked()){
	        		pp.set_circulation('n');
	        	}
	        	if (primary_survey_circulation_pale.isChecked()){
                    pp.set_circulation('p');
	        	}
	        	if (primary_survey_circulation_flushed.isChecked()){
                    pp.set_circulation('f');
	        	}
	        	if (primary_survey_circulation_cyanosed.isChecked()){
                    pp.set_circulation('c');
	        	}
	        	
	        	RadioButton primary_survey_external_bleeding_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_yes);
	        	RadioButton primary_survey_external_bleeding_no = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_no);
	        	
	        	if (primary_survey_external_bleeding_yes.isChecked()){
	        		pp.set_external('y');
	        	}
	        	if (primary_survey_external_bleeding_no.isChecked()){
                    pp.set_external('n');
	        	}

	         	RadioButton primary_survey_consiousness_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_yes);
	        	RadioButton primary_survey_consiousness_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_no);
	        	
	        	if (primary_survey_consiousness_yes.isChecked()){
                    pp.set_consciousness('y');
	        	}
	        	if (primary_survey_consiousness_no.isChecked()){
                    pp.set_consciousness('n');
	        	}

                RadioButton primary_survey_alcoholdrugs_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_alcoholdrugs_yes);
                RadioButton primary_survey_alcoholdrugs_no = (RadioButton)mainView.findViewById(R.id.primary_survey_alcoholdrugs_no);

                if (primary_survey_alcoholdrugs_yes.isChecked()){
                    pp.set_alcoholdrugs('y');
                }
                if (primary_survey_alcoholdrugs_no.isChecked()){
                    pp.set_alcoholdrugs('n');
                }
	        }
	    }

}
