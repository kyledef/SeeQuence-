package com.redink.seequence;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public abstract class Screen {

	protected String screenName;
	protected Context context;
	protected ScreenManager manager;
	protected List<Sprite> elements;

	public Screen(ScreenManager manager, String name) {
		this.context = manager.getActivty();
		this.manager = manager;
		this.screenName = name;
	}

	public abstract void loadContent();

	public abstract void draw(Canvas canvas);

	public abstract void update();

	public abstract void pause();

	public abstract void ready();

	public boolean processTouchInput(MotionEvent event) {

//		for (Sprite element : elements) {
//			element.onTouchEvent(event);
//		}
		return true;
	}

	public boolean processMotionInput(SensorEvent event) {
//		for (Sprite element : elements) {
//			element.onAccelerometerEvent(event);
//		}
		return true;
	}

	public String getName() {
		return this.screenName;
	}

}
