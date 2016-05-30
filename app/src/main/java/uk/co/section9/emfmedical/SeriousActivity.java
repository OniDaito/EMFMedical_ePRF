package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.util.Date;

import uk.co.section9.emfmedical.data.PRF;
import uk.co.section9.emfmedical.data.Secondary;
import uk.co.section9.emfmedical.data.Serious;

// Resuscitation Tab

public class SeriousActivity extends FragmentActivity {

	
	 public static class SeriousFragment extends Fragment {
	     
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
	        
	        
	        public static void setCurrentPRF() {

				PRF prf = EMFMedicalApp.getCurrentPRF();
				Serious ss = prf.get_serious();

	        	RadioButton resuscitation_witnessed_collapse_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_witnessed_collapse_yes);
	        	RadioButton resuscitation_witnessed_collapse_no = (RadioButton)mainView.findViewById(R.id.resuscitation_witnessed_collapse_no);
	        	
	        	if (resuscitation_witnessed_collapse_yes.isChecked()){
	        		ss.set_witnessed_collapse('y');
	        	}
	        	if (resuscitation_witnessed_collapse_no.isChecked()){
                    ss.set_witnessed_collapse('n');
	        	}

	        	RadioButton resuscitation_cpr_started_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_started_yes);
	        	RadioButton resuscitation_cpr_started_no = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_started_no);
	        	
	        	if (resuscitation_cpr_started_yes.isChecked()){
                    ss.set_cpr('y');

                    TimePicker resuscitation_cpr_started_time = (TimePicker)mainView.findViewById(R.id.resuscitation_cpr_started_time);
                    Date dd = new Date();
                    dd.setHours(resuscitation_cpr_started_time.getCurrentHour());
                    dd.setMinutes(resuscitation_cpr_started_time.getCurrentMinute());
                    ss.set_cpr_started(dd);
                }

	        	if (resuscitation_cpr_started_no.isChecked()){
                    ss.set_cpr('n');
                }

	        	RadioButton resuscitation_defibrillator_used_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_defibrillator_used_yes);
	        	RadioButton resuscitation_defibrillator_used_no = (RadioButton)mainView.findViewById(R.id.resuscitation_defibrillator_used_no);
	        	
	        	if (resuscitation_defibrillator_used_yes.isChecked()){
                    ss.set_defib_used('y');
                    EditText resuscitation_number_of_shocks = (EditText)mainView.findViewById(R.id.resuscitation_number_of_shocks);
                    ss.set_defib_shocks(Integer.getInteger(""+resuscitation_number_of_shocks));
	        	}

	        	if (resuscitation_defibrillator_used_no.isChecked()){
                    ss.set_defib_used('n');
	        	}

                RadioButton ambulance_called_yes = (RadioButton)mainView.findViewById(R.id.ambulance_called_yes);
                RadioButton ambulance_called_no = (RadioButton)mainView.findViewById(R.id.ambulance_called_no);

                if (ambulance_called_yes.isChecked()){
                    ss.set_ambulance_called('y');

                    TimePicker ambulance_arrived = (TimePicker)mainView.findViewById(R.id.ambulance_arrived);
                    Date dd = new Date();
                    dd.setHours(ambulance_arrived.getCurrentHour());
                    dd.setMinutes(ambulance_arrived.getCurrentMinute());
                    ss.set_ambulance_arrived(dd);

                    TimePicker ambulance_departed = (TimePicker)mainView.findViewById(R.id.ambulance_departed);
                    Date de = new Date();
                    de.setHours(ambulance_arrived.getCurrentHour());
                    de.setMinutes(ambulance_arrived.getCurrentMinute());
                    ss.set_ambulance_departed(de);
                }

                if (ambulance_called_no.isChecked()){
                    ss.set_ambulance_called('n');
                }

	        }
	    }

	
}
