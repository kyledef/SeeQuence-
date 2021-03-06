package com.redink.seequence;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class GameThread extends Thread {

	private ScreenManager phoneScreen;
	private SurfaceHolder surfaceHolder;
	private boolean isRunning;
	private long startTime;
	private long timeElapsed;
	protected boolean pause;

	public GameThread(ScreenManager phoneScreen) {
		this.phoneScreen = phoneScreen;
		surfaceHolder = phoneScreen.getHolder();
		startTime = 0;
		timeElapsed = 0;
		isRunning = true;
	}

	public void startTime() {
		startTime = System.currentTimeMillis();
	}

	public long getTimeElapsed() {
		return this.timeElapsed;
	}

	public void shutDown() {
		isRunning = false;
	}

	@Override
	public void run() {
		Canvas canvas = null;
		while (isRunning) {
			canvas = surfaceHolder.lockCanvas();
			if (canvas != null) {
				phoneScreen.update();
				phoneScreen.draw(canvas);
				timeElapsed = System.currentTimeMillis() - startTime;
				surfaceHolder.unlockCanvasAndPost(canvas);
			}

			// check to see if to pause
			synchronized (this) {
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
