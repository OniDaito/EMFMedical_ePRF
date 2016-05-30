package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Date;

import uk.co.section9.emfmedical.data.Discharge;
import uk.co.section9.emfmedical.data.PRF;

// Outcome Tab

public class DischargeActivity extends FragmentActivity {
	
	 public static class OutcomeFragment extends Fragment {

		 	public static View mainView;
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
	            mainView = inflater.inflate(R.layout.outcome, container, false);
	            mUsed = true;
	            return mainView;
	        }

	        public static void getCurrentPRF() {
				PRF prf = EMFMedicalApp.getCurrentPRF();
                Discharge ds = prf.get_discharge();

	        	CheckBox outcome_walking_unaided = (CheckBox)mainView.findViewById(R.id.outcome_walking_unaided);
	        	if (outcome_walking_unaided.isChecked()){
                    ds.set_walking_unaided('y');
	        	}
	        	
	        	CheckBox outcome_walking_aided = (CheckBox)mainView.findViewById(R.id.outcome_walking_aided);
	        	if (outcome_walking_aided.isChecked()){
                    ds.set_walking_aided('y');
	        	}

	        	EditText outcome_departure_other = (EditText)mainView.findViewById(R.id.outcome_departure_other);
                ds.set_other(""+outcome_departure_other.getEditableText());

	        	CheckBox outcome_own_transport = (CheckBox)mainView.findViewById(R.id.outcome_own_transport);
	        	if (outcome_own_transport.isChecked()){
	        		ds.set_own_transport('y');
	        	}
	        	
	        	CheckBox outcome_public_transport = (CheckBox)mainView.findViewById(R.id.outcome_public_transport);
	        	if (outcome_public_transport.isChecked()){
	        		ds.set_public_transport('y');
	        	}
	        	
	        	CheckBox outcome_ambulance = (CheckBox)mainView.findViewById(R.id.outcome_ambulance);
	        	if (outcome_ambulance.isChecked()){
                    ds.set_ambulance('y');
	        	}

	        	CheckBox outcome_treatment_completed = (CheckBox)mainView.findViewById(R.id.outcome_treatment_completed);
	        	if (outcome_treatment_completed.isChecked()){
	        		ds.set_completed('y');
	        	}

	        	CheckBox outcome_advised_to_seek = (CheckBox)mainView.findViewById(R.id.outcome_advised_to_seek);
	        	if (outcome_advised_to_seek.isChecked()){
	        		ds.set_advised('y');
	        	}

	        	CheckBox outcome_hospital = (CheckBox)mainView.findViewById(R.id.outcome_hospital);
	        	if (outcome_hospital.isChecked()){
	        		ds.set_hospital('y');
	        	}
	        	
	        	CheckBox outcome_review_later = (CheckBox)mainView.findViewById(R.id.outcome_review_later);
	        	if (outcome_review_later.isChecked()){
	        		ds.set_review('y');
	        	}

                EditText outcome_receiving_centre = (EditText)mainView.findViewById(R.id.outcome_receiving_centre);
                ds.set_other(""+outcome_receiving_centre.getEditableText());
	        	
	        	TimePicker outcome_time_left_first_aid = (TimePicker)mainView.findViewById(R.id.outcome_time_left_first_aid);

                Date dd = new Date();
                dd.setHours(outcome_time_left_first_aid.getCurrentHour());
                dd.setMinutes(outcome_time_left_first_aid.getCurrentMinute());
                ds.set_time_left(dd);
	        }
	        
	    }


}
