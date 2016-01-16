/**
 * Gather data about the phones screen size/dimensions and make this data publicly available
 * to extending objects. Also retrieves a drawing context to allow drawing to the phone screen
 * by extending methods. Objects using this class should extend the Screen class to ensure all required methods are 
 * implemented.
 * 
 */
package org.kyledef.seequence;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;


public class ScreenManager extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final String TAG = "ScreenManager";
	private static ScreenManager phoneScreen = null;
	protected float width, height;
	protected boolean screenReady;
	protected GameThread gameThread = null;
	private Screen activeScreen = null;
	private HashMap<String, Screen> screenMap;
	private Activity activity;
	public GoogleApiClient mGoogleApiClient;

	public Activity getActivty() {
		return activity;
	}

	public HashMap<String, Screen> getScreens() {
		return screenMap;
	}

	public ScreenManager(Activity activity, GoogleApiClient apiClient) {
		super(activity);
		Log.i(TAG, "Constructor of Screen Manager called");
		getHolder().addCallback(this);
		screenMap = new HashMap<>();
		this.activity = activity;
		this.mGoogleApiClient=apiClient;
	}

	public static ScreenManager getInstance(Activity activity) {
		if (phoneScreen == null)
			phoneScreen = new ScreenManager(activity,null);
		return phoneScreen;
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (activeScreen != null)
			activeScreen.draw(canvas);
		else
			Log.i(TAG, "Active Screen is null");

	}

	public void update() {
		if (activeScreen != null)
			activeScreen.update();
		else
			Log.i(TAG, "Active Screen is null");

	}

	/**
	 * Returns a RectF representation of the drawable phone screen region.
	 * 
	 * @return
	 */
	public RectF getWindow() {
		return new RectF(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Returns the current active screen to the calling object
	 * 
	 * @return
	 */
	public Screen getActiveScreen() {
		return activeScreen;
	}

	/**
	 * Sets the Active Screen to be displayed by specifying the screen ScreenID
	 * 
	 * @param screenKey
	 */
	public void setActiveScreen(String screenKey) {
		this.activeScreen = screenMap.get(screenKey);
		activeScreen.ready();
	}

	/**
	 * Pauses the running game thread
	 */
	public void pause() {

		Log.i(TAG, "pause of Screen Manager called");
		if (gameThread != null && activeScreen != null) {
			gameThread.shutDown();
			activeScreen.pause();
		}
	}

	/**
	 * Resumes the running game thread
	 */
	public void resume() {

		Log.i(TAG, "resume of Screen Manager called");
		if (gameThread != null) { // Skip First Run
			gameThread = new GameThread(this);
			gameThread.start();
			screenReady = true;
//			setActiveScreen("GAMESELECT");
		}
	}

	/**
	 * Ends the currently running gameThread
	 */
	public void endGame() {
		Log.i(TAG, "end game of Screen Manager called");
		if (gameThread != null) {
			gameThread.shutDown();
			gameThread = null;
		}
		// attempting to force garbage collection
		activeScreen.pause();
		activeScreen = null;
		screenMap.clear();
		screenMap = null;

		// end activity
		activity.finish();
	}

	public void startTiming() {
		gameThread.startTime();
	}

	public long getGameTime() {
		return gameThread.getTimeElapsed() / 1000;
	}

	/**
	 * Triggers the active screen's touch event
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (this.activeScreen != null)
			this.activeScreen.processTouchInput(event);
		return super.onTouchEvent(event);
	}


	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		if (gameThread == null) {
			gameThread = new GameThread(this);

			gameThread.start();

			width = this.getWidth();// * 0.01f;
			height = this.getHeight();// * 0.01f;
			screenReady = true;

			this.addScreen(GameScreen.getInstance(this));
			this.addScreen(GameSelectScreen.getInstance(this));
			this.addScreen(GameOverScreen.getInstance(this));
			this.addScreen(InstructionsScreen.getInstance(this));
			this.setActiveScreen("GAMESELECT");

		}
	}

	private void addScreen(Screen screen) {
		this.screenMap.put(screen.getName(), screen);
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if (gameThread != null)
			gameThread.shutDown();
	}

	public boolean sendTouchData(MotionEvent event) {
		if (this.activeScreen != null)
			return activeScreen.processTouchInput(event);
		return false;
	}
}
