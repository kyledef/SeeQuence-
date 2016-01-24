package org.kyledef.seequence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import java.util.Random;

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

	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, src, this.getDst(), null);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		if(this.value<100)
			paint.setTextSize(scaleMeasurement(5));
		else if(this.value>=100 && this.value<1000)
			paint.setTextSize(scaleMeasurement(4));
		else
			paint.setTextSize(scaleMeasurement(3));
		paint.setTypeface(Typeface.DEFAULT);
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
//			System.out.println("Did u click?"  + event.getX() + " | " + event.getY());
			this.image = BitmapFactory.decodeResource(this.p.getContext()
					.getResources(), R.drawable.selected_2);
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
