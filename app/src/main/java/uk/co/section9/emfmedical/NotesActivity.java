package uk.co.section9.emfmedical;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import uk.co.section9.emfmedical.data.Notes;
import uk.co.section9.emfmedical.data.PRF;

// Tab for any further notes

public class NotesActivity extends FragmentActivity {

    public static class NotesFragment extends Fragment {

        static View mainView;
        static boolean mUsed = false;

        @Override
        public void onStop() {
            super.onStop();
            setCurrentPRF();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            setCurrentPRF();
        }

        @Override
        public void onPause() {
            super.onPause();
            setCurrentPRF();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            mainView = inflater.inflate(R.layout.notes, container, false);
            mUsed = true;
            getCurrentPRF();
            return mainView;
        }

        public static boolean used() {
            return mUsed;
        }
        
        public static void setCurrentPRF() {
            PRF prf = EMFMedicalApp.getCurrentPRF();
            Notes nn = prf.get_notes();
            EditText notes_notes = (EditText)mainView.findViewById(R.id.notes_notes);
            nn.set_notes(""+notes_notes);
        }

        public static void getCurrentPRF() {
            PRF prf = EMFMedicalApp.getCurrentPRF();
            Notes nn = prf.get_notes();
            EditText notes_notes = (EditText)mainView.findViewById(R.id.notes_notes);
            notes_notes.setText(nn.get_notes());
        }
    }
	
}
