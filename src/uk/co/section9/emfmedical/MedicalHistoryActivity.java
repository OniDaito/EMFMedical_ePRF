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

// Medical History tab

public class MedicalHistoryActivity extends FragmentActivity {

	  public static class MedicalHistoryFragment extends Fragment {
		     
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
	            return mainView;
	        }
	        
	        public static boolean used() {
		    	return mUsed;
		    }
	        
	        
	        public static String getData() {
	        	String data = new String();
	        	
	        	CheckBox medical_history_high_blood_pressure = (CheckBox)mainView.findViewById(R.id.medical_history_high_blood_pressure);
	        	if (medical_history_high_blood_pressure.isChecked()){
	        		data += "medical_history_high_blood_pressure: yes\n";
	        	}
	        	
	        	CheckBox medical_history_stroke = (CheckBox)mainView.findViewById(R.id.medical_history_stroke);
	        	if (medical_history_stroke.isChecked()){
	        		data += "medical_history_stroke: yes\n";
	        	}
	        		
	        	CheckBox medical_history_seizures = (CheckBox)mainView.findViewById(R.id.medical_history_seizures);
	        	if (medical_history_seizures.isChecked()){
	        		data += "medical_history_seizures: yes\n";
	        	}
	        
	        	CheckBox medical_history_diabetes = (CheckBox)mainView.findViewById(R.id.medical_history_diabetes);
	        	if (medical_history_diabetes.isChecked()){
	        		data += "medical_history_diabetes: yes\n";
	        	}
	        	
	        	
	        	CheckBox medical_history_cardiac = (CheckBox)mainView.findViewById(R.id.medical_history_cardiac);
	        	if (medical_history_cardiac.isChecked()){
	        		data += "medical_history_cardiac: yes\n";
	        	}
	        	
	        	
	        	CheckBox medical_history_asthma = (CheckBox)mainView.findViewById(R.id.medical_history_asthma);
	        	if (medical_history_asthma.isChecked()){
	        		data += "medical_history_asthma: yes\n";
	        	}
	        	
	        	CheckBox medical_history_respiratory = (CheckBox)mainView.findViewById(R.id.medical_history_respiratory);
	        	if (medical_history_respiratory.isChecked()){
	        		data += "medical_history_respiratory: yes\n";
	        	}
	        	
	        	EditText medical_history_other = (EditText)mainView.findViewById(R.id.medical_history_other);
	        	data += "medical_history_other: " + medical_history_other.getEditableText() + "\n";
	        	
	        	EditText medical_history_allergies = (EditText)mainView.findViewById(R.id.medical_history_allergies);
	        	data += "medical_history_allergies: " + medical_history_allergies.getEditableText() + "\n";
	        	
	        	EditText medical_history_medication = (EditText)mainView.findViewById(R.id.medical_history_medication);
	        	data += "medical_history_medication: " + medical_history_medication.getEditableText() + "\n";
	        	
	        	return data;
	        }
	    }
	
}
