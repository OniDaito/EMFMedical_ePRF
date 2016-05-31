package uk.co.section9.emfmedical;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import uk.co.section9.emfmedical.data.Observation;
import uk.co.section9.emfmedical.data.PRF;

// Observations main tab

public class ObservationsActivity extends FragmentActivity {

    public static class ObservationsFragment extends Fragment {

        View mainView;
        static LinearLayout obsLinearLayout;
        static ArrayList<View> obsViews;
        static boolean mUsed = false;

        static LayoutInflater tinflater;
        static ViewGroup tgroup;

        // TODO - Make double sure we destroy all views and recreate as recreate from
        // loaded PRF is now the default action

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            obsViews = new ArrayList<View>();
            mUsed = true;
        }

        // TODO - we are removing all the views but I suspect we only need to do that
        // if we are quitting. Shouldnt do it if we are just popping back and forth between forms
        @Override
        public void onPause() {
            setCurrentPRF();
            super.onPause();
            for(View v: obsViews){
                obsLinearLayout.removeView(v);
            }
            obsViews.clear();
        }

        @Override
        public void onStop() {
            setCurrentPRF();
            super.onStop();
            for(View v: obsViews){
                obsLinearLayout.removeView(v);
            }
            obsViews.clear();
        }

        @Override
        public void onDestroy() {
            setCurrentPRF();
            super.onDestroy();
            for(View v: obsViews){
                obsLinearLayout.removeView(v);
            }
            obsViews.clear();
        }

        public static boolean used() {
return mUsed;
}

        public static void setCurrentPRF(){
            int idx = 0;

            PRF prf = EMFMedicalApp.getCurrentPRF();
            Vector<Observation> obs = prf.get_observations(); // This vec is probably temporary

            for(View v: obsViews){

                // Check if we need to add an new Observation
                if (idx >= obs.size()){
                    Observation ob = new Observation();
                    obs.add(ob);
                }

                Observation ob = obs.elementAt(idx);
                ob.set_oborder(idx);

                TimePicker observation_time = (TimePicker)v.findViewById(R.id.observation_time);
                observation_time.setIs24HourView(true);
                observation_time.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                Date dd = new Date();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                cal.set(Calendar.HOUR,observation_time.getCurrentHour());
                cal.set(Calendar.MINUTE,observation_time.getCurrentMinute());
                dd = cal.getTime();
                ob.set_time(dd);

                Spinner observation_response = (Spinner)v.findViewById(R.id.observation_response);
                if ( observation_response.getSelectedItem() != null) {
                    if (observation_response.getSelectedItem().toString().equals("Alert"))
                        ob.set_response('a');
                    else if (observation_response.getSelectedItem().toString().equals("Voice"))
                        ob.set_response('v');
                    else if (observation_response.getSelectedItem().toString().equals("Pain"))
                        ob.set_response('p');
                    else if (observation_response.getSelectedItem().toString().equals("Unresponsive"))
                        ob.set_response('u');
                }

                EditText observation_respiratory_rate = (EditText)v.findViewById(R.id.observation_respiratory_rate);
                ob.set_respiratory(""+observation_respiratory_rate.getEditableText());

                EditText observation_pulse_rate = (EditText)v.findViewById(R.id.observation_pulse_rate);
                ob.set_pulse(""+observation_pulse_rate.getEditableText());

                SeekBar observation_pain_score = (SeekBar) v.findViewById(R.id.observation_painscore);
                ob.set_painscore(observation_pain_score.getProgress());

                EditText observation_o2_saturation = (EditText)v.findViewById(R.id.observation_o2_saturation);
                try {
                    ob.set_o2sats(Float.parseFloat(""+observation_o2_saturation.getEditableText()));
                } catch(java.lang.NumberFormatException e) {
                    // Simply pass as its probably blank :S Not the best solution at present
                }

                EditText observation_blood_pressure_systolic = (EditText)v.findViewById(R.id.observation_blood_pressure_systolic);

                try {
                    ob.set_bp_sis(Integer.parseInt("" + observation_blood_pressure_systolic.getEditableText()));
                }catch(java.lang.NumberFormatException e) {
                    // Simply pass as its probably blank :S Not the best solution at present
                }

                EditText observation_blood_pressure_dystolic = (EditText)v.findViewById(R.id.observation_blood_pressure_dystolic);
                try {
                    ob.set_bp_dis(Integer.parseInt("" + observation_blood_pressure_dystolic.getEditableText()));
                }catch(java.lang.NumberFormatException e) {
                    // Simply pass as its probably blank :S Not the best solution at present
                }

                EditText observation_temperature = (EditText)v.findViewById(R.id.observation_temperature);
                try {
                    ob.set_temperature(Float.parseFloat(""+observation_temperature.getText()));
                } catch(java.lang.NumberFormatException e) {
                    // Simply pass as its probably blank :S Not the best solution at present
                }

                RadioButton observation_perl_yes = (RadioButton)v.findViewById(R.id.observation_perl_yes);
                RadioButton observation_perl_no = (RadioButton)v.findViewById(R.id.observation_perl_no);

                if (observation_perl_yes.isChecked()){
                    ob.set_perl('y');
                }
                if (observation_perl_no.isChecked()){
                    ob.set_perl('n');
                }

                RadioButton observation_eye_normal = (RadioButton)v.findViewById(R.id.observation_eye_normal);
                RadioButton observation_eye_constricted = (RadioButton)v.findViewById(R.id.observation_eye_constricted);
                RadioButton observation_eye_unequal = (RadioButton)v.findViewById(R.id.observation_eye_unequal);
                RadioButton observation_eye_dilated = (RadioButton)v.findViewById(R.id.observation_eye_dilated);

                if (observation_eye_normal.isChecked()){
                    ob.set_eyes('n');
                }
                if (observation_eye_constricted.isChecked()){
                    ob.set_eyes('c');
                }
                if (observation_eye_unequal.isChecked()){
                    ob.set_eyes('u');
                }
                if (observation_eye_dilated.isChecked()){
                    ob.set_eyes('d');
                }

                idx++;
            }
        }

