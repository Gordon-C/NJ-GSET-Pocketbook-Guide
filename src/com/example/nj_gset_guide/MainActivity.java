package com.example.nj_gset_guide;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	private static ListView lv;
	private static String[] buildings;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
            Log.d("DEBUG", "onviewcreated");
			buildings = getResources().getStringArray(R.array.locations_array);
            lv = (ListView) getView().findViewById(R.id.lv_places);
            lv.setAdapter(new ListAdapter(getActivity(), buildings));
		}
    }
    
    public static class ListAdapter extends ArrayAdapter<String>	{
    	
    	String [] values;
    	TextView buildingName;
    	ImageView buildingImg;
    	
    	public ListAdapter(Context context, String[] values) {
    		super(context, R.layout.rowlayout, values);
    	    this.values = values;
    	}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			buildingName.setText(values[position]);//TODO nullpointerexception on values
			//TODO set building image
			return super.getView(position, convertView, parent);
		}
    }

}
