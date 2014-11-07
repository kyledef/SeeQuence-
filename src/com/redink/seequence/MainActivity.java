package com.redink.seequence;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ScreenManager phoneScreen = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		phoneScreen = ScreenManager.getInstance(this);
		try {
			setContentView(phoneScreen);

			getWindow()
					.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		} catch (Exception e) {
			// Just going to ignore this error for now.
		}

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		phoneScreen.resume();
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
		phoneScreen.pause();
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onSensorChanged(SensorEvent sensorEvent) {
		// TODO Auto-generated method stub
		if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER
				&& phoneScreen != null) {
			phoneScreen.sendSensorData(sensorEvent);
		}
	}

}
