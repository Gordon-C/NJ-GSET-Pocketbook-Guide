package com.example.nj_gset_guide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static ListView lv;
	private static String[] buildings;
	private static Location[] locations;
	public static final String KEY = "com.example.nj_gset_guide.MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

		buildings = getResources().getStringArray(R.array.locations_array);
        locations = new Location[buildings.length];
	    for(int i = 0; i < buildings.length; i++)
	    	locations[i] = new Location(buildings[i], this);
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
            lv = (ListView) getView().findViewById(R.id.lv_places);
            Log.d("DEBUG", "listview created");
            lv.setAdapter(new ListAdapter(getActivity(), buildings));
            lv.setOnItemClickListener(new OnItemClickListener()	{
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent descIntent = new Intent(view.getContext(), DescriptionsActivity.class);
					descIntent.putExtra(KEY, locations[position].getName());
					startActivity(descIntent);
				}
            });
		}
    }
    
    public static class ListAdapter extends ArrayAdapter<String>	{
    	
    	private final String [] values;
    	private final Context context;
    	
    	public ListAdapter(Context context, String[] values) {
    		super(context, R.layout.rowlayout, values);
    	    this.values = values;
    	    this.context = context;
    	}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
    		TextView buildingName = (TextView) rowView.findViewById(R.id.tv_building_name);
    		buildingName.setTextSize(24);
    		ImageView buildingImg = (ImageView) rowView.findViewById(R.id.iv_building_img);
			buildingName.setText(locations[position].getName());
			buildingImg.setImageDrawable(parent.getResources().getDrawable(R.drawable.default_menu_item));
			return rowView;
		}
    }

}
