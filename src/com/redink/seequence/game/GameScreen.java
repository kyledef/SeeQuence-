package com.redink.seequence.game;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import com.redink.seequence.Screen;
import com.redink.seequence.ScreenManager;

public class GameScreen extends Screen {

	private NumberGenerator generator;

	public GameScreen(ScreenManager manager) {
		super(manager, "GAMESCREEN");
		// TODO Auto-generated constructor stub

		this.generator = new NumberGenerator(manager);
	}

	@Override
	public void loadContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	
	private RectF border1, border2, button;

	@Override
	public void ready() {
		RectF window = this.manager.getWindow();

		this.border1 = new RectF(window.left + 25, window.top + 200,
				window.right - 25, window.width() + 200);
		this.border2 = new RectF(window.left + 35, window.top + 210,
				window.right - 35, window.width() + 190);
		
		this.button = new RectF(window.left + 25, window.bottom - 250, window.right - 25, window.bottom - 25);

		this.generator.readyElements(4, border2);
	}

	private void drawBorder(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(border1, paint);
		paint.setColor(Color.WHITE);
		canvas.drawRect(border2, paint);
		
		paint.setColor(Color.GREEN);
		canvas.drawRoundRect(button, 15, 15, paint);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		drawBorder(canvas);
		
		List<NumberElement> list = this.generator.getList();
		for (NumberElement e : list){
			e.draw(canvas);
		}
	}

	@Override
	public boolean processTouchInput(MotionEvent event) {
		List<NumberElement> list = this.generator.getList();
		
		// if the element was touched exit the loop.
		for (NumberElement e : list){
			if (e.onTouchEvent(event)) {
				System.out.println("found touched element");
				break;
			}
		}
		return false;
	}

	@Override
	public boolean processMotionInput(SensorEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
