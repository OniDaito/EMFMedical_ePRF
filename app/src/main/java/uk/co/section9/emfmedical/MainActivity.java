package uk.co.section9.emfmedical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;


// The starting screen with options for PRFDatabase, a clock and some extra details

public class MainActivity extends Activity {

	private PRFDatabase prfDatabase;

	public final static String EXTRA_MESSAGE = "uk.co.section9.emfmedical";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final MainActivity _ref = this;
	    super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

	    setContentView(R.layout.main);

        Button button = (Button)findViewById(R.id.main_start_new_prf);

        Context context = getApplicationContext();
        context.

        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(_ref, PRFActivity.class);
				String message = "None";
				intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
			}
		});


		Button button2 = (Button)findViewById(R.id.main_minor_wound_dressed);

		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(_ref, PRFActivity.class);
				String message = "MinorWoundDressed";
				intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
			}
		});
	}
}
	
