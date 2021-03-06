package com.example.nj_gset_guide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DescriptionsActivity extends Activity {

	private static Location location;
	public static final String KEY = "com.example.nj_gset_guide.DescriptionsActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_descriptions);
		
		location = new Location(getIntent().getStringExtra(MainActivity.KEY).replace(" ", "_"), this);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.descriptions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private TextView title;
		private TextView desc;
		private TextView addr;
		private Button mapBtn;
		
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_descriptions,
					container, false);
			title = (TextView) rootView.findViewById(R.id.tvTitle);
			desc = (TextView) rootView.findViewById(R.id.tvDescription);
			addr = (TextView) rootView.findViewById(R.id.tvAddress);
			mapBtn = (Button) rootView.findViewById(R.id.bMap);
			
			title.setText(location.getName());
			desc.setText(location.getDescription());
			addr.setText(location.getAddress());
			mapBtn.setText("Google Maps");
			
			mapBtn.setOnClickListener(new OnClickListener()	{
				@Override
				public void onClick(View v) { 
					Intent navIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps/?daddr=" + location.getAddress().replace(" ", "+")));//"geo:0,0?q="+location.getAddress()));
					if (isAppInstalled("com.google.android.apps.maps")) {
					    navIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
					}
					startActivity(navIntent);
				}
			});
			
			return rootView;
		}
		
		// helper function to check if Maps is installed - http://stackoverflow.com/questions/6560345/suppressing-google-maps-intent-selection-dialog/7116840#7116840
		private boolean isAppInstalled(String uri) {
		    PackageManager pm = getApplicationContext().getPackageManager();
		    boolean app_installed = false;
		    try {
		        pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
		        app_installed = true;
		    } catch (PackageManager.NameNotFoundException e) {
		        app_installed = false;
		    }
		    return app_installed;
		}
	}

}
