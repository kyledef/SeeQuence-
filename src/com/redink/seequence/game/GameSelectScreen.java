package com.redink.seequence.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import com.redink.seequence.Screen;
import com.redink.seequence.ScreenManager;

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

	private RectF banner, fourbtn, fivebtn, sixbtn, sevenbtn, eightbtn;

	@Override
	public void ready() {
		RectF window = this.manager.getWindow();

		banner = new RectF(window.left + 25, window.top + 50,
				window.right - 25, window.top + 250);

		fourbtn = new RectF(window.left + 100, banner.bottom + 150,
				window.right - 100, banner.bottom + 300);

		fivebtn = new RectF(window.left + 100, fourbtn.bottom + 50,
				window.right - 100, fourbtn.bottom + 200);

		sixbtn = new RectF(window.left + 100, fivebtn.bottom + 50,
				window.right - 100, fivebtn.bottom + 200);

		sevenbtn = new RectF(window.left + 100, sixbtn.bottom + 50,
				window.right - 100, sixbtn.bottom + 200);

		eightbtn = new RectF(window.left + 100, sevenbtn.bottom + 50,
				window.right - 100, sevenbtn.bottom + 200);

	}

	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(this.manager.getWindow(), paint);

		paint.setColor(Color.WHITE);
		paint.setTextSize(150);
		paint.setTypeface(Typeface.SERIF);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("SeeQuence", banner.centerX(), banner.centerY() + 75,
				paint);

		paint.setColor(Color.BLACK);
		canvas.drawRoundRect(fourbtn, 50, 50, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("4 x 4 Grid", fourbtn.centerX(), fourbtn.centerY(),
				paint);

		paint.setColor(Color.BLACK);
		canvas.drawRoundRect(fivebtn, 50, 50, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("5 x 5 Grid", fivebtn.centerX(), fivebtn.centerY(),
				paint);

		paint.setColor(Color.BLACK);
		canvas.drawRoundRect(sixbtn, 50, 50, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("6 x 6 Grid", sixbtn.centerX(), sixbtn.centerY(), paint);

		paint.setColor(Color.BLACK);
		canvas.drawRoundRect(sevenbtn, 50, 50, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("7 x 7 Grid", sevenbtn.centerX(), sevenbtn.centerY(),
				paint);

//		paint.setColor(Color.BLACK);
//		canvas.drawRoundRect(eightbtn, 50, 50, paint);
//		paint.setColor(Color.WHITE);
//		paint.setTextSize(50);
//		paint.setTypeface(Typeface.DEFAULT_BOLD);
//		paint.setTextAlign(Align.CENTER);
//		canvas.drawText("8 x 8 Grid", eightbtn.centerX(), eightbtn.centerY(),
//				paint);
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
		if (buttonPressed(fourbtn, event)) {
			GameScreen.getInstance(this.manager).setAmount(4);
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(fivebtn, event)) {
			GameScreen.getInstance(this.manager).setAmount(5);
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(sixbtn, event)) {
			GameScreen.getInstance(this.manager).setAmount(6);
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(sevenbtn, event)) {
			GameScreen.getInstance(this.manager).setAmount(7);
			this.manager.setActiveScreen("GAMESCREEN");
		} else if (buttonPressed(eightbtn, event)) {
			GameScreen.getInstance(this.manager).setAmount(8);
			this.manager.setActiveScreen("GAMESCREEN");
		}
		return false;
	}

	@Override
	public boolean processMotionInput(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
