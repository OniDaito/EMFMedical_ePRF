package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ItemsActivity extends FragmentActivity {
	
	
	  public static class ItemsFragment extends Fragment {
		     
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
	            mainView = inflater.inflate(R.layout.items, container, false);
	            return mainView;
	        }
	        
	        
	        static String getData() {
	        	String data = new String();
	        	EditText items_used = (EditText)mainView.findViewById(R.id.items_used);
	        	data += "items_used: " + items_used.getEditableText() + "\n";
	        	
	        	return data;
	        }
	    }

}
