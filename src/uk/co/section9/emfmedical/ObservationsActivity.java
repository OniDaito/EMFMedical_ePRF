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
		 	
		 	public class ObservationActual extends View {

				public ObservationActual(Context context, AttributeSet attrs) {
					super(context, attrs);
					// TODO Auto-generated constructor stub
					
				
				}
		 		
		 	}
		 		
		 	
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            	            
	        }

	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	        	mainView = inflater.inflate(R.layout.observations, container, false);
	        		        	
	        	final LayoutInflater tinflater = inflater;
	        	final ViewGroup tgroup = container;
	        	
	            Button button = (Button) mainView.findViewById(R.id.button_add_observation);
	            final LinearLayout obsList =(LinearLayout) mainView.findViewById(R.id.layout_observations);
	            
        		button.setOnClickListener(new OnClickListener() {
         
        			@Override
        			public void onClick(View arg0) {
        				View obs = tinflater.inflate(R.layout.observation_actual, tgroup, false);
        				obsList.addView(obs);
        				
        				// Reverse Order
						ArrayList<View> views = new ArrayList<View>();
						for(int x = 0; x < obsList.getChildCount(); x++) {
						    views.add(obsList.getChildAt(x));
						}
						obsList.removeAllViews();
						for(int x = views.size() - 1; x >= 0; x--) {
							obsList.addView(views.get(x));
						}
        			
        			}
        		});
	            
	            return mainView;
	        }
	    }

}
