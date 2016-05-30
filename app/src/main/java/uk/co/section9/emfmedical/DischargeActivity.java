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

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
        public void onStop() {
            super.onStop();
            setCurrentPRF();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            setCurrentPRF();
        }

        @Override
        public void onPause() {
            super.onPause();
            setCurrentPRF();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            mainView = inflater.inflate(R.layout.outcome, container, false);
            mUsed = true;

            TimePicker tt = (TimePicker)mainView.findViewById(R.id.outcome_time_left_first_aid);
            tt.setIs24HourView(true);
            tt.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
            getCurrentPRF();
            return mainView;
        }

        public static void setCurrentPRF() {
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
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.set(Calendar.HOUR, outcome_time_left_first_aid.getCurrentHour());
            cal.set(Calendar.MINUTE, outcome_time_left_first_aid.getCurrentMinute() );
            dd = cal.getTime();
            ds.set_time_left(dd);
        }

        public static void getCurrentPRF() {
            PRF prf = EMFMedicalApp.getCurrentPRF();
            Discharge ds = prf.get_discharge();

            char datachar = ds.get_walking_unaided();
            CheckBox outcome_walking_unaided = (CheckBox)mainView.findViewById(R.id.outcome_walking_unaided);
            if (datachar == 'y'){
                outcome_walking_unaided.setChecked(true);
            } else {
                outcome_walking_unaided.setChecked(false);
            }

            datachar = ds.get_walking_aided();
            CheckBox outcome_walking_aided = (CheckBox)mainView.findViewById(R.id.outcome_walking_aided);
            if (datachar == 'y'){
               outcome_walking_aided.setChecked(true);
            } else {
               outcome_walking_aided.setChecked(false);
            }

            EditText outcome_departure_other = (EditText)mainView.findViewById(R.id.outcome_departure_other);
            outcome_departure_other.setText(ds.get_other());

            CheckBox outcome_own_transport = (CheckBox)mainView.findViewById(R.id.outcome_own_transport);
            if (ds.get_own_transport() == 'y'){
                outcome_own_transport.setChecked(true);
            } else {
                outcome_own_transport.setChecked(false);
            }

            CheckBox outcome_public_transport = (CheckBox)mainView.findViewById(R.id.outcome_public_transport);
            if (ds.get_own_transport() == 'y'){
                outcome_own_transport.setChecked(true);
            } else {
                outcome_own_transport.setChecked(false);
            }

            CheckBox outcome_ambulance = (CheckBox)mainView.findViewById(R.id.outcome_ambulance);
            if (ds.get_ambulance() == 'y'){
                outcome_ambulance.setChecked(true);
            } else {
                outcome_ambulance.setChecked(false);
            }

            CheckBox outcome_treatment_completed = (CheckBox)mainView.findViewById(R.id.outcome_treatment_completed);
            if (ds.get_completed() == 'y'){
                outcome_treatment_completed.setChecked(true);
            } else {
                outcome_treatment_completed.setChecked(false);
            }

            CheckBox outcome_advised_to_seek = (CheckBox)mainView.findViewById(R.id.outcome_advised_to_seek);
            if (ds.get_advised() == 'y'){
                outcome_advised_to_seek.setChecked(true);
            } else {
                outcome_advised_to_seek.setChecked(false);
            }

            CheckBox outcome_hospital = (CheckBox)mainView.findViewById(R.id.outcome_hospital);
            if (ds.get_advised() == 'y'){
                outcome_hospital.setChecked(true);
            } else {
                outcome_hospital.setChecked(false);
            }

            CheckBox outcome_review_later = (CheckBox)mainView.findViewById(R.id.outcome_review_later);
            if (ds.get_review() == 'y'){
                outcome_review_later.setChecked(true);
            } else {
                outcome_review_later.setChecked(false);
            }

            EditText outcome_receiving_centre = (EditText)mainView.findViewById(R.id.outcome_receiving_centre);
            outcome_receiving_centre.setText(ds.get_receiving_centre());

            TimePicker outcome_time_left_first_aid = (TimePicker)mainView.findViewById(R.id.outcome_time_left_first_aid);

            Date dd = ds.get_time_left();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            cal.setTime(dd);
            outcome_time_left_first_aid.setCurrentHour(cal.get(Calendar.HOUR));
            outcome_time_left_first_aid.setCurrentMinute(cal.get(Calendar.MINUTE));

        }

    }

}
