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

// Treatment Administered

public class TreatmentActivity extends FragmentActivity {
	
	
	 public static class TreatmentFragment extends Fragment {
	     
		 	static View mainView;
		 	static boolean mUsed = false;
		 
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	           
	        }
	        
	        public static boolean used() {
		    	return mUsed;
		    }
	        
	        // If minor wound, set a few things for us
	        
	        public static void populateMinorWound() {
	        	CheckBox treatment_wound_dressed = (CheckBox)mainView.findViewById(R.id.treatment_wound_dressed);
	        	treatment_wound_dressed.setChecked(true);
	        	CheckBox treatment_wound_cleansed = (CheckBox)mainView.findViewById(R.id.treatment_wound_cleansed);
	        	treatment_wound_cleansed.setChecked(true);

	        }
	        
	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            mainView = inflater.inflate(R.layout.treatment, container, false);
	            mUsed = true;
	            
	            if (PRFActivity.prePopulate.equalsIgnoreCase("MinorWoundDressed")){
	        		populateMinorWound();
	        	}
	            
	            return mainView;
	        }
	        
	        
	        public static String getData() {
	        	String data = new String();
	        	
	        	EditText treatment_other = (EditText)mainView.findViewById(R.id.treatment_other);
	        	data += "treatment_other: " + treatment_other.getEditableText() + "\n";
	        	
	        	CheckBox treatment_none = (CheckBox)mainView.findViewById(R.id.treatment_none);
	        	if (treatment_none.isChecked()){
	        		data += "treatment_none: yes\n";
	        	}
	        	
	        	CheckBox treatment_airway_opened = (CheckBox)mainView.findViewById(R.id.treatment_airway_opened);
	        	if (treatment_airway_opened.isChecked()){
	        		data += "treatment_airway_opened: yes\n";
	        	}
	        	
	        	CheckBox treatment_airway_suctioned = (CheckBox)mainView.findViewById(R.id.treatment_airway_suctioned);
	        	if (treatment_airway_suctioned.isChecked()){
	        		data += "treatment_airway_suctioned: yes\n";
	        	}
	        	
	        	CheckBox treatment_wound_cleansed = (CheckBox)mainView.findViewById(R.id.treatment_wound_cleansed);
	        	if (treatment_wound_cleansed.isChecked()){
	        		data += "treatment_wound_cleansed: yes\n";
	        	}
	        	
	        	CheckBox treatment_recovery_position = (CheckBox)mainView.findViewById(R.id.treatment_recovery_position);
	        	if (treatment_recovery_position.isChecked()){
	        		data += "treatment_recovery_position: yes\n";
	        	}
	        	
	        	CheckBox treatment_adhesive_dressing = (CheckBox)mainView.findViewById(R.id.treatment_adhesive_dressing);
	        	if (treatment_adhesive_dressing.isChecked()){
	        		data += "treatment_adhesive_dressing: yes\n";
	        	}
	        	
	        	CheckBox treatment_wound_dressed = (CheckBox)mainView.findViewById(R.id.treatment_wound_dressed);
	        	if (treatment_wound_dressed.isChecked()){
	        		data += "treatment_wound_dressed: yes\n";
	        	}
	        	
	        	CheckBox treatment_cervical_spine_control = (CheckBox)mainView.findViewById(R.id.treatment_cervical_spine_control);
	        	if (treatment_cervical_spine_control.isChecked()){
	        		data += "treatment_cervical_spine_control: yes\n";
	        	}
	        	
	        	CheckBox treatment_rice = (CheckBox)mainView.findViewById(R.id.treatment_rice);
	        	if (treatment_rice.isChecked()){
	        		data += "treatment_rice: yes\n";
	        	}
	        	
	        	
	        	CheckBox treatment_sling = (CheckBox)mainView.findViewById(R.id.treatment_sling);
	        	if (treatment_sling.isChecked()){
	        		data += "treatment_sling: yes\n";
	        	}
	        	
	        	CheckBox treatment_splint = (CheckBox)mainView.findViewById(R.id.treatment_splint);
	        	if (treatment_splint.isChecked()){
	        		data += "treatment_splint: yes\n";
	        	}
	        	
	        	CheckBox treatment_aspirin = (CheckBox)mainView.findViewById(R.id.treatment_aspirin);
	        	if (treatment_aspirin.isChecked()){
	        		data += "treatment_aspirin: yes\n";
	        	}
	        	
	        	
	        	CheckBox treatment_ibuprofen = (CheckBox)mainView.findViewById(R.id.treatment_ibuprofen);
	        	if (treatment_ibuprofen.isChecked()){
	        		data += "treatment_ibuprofen: yes\n";
	        	}
	        	
	        	CheckBox treatment_loratadine = (CheckBox)mainView.findViewById(R.id.treatment_loratadine);
	        	if (treatment_loratadine.isChecked()){
	        		data += "treatment_loratadine: yes\n";
	        	}
	        	
	        	CheckBox treatment_paraceatomol = (CheckBox)mainView.findViewById(R.id.treatment_paraceatomol);
	        	if (treatment_paraceatomol.isChecked()){
	        		data += "treatment_paraceatomol: yes\n";
	        	}
	        	
	        	return data;
	        	
	        }
	        
	    }

}
