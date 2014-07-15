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
import android.widget.LinearLayout;

public class ObservationsActivity extends FragmentActivity {
	
		
	 public static class ObservationsFragment extends Fragment {
	     
		 	View mainView;
		 	LinearLayout obsLinearLayout;
		 	ArrayList<View> obsViews;
		 	
		 	
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            obsViews = new ArrayList<View>();
	            	            
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
