package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import uk.co.section9.emfmedical.data.PRF;
import uk.co.section9.emfmedical.data.Primary;


// Primary Survey Tab

public class PrimarySurveyActivity extends FragmentActivity {

    public static class PrimarySurveyFragment extends Fragment {

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
        public void onStop() {
            setCurrentPRF();
            super.onStop();
        }

        @Override
        public void onDestroy() {
            setCurrentPRF();
            super.onDestroy();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
            mainView = inflater.inflate(R.layout.primary_survey, container, false);
            mUsed = true;
            getCurrentPRF();
            return mainView;
        }


        public static void setCurrentPRF() {

            PRF prf = EMFMedicalApp.getCurrentPRF();
            Primary pp = prf.get_primary();

            EditText primary_survey_problem = (EditText)mainView.findViewById(R.id.primary_survey_problem);
            pp.set_presenting(""+primary_survey_problem.getEditableText());

            RadioButton primary_survey_capacity_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_yes);
            RadioButton primary_survey_capacity_no = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_no);

            if (primary_survey_capacity_yes.isChecked()){
                pp.set_capacity('y');
            }
            if (primary_survey_capacity_no.isChecked()){
                pp.set_capacity('n');
            }

            RadioButton primary_survey_consent_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_yes);
            RadioButton primary_survey_consent_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_no);

            if (primary_survey_consent_yes.isChecked()){
                pp.set_consent('y');
            }
            if (primary_survey_consent_no.isChecked()){
                pp.set_consent('n');
            }

            RadioButton primary_survey_response_alert = (RadioButton)mainView.findViewById(R.id.primary_survey_response_alert);
            RadioButton primary_survey_response_voice = (RadioButton)mainView.findViewById(R.id.primary_survey_response_voice);
            RadioButton primary_survey_response_pain = (RadioButton)mainView.findViewById(R.id.primary_survey_response_pain);
            RadioButton primary_survey_response_none = (RadioButton)mainView.findViewById(R.id.primary_survey_response_none);

            if (primary_survey_response_alert.isChecked()){
                pp.set_response('a');
            }
            if (primary_survey_response_voice.isChecked()){
                pp.set_response('v');
            }
            if (primary_survey_response_pain.isChecked()){
                pp.set_response('p');
            }
            if (primary_survey_response_none.isChecked()){
                pp.set_response('u');
            }


            RadioButton primary_survey_airway_clear = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_clear);
            RadioButton primary_survey_airway_obstructed = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_obstructed);

            if (primary_survey_airway_clear.isChecked()){
                pp.set_airway('c');
            }
            if (primary_survey_airway_obstructed.isChecked()){
                pp.set_airway('o');
            }

            RadioButton primary_survey_breathing_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_normal);
            RadioButton primary_survey_breathing_shallow = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_shallow);
            RadioButton primary_survey_breathing_agonal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_agonal);
            RadioButton primary_survey_breathing_absent = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_absent);
            RadioButton primary_survey_breathing_rapid = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_rapid);

            if (primary_survey_breathing_normal.isChecked()){
                pp.set_breathing('n');
            }
            if (primary_survey_breathing_rapid.isChecked()){
                pp.set_breathing('r');
            }
            if (primary_survey_breathing_shallow.isChecked()){
                pp.set_breathing('s');
            }
            if (primary_survey_breathing_agonal.isChecked()){
                pp.set_breathing('a');
            }
            if (primary_survey_breathing_absent.isChecked()){
                pp.set_breathing('b');
            }

            RadioButton primary_survey_circulation_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_normal);
            RadioButton primary_survey_circulation_pale = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_pale);
            RadioButton primary_survey_circulation_flushed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_flushed);
            RadioButton primary_survey_circulation_cyanosed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_cyanosed);

            if (primary_survey_circulation_normal.isChecked()){
                pp.set_circulation('n');
            }
            if (primary_survey_circulation_pale.isChecked()){
                pp.set_circulation('p');
            }
            if (primary_survey_circulation_flushed.isChecked()){
                pp.set_circulation('f');
            }
            if (primary_survey_circulation_cyanosed.isChecked()){
                pp.set_circulation('c');
            }

            RadioButton primary_survey_external_bleeding_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_yes);
            RadioButton primary_survey_external_bleeding_no = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_no);

            if (primary_survey_external_bleeding_yes.isChecked()){
                pp.set_external('y');
            }
            if (primary_survey_external_bleeding_no.isChecked()){
                pp.set_external('n');
            }

            RadioButton primary_survey_consiousness_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_yes);
            RadioButton primary_survey_consiousness_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_no);

            if (primary_survey_consiousness_yes.isChecked()){
                pp.set_consciousness('y');
            }
            if (primary_survey_consiousness_no.isChecked()){
                pp.set_consciousness('n');
            }

            RadioButton primary_survey_alcoholdrugs_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_alcoholdrugs_yes);
            RadioButton primary_survey_alcoholdrugs_no = (RadioButton)mainView.findViewById(R.id.primary_survey_alcoholdrugs_no);

            if (primary_survey_alcoholdrugs_yes.isChecked()){
                pp.set_alcoholdrugs('y');
            }
            if (primary_survey_alcoholdrugs_no.isChecked()){
                pp.set_alcoholdrugs('n');
            }
        }

        public static void getCurrentPRF() {

            PRF prf = EMFMedicalApp.getCurrentPRF();
            Primary pp = prf.get_primary();

            EditText primary_survey_problem = (EditText)mainView.findViewById(R.id.primary_survey_problem);
            primary_survey_problem.setText(pp.get_presenting());

            RadioButton primary_survey_capacity_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_yes);
            RadioButton primary_survey_capacity_no = (RadioButton)mainView.findViewById(R.id.primary_survey_capacity_no);

            primary_survey_capacity_yes.setChecked(false);
            primary_survey_capacity_no.setChecked(false);

            char datachar = pp.get_capacity();
            if (datachar == 'y'){
                primary_survey_capacity_yes.setChecked(true);
            } else if (datachar =='n'){
                primary_survey_capacity_no.setChecked(true);
            }

            RadioButton primary_survey_consent_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_yes);
            RadioButton primary_survey_consent_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consent_no);

            primary_survey_consent_yes.setChecked(false);
            primary_survey_consent_no.setChecked(false);

            datachar = pp.get_consent();
            if (datachar == 'y'){
                primary_survey_consent_yes.setChecked(true);
            } else if (datachar =='n'){
                primary_survey_consent_no.setChecked(true);
            }

            RadioButton primary_survey_response_alert = (RadioButton)mainView.findViewById(R.id.primary_survey_response_alert);
            RadioButton primary_survey_response_voice = (RadioButton)mainView.findViewById(R.id.primary_survey_response_voice);
            RadioButton primary_survey_response_pain = (RadioButton)mainView.findViewById(R.id.primary_survey_response_pain);
            RadioButton primary_survey_response_none = (RadioButton)mainView.findViewById(R.id.primary_survey_response_none);

            primary_survey_response_alert.setChecked(false);
            primary_survey_response_voice.setChecked(false);
            primary_survey_response_pain.setChecked(false);
            primary_survey_response_none.setChecked(false);

            datachar = pp.get_response();
            switch(datachar) {
                case 'a': {
                    primary_survey_response_alert.setChecked(true);
                    break;
                }
                case 'v': {
                    primary_survey_response_voice.setChecked(true);
                    break;
                }
                case 'p': {
                    primary_survey_response_pain.setChecked(true);
                    break;
                }
                case 'u':{
                    primary_survey_response_none.setChecked(true);
                    break;
                }
            }


            RadioButton primary_survey_airway_clear = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_clear);
            RadioButton primary_survey_airway_obstructed = (RadioButton)mainView.findViewById(R.id.primary_survey_airway_obstructed);

            primary_survey_airway_clear.setChecked(false);
            primary_survey_airway_obstructed.setChecked(false);

            datachar = pp.get_airway();
            if (datachar == 'c'){
                primary_survey_airway_clear.setChecked(true);
            } else if (datachar == 'o') {
                primary_survey_airway_obstructed.setChecked(true);
            }


            RadioButton primary_survey_breathing_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_normal);
            RadioButton primary_survey_breathing_shallow = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_shallow);
            RadioButton primary_survey_breathing_agonal = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_agonal);
            RadioButton primary_survey_breathing_absent = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_absent);
            RadioButton primary_survey_breathing_rapid = (RadioButton)mainView.findViewById(R.id.primary_survey_breathing_rapid);

            primary_survey_breathing_normal.setChecked(false);
            primary_survey_breathing_shallow.setChecked(false);
            primary_survey_breathing_agonal.setChecked(false);
            primary_survey_breathing_absent.setChecked(false);
            primary_survey_breathing_rapid.setChecked(false);

            datachar = pp.get_breathing();
            switch (datachar){
                case 'n': {
                    primary_survey_breathing_normal.setChecked(true);
                    break;
                }
                case 'r': {
                    primary_survey_breathing_rapid.setChecked(true);
                    break;
                }
                case 's': {
                    primary_survey_breathing_shallow.setChecked(true);
                    break;
                }
                case 'a': {
                    primary_survey_breathing_agonal.setChecked(true);
                    break;
                }
                case 'b': {
                    primary_survey_breathing_absent.setChecked(true);
                    break;
                }
            }


            RadioButton primary_survey_circulation_normal = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_normal);
            RadioButton primary_survey_circulation_pale = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_pale);
            RadioButton primary_survey_circulation_flushed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_flushed);
            RadioButton primary_survey_circulation_cyanosed = (RadioButton)mainView.findViewById(R.id.primary_survey_circulation_cyanosed);

            datachar = pp.get_circulation();

            primary_survey_circulation_normal.setChecked(false);
            primary_survey_circulation_pale.setChecked(false);
            primary_survey_circulation_flushed.setChecked(false);
            primary_survey_circulation_cyanosed.setChecked(false);

            switch(datachar){
                case 'n' : {
                    primary_survey_circulation_normal.setChecked(true);
                    break;
                }
                case 'p' : {
                    primary_survey_circulation_pale.setChecked(true);
                    break;
                }
                case 'f' : {
                    primary_survey_circulation_flushed.setChecked(true);
                    break;
                }
                case 'c' : {
                    primary_survey_circulation_cyanosed.setChecked(true);
                    break;
                }

            }

            RadioButton primary_survey_external_bleeding_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_yes);
            RadioButton primary_survey_external_bleeding_no = (RadioButton)mainView.findViewById(R.id.primary_survey_external_bleeding_no);

            primary_survey_external_bleeding_yes.setChecked(false);
            primary_survey_external_bleeding_no.setChecked(false);

            datachar = pp.get_external();

            if (datachar == 'y') {
                primary_survey_external_bleeding_yes.setChecked(true);
            } else if (datachar == 'n'){
                primary_survey_external_bleeding_no.setChecked(true);
            }


            RadioButton primary_survey_consiousness_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_yes);
            RadioButton primary_survey_consiousness_no = (RadioButton)mainView.findViewById(R.id.primary_survey_consiousness_no);

            primary_survey_consiousness_yes.setChecked(false);
            primary_survey_consiousness_no.setChecked(false);

            datachar = pp.get_consciousness();
            if (datachar == 'y'){
                primary_survey_consiousness_yes.setChecked(true);
            } else if (datachar == 'n') {
                primary_survey_consiousness_no.setChecked(true);
            }



            RadioButton primary_survey_alcoholdrugs_yes = (RadioButton)mainView.findViewById(R.id.primary_survey_alcoholdrugs_yes);
            RadioButton primary_survey_alcoholdrugs_no = (RadioButton)mainView.findViewById(R.id.primary_survey_alcoholdrugs_no);

            primary_survey_alcoholdrugs_yes.setChecked(false);
            primary_survey_alcoholdrugs_no.setChecked(false);

            datachar = pp.get_alcoholdrugs();

            if (datachar == 'y'){
                primary_survey_alcoholdrugs_yes.setChecked(true);
            } else if (datachar == 'n') {
                primary_survey_alcoholdrugs_no.setChecked(true);
            }
        }
    }

}
