package com.redink.seequence.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import com.redink.seequence.R;
import com.redink.seequence.ScreenManager;
import com.redink.seequence.Sprite;

public class NumberElement extends Sprite {

	private int value;

	public NumberElement(Bitmap img, float x, float y, float width,
			float height, ScreenManager p, int value) {
		super(img, x, y, width, height, p);
		this.value = value;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, src, this.getDst(), null);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(60);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("" + this.value, this.getDst().centerX(), this.getDst()
				.centerY() + 15, paint);
		
		
		//canvas.drawRect(getDst(), paint);
	}

	@Override
	public void update() {	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (this.getDst().contains(event.getX(), event.getY())) {
			System.out.println("Did u click?"  + event.getX() + " | " + event.getY());
			this.image = BitmapFactory.decodeResource(this.p.getContext()
					.getResources(), R.drawable.selected);
			return true;
		}
		return false;
	}

	@Override
	public boolean onAccelerometerEvent(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
