package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class IncidentActivity  extends FragmentActivity {
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      /*  
	        FragmentManager fm = getSupportFragmentManager();

	        // Create the Incident fragment and add it as our sole content.
	        if (fm.findFragmentById(android.R.id.content) == null) {
	        	IncidentFragment incident = new IncidentFragment();
	            fm.beginTransaction().add(android.R.id.content, incident).commit();
	        }*/


	    }

	    @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);

	    }


	    public static class IncidentFragment extends Fragment {
	     
	     
	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	            View v = inflater.inflate(R.layout.incident, container, false);
	          //  View tv = v.findViewById(R.id.text);
	         //   ((TextView)tv).setText("Fragment #" + mNum);
	         //   tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
	            return v;
	        }
	    }


}
