package com.redink.seequence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

/**
 * Created by Shiva on 1/8/2016.
 */
public class InstructionsScreen extends Screen {

    public static InstructionsScreen instance = null;
    private int score=0;

    private InstructionsScreen(ScreenManager manager) {
        super(manager, "INSTRUCTIONS");
    }

    public static InstructionsScreen getInstance(ScreenManager manager) {
        if (instance == null)
            instance = new InstructionsScreen(manager);
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

        paint.setColor(Color.WHITE);
        paint.setTextSize(scaleMeasurement(7));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Instructions", window.centerX() - 350, window.centerY() - 430,
                paint);


        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(scaleMeasurement(5));
        canvas.drawText("-> The game has 4 levels, with each level ", window.centerX() - 350, window.centerY() + -370,
                paint);
        canvas.drawText("     having new sequences to determine.", window.centerX() - 350, window.centerY() + -330,
                paint);
        canvas.drawText("-> Tap the green button to check answer. ", window.centerX() - 350, window.centerY() - 290,
                paint);
        canvas.drawText("-> Duplicate sequences are not", window.centerX() - 350, window.centerY() - 250,
                paint);
        canvas.drawText("     considered.", window.centerX() - 350, window.centerY() - 210,
                paint);

        paint.setTextSize(scaleMeasurement(7));
        paint.setColor(Color.WHITE);
        canvas.drawText("Levels", window.centerX() - 350, window.centerY() - 160,
                paint);

        paint.setTextSize(scaleMeasurement(5));
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("4 X 4 - Even and Odd.", window.centerX() - 350, window.centerY() + -100,
                paint);
        canvas.drawText("5 X 5 - Even, Odd and Multiples. ", window.centerX() - 350, window.centerY() + -40,
                paint);
        canvas.drawText("6 X 6 - Even, Odd, Multiples and Powers ", window.centerX() - 350, window.centerY() + 20,
                paint);
        canvas.drawText("7 X 7 - Even, Odd, Multiples ", window.centerX() - 350, window.centerY() + 80,
                paint);
        canvas.drawText("  Powers and Primes ", window.centerX() - 250, window.centerY() + 130,
                paint);

        paint.setTextSize(scaleMeasurement(7));
        paint.setColor(Color.WHITE);
        canvas.drawText("NOTE", window.centerX() - 350, window.centerY() + 210,
                paint);

        paint.setTextSize(scaleMeasurement(5));
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("*Each sequence must be >=4 numbers.", window.centerX() - 350, window.centerY() + 270,
                paint);
        canvas.drawText("*Sequences having even, odd and multiples ", window.centerX() - 350, window.centerY()+320,
                paint);
        canvas.drawText(" will get longer as the grid gets bigger! ", window.centerX() - 350, window.centerY()+360,
                paint);

        paint.setTextSize(scaleMeasurement(6));
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(scaleMeasurement(5));
        canvas.drawText("Tap Anywhere to Return to The Main Screen", window.centerX(), window.centerY() + 420,
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

    @Override
    public boolean processMotionInput(SensorEvent event) {
        // TODO Auto-generated method stub
        return false;
    }
}
