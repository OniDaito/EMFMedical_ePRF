package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

// Resuscitation Tab

public class ResuscitationActivity extends FragmentActivity {

	
	 public static class ResuscitationFragment extends Fragment {
	     
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
	            mainView = inflater.inflate(R.layout.resuscitation, container, false);
	            mUsed = true;
	            return mainView;
	        }
	        
	        
	        public static String getData() {
	        	String data = new String();
	        	
	        	TimePicker resuscitation_approximate_time_of_arrest = (TimePicker)mainView.findViewById(R.id.resuscitation_approximate_time_of_arrest);
	        	data += "resuscitation_approximate_time_of_arrest: " + resuscitation_approximate_time_of_arrest.getCurrentHour() + ":" + resuscitation_approximate_time_of_arrest.getCurrentMinute() + "\n";
	        	
	        	RadioButton resuscitation_witnessed_collapse_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_witnessed_collapse_yes);
	        	RadioButton resuscitation_witnessed_collapse_no = (RadioButton)mainView.findViewById(R.id.resuscitation_witnessed_collapse_no);
	        	
	        	if (resuscitation_witnessed_collapse_yes.isChecked()){
	        		data += "resuscitation_witnessed_collapse: yes\n";
	        	}
	        	if (resuscitation_witnessed_collapse_no.isChecked()){
	        		data += "resuscitation_witnessed_collapse: no\n";
	        	}
	        	
	        	
	        	RadioButton resuscitation_bystander_cpr_in_progress_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_bystander_cpr_in_progress_yes);
	        	RadioButton resuscitation_bystander_cpr_in_progress_no = (RadioButton)mainView.findViewById(R.id.resuscitation_bystander_cpr_in_progress_no);
	        	
	        	if (resuscitation_bystander_cpr_in_progress_yes.isChecked()){
	        		data += "resuscitation_bystander_cpr_in_progress_yes: yes\n";
	        	}
	        	if (resuscitation_bystander_cpr_in_progress_no.isChecked()){
	        		data += "resuscitation_bystander_cpr_in_progress_yes: no\n";
	        	}
	        	
	        	
	        	TimePicker resuscitation_bystander_cpr_in_progress_time = (TimePicker)mainView.findViewById(R.id.resuscitation_bystander_cpr_in_progress_time);
	        	data += "resuscitation_bystander_cpr_in_progress_time: " + resuscitation_bystander_cpr_in_progress_time.getCurrentHour() + ":" + resuscitation_bystander_cpr_in_progress_time.getCurrentMinute() + "\n";
	        	
	        	RadioButton resuscitation_cpr_started_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_started_yes);
	        	RadioButton resuscitation_cpr_started_no = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_started_no);
	        	
	        	if (resuscitation_cpr_started_yes.isChecked()){
	        		data += "resuscitation_cpr_started: yes\n";
	        	}
	        	if (resuscitation_cpr_started_no.isChecked()){
	        		data += "resuscitation_cpr_started: no\n";
	        	}
	        	
	        	TimePicker resuscitation_cpr_started_time = (TimePicker)mainView.findViewById(R.id.resuscitation_cpr_started_time);
	        	data += "resuscitation_cpr_started_time: " + resuscitation_cpr_started_time.getCurrentHour() + ":" + resuscitation_cpr_started_time.getCurrentMinute() + "\n";
	        	
	        	RadioButton resuscitation_cpr_continued_during_transfer_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_continued_during_transfer_yes);
	        	RadioButton resuscitation_cpr_continued_during_transfer_no = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_continued_during_transfer_no);
	        	
	        	if (resuscitation_cpr_continued_during_transfer_yes.isChecked()){
	        		data += "resuscitation_cpr_continued_during_transfer: yes\n";
	        	}
	        	if (resuscitation_cpr_continued_during_transfer_no.isChecked()){
	        		data += "resuscitation_cpr_continued_during_transfer: no\n";
	        	}
	        	
	        	RadioButton resuscitation_defibrillator_used_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_defibrillator_used_yes);
	        	RadioButton resuscitation_defibrillator_used_no = (RadioButton)mainView.findViewById(R.id.resuscitation_defibrillator_used_no);
	        	
	        	if (resuscitation_defibrillator_used_yes.isChecked()){
	        		data += "resuscitation_defibrillator_used: yes\n";
	        	}
	        	if (resuscitation_defibrillator_used_no.isChecked()){
	        		data += "resuscitation_defibrillator_used: no\n";
	        	}
	        	
	        	
	        	RadioButton resuscitation_return_of_normal_breathing_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_return_of_normal_breathing_yes);
	        	RadioButton resuscitation_return_of_normal_breathing_no = (RadioButton)mainView.findViewById(R.id.resuscitation_return_of_normal_breathing_no);
	        	
	        	if (resuscitation_return_of_normal_breathing_yes.isChecked()){
	        		data += "resuscitation_return_of_normal_breathing: yes\n";
	        	}
	        	if (resuscitation_return_of_normal_breathing_no.isChecked()){
	        		data += "resuscitation_return_of_normal_breathing: no\n";
	        	}
	        	
	        	TimePicker resuscitation_return_of_normal_breathing_time = (TimePicker)mainView.findViewById(R.id.resuscitation_return_of_normal_breathing_time);
	        	data += "resuscitation_return_of_normal_breathing_time: " + resuscitation_return_of_normal_breathing_time.getCurrentHour() + ":" + resuscitation_return_of_normal_breathing_time.getCurrentMinute() + "\n";
	        	
	        	EditText resuscitation_number_of_shocks = (EditText)mainView.findViewById(R.id.resuscitation_number_of_shocks);
	        	data += "resuscitation_number_of_shocks: " + resuscitation_number_of_shocks.getEditableText() + "\n";
	        	
	        	return data;
	        }
	    }

	
}
