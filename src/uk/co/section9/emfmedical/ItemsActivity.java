package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsActivity extends FragmentActivity {
	
	
	  public static class ItemsFragment extends Fragment {
		     
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	        }

	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	            View v = inflater.inflate(R.layout.items, container, false);
	          //  View tv = v.findViewById(R.id.text);
	         //   ((TextView)tv).setText("Fragment #" + mNum);
	         //   tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
	            return v;
	        }
	    }

}
