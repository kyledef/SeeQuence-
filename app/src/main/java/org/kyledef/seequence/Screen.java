package org.kyledef.seequence;

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

	public abstract boolean processTouchInput(MotionEvent event);

	public abstract boolean processMotionInput(SensorEvent event);

	public String getName() {
		return this.screenName;
	}

}
