package uk.co.section9.emfmedical;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import uk.co.section9.emfmedical.data.Discharge;
import uk.co.section9.emfmedical.data.PRF;


// If the patient refuses, they should use this tab

public class RefusedActivity extends FragmentActivity {

    public static class RefusedFragment extends PRFFragment {

        static View mainView;

        static boolean mUsed = false;

        public static boolean used() {
    return mUsed;
    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        public static DrawingView getSignatureView() {
            return (DrawingView) mainView.findViewById(R.id.refused_signature);
        }

        @Override
        public void onStop() {
            setCurrentPRF();
            super.onStop();
        }

        @Override
        public void onDestroy() {
            setCurrentPRF();
            super.onDestroy();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
            mainView = inflater.inflate(R.layout.refused, container, false);
            mUsed = true;
            Button button = (Button) mainView.findViewById(R.id.refused_complete_form);

            button.setOnClickListener(new OnClickListener() {

            @Override
                public void onClick(View arg0) {
                    final PRFActivity ma = (PRFActivity) getActivity();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                    // set title
                    alertDialogBuilder.setTitle("Complete Form");

                    // set dialog message
                    alertDialogBuilder
                        .setMessage("This will commit the Patient report form and encrypt it.")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity, and also set the refused flag
                                // Only set if this is signed and accepted from this page
                                PRF prf = EMFMedicalApp.getCurrentPRF();
                                Discharge ds = prf.get_discharge();
                                ds.set_refused('y');
                                ma.completeForm();
                            }
                          })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                    }

                });

            return mainView;
        }

        public static void setCurrentPRF() {
                PRF prf = EMFMedicalApp.getCurrentPRF();
                Discharge ds = prf.get_discharge();
                ds.set_refused('x');
        }
    }
}
