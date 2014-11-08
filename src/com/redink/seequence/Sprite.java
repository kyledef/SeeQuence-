package com.redink.seequence;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public abstract class Sprite {
	public Bitmap image;
	protected float x, y, width, height;
	protected ScreenManager p;
	protected Rect src;
	protected Rect dst;

	public Sprite(Bitmap img, float x, float y, float width, float height,
			ScreenManager p) {
		this.image = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.p = p;
		this.dst = new Rect((int) (x * p.width), (int) (y * p.height),
				(int) ((x + width) * p.width), (int) ((y + height) * p.height));
		this.src = null;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, src, this.getDst(), null);
		System.out.println("drawing the screen");
	}

	public RectF getDst() {
		return new RectF(x, y , x + (width * p.width), y
				+ (height * p.height));
	}
	public abstract void update();

	// public abstract void draw(Canvas canvas);
	
	public abstract boolean onTouchEvent(MotionEvent event);
	
	public abstract boolean onAccelerometerEvent(SensorEvent event);

}
