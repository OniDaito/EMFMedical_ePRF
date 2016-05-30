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
import java.util.Calendar;

import java.util.Date;

import uk.co.section9.emfmedical.data.Incident;
import uk.co.section9.emfmedical.data.PRF;

// Incident Tab - the first tab to be completed

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

                TimePicker incident_time = (TimePicker)mainView.findViewById(R.id.incident_time);
                incident_time.setIs24HourView(true);
                incident_time.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

                return mainView;
	        }
	        

	        public static boolean used() {
		    	return mUsed;
		    }
	        
	        // Parse the form and grab the data
	        
	        public static void setCurrentPRF(){
                PRF prf = EMFMedicalApp.getCurrentPRF();
                Incident inc = prf.get_incident();

	        	TimePicker incident_time = (TimePicker)mainView.findViewById(R.id.incident_time);
                Date dd = new Date();
                dd.setHours(incident_time.getCurrentHour());
                dd.setMinutes(incident_time.getCurrentMinute());
                DatePicker incident_date = (DatePicker)mainView.findViewById(R.id.incident_date);

                dd.setDate(incident_date.getDayOfMonth());
                dd.setMonth(incident_date.getMonth());
                dd.setYear(incident_date.getYear());

                inc.set_time(dd);

	        	EditText incident_location = (EditText)mainView.findViewById(R.id.incident_location);
                inc.set_location(""+incident_location.getEditableText());

	        	EditText incident_family_name = (EditText)mainView.findViewById(R.id.incident_family_name);
                inc.set_surname(""+incident_family_name.getEditableText());

	        	EditText incident_forename = (EditText)mainView.findViewById(R.id.incident_forename);
                inc.set_forname(""+incident_forename.getEditableText());

	        	EditText incident_email = (EditText)mainView.findViewById(R.id.incident_email);
                inc.set_email(""+incident_email.getEditableText());

	        	EditText incident_address = (EditText)mainView.findViewById(R.id.incident_address);
                inc.set_address(""+incident_address.getEditableText());

	        	EditText incident_postcode = (EditText)mainView.findViewById(R.id.incident_postcode);
                inc.set_postcode(""+incident_postcode.getEditableText());

	        	DatePicker incident_date_of_birth = (DatePicker)mainView.findViewById(R.id.incident_date_of_birth);
                Date dob = new Date();

                dob.setDate(incident_date_of_birth.getDayOfMonth());
                dob.setMonth(incident_date_of_birth.getMonth());
                dob.setYear(incident_date_of_birth.getYear());
                inc.set_dob(dob);

	        	EditText incident_age = (EditText)mainView.findViewById(R.id.incident_age);
                inc.set_age(Integer.getInteger(""+ incident_age.getEditableText()));

                EditText incident_gender = (EditText)mainView.findViewById(R.id.incident_gender);
                inc.set_gender(""+incident_gender.getEditableText());

	        	EditText incident_next_of_kin = (EditText)mainView.findViewById(R.id.incident_next_of_kin);
                inc.set_kin(""+incident_next_of_kin.getEditableText());

	        }
	    }


}
