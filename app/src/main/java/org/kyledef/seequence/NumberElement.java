package org.kyledef.seequence;

import java.util.Random;

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

public class NumberElement extends Sprite {

	private int value;

	public NumberElement(Bitmap img, float x, float y, float width,
			float height, ScreenManager p, int num) {
		super(img, x, y, width, height, p);
//		this.value = generateNumber();
		this.value=num;
	}
	
	private float scaleMeasurement(float size) {
		return p.height * (size / 100);
	}

	private int generateNumber() {
		Random rand = new Random();
		int min = 1, max = 9;
		return rand.nextInt((max - min) + 1) + min;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, src, this.getDst(), null);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(scaleMeasurement(5));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("" + this.value, this.getDst().centerX(), this.getDst()
				.centerY() + this.scaleMeasurement(1), paint);
		
		
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
		return false;
	}

	public int getValue() {
		return this.value;
	}

	public void reset() {
		this.image = BitmapFactory.decodeResource(this.p.getContext().getResources(),
				R.drawable.unselected);
//		this.value = this.generateNumber();
	}

}
