package com.example.nj_gset_guide;

import java.lang.reflect.Field;
import android.app.Activity;

public class Location {
	private String id;
	private Activity act; 
	
	public Location(String id, Activity act) {
		this.id = id;
		this.act = act;
	}
	
	public String getName() {
		return act.getResources().getString(Location.getId(id, R.string.class));
	}
	
	public String getDescription() {
		return act.getResources().getString(Location.getId(id, R.string.class));
	}
	
	private static int getId(String resourceName, Class<?> c) {
	    try {
	        Field idField = c.getDeclaredField(resourceName);
	        return idField.getInt(idField);
	    } catch (Exception e) { //TODO display error nicely
	        throw new RuntimeException("No resource ID found for: "
	                + resourceName + " / " + c, e);
	    }
	}
}
