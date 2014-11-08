package com.redink.seequence.game;

import android.graphics.Bitmap;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import com.redink.seequence.Sprite;
import com.redink.seequence.ScreenManager;

public class NumberElement extends Sprite {
	
	private int value;

	public NumberElement(Bitmap img, float x, float y, float width,
			float height, ScreenManager p, int value) {
		super(img, x, y, width, height, p);
		this.value = value;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onAccelerometerEvent(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
