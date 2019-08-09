/**
	@author
		• Denis Christopher
**/

package be.twinkle.ihm;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import be.twinkle.domaine.Bundle;

public class Model {

	/* ATTRIBUTES */
	private Bundle bundle;
	private ArrayList<ChangeListener> views;
	private ChangeListener currentView;
	
	
	/* CONSTRUCTOR */
	public Model() {
		bundle = new Bundle();
		this.bundle.put(Bundle.SUCCESS, true);
		this.views = new ArrayList<ChangeListener>();
		this.currentView = null;
	}
	
	
	/* METHODS */
	public void processEvent(ChangeEvent e) {
		for(ChangeListener view : views)
			view.stateChanged(e);
	}
	
	public Bundle getBundle() {
		return bundle;
	}
	
	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	public void addView(ChangeListener view) {
		if(!views.contains(view))
			views.add(view);
	}
	
	public void removeView(ChangeListener view) {
		if(views.contains(view))
			views.remove(view);
	}

	public ArrayList<ChangeListener> getViews() {
		return views;
	}

	public ChangeListener getCurrentView() {
		return currentView;
	}

	public void setCurrentView(ChangeListener currentView) {
		this.currentView = currentView;
		processEvent(new ChangeEvent(this));
	}
	
	public void changeCurrentView(String view) {
		ArrayList<ChangeListener> views = this.getViews();
		boolean found = false;
		
		for(int i = 0; i < views.size() && !found; i++) {
			if(views.get(i).toString().contains(view)) {
				found = true;
				this.setCurrentView(views.get(i));
			}
		}
	}
	
}
