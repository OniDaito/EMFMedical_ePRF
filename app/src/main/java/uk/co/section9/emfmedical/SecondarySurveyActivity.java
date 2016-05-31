package uk.co.section9.emfmedical;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import uk.co.section9.emfmedical.data.PRF;
import uk.co.section9.emfmedical.data.Secondary;

// Secondary Survey tab - Perhaps not the best name

public class SecondarySurveyActivity extends FragmentActivity {

    public static class SecondarySurveyFragment extends Fragment {
        static View mainView;
        static boolean mUsed = false;
		  
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

	
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            mainView  = inflater.inflate(R.layout.medical_history, container, false);
            mUsed = true;
            getCurrentPRF();
            return mainView;
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

        public static void setCurrentPRF() {
            PRF prf = EMFMedicalApp.getCurrentPRF();
            Secondary ss = prf.get_secondary();

            // TODO - We need to replace check boxes because we cant tell between no and 'not checked'

            CheckBox medical_history_high_blood_pressure = (CheckBox)mainView.findViewById(R.id.medical_history_high_blood_pressure);
            if (medical_history_high_blood_pressure.isChecked()){
                ss.set_high_blood_pressure('y');
            }

            CheckBox medical_history_stroke = (CheckBox)mainView.findViewById(R.id.medical_history_stroke);
            if (medical_history_stroke.isChecked()){
                ss.set_stroke('y');
            }

            CheckBox medical_history_seizures = (CheckBox)mainView.findViewById(R.id.medical_history_seizures);
            if (medical_history_seizures.isChecked()){
                ss.set_seizures('y');
            }

            CheckBox medical_history_diabetes = (CheckBox)mainView.findViewById(R.id.medical_history_diabetes);
            if (medical_history_diabetes.isChecked()){
                ss.set_diabetes('y');
            }

            CheckBox medical_history_cardiac = (CheckBox)mainView.findViewById(R.id.medical_history_cardiac);
            if (medical_history_cardiac.isChecked()){
                ss.set_cardiac('y');
            }

            RadioButton medical_history_fast_yes = (RadioButton)mainView.findViewById(R.id.medical_history_fast_yes);
            RadioButton medical_history_fast_no = (RadioButton)mainView.findViewById(R.id.medical_history_fast_no);

            if (medical_history_fast_yes.isChecked()){
                ss.set_fast('y');
            }
            if (medical_history_fast_no.isChecked()){
                ss.set_fast('n');
            }

            CheckBox medical_history_asthma = (CheckBox)mainView.findViewById(R.id.medical_history_asthma);
            if (medical_history_asthma.isChecked()){
                ss.set_asthma('y');
            }

            CheckBox medical_history_respiratory = (CheckBox)mainView.findViewById(R.id.medical_history_respiratory);
            if (medical_history_respiratory.isChecked()){
                ss.set_respiratory('y');
            }

            EditText medical_history_other = (EditText)mainView.findViewById(R.id.medical_history_other);
            ss.set_medical_history(""+medical_history_other.getEditableText());

            EditText medical_history_presenting = (EditText)mainView.findViewById(R.id.medical_history_presenting_complaint);
            ss.set_history_presenting_complaint(""+medical_history_presenting.getEditableText());

            EditText medical_history_allergies = (EditText)mainView.findViewById(R.id.medical_history_allergies);
            ss.set_allergies(""+medical_history_allergies.getEditableText());

            EditText medical_history_medication = (EditText)mainView.findViewById(R.id.medical_history_medication);
            ss.set_medications(""+medical_history_medication.getEditableText());
        }

        public static void getCurrentPRF() {
            PRF prf = EMFMedicalApp.getCurrentPRF();
            Secondary ss = prf.get_secondary();

            CheckBox medical_history_high_blood_pressure = (CheckBox)mainView.findViewById(R.id.medical_history_high_blood_pressure);
            medical_history_high_blood_pressure.setChecked(false);

            char datachar = ss.get_high_blood_pressure();
            if (datachar == 'y') { medical_history_high_blood_pressure.setChecked(true); }

            CheckBox medical_history_stroke = (CheckBox)mainView.findViewById(R.id.medical_history_stroke);
            medical_history_stroke.setChecked(false);
            datachar = ss.get_stroke();
            if (datachar == 'y') { medical_history_stroke.setChecked(true); }

            CheckBox medical_history_seizures = (CheckBox)mainView.findViewById(R.id.medical_history_seizures);
            medical_history_seizures.setChecked(false);
            datachar = ss.get_seizures();
            if (datachar == 'y') { medical_history_seizures.setChecked(true); }

            CheckBox medical_history_diabetes = (CheckBox)mainView.findViewById(R.id.medical_history_diabetes);
            medical_history_diabetes.setChecked(false);
            datachar = ss.get_diabetes();
            if (datachar == 'y') { medical_history_diabetes.setChecked(true); }

            CheckBox medical_history_cardiac = (CheckBox)mainView.findViewById(R.id.medical_history_cardiac);
            medical_history_cardiac.setChecked(false);
            datachar = ss.get_cardiac();
            if (datachar == 'y') { medical_history_cardiac.setChecked(true); }

            RadioButton medical_history_fast_yes = (RadioButton)mainView.findViewById(R.id.medical_history_fast_yes);
            RadioButton medical_history_fast_no = (RadioButton)mainView.findViewById(R.id.medical_history_fast_no);

            medical_history_fast_yes.setChecked(false);
            medical_history_fast_no.setChecked(false);

            datachar = ss.get_fast();

            if (datachar == 'y') { medical_history_fast_yes.setChecked(true);}
            else if (datachar == 'n') {medical_history_fast_no.setChecked(true);}

            CheckBox medical_history_asthma = (CheckBox)mainView.findViewById(R.id.medical_history_asthma);
            medical_history_asthma.setChecked(false);

            datachar = ss.get_asthma();
            if (datachar == 'y') { medical_history_asthma.setChecked(true); }

            CheckBox medical_history_respiratory = (CheckBox)mainView.findViewById(R.id.medical_history_respiratory);
            datachar = ss.get_respiratory();
            if (datachar == 'y') {medical_history_respiratory.setChecked(true);}


            EditText medical_history_other = (EditText)mainView.findViewById(R.id.medical_history_other);
            medical_history_other.setText(ss.get_medical_history());

            EditText medical_history_presenting = (EditText)mainView.findViewById(R.id.medical_history_presenting_complaint);
            medical_history_presenting.setText(ss.get_history_presenting_complaint());

            EditText medical_history_allergies = (EditText)mainView.findViewById(R.id.medical_history_allergies);
            medical_history_allergies.setText(ss.get_allergies());

            EditText medical_history_medication = (EditText)mainView.findViewById(R.id.medical_history_medication);
            medical_history_medication.setText(ss.get_medications());
        }
    }
	
}
