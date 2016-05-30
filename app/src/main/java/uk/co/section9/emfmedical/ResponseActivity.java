package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

// Ambulance Response form

public class ResponseActivity extends FragmentActivity {
	
	
	 public static class ResponseFragment extends Fragment {
	     
		 	static View mainView;
		 	static boolean mUsed = false;
		 
		 	@Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	        }

	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	        	mainView = inflater.inflate(R.layout.response, container, false);
	        	mUsed = true;
	            return mainView;
	        }
	        
	        public static boolean used() {
		    	return mUsed;
		    }
	        
	        static String getData() {
	        	String data = new String();
	        	
	        	EditText response_callsign = (EditText)mainView.findViewById(R.id.response_callsign);
	        	data += "response_callsign: " + response_callsign.getEditableText() + "\n";
	        	
	        	TimePicker response_time_received = (TimePicker)mainView.findViewById(R.id.response_time_received);
	        	data += "response_time_received: " + response_time_received.getCurrentHour() + ":" + response_time_received.getCurrentMinute() + "\n";

	        	TimePicker response_time_arrived = (TimePicker)mainView.findViewById(R.id.response_time_arrived);
	        	data += "response_time_arrived: " + response_time_arrived.getCurrentHour() + ":" + response_time_arrived.getCurrentMinute() + "\n";
	        	
	        	TimePicker response_time_left = (TimePicker)mainView.findViewById(R.id.response_time_left);
	        	data += "response_time_left: " + response_time_left.getCurrentHour() + ":" + response_time_left.getCurrentMinute() + "\n";
	        	
	        	TimePicker response_time_arrived_at_receiving_centre = (TimePicker)mainView.findViewById(R.id.response_time_arrived_at_receiving_centre);
	        	data += "response_time_arrived_at_receiving_centre: " + response_time_arrived_at_receiving_centre.getCurrentHour() + ":" + response_time_arrived_at_receiving_centre.getCurrentMinute() + "\n";
	        	
	        	EditText response_name_of_receiving_centre = (EditText)mainView.findViewById(R.id.response_name_of_receiving_centre);
	        	data += "response_name_of_receiving_centre: " + response_name_of_receiving_centre.getEditableText() + "\n";
	        	
	        	return data;
	        }
	    }


}
