package com.imie.trybaproject.model;

import android.support.v4.app.Fragment;

public class MenuItem {

	private Fragment fragment;
	private String title;
	private int icon;
	
	public MenuItem(String title){
		this.title = title;
	}
	
	public MenuItem(String title, int icon){
		this.title = title;
		this.icon = icon;
	}
	
	public MenuItem(Fragment fragment, String title, int icon) {
		super();
		this.fragment = fragment;
		this.title = title;
		this.icon = icon;
	}
	public MenuItem() {
		
	}
	public Fragment getFragment() {
		return fragment;
	}
	public void setFragment(Fragment fragment) {
		this.fragment = fragment;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return title;
	}
	
	@Override
	public boolean equals(Object o) {
		MenuItem item = (MenuItem)o;
		
		if(item.getTitle().equals(getTitle()))
			return true;
		
		return false;
	}
	
}
