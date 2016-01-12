package org.kyledef.seequence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public class GameSelectScreen extends Screen {
	private static GameSelectScreen instance = null;

	private GameSelectScreen(ScreenManager manager) {
		super(manager, "GAMESELECT");
		// TODO Auto-generated constructor stub
	}

	public static GameSelectScreen getInstance(ScreenManager manager) {
		if (instance == null)
			instance = new GameSelectScreen(manager);
		return instance;
	}

	@Override
	public void loadContent() {
		// TODO Auto-generated method stub

	}

	private RectF banner, fourbtn, fivebtn, sixbtn, sevenbtn, eightbtn, instructbtn;

	private RectF getRect(float x, float y, float width, float height) {
		float left = manager.width * (x / 100);
		float top = manager.height * (y / 100);
		float right = left + (manager.width * (width / 100));
		float bottom = top + (manager.height * (height / 100));

//		System.out.println("Drawing rect (" + left + "," + top + "," + right + "," + bottom);
		return new RectF(left, top, right, bottom);
	}

	private float scaleMeasurement(float size) {
		return manager.height * (size / 100);
	}

	@Override
	public void ready() {
		banner = this.getRect(5, 5, 90, 20);

		// new RectF(window.left + (0.05f * manager.width), window.top + (0.1f *
		// manager.height),
		// window.right - (0.05f * manager.width), window.top + (0.75f *
		// manager.height));

		instructbtn = this.getRect(10, 20, 80, 10);

		fourbtn = this.getRect(10, 35, 80, 10);

		// = new RectF(window.left + 100, banner.bottom + 150,
		// window.right - 100, banner.bottom + 300);

		fivebtn = this.getRect(10, 50, 80, 10);
		// = new RectF(window.left + 100, fourbtn.bottom + 50,
		// window.right - 100, fourbtn.bottom + 200);

		sixbtn = this.getRect(10, 65, 80, 10);
		// new RectF(window.left + 100, fivebtn.bottom + 50,
		// window.right - 100, fivebtn.bottom + 200);

		sevenbtn = this.getRect(10, 80, 80, 10);
		// = new RectF(window.left + 100, sixbtn.bottom + 50,
		// window.right - 100, sixbtn.bottom + 200);

		eightbtn = this.getRect(10, 90, 80, 10);
		// new RectF(window.left + 100, sevenbtn.bottom + 50,
		// window.right - 100, sevenbtn.bottom + 200);

	}

	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(this.manager.getWindow(), paint);

		paint.setColor(Color.DKGRAY);
		// canvas.drawRect(banner, paint);

		paint.setTextSize(this.scaleMeasurement(10));
		paint.setTypeface(Typeface.SERIF);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("SeeQuence", banner.centerX(), banner.centerY()-50
				+ (manager.height * 0.03f), paint);

		paint.setColor(Color.GRAY);
		canvas.drawRoundRect(instructbtn, this.scaleMeasurement(5), this.scaleMeasurement(5), paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(this.scaleMeasurement(4));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("Instructions", instructbtn.centerX(), instructbtn.centerY(),
				paint);

		paint.setColor(Color.rgb(51,153,255));
		canvas.drawRoundRect(fourbtn, this.scaleMeasurement(5), this.scaleMeasurement(5), paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(this.scaleMeasurement(4));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("4 x 4 Grid", fourbtn.centerX(), fourbtn.centerY(),
				paint);

		paint.setColor(Color.rgb(0,153,0));
		canvas.drawRoundRect(fivebtn, this.scaleMeasurement(5), this.scaleMeasurement(5), paint);
		paint.setColor(Color.WHITE);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("5 x 5 Grid", fivebtn.centerX(), fivebtn.centerY(),
				paint);

		paint.setColor(Color.rgb(255,128,0));
		canvas.drawRoundRect(sixbtn, this.scaleMeasurement(5), this.scaleMeasurement(5), paint);
		paint.setColor(Color.WHITE);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("6 x 6 Grid", sixbtn.centerX(), sixbtn.centerY(), paint);

		paint.setColor(Color.rgb(255,51,51));
		canvas.drawRoundRect(sevenbtn, this.scaleMeasurement(5), this.scaleMeasurement(5), paint);
		paint.setColor(Color.WHITE);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("7 x 7 Grid", sevenbtn.centerX(), sevenbtn.centerY(),
				paint);

		// paint.setColor(Color.BLACK);
		// canvas.drawRoundRect(eightbtn, 50, 50, paint);
		// paint.setColor(Color.WHITE);
		// paint.setTextSize(50);
		// paint.setTypeface(Typeface.DEFAULT_BOLD);
		// paint.setTextAlign(Align.CENTER);
		// canvas.drawText("8 x 8 Grid", eightbtn.centerX(), eightbtn.centerY(),
		// paint);
	}

	private boolean buttonPressed(RectF button, MotionEvent event) {
		return button.contains(event.getX(), event.getY());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean processTouchInput(MotionEvent event) {
		if(buttonPressed(instructbtn,event)){
			this.manager.setActiveScreen("INSTRUCTIONS");
		}
		if (buttonPressed(fourbtn, event)) {
			GameScreen.getInstance(this.manager).resetGameScreenVariables();
			GameScreen.getInstance(this.manager).setAmount(4);
			GameScreen.getInstance(this.manager).addSequence(true);//even
			GameScreen.getInstance(this.manager).addSequence(true);//odd
			GameScreen.getInstance(this.manager).addSequence(false);//multiple
			GameScreen.getInstance(this.manager).addSequence(false);//power
			GameScreen.getInstance(this.manager).addSequence(false);//prime
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(fivebtn, event)) {
			GameScreen.getInstance(this.manager).resetGameScreenVariables();
			GameScreen.getInstance(this.manager).setAmount(5);
			GameScreen.getInstance(this.manager).addSequence(true);//even
			GameScreen.getInstance(this.manager).addSequence(true);//odd
			GameScreen.getInstance(this.manager).addSequence(true);//multiple
			GameScreen.getInstance(this.manager).addSequence(false);//power
			GameScreen.getInstance(this.manager).addSequence(false);//prime
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(sixbtn, event)) {
			GameScreen.getInstance(this.manager).resetGameScreenVariables();
			GameScreen.getInstance(this.manager).setAmount(6);
			GameScreen.getInstance(this.manager).addSequence(true);//even
			GameScreen.getInstance(this.manager).addSequence(true);//odd
			GameScreen.getInstance(this.manager).addSequence(true);//multiple
			GameScreen.getInstance(this.manager).addSequence(true);//power
			GameScreen.getInstance(this.manager).addSequence(false);//prime
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(sevenbtn, event)) {
			GameScreen.getInstance(this.manager).resetGameScreenVariables();
			GameScreen.getInstance(this.manager).setAmount(7);
			GameScreen.getInstance(this.manager).addSequence(true);//even
			GameScreen.getInstance(this.manager).addSequence(true);//odd
			GameScreen.getInstance(this.manager).addSequence(true);//multiple
			GameScreen.getInstance(this.manager).addSequence(true);//power
			GameScreen.getInstance(this.manager).addSequence(true);//prime
			this.manager.setActiveScreen("GAMESCREEN");
		}
//		else if (buttonPressed(eightbtn, event)) {
//			GameScreen.getInstance(this.manager).setAmount(8);
//			this.manager.setActiveScreen("GAMESCREEN");
//		}
		return false;
	}

	@Override
	public boolean processMotionInput(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
