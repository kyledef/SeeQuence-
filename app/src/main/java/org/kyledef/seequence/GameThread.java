package org.kyledef.seequence;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

class GameThread extends Thread {

	private static final String TAG = "GameThread";
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
		Log.d(TAG, "Run Of thread initiated");
		Canvas canvas = null;
		while (isRunning) {
			Log.d(TAG, "Looped");
			canvas = surfaceHolder.lockCanvas();
			if (canvas != null) {
				phoneScreen.update();
				phoneScreen.draw(canvas);
				timeElapsed = System.currentTimeMillis() - startTime;
				surfaceHolder.unlockCanvasAndPost(canvas);
			}

//			if (pause){
//				return; // Attempt to stop Thread
//			}

//			// check to see if to pause
//			while (pause) {
//				Log.d(TAG, "Paused");
//				try {
//					sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}
}
