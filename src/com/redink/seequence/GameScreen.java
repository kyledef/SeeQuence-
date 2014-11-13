package com.redink.seequence;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.os.CountDownTimer;
import android.view.MotionEvent;

import com.redink.seequence.R;

public class GameScreen extends Screen {

	// private NumberGenerator generator;
	private List<NumberElement> sequence = new ArrayList<NumberElement>();
	private int score = 0;
	private long timer;

	private RectF border1, border2, button, scoreRect, timerRect;
	private int amount = 4;
	private List<NumberElement> elementList = new ArrayList<NumberElement>();

	private static GameScreen instance = null;

	private GameScreen(ScreenManager manager) {
		super(manager, "GAMESCREEN");
		// this.generator = new NumberGenerator(manager);
	}

	public static GameScreen getInstance(ScreenManager manager) {
		if (instance == null)
			instance = new GameScreen(manager);
		return instance;
	}

	public void setAmount(int amt) {
		this.amount = amt;
	}

	@Override
	public void loadContent() {

	}

	@Override
	public void update() {

	}

	@Override
	public void pause() {

	}

	private RectF getRect(float x, float y, float width, float height) {
		float left = manager.width * (x / 100);
		float top = manager.height * (y / 100);
		float right = left + (manager.width * (width / 100));
		float bottom = top + (manager.height * (height / 100));

		System.out.println("Drawing rect (" + left + "," + top + "," + right
				+ "," + bottom);
		return new RectF(left, top, right, bottom);
	}

	private RectF getSquare(float x, float y, float width) {
		float left = manager.width * (x / 100);
		float top = manager.height * (y / 100);
		float right = left + (manager.width * (width / 100));
		float bottom = top + (manager.width * (width / 100));

		System.out.println("Drawing rect (" + left + "," + top + "," + right
				+ "," + bottom);
		return new RectF(left, top, right, bottom);
	}

	private float scaleMeasurement(float size) {
		return manager.height * (size / 100);
	}
	
	private float scaleMeasurementW(float size) {
		return manager.width * (size / 100);
	}

	@Override
	public void ready() {
		this.elementList.clear();
		this.score = 0;

		// RectF window = this.manager.getWindow();

		// this.border1 = this.getSquare(5, 150, 50 );
		// = new RectF(window.left + 25, window.top + 200,
		// window.right - 25, window.width() + 200);
		this.border2 = this.getSquare(5, 15, 90);
		// new RectF(window.left + 35, window.top + 210,
		// window.right - 35, window.width() + 190);

		this.button = this.getRect(5, 82, 90, 13);
		// new RectF(window.left + 25, window.bottom - 250,
		// window.right - 25, window.bottom - 25);

		this.scoreRect = this.getRect(5, 5, 45, 10);

		// new RectF(window.left + 25, window.top + 25,
		// window.right - 25, window.top + 175);

		this.timerRect = this.getRect(45, 5, 50, 10);
		// = new RectF(window.left + 25, window.top + 25,
		// window.right - 25, window.top + 175);

		try {
			Bitmap img = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.unselected);

			float width = border2.width() / amount;
			float height = border2.height() / amount;

			for (int i = 0; i < amount; ++i) {
				for (int j = 0; j < amount; ++j) {
					this.elementList
							.add(new NumberElement(img, (width * i)
									+ this.scaleMeasurementW(5), (height * j)
									+ this.scaleMeasurement(15), width, height,
									manager));
				}
			}
		} catch (Exception e) {
		}

		new CountDownTimer(60000, 1000) {

			public void onTick(long millisUntilFinished) {
				timer = millisUntilFinished / 1000;
			}

			public void onFinish() {
				timer = 0;
				GameOverScreen.getInstance(manager).setScore(score);
				manager.setActiveScreen("GAMEOVER");
			}
		}.start();
	}

	private void drawBorder(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(this.manager.getWindow(), paint);
		paint.setColor(Color.WHITE);
		canvas.drawRect(border2, paint);

		paint.setColor(Color.GREEN);
		canvas.drawRoundRect(button, 15, 15, paint);

		paint.setColor(Color.BLACK);
		paint.setTextSize(this.scaleMeasurement(5));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.LEFT);
		canvas.drawText("Score: " + this.score, this.scaleMeasurement(5),
				scoreRect.centerY(), paint);
		paint.setTextAlign(Align.RIGHT);
		canvas.drawText("Timer: " + this.timer, this.scaleMeasurement(60),
				timerRect.centerY(), paint);

	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		drawBorder(canvas);

		for (NumberElement e : elementList) {
			e.draw(canvas);
		}
	}

	private boolean checkSequence() {
		if (sequence.size() > 2) {
			NumberElement e1 = this.sequence.get(0);
			NumberElement e2 = this.sequence.get(1);

			int diff = e1.getValue() - e2.getValue();

			for (int i = 0; i < sequence.size() - 1; i++) {
				e1 = this.sequence.get(i);
				e2 = this.sequence.get(i + 1);
				if (diff != e1.getValue() - e2.getValue())
					return false;
			}
			score += (Math.abs(diff) * sequence.size());
			return true;
		} // else sequence to short to be considered.
		return false;
	}

	@Override
	public boolean processTouchInput(MotionEvent event) {
		// if the element was touched exit the loop.
		for (NumberElement e : elementList) {
			if (e.onTouchEvent(event)) {
				sequence.add(e);
				return true;
			}
		}

		if (this.button.contains(event.getX(), event.getY())) {
			System.out.println("Enter pressed");
			if (checkSequence())
				System.out.println("This is a sequence! " + score);
			else
				System.out.println("This is not a sequence.");
			// always empty the sequence
			for (NumberElement e : sequence)
				e.reset();
			this.sequence.clear();
		}

		return false;
	}

	@Override
	public boolean processMotionInput(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
