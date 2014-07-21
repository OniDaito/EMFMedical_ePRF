package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TimePicker;
import uk.co.section9.emfmedical.DrawingView;


public class OutcomeActivity extends FragmentActivity {
	
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
	        
	        
	        public static String getData() {
	        	String data = new String();
	        	
	        	CheckBox outcome_walking_unaided = (CheckBox)mainView.findViewById(R.id.outcome_walking_unaided);
	        	if (outcome_walking_unaided.isChecked()){
	        		data += "outcome_walking_unaided: yes\n";
	        	}
	        	
	        	CheckBox outcome_walking_aided = (CheckBox)mainView.findViewById(R.id.outcome_walking_aided);
	        	if (outcome_walking_aided.isChecked()){
	        		data += "outcome_walking_aided: yes\n";
	        	}
	        	
	        	
	        	CheckBox outcome_stretcher = (CheckBox)mainView.findViewById(R.id.outcome_stretcher);
	        	if (outcome_stretcher.isChecked()){
	        		data += "outcome_stretcher: yes\n";
	        	}
	        	
	        	CheckBox outcome_longboard = (CheckBox)mainView.findViewById(R.id.outcome_longboard);
	        	if (outcome_longboard.isChecked()){
	        		data += "outcome_longboard: yes\n";
	        	}
	        	
	        	CheckBox outcome_carry_chair = (CheckBox)mainView.findViewById(R.id.outcome_carry_chair);
	        	if (outcome_carry_chair.isChecked()){
	        		data += "outcome_carry_chair: yes\n";
	        	}
	        	
	        	CheckBox outcome_orthopaedic_stretcher = (CheckBox)mainView.findViewById(R.id.outcome_orthopaedic_stretcher);
	        	if (outcome_orthopaedic_stretcher.isChecked()){
	        		data += "outcome_orthopaedic_stretcher: yes\n";
	        	}
	        	
	        	EditText outcome_departure_other = (EditText)mainView.findViewById(R.id.outcome_departure_other);
	        	data += "outcome_departure_other: " + outcome_departure_other.getEditableText() + "\n";
	        	
	        	CheckBox outcome_own_transport = (CheckBox)mainView.findViewById(R.id.outcome_own_transport);
	        	if (outcome_own_transport.isChecked()){
	        		data += "outcome_own_transport: yes\n";
	        	}
	        	
	        	CheckBox outcome_public_transport = (CheckBox)mainView.findViewById(R.id.outcome_public_transport);
	        	if (outcome_public_transport.isChecked()){
	        		data += "outcome_public_transport: yes\n";
	        	}
	        	
	        	CheckBox outcome_nhs_ambulance = (CheckBox)mainView.findViewById(R.id.outcome_nhs_ambulance);
	        	if (outcome_nhs_ambulance.isChecked()){
	        		data += "outcome_nhs_ambulance: yes\n";
	        	}
	        	
	        	
	        	CheckBox outcome_treatment_completed = (CheckBox)mainView.findViewById(R.id.outcome_treatment_completed);
	        	if (outcome_treatment_completed.isChecked()){
	        		data += "outcome_treatment_completed: yes\n";
	        	}
	        	
	        	
	        	CheckBox outcome_advised_to_seek = (CheckBox)mainView.findViewById(R.id.outcome_advised_to_seek);
	        	if (outcome_advised_to_seek.isChecked()){
	        		data += "outcome_advised_to_seek: yes\n";
	        	}
	        	
	        	
	        	CheckBox outcome_hospital = (CheckBox)mainView.findViewById(R.id.outcome_hospital);
	        	if (outcome_hospital.isChecked()){
	        		data += "outcome_hospital: yes\n";
	        	}
	        	
	        	CheckBox outcome_review_later = (CheckBox)mainView.findViewById(R.id.outcome_review_later);
	        	if (outcome_review_later.isChecked()){
	        		data += "outcome_review_later: yes\n";
	        	}
	        	
	        	TimePicker outcome_time_left_first_aid = (TimePicker)mainView.findViewById(R.id.outcome_time_left_first_aid);
	        	data += "outcome_time_left_first_aid: " + outcome_time_left_first_aid.getCurrentHour() + ":" + outcome_time_left_first_aid.getCurrentMinute() + "\n";
	        	
	        	return data;
	        }
	        
	    }


}
