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

	@Override
	public void loadContent() {
		// TODO Auto-generated method stub
		
	}
	
	public void setScore(int score){
		this.score = score;
	}

	@Override
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(this.manager.getWindow(), paint);
		
		paint.setColor(Color.BLACK);
		paint.setTextSize(150);
		paint.setTypeface(Typeface.SERIF);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.CENTER);
		canvas.drawText("Game Over!\nYou Scored " + this.score + "\nTap anywhere to continue", banner.centerX(), banner.centerY() + 75,
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
	
	private RectF banner;

	@Override
	public void ready() {
		RectF window = this.manager.getWindow();
		
		banner = new RectF(window.left + 25, window.top + 50,
				window.right - 25, window.top + 250);
		
	}

	@Override
	public boolean processTouchInput(MotionEvent event) {
		this.manager.setActiveScreen("GAMESELECT");
		return false;
	}

	@Override
	public boolean processMotionInput(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
