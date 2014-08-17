package uk.co.section9.emfmedical;

import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

// Observations main tab

public class ObservationsActivity extends FragmentActivity {
		
	 public static class ObservationsFragment extends Fragment {
	     
		 	View mainView;
		 	LinearLayout obsLinearLayout;
		 	static ArrayList<View> obsViews;
		 	static boolean mUsed = false;
		 	
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            obsViews = new ArrayList<View>();
	            mUsed = true;
	        }
	        
	        @Override
	        public void onPause() {
	        	super.onPause();
	        	for(View v: obsViews){
	            	obsLinearLayout.removeView(v);
	            }
	        }
	       
		    @Override
		    public void onDestroy() {
		    	super.onDestroy();
		    	for(View v: obsViews){
	            	obsLinearLayout.removeView(v);
	            }
	            obsViews.clear();
		    }
		    
	        public static boolean used() {
		    	return mUsed;
		    }
		    
		    public static String getData(){
		    	String data = new String();
		    	int idx = 0;
		    	for(View v: obsViews){
		    		
		    		TimePicker observation_time = (TimePicker)v.findViewById(R.id.observation_time);
		        	data += "observation_time_" + idx + " : " + observation_time.getCurrentHour() + ":" + observation_time.getCurrentMinute() + "\n";
		    		
		        	Spinner observation_response = (Spinner)v.findViewById(R.id.observation_response);
		        	if ( observation_response.getSelectedItem() != null)
		        		data += "observation_response_" + idx + " : " +observation_response.getSelectedItem() + "\n";
		    		
		        	EditText observation_respiratory_rate = (EditText)v.findViewById(R.id.observation_respiratory_rate);
		        	data += "observation_respiratory_rate_" + idx + " : " + observation_respiratory_rate.getEditableText() + "\n";
		    		
		        	EditText observation_pule_rate = (EditText)v.findViewById(R.id.observation_pule_rate);
		        	data += "observation_pule_rate_" + idx + " : " + observation_pule_rate.getEditableText() + "\n";
		        	
		        	RadioButton observation_pain_score_0 = (RadioButton)v.findViewById(R.id.observation_pain_score_0);
		        	RadioButton observation_pain_score_1 = (RadioButton)v.findViewById(R.id.observation_pain_score_1);
		        	RadioButton observation_pain_score_2 = (RadioButton)v.findViewById(R.id.observation_pain_score_2);
		        	RadioButton observation_pain_score_3 = (RadioButton)v.findViewById(R.id.observation_pain_score_3);
		        	
		        	if (observation_pain_score_0.isChecked()){
		        		data += "observation_pain_score_" + idx +" : 0\n";
		        	}
		        	if (observation_pain_score_1.isChecked()){
		        		data += "observation_pain_score_" + idx +" : 1\n";
		        	}
		        	if (observation_pain_score_2.isChecked()){
		        		data += "observation_pain_score_" + idx +" : 2\n";
		        	}
		        	if (observation_pain_score_3.isChecked()){
		        		data += "observation_pain_score_" + idx +" : 3\n";
		        	}
		        	
		        	EditText observation_o2_saturation = (EditText)v.findViewById(R.id.observation_o2_saturation);
		        	data += "observation_o2_saturation_" + idx + " : " + observation_o2_saturation.getEditableText() + "\n";
		    		
		        	EditText observation_oxygen = (EditText)v.findViewById(R.id.observation_oxygen);
		        	data += "observation_oxygen_" + idx + " : " + observation_oxygen.getEditableText() + "\n";
		    		
		        	EditText observation_entonox = (EditText)v.findViewById(R.id.observation_entonox);
		        	data += "observation_entonox_" + idx + " : " + observation_entonox.getEditableText() + "\n";
		        	
		        	EditText observation_blood_pressure_systolic = (EditText)v.findViewById(R.id.observation_blood_pressure_systolic);
		        	data += "observation_blood_pressure_systolic_" + idx + " : " + observation_blood_pressure_systolic.getEditableText() + "\n";
		        	
		        	EditText observation_blood_pressure_dystolic = (EditText)v.findViewById(R.id.observation_blood_pressure_dystolic);
		        	data += "observation_blood_pressure_dystolic_" + idx + " : " + observation_blood_pressure_dystolic.getEditableText() + "\n";
		        	
		        	
		        	EditText observation_temperature = (EditText)v.findViewById(R.id.observation_temperature);
		        	data += "observation_temperature_" + idx + " : " + observation_temperature.getEditableText() + "\n";
		        	
		        	EditText observation_blood_sugar = (EditText)v.findViewById(R.id.observation_blood_sugar);
		        	data += "observation_blood_sugar_" + idx + " : " + observation_blood_sugar.getEditableText() + "\n";
		        	
		        	CheckBox observation_pupils_reactive_left_yes = (CheckBox)v.findViewById(R.id.observation_pupils_reactive_left_yes);
		        	
		        	if (observation_pupils_reactive_left_yes.isChecked()){
		        		data += "observation_pupils_reactive_left" + idx +" : yes\n";
		        	} 
		        	
		        	CheckBox observation_pupils_reactive_left_no = (CheckBox)v.findViewById(R.id.observation_pupils_reactive_left_no);
		        	
		        	if (observation_pupils_reactive_left_no.isChecked()){
		        		data += "observation_pupils_reactive_left_no" + idx +" : no\n";
		        	} 
		        	
		        	
		        	CheckBox observation_pupils_reactive_right_yes = (CheckBox)v.findViewById(R.id.observation_pupils_reactive_right_yes);
		        	
		        	if (observation_pupils_reactive_right_yes.isChecked()){
		        		data += "observation_pupils_reactive_right" + idx +" : yes\n";
		        	} 
		        	
		        	CheckBox observation_pupils_reactive_right_no = (CheckBox)v.findViewById(R.id.observation_pupils_reactive_right_no);
		        	
		        	if (observation_pupils_reactive_right_no.isChecked()){
		        		data += "observation_pupils_reactive_right_no" + idx +" : no\n";
		        	} 
		        	
		        	Spinner observation_pupil_size_left = (Spinner)v.findViewById(R.id.observation_pupil_size_left);
		        	if ( observation_pupil_size_left.getSelectedItem() != null)
		        		data += "observation_pupil_size_left_" + idx + " : " +observation_pupil_size_left.getSelectedItem() + "\n";
		        	
		        	
		        	Spinner observation_pupil_size_right = (Spinner)v.findViewById(R.id.observation_pupil_size_right);
		        	if ( observation_pupil_size_right.getSelectedItem() != null)
		        		data += "observation_pupil_size_right_" + idx + " : " +observation_pupil_size_right.getSelectedItem() + "\n";
		        	
		    		idx++;
		    	}
		    	
		    	return data;
		    }
	        
		    
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	        	mainView = inflater.inflate(R.layout.observations, container, false);
	        		        	
	        	final LayoutInflater tinflater = inflater;
	        	final ViewGroup tgroup = container;
	        	
	            Button button = (Button) mainView.findViewById(R.id.button_add_observation);
	            obsLinearLayout = (LinearLayout) mainView.findViewById(R.id.layout_observations);
	            
	            // run through the master obsViews and re-create them   
	            if (obsViews.size() > 0){
	            	for(View v: obsViews){
		            	obsLinearLayout.addView(v);
		            }
	            }
	           
	            
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
