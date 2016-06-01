package uk.co.section9.emfmedical;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;

import java.util.Date;
import java.util.TimeZone;

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

	public static class IncidentFragment extends PRFFragment {

		static View mainView;
		static boolean mUsed = false;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			mainView = inflater.inflate(R.layout.incident, container, false);
			mUsed = true;

            getCurrentPRF();

			return mainView;
		}

        @Override
        public void onPause() {
            super.onPause();
            setCurrentPRF();
        }

        @Override
        public void onStop() {
            super.onStop();
            setCurrentPRF();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            setCurrentPRF();
        }

		public static boolean used() {
		return mUsed;
		}

		// Parse the form and grab the data

		public static void setCurrentPRF(){
			PRF prf = EMFMedicalApp.getCurrentPRF();
			Incident inc = prf.get_incident();

			TimePicker incident_time = (TimePicker)mainView.findViewById(R.id.incident_time);
            incident_time.setIs24HourView(true);
			DatePicker incident_date = (DatePicker)mainView.findViewById(R.id.incident_date);
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.set( Calendar.YEAR, incident_date.getYear());
            cal.set( Calendar.MONTH, incident_date.getMonth());
            cal.set( Calendar.DATE, incident_date.getDayOfMonth());
            cal.set( Calendar.MINUTE, incident_time.getCurrentMinute());
            cal.set( Calendar.HOUR_OF_DAY, incident_time.getCurrentHour());

            Date dd = cal.getTime();
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

            cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

            cal.set( Calendar.YEAR, incident_date_of_birth.getYear());
            cal.set( Calendar.MONTH, incident_date_of_birth.getMonth());
            cal.set( Calendar.DATE, incident_date_of_birth.getDayOfMonth());
            Date dob = cal.getTime();
			inc.set_dob(dob);

			EditText incident_age = (EditText)mainView.findViewById(R.id.incident_age);

            try  {
                inc.set_age( Integer.parseInt((incident_age.getText().toString())));
            } catch (NumberFormatException e ){
                // Just pass for now - not great
            }

			EditText incident_gender = (EditText)mainView.findViewById(R.id.incident_gender);
			inc.set_gender(""+incident_gender.getEditableText());

			EditText incident_next_of_kin = (EditText)mainView.findViewById(R.id.incident_next_of_kin);
			inc.set_kin(""+incident_next_of_kin.getEditableText());

		}

		public static void getCurrentPRF(){
			PRF prf = EMFMedicalApp.getCurrentPRF();
			Incident inc = prf.get_incident();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

			TimePicker incident_time = (TimePicker)mainView.findViewById(R.id.incident_time);
            incident_time.setIs24HourView(true);
			Date dd = inc.get_time();
            cal.set(Calendar.HOUR_OF_DAY, dd.getHours());
            cal.set(Calendar.MINUTE, dd.getMinutes());
			incident_time.setCurrentMinute(cal.get(Calendar.MINUTE));
            incident_time.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));

            DatePicker incident_date = (DatePicker)mainView.findViewById(R.id.incident_date);
            cal.setTime(dd);

            incident_date.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

			EditText incident_location = (EditText)mainView.findViewById(R.id.incident_location);
            incident_location.setText(inc.get_location());

			EditText incident_family_name = (EditText)mainView.findViewById(R.id.incident_family_name);
            incident_family_name.setText(inc.get_surname());

			EditText incident_forename = (EditText)mainView.findViewById(R.id.incident_forename);
			incident_forename.setText(inc.get_forname());

			EditText incident_email = (EditText)mainView.findViewById(R.id.incident_email);
            incident_email.setText(inc.get_email());

			EditText incident_address = (EditText)mainView.findViewById(R.id.incident_address);
			incident_address.setText(inc.get_address());

			EditText incident_postcode = (EditText)mainView.findViewById(R.id.incident_postcode);
			incident_postcode.setText(inc.get_postcode());

			DatePicker incident_date_of_birth = (DatePicker)mainView.findViewById(R.id.incident_date_of_birth);
			dd = inc.get_dob();
            cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.setTime(dd);
            incident_date_of_birth.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

			EditText incident_age = (EditText)mainView.findViewById(R.id.incident_age);
            incident_age.setText(String.valueOf(inc.get_age()));

			EditText incident_gender = (EditText)mainView.findViewById(R.id.incident_gender);
            incident_gender.setText(inc.get_gender());

			EditText incident_next_of_kin = (EditText)mainView.findViewById(R.id.incident_next_of_kin);
            incident_next_of_kin.setText(inc.get_kin());

		}
	}

}
