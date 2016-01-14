package org.kyledef.seequence;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.view.MotionEvent;


public class InstructionsScreen extends Screen {

    public static InstructionsScreen instance = null;
    private int score = 0;

    private InstructionsScreen(ScreenManager manager) {
        super(manager, "INSTRUCTIONS");
    }

    public static InstructionsScreen getInstance(ScreenManager manager) {
        if (instance == null)
            instance = new InstructionsScreen(manager);
        return instance;
    }

    private float scaleMeasurementW(float size) {
        return manager.width * (size / 100);
    }

    private float scaleMeasurement(float size){
        return manager.height * (size /100);
    }

    @Override
    public void loadContent() {
        // TODO Auto-generated method stub

    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void draw(Canvas canvas) {
        RectF window = this.manager.getWindow();

        Paint paint = new Paint();
        paint.setARGB(255, 242, 158, 102);
        canvas.drawRect(this.manager.getWindow(), paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(scaleMeasurement(5));
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Instructions", scaleMeasurementW(1), scaleMeasurement(10),
                paint);


        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextSize(scaleMeasurement(3));
        canvas.drawText("-> The game has 4 levels, with each level ", this.scaleMeasurementW(1), this.scaleMeasurement(15),
                paint);
        canvas.drawText("     having new sequences to determine.", this.scaleMeasurementW(1), this.scaleMeasurement(18),
                paint);
        canvas.drawText("-> Tap the green button to check answer. ", this.scaleMeasurementW(1), this.scaleMeasurement(22),
                paint);
        canvas.drawText("-> Duplicate sequences are not", this.scaleMeasurementW(1), this.scaleMeasurement(26),
                paint);
        canvas.drawText("     considered.", this.scaleMeasurementW(1), this.scaleMeasurement(29),
                paint);

        paint.setTextSize(scaleMeasurement(5));
        paint.setColor(Color.WHITE);
        canvas.drawText("Levels", scaleMeasurementW(1), scaleMeasurement(35),
                paint);

        paint.setTextSize(scaleMeasurement(3));
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("4 X 4 - Even, Odd and Multiples.", this.scaleMeasurementW(1), this.scaleMeasurement(39),
                paint);
        canvas.drawText("5 X 5 - Even, Odd and Multiples. ", this.scaleMeasurementW(1), this.scaleMeasurement(43),
                paint);
        canvas.drawText("6 X 6 - Even, Odd, Multiples and Powers.", this.scaleMeasurementW(1), this.scaleMeasurement(47),
                paint);
        canvas.drawText("7 X 7 - Even, Odd, Multiples ", this.scaleMeasurementW(1), this.scaleMeasurement(51),
                paint);
        canvas.drawText("Powers and Primes.", this.scaleMeasurementW(1), this.scaleMeasurement(54),
                paint);

        paint.setTextSize(scaleMeasurement(5));
        paint.setColor(Color.WHITE);
        canvas.drawText("NOTE", scaleMeasurementW(1), scaleMeasurement(62),
                paint);

        paint.setTextSize(scaleMeasurement(3));
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("*Each sequence must be >=4 numbers.", this.scaleMeasurementW(1), this.scaleMeasurement(67),
                paint);
        canvas.drawText("*Sequences having even, odd and multiples ", this.scaleMeasurementW(1), this.scaleMeasurement(71),
                paint);
        canvas.drawText(" will get longer as the grid gets bigger! ", this.scaleMeasurementW(1), this.scaleMeasurement(74),
                paint);

        paint.setTextSize(scaleMeasurement(4));
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Tap Anywhere to Return ", scaleMeasurementW(1), scaleMeasurement(85),
                paint);
        canvas.drawText("to The Main Screen", scaleMeasurementW(2), scaleMeasurement(90),
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
