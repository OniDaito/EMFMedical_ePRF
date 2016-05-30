package uk.co.section9.emfmedical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Vector;

import uk.co.section9.emfmedical.data.PRF;


// The starting screen with options for PRFDatabase, a clock and some extra details

public class MainActivity extends Activity {

    public final static String EXTRA_MESSAGE = "uk.co.section9.emfmedical";
    public final static String PRF_ID = "uk.co.section9.emfmedical.prf.id";

    @Override
    protected void onResume() {
        super.onResume();
        final MainActivity _ref = this;
        // Any existing PRFs in the local database
        // Clear and refresh this list as we've probably just added one by returning

        Vector<String> prfs = EMFMedicalApp.getAllPRFIDs();
        LinearLayout layout = (LinearLayout) findViewById(R.id.main_prf_list);

        layout.removeAllViewsInLayout();

        for (String s : prfs){
            final String prf_id = s;
            Button prfButton = new Button(this);
            final PRF prf = EMFMedicalApp.getPRFByID(s);
            prfButton.setText(prf.get_incident().get_forname());

            prfButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(_ref, PRFActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, "Existing PRF");
                    intent.putExtra(PRF_ID, prf_id);
                    startActivity(intent);
                }
            });

            layout.addView(prfButton);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MainActivity _ref = this;
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        Context context = getApplicationContext();

        // Button option for a full PRF
        Button button = (Button) findViewById(R.id.main_start_new_prf);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(_ref, PRFActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "New PRF");
                intent.putExtra(PRF_ID, "new");
                startActivity(intent);
            }
        });


        // Button option for a minor wound dressed PRF
        Button button2 = (Button) findViewById(R.id.main_minor_wound_dressed);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(_ref, PRFActivity.class);
                String message = "MinorWoundDressed";
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(PRF_ID, "new");
                startActivity(intent);
            }
        });


        // Any existing PRFs in the local database

        Vector<String> prfs = EMFMedicalApp.getAllPRFIDs();
        LinearLayout layout = (LinearLayout) findViewById(R.id.main_prf_list);
        for (String s : prfs) {
            final String prf_id = s;
            Button prfButton = new Button(this);
            final PRF prf = EMFMedicalApp.getPRFByID(s);
            prfButton.setText(prf.get_incident().get_forname());

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(_ref, PRFActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, "Existing PRF");
                    intent.putExtra(PRF_ID, prf_id);
                    startActivity(intent);
                }
            });

            layout.addView(prfButton);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
	
