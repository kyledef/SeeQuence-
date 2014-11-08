package com.redink.seequence.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.redink.seequence.Screen;
import com.redink.seequence.ScreenManager;

public class GameScreen extends Screen{

	public GameScreen(ScreenManager manager) {
		super(manager, "GAMESCREEN");
		// TODO Auto-generated constructor stub
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

	@Override
	public void ready() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		RectF window = this.manager.getWindow();
		canvas.drawColor(Color.WHITE);
		
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		RectF border = new RectF(window.left + 10, window.top + 150, window.right - 10, window.bottom - 10);
		canvas.drawRect(border, paint);
	}

}
