package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

public class IncidentActivity  extends FragmentActivity {
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    
	    }

	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);

	    }

	    public static class IncidentFragment extends Fragment {
	     
	    	static View mainView;
	    	
	    	static boolean mUsed = false;
	    	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        	mainView = inflater.inflate(R.layout.incident, container, false);
	        	mUsed = true;
	            return mainView;
	        }
	        
	        
	        public static boolean used() {
		    	return mUsed;
		    }
	        
	        // Parse the form and grab the data
	        
	        public static String getData(){
	        	String data = new String();
	        	TimePicker incident_time = (TimePicker)mainView.findViewById(R.id.incident_time);
	        	data += "incident_time: " + incident_time.getCurrentHour() + ":" + incident_time.getCurrentMinute() + "\n";
	        	
	        	DatePicker incident_date = (DatePicker)mainView.findViewById(R.id.incident_date);
	        	data += "incident_date: " + incident_date.getDayOfMonth() + "/" + incident_date.getMonth() + "/" + incident_date.getYear() + "\n";
	        	
	        	EditText incident_location = (EditText)mainView.findViewById(R.id.incident_location);
	        	data += "incident_location: " + incident_location.getEditableText() + "\n";
	        	
	        	RadioButton incident_alerted_called = (RadioButton)mainView.findViewById(R.id.incident_alerted_called);
	        	RadioButton incident_alerted_self = (RadioButton)mainView.findViewById(R.id.incident_alerted_self);
	        	
	        	if (incident_alerted_called.isChecked()){
	        		data += "incident_alerted: called\n";
	        	}
	        	if (incident_alerted_self.isChecked()){
	        		data += "incident_alerted: self\n";
	        	}
	        	
	        	EditText incident_family_name = (EditText)mainView.findViewById(R.id.incident_family_name);
	        	data += "incident_family_name: " + incident_family_name.getEditableText() + "\n";
	        	
	        	EditText incident_forename = (EditText)mainView.findViewById(R.id.incident_forename);
	        	data += "incident_forename: " + incident_forename.getEditableText() + "\n";
	        	
	        	EditText incident_address = (EditText)mainView.findViewById(R.id.incident_address);
	        	data += "incident_address: " + incident_address.getEditableText() + "\n";
	        	
	        	EditText incident_postcode = (EditText)mainView.findViewById(R.id.incident_postcode);
	        	data += "incident_postcode: " + incident_postcode.getEditableText() + "\n";
	        	
	        	DatePicker incident_date_of_birth = (DatePicker)mainView.findViewById(R.id.incident_date_of_birth);
	        	data += "incident_date_of_birth: " + incident_date_of_birth.getDayOfMonth() + "/" + incident_date_of_birth.getMonth() + "/" + incident_date_of_birth.getYear() + "\n";
	        	
	        	EditText incident_age = (EditText)mainView.findViewById(R.id.incident_age);
	        	data += "incident_age: " + incident_age.getEditableText() + "\n";
	        	
	        	RadioButton incident_gender_male = (RadioButton)mainView.findViewById(R.id.incident_gender_male);
	        	RadioButton incident_gender_female = (RadioButton)mainView.findViewById(R.id.incident_gender_female);
	        	
	        	if (incident_gender_male.isChecked()){
	        		data += "incident_gender: male\n";
	        	}
	        	if (incident_gender_female.isChecked()){
	        		data += "incident_gender: female\n";
	        	}
	        	
	        	EditText incident_next_of_kin = (EditText)mainView.findViewById(R.id.incident_next_of_kin);
	        	data += "incident_next_of_kin: " + incident_next_of_kin.getEditableText() + "\n";
	        	
	        	return data;
	        }
	    }


}
