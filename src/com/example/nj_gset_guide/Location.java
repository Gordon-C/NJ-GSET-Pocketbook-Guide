package com.example.nj_gset_guide;

import java.lang.reflect.Field;

import android.app.Activity;

public class Location {
	private String name;
	private Activity act; 
	
	public Location(String name, Activity act) {
		this.name = name;
		this.act = act;
	}
	
	public String getName() {
		return name.replace("_", " ");
	}
	
	public String getDescription() {
		return act.getResources().getString(Location.getId(name + "_desc", R.string.class));
	}
	
	public String getAddress() {
		return act.getResources().getString(Location.getId(name + "_addr", R.string.class));
	}
	
	//later implement getting image
	public String getImageName() {
		return name + "_img";
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