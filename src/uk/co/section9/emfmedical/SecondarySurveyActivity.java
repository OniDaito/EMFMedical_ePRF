package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SecondarySurveyActivity extends FragmentActivity {

	
	 public static class SecondarySurveyFragment extends Fragment {
	     
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
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            mainView = inflater.inflate(R.layout.secondary_survey, container, false);
	            mUsed = true;
	            return mainView;
	        }
	        
	        public static String getData() {
	        	String data = new String();
	        	
	        	EditText secondary_survey_head_and_face = (EditText)mainView.findViewById(R.id.secondary_survey_head_and_face);
	        	data += "secondary_survey_head_and_face: " + secondary_survey_head_and_face.getEditableText() + "\n";
	        	
	        	EditText secondary_survey_right_arm_and_hand = (EditText)mainView.findViewById(R.id.secondary_survey_right_arm_and_hand);
	        	data += "secondary_survey_right_arm_and_hand: " + secondary_survey_right_arm_and_hand.getEditableText() + "\n";
	        	
	        	EditText secondary_survey_torso_front_and_back = (EditText)mainView.findViewById(R.id.secondary_survey_torso_front_and_back);
	        	data += "secondary_survey_torso_front_and_back: " + secondary_survey_torso_front_and_back.getEditableText() + "\n";
	        	
	        	EditText secondary_survey_left_arm_and_hand = (EditText)mainView.findViewById(R.id.secondary_survey_left_arm_and_hand);
	        	data += "secondary_survey_left_arm_and_hand: " + secondary_survey_left_arm_and_hand.getEditableText() + "\n";
	        	
	        	EditText secondary_survey_right_leg_and_right_foot = (EditText)mainView.findViewById(R.id.secondary_survey_right_leg_and_right_foot);
	        	data += "secondary_survey_right_leg_and_right_foot: " + secondary_survey_right_leg_and_right_foot.getEditableText() + "\n";
	        	
	        	EditText secondary_survey_left_leg_and_left_foot = (EditText)mainView.findViewById(R.id.secondary_survey_left_leg_and_left_foot);
	        	data += "secondary_survey_left_leg_and_left_foot: " + secondary_survey_left_leg_and_left_foot.getEditableText() + "\n";
	  
	        	return data;
	        }
	        
	    }

	
}
