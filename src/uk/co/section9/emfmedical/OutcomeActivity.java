package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import uk.co.section9.emfmedical.DrawingView;


public class OutcomeActivity extends FragmentActivity {
	
	 public static class OutcomeFragment extends Fragment {
	     
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	        }

	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            View v = inflater.inflate(R.layout.outcome, container, false);
	        /*    DrawingView dv = new DrawingView(getActivity());
	            
	            dv.setMinimumHeight(400);
	            dv.setMinimumWidth(400);
	            
	            LinearLayout ll = (LinearLayout) v.findViewById(R.id.outcome_linear_layout);
	            ll.addView(dv);
	            */
	            return v;
	        }
	    }


}
