package org.kyledef.seequence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.view.MotionEvent;


public class GameOverScreen extends Screen{
	
	public static GameOverScreen instance = null;
	private int score;

	private GameOverScreen(ScreenManager manager) {
		super(manager, "GAMEOVER");
	}
	
	public static GameOverScreen getInstance(ScreenManager manager) {
		if (instance == null) instance = new GameOverScreen(manager);
		return instance;
	}
	
	private float scaleMeasurement(float size) {
		return manager.width * (size / 100);
	}
	
	@Override
	public void loadContent() {
		// TODO Auto-generated method stub
		
	}
	
	public void setScore(int score){
		this.score = score;
	}

	@Override
	public void draw(Canvas canvas) {
		RectF window = this.manager.getWindow();
		
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(this.manager.getWindow(), paint);
		
		paint.setColor(Color.BLACK);
		paint.setTextSize(scaleMeasurement(10));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("Game Over", window.centerX(), window.centerY() - 175,
				paint);
		canvas.drawText("You Scored " + this.score, window.centerX(), window.centerY() - 50,
				paint);
		paint.setTextSize(scaleMeasurement(5));
		canvas.drawText("Tap anywhere to continue", window.centerX(), window.centerY() + 100,
				paint);
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
	public void ready() {

	}

	@Override
	public boolean processTouchInput(MotionEvent event) {
		this.manager.setActiveScreen("GAMESELECT");
		return false;
	}

}