        public static void getCurrentPRF(){
            int idx = 0;

            PRF prf = EMFMedicalApp.getCurrentPRF();
            Vector<Observation> obs = prf.get_observations();

            // One hopes the order of the obs is at least remembered :S Perhaps that wont work on
            // db recall

            // Assume the views have not been created (which of course they wouldnt be)
            // TODO - we do now have the order saved in the DB - should use that
            // TODO - this should be a method internally
            for (int i=obs.size()-1; i >=0; i--) {
                View obsview = tinflater.inflate(R.layout.observation_actual, tgroup, false);
                obsLinearLayout.addView(obsview);
                obsViews.add(obsview);
                TextView tv = (TextView) obsview.findViewById(R.id.observation_actual_heading);
                tv.append(" " + (obs.size()-i+1));

                TimePicker observation_time = (TimePicker)obsview.findViewById(R.id.observation_time);
                observation_time.setIs24HourView(true);
                observation_time.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

                // Painscore seek bar changer add
                final TextView tp = (TextView) obsview.findViewById(R.id.observation_painscore_value);
                class PainScoreListener implements SeekBar.OnSeekBarChangeListener {

                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        tp.setText(""+progress);
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {}
                    public void onStopTrackingTouch(SeekBar seekBar) {}

                }

                SeekBar painseekbar=(SeekBar) obsview.findViewById(R.id.observation_painscore);
                painseekbar.setOnSeekBarChangeListener(new PainScoreListener());
            }


            for(View v: obsViews) {

                Observation ob = obs.elementAt(idx);

                TimePicker observation_time = (TimePicker) v.findViewById(R.id.observation_time);
                Date dd = ob.get_time();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                observation_time.setIs24HourView(true);
                observation_time.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                cal.setTime(dd);
                observation_time.setCurrentHour(cal.get(Calendar.HOUR));
                observation_time.setCurrentMinute(cal.get(Calendar.MINUTE));

                Spinner observation_response = (Spinner) v.findViewById(R.id.observation_response);
                char datachar = ob.get_response();

                switch (datachar){
                    case 'a': {
                        observation_response.setSelection(0);
                        break;
                    }
                    case 'v' : {
                        observation_response.setSelection(1);
                        break;
                    }
                    case 'p' : {
                        observation_response.setSelection(2);
                        break;
                    }
                    case 'u' : {
                        observation_response.setSelection(3);
                        break;
                    }
                }


                EditText observation_respiratory_rate = (EditText) v.findViewById(R.id.observation_respiratory_rate);
                observation_respiratory_rate.setText(ob.get_respiratory());

                EditText observation_pulse_rate = (EditText) v.findViewById(R.id.observation_pulse_rate);
                observation_pulse_rate.setText(ob.get_pulse());

                SeekBar observation_pain_score = (SeekBar) v.findViewById(R.id.observation_painscore);
                observation_pain_score.setProgress(ob.get_painscore());

                EditText observation_o2_saturation = (EditText) v.findViewById(R.id.observation_o2_saturation);
                observation_o2_saturation.setText(String.valueOf(ob.get_o2sats()));

                EditText observation_blood_pressure_systolic = (EditText) v.findViewById(R.id.observation_blood_pressure_systolic);
                observation_blood_pressure_systolic.setText(String.valueOf(ob.get_bp_sis()));

                EditText observation_blood_pressure_dystolic = (EditText) v.findViewById(R.id.observation_blood_pressure_dystolic);
                observation_blood_pressure_dystolic.setText(String.valueOf(ob.get_bp_dis()));

                EditText observation_temperature = (EditText) v.findViewById(R.id.observation_temperature);
                observation_temperature.setText(String.valueOf(ob.get_temperature()));

                datachar = ob.get_perl();
                RadioButton observation_perl_yes = (RadioButton) v.findViewById(R.id.observation_perl_yes);
                RadioButton observation_perl_no = (RadioButton) v.findViewById(R.id.observation_perl_no);

                if (datachar == 'y') {
                    observation_perl_yes.setChecked(true);
                    observation_perl_no.setChecked(false);
                } else if (datachar == 'n') {
                    observation_perl_yes.setChecked(false);
                    observation_perl_no.setChecked(true);
                } else {
                    observation_perl_yes.setChecked(false);
                    observation_perl_no.setChecked(false);
                }

                datachar = ob.get_eyes();
                RadioButton observation_eye_normal = (RadioButton) v.findViewById(R.id.observation_eye_normal);
                RadioButton observation_eye_constricted = (RadioButton) v.findViewById(R.id.observation_eye_constricted);
                RadioButton observation_eye_unequal = (RadioButton) v.findViewById(R.id.observation_eye_unequal);
                RadioButton observation_eye_dilated = (RadioButton) v.findViewById(R.id.observation_eye_dilated);

                observation_eye_normal.setChecked(false);
                observation_eye_constricted.setChecked(false);
                observation_eye_unequal.setChecked(false);
                observation_eye_dilated.setChecked(false);

                switch (datachar){
                    case 'n' : {
                        observation_eye_normal.setChecked(true);
                    }
                    case 'c' : {
                        observation_eye_constricted.setChecked(true);
                    }
                    case 'u' : {
                        observation_eye_unequal.setChecked(true);
                    }
                    case 'd' : {
                        observation_eye_dilated.setChecked(true);
                    }
                }

                idx++;
            }
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

            mainView = inflater.inflate(R.layout.observations, container, false);
            // Set our static creator variables - potentially naughty?
            tinflater = inflater;
            tgroup = container;

            Button button = (Button) mainView.findViewById(R.id.button_add_observation);
            obsLinearLayout = (LinearLayout) mainView.findViewById(R.id.layout_observations);

            // run through the master obsViews and re-create them
            if (obsViews.size() > 0){
                for(View v: obsViews){
                    obsLinearLayout.addView(v);
                }
            }

            getCurrentPRF();

            // When button is clicked, create a new Obs form

            button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    View obs = tinflater.inflate(R.layout.observation_actual, tgroup, false);
                    obsLinearLayout.addView(obs);

                    // Reverse Order
                    ArrayList<View> views = new ArrayList<View>();
                    for(int x = 0; x < obsLinearLayout.getChildCount(); x++) {
                        views.add(obsLinearLayout.getChildAt(x));
                    }
                    obsLinearLayout.removeAllViews();
                    for(int x = views.size() - 1; x >= 0; x--) {
                        obsLinearLayout.addView(views.get(x));
                    }

                    // Also add to the master list of obs
                    obsViews.add(obs);
                    TextView tv = (TextView) obs.findViewById(R.id.observation_actual_heading);
                    tv.append(" " + obsViews.size());

                    // Setup bits of the new obs
                    TimePicker observation_time = (TimePicker)obs.findViewById(R.id.observation_time);
                    observation_time.setIs24HourView(true);
                    observation_time.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

                    // Painscore seek bar changer add
                    final TextView tp = (TextView) obs.findViewById(R.id.observation_painscore_value);
                    class PainScoreListener implements SeekBar.OnSeekBarChangeListener {

                        public void onProgressChanged(SeekBar seekBar, int progress,
                                                      boolean fromUser) {
                            tp.setText(""+progress);
                        }

                        public void onStartTrackingTouch(SeekBar seekBar) {}
                        public void onStopTrackingTouch(SeekBar seekBar) {}

                    }

                    SeekBar painseekbar=(SeekBar) obs.findViewById(R.id.observation_painscore);
                    painseekbar.setOnSeekBarChangeListener(new PainScoreListener());

                    // Reverse the order
                    views = new ArrayList<View>();
                    for(int x = 0; x < obsViews.size(); x++) {
                        views.add(obsViews.get(x));
                    }
                    obsViews.clear();
                    for(int x = views.size() - 1; x >= 0; x--) {
                        obsViews.add(views.get(x));
                    }

                }

            });

            return mainView;
        }
    }

}
