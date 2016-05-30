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
import java.util.TimeZone;

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

        @Override
        public void onStop() {
            setCurrentPRF();
            super.onStop();
        }

        @Override
        public void onDestroy() {
            setCurrentPRF();
            super.onDestroy();
        }

        public static boolean used() {
        return mUsed;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
            mainView = inflater.inflate(R.layout.resuscitation, container, false);
            mUsed = true;

            TimePicker tt = (TimePicker)mainView.findViewById(R.id.resuscitation_cpr_started_time);
            tt.setIs24HourView(true);
            tt.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));


            tt = (TimePicker)mainView.findViewById(R.id.ambulance_arrived);
            tt.setIs24HourView(true);
            tt.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

            tt = (TimePicker)mainView.findViewById(R.id.ambulance_departed);
            tt.setIs24HourView(true);
            tt.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

            getCurrentPRF();

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

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

            if (resuscitation_cpr_started_yes.isChecked()){
                ss.set_cpr('y');

                TimePicker resuscitation_cpr_started_time = (TimePicker)mainView.findViewById(R.id.resuscitation_cpr_started_time);
                cal.set(Calendar.HOUR,resuscitation_cpr_started_time.getCurrentHour());
                cal.set(Calendar.MINUTE,resuscitation_cpr_started_time.getCurrentMinute());
                Date dd = cal.getTime();
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
                if (Integer.getInteger(""+ resuscitation_number_of_shocks.getEditableText()) != null) {
                    ss.set_defib_shocks(Integer.getInteger("" + resuscitation_number_of_shocks.getEditableText()));
                }
            }

            if (resuscitation_defibrillator_used_no.isChecked()){
                ss.set_defib_used('n');
            }

            RadioButton ambulance_called_yes = (RadioButton)mainView.findViewById(R.id.ambulance_called_yes);
            RadioButton ambulance_called_no = (RadioButton)mainView.findViewById(R.id.ambulance_called_no);

            if (ambulance_called_yes.isChecked()){
                ss.set_ambulance_called('y');

                TimePicker ambulance_arrived = (TimePicker)mainView.findViewById(R.id.ambulance_arrived);
                cal.set(Calendar.HOUR,ambulance_arrived.getCurrentHour());
                cal.set(Calendar.MINUTE, ambulance_arrived.getCurrentMinute());
                Date dd = cal.getTime();
                ss.set_ambulance_arrived(dd);

                TimePicker ambulance_departed = (TimePicker)mainView.findViewById(R.id.ambulance_departed);
                cal.set(Calendar.HOUR, ambulance_departed.getCurrentHour());
                cal.set(Calendar.MINUTE, ambulance_departed.getCurrentMinute());
                Date de = cal.getTime();
                ss.set_ambulance_departed(de);
            }

            if (ambulance_called_no.isChecked()){
                ss.set_ambulance_called('n');
            }
        }

        public static void getCurrentPRF() {
            PRF prf = EMFMedicalApp.getCurrentPRF();
            Serious ss = prf.get_serious();

            RadioButton resuscitation_witnessed_collapse_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_witnessed_collapse_yes);
            RadioButton resuscitation_witnessed_collapse_no = (RadioButton)mainView.findViewById(R.id.resuscitation_witnessed_collapse_no);

            resuscitation_witnessed_collapse_yes.setChecked(false);
            resuscitation_witnessed_collapse_no.setChecked(false);

            char datachar = ss.get_witnessed_collapse();

            if (datachar == 'y'){
                resuscitation_witnessed_collapse_yes.setChecked(true);
            } else if (datachar == 'n' ) {
                resuscitation_witnessed_collapse_no.setChecked(true);
            }

            RadioButton resuscitation_cpr_started_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_started_yes);
            RadioButton resuscitation_cpr_started_no = (RadioButton)mainView.findViewById(R.id.resuscitation_cpr_started_no);

            resuscitation_cpr_started_yes.setChecked(false);
            resuscitation_cpr_started_no.setChecked(false);

            datachar = ss.get_cpr();
            if (datachar == 'y') {
                resuscitation_cpr_started_yes.setChecked(true);
            } else if (datachar == 'n') {
                resuscitation_cpr_started_no.setChecked(true);
            }

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));


            TimePicker resuscitation_cpr_started_time = (TimePicker)mainView.findViewById(R.id.resuscitation_cpr_started_time);
            Date dd = ss.get_cpr_started();
            cal.setTime(dd);
            resuscitation_cpr_started_time.setCurrentMinute(cal.get(Calendar.HOUR));
            resuscitation_cpr_started_time.setCurrentHour(cal.get(Calendar.MINUTE));

            RadioButton resuscitation_defibrillator_used_yes = (RadioButton)mainView.findViewById(R.id.resuscitation_defibrillator_used_yes);
            RadioButton resuscitation_defibrillator_used_no = (RadioButton)mainView.findViewById(R.id.resuscitation_defibrillator_used_no);

            resuscitation_defibrillator_used_yes.setChecked(false);
            resuscitation_defibrillator_used_no.setChecked(false);

            datachar = ss.get_defib_used();
            if (datachar == 'y') { resuscitation_defibrillator_used_yes.setChecked(true); }
            else if (datachar == 'n') { resuscitation_defibrillator_used_no.setChecked(true); }

            RadioButton ambulance_called_yes = (RadioButton)mainView.findViewById(R.id.ambulance_called_yes);
            RadioButton ambulance_called_no = (RadioButton)mainView.findViewById(R.id.ambulance_called_no);

            ambulance_called_yes.setChecked(false);
            ambulance_called_no.setChecked(false);

            datachar = ss.get_ambulance_called();
            if (datachar == 'y') { ambulance_called_yes.setChecked(true); }
            else if (datachar == 'n') { ambulance_called_no.setChecked(true); }

            TimePicker ambulance_arrived = (TimePicker)mainView.findViewById(R.id.ambulance_arrived);
            dd = ss.get_ambulance_arrived();
            cal.setTime(dd);
            ambulance_arrived.setCurrentHour(cal.get(Calendar.HOUR));
            ambulance_arrived.setCurrentMinute(cal.get(Calendar.MINUTE));

            TimePicker ambulance_departed = (TimePicker)mainView.findViewById(R.id.ambulance_departed);
            dd = ss.get_ambulance_departed();
            cal.setTime(dd);
            ambulance_departed.setCurrentMinute(cal.get(Calendar.HOUR));
            ambulance_departed.setCurrentHour(cal.get(Calendar.MINUTE));

        }
    }

}
