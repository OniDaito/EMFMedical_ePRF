package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NotesActivity extends FragmentActivity {

	 public static class NotesFragment extends Fragment {
	     		 
		 	static View mainView;
		 
	        @Override
	        public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	        }

	
	        @Override
	        public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                Bundle savedInstanceState) {
	        	mainView = inflater.inflate(R.layout.notes, container, false);
	            return mainView;
	        }
	        
	        
	        public static String getData() {
	        	String data = new String();

	        	EditText notes_notes = (EditText)mainView.findViewById(R.id.notes_notes);
	        	data += "notes_notes: " + notes_notes.getEditableText() + "\n";
	        
	        	return data;
	        }
	    }
	
}
