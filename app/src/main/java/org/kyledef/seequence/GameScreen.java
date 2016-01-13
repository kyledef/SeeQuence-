package org.kyledef.seequence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.SensorEvent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameScreen extends Screen {

	private static final String TAG = "GameScreen";
	// private NumberGenerator generator;
	private List<NumberElement> sequence = new ArrayList<>();
	private int score = 0;
	private long timer;

	private RectF border1, border2, button, scoreRect, timerRect, sequenceRect;
	private int amount = 4;
	private List<NumberElement> elementList = new ArrayList<>();
	private List<Boolean> sequenceSelection = new ArrayList<>();
	private List<Integer>  sequenceListToInsert = new ArrayList<>();
	private ArrayList<Integer> primes = new ArrayList<>();

	private String sequenceString="";
	private String notify="";

	//Defining the type of sequences
	//	boolean even, odd, prime, multiple, power;

	private static GameScreen instance = null;

	public void addSequence(boolean value){
		sequenceSelection.add(value);
	}

	private GameScreen(ScreenManager manager) {
		super(manager, "GAMESCREEN");
		// this.generator = new NumberGenerator(manager);
	}

	public static GameScreen getInstance(ScreenManager manager) {
		if (instance == null)
			instance = new GameScreen(manager);
		return instance;
	}

	public void setAmount(int amt) {
		this.amount = amt;
	}

	@Override
	public void loadContent() {

	}

	@Override
	public void update() {

	}

	@Override
	public void pause() {

	}

	private RectF getRect(float x, float y, float width, float height) {
		float left = manager.width * (x / 100);
		float top = manager.height * (y / 100);
		float right = left + (manager.width * (width / 100));
		float bottom = top + (manager.height * (height / 100));

//		System.out.println("Drawing rect (" + left + "," + top + "," + right + "," + bottom);
		return new RectF(left, top, right, bottom);
	}

	private RectF getSquare(float x, float y, float width) {
		float left = manager.width * (x / 100);
		float top = manager.height * (y / 100);
		float right = left + (manager.width * (width / 100));
		float bottom = top + (manager.width * (width / 100));

//		System.out.println("Drawing rect (" + left + "," + top + "," + right + "," + bottom);
		return new RectF(left, top, right, bottom);
	}

	private float scaleMeasurement(float size) {
		return manager.height * (size / 100);
	}
	
	private float scaleMeasurementW(float size) {
		return manager.width * (size / 100);
	}

	@Override
	public void ready() {
		this.elementList.clear();
		this.score = 0;

		// RectF window = this.manager.getWindow();

		// this.border1 = this.getSquare(5, 150, 50 );
		// = new RectF(window.left + 25, window.top + 200,
		// window.right - 25, window.width() + 200);
		this.border2 = this.getSquare(5, 15, 90);
		// new RectF(window.left + 35, window.top + 210,
		// window.right - 35, window.width() + 190);

		this.button = this.getRect(5, 85, 90, 8);
		// new RectF(window.left + 25, window.bottom - 250,
		// window.right - 25, window.bottom - 25);

		this.scoreRect = this.getRect(5, 5, 45, 10);

		// new RectF(window.left + 25, window.top + 25,
		// window.right - 25, window.top + 175);

		this.timerRect = this.getRect(45, 5, 50, 10);
		// = new RectF(window.left + 25, window.top + 25,
		// window.right - 25, window.top + 175);

		this.sequenceRect = this.getRect(45,82,20,15);

		try {
			Bitmap img = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.unselected);

			float width = border2.width() / amount;
			float height = border2.height() / amount;
			generateSequences();
			int numToInsert, pos=0, length = sequenceListToInsert.size();
//			for(int j :sequenceListToInsert){
////				Log.i("HI",""+j);
//			}
			for (int i = 0; i < amount; ++i) {
				for (int j = 0; j < amount; ++j) {

					if(pos<length){
						numToInsert=sequenceListToInsert.get(pos);
						pos++;
					}
					else {
						//Generate Random Number Here

						numToInsert=generateNumber(1,100);
						while(sequenceListToInsert.contains(new Integer(numToInsert))){
							numToInsert=generateNumber(1,100);
						}
					}
					this.elementList
							.add(new NumberElement(img, (width * i)
									+ this.scaleMeasurementW(5), (height * j)
									+ this.scaleMeasurement(15), width, height,
									manager,numToInsert));
				}
			}
			long seed = System.nanoTime();
			Collections.shuffle(elementList, new Random(seed));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		new CountDownTimer(60000, 1000) {

			public void onTick(long millisUntilFinished) {
				timer = millisUntilFinished / 1000;
			}

			public void onFinish() {
				timer = 0;
				GameOverScreen.getInstance(manager).setScore(score);
				manager.setActiveScreen("GAMEOVER");
			}
		}.start();
	}

	private void drawBorder(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 242, 158, 102);
		canvas.drawRect(this.manager.getWindow(), paint);
		paint.setColor(Color.WHITE);
		canvas.drawRect(border2, paint);

		paint.setColor(Color.GREEN);
		canvas.drawRoundRect(button, 15, 25, paint);

		paint.setColor(Color.BLACK);
		paint.setTextSize(scaleMeasurement(3));
		canvas.drawText("Check Sequence", this.scaleMeasurementW(31), this.scaleMeasurement(90), paint);

		paint.setColor(Color.BLACK);
		paint.setTextSize(this.scaleMeasurement(3));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		canvas.drawText("You ", this.scaleMeasurementW(8), this.scaleMeasurement(72), paint);
		canvas.drawText("Selected:", this.scaleMeasurementW(5), this.scaleMeasurement(75), paint);

		canvas.drawText("Message:", this.scaleMeasurementW(5), this.scaleMeasurement(80), paint);

		paint.setColor(Color.WHITE);
		paint.setTextSize(this.scaleMeasurement(3));
		canvas.drawText("" + this.sequenceString, this.scaleMeasurementW(28),
				this.scaleMeasurement(75), paint);

		paint.setColor(Color.WHITE);
		paint.setTextSize(this.scaleMeasurement(3));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		canvas.drawText("" + this.notify, this.scaleMeasurementW(30),
				this.scaleMeasurement(80), paint);

		paint.setColor(Color.BLACK);
		paint.setTextSize(this.scaleMeasurement(4));
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Align.LEFT);
		canvas.drawText("Score: " + this.score, this.scaleMeasurement(5),
				scoreRect.centerY(), paint);
		paint.setTextAlign(Align.RIGHT);
		canvas.drawText("Timer: " + this.timer, this.scaleMeasurement(52),
				timerRect.centerY(), paint);

	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		drawBorder(canvas);

		for (NumberElement e : elementList) {
			e.draw(canvas);
		}

	}

	private boolean checkSequence() {
//		if (sequence.size() > 2) {
//			NumberElement e1 = this.sequence.get(0);
//			NumberElement e2 = this.sequence.get(1);
//
//			int diff = e1.getValue() - e2.getValue();
//
//			for (int i = 0; i < sequence.size() - 1; i++) {
//				e1 = this.sequence.get(i);
//				e2 = this.sequence.get(i + 1);
//				if (diff != e1.getValue() - e2.getValue())
//					return false;
//			}
//			score += (Math.abs(diff) * sequence.size());
//			return true;
//		} // else sequence to short to be considered.
//		return false;
		if(sequence.size()>=4) {
			if (sequenceSelection.get(0)) {
//				Log.i("EVEN","EVEN");
				if (checkEvenSolution()) {
					sequenceSelection.set(0, false);
					score += 5;
					if(sequence.size()>=5){
						score+=(sequence.size()-4)*2;
					}
					return true;
				}
			}
			if (sequenceSelection.get(1)) {
//				Log.i("ODDD","ODDD");
				if (checkOddSolution()) {
					sequenceSelection.set(1,false);
					score+=5;
					if(sequence.size()>=5){
						score+=(sequence.size()-4)*2;
					}
					return true;
				}
			}
			if (sequenceSelection.get(2)) {
//				Log.i("MULTIPLE","MULTIPLE");
				if (checkMultipleSolution()) {
					sequenceSelection.set(2,false);
					score+=10;
					if(sequence.size()>=5){
						score+=(sequence.size()-4)*3;
					}
					return true;
				}
			}
			if (sequenceSelection.get(3)) {
//				Log.i("POWER CHECK","POWER");
				if (checkPowerSolution()) {
					sequenceSelection.set(3,false);
					score+=10;
					return true;
				}
				else{
					if(checkPowerSolutionReverse()){
						sequenceSelection.set(2,false);
						score+=10;
						return true;
					}
				}
			}
			if (sequenceSelection.get(4)) {
				if (checkPrimeSolution()) {
					sequenceSelection.set(4,false);
					score+=15;
					return true;
				}
				else{
					if(checkPrimeSolutionReverse()){
						sequenceSelection.set(4,false);
						score+=15;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean processTouchInput(MotionEvent event) {
		Log.i(TAG, "Received: "+ event.toString());
		// if the element was touched exit the loop.
		for (NumberElement e : elementList) {
			if(sequence.size()>=7){
				notify="Max Size is 7!";
//				return true;
			}
			else if (e.onTouchEvent(event)) {
				sequence.add(e);
				if(sequence.size()==7)
					sequenceString+=" "+e.getValue()+".";
				else
					sequenceString+=" "+e.getValue()+",";
				notify="";
				return true;
			}
		}

		if (this.button.contains(event.getX(), event.getY())) {
//			System.out.println("Enter pressed");
			if (checkSequence()) {
				notify = "Correct Sequence!";
//				System.out.println("This is a sequence! " + score);
			} else {
				notify = "Wrong Sequence!";
//				System.out.println("This is not a sequence.");
			}
		}
		// always empty the sequence
		for (NumberElement e : sequence)
			e.reset();
		this.sequence.clear();
		sequenceString="";
		return false;
	}


	private void generateSequences() {
		int i;
		for (i = 0; i < 5; i++) {
//			Log.i(">>>>", "" + sequenceSelection.get(i));
			if (sequenceSelection.get(i)) {
				if (i == 0)
					generateEven();
				else if (i == 1) {
					generateOdd();
				}
				else if (i == 2) {
					generateMultiple();
				}
				else if (i == 3) {
					generatePower();
				}
				else {
					generatePrime();
				}
			}
		}
		long seed = System.nanoTime();
		Collections.shuffle(sequenceListToInsert, new Random(seed));
	}

	private void generateEven(){
		int i=0;
		int num = generateNumber(2, amount * amount);
		if(num%2!=0) num++;
		do{
//			Log.i("Adding EVEN", "" + num);
			sequenceListToInsert.add(num);
			num+=2;
			i++;
		}while(i!=amount);
	}

	private boolean checkEvenSolution(){
		int i=0,e1,e2,diff;
		e2 = this.sequence.get(i).getValue();
		e1 = this.sequence.get(i+1).getValue();
		diff = e2-e1;
		diff = Math.abs(diff);
		if(diff==0 || diff%2!=0){
			return false;
		}
		for(i=2;i<sequence.size();i++){
			e2 = this.sequence.get(i).getValue();
//			Log.i("EVEN TEST",""+e1+"---"+""+e2);
			if(Math.abs(e2-e1)!=diff || diff%2!=0) {
//				Log.i("RETURNING FALSE","FALSE");
				return false;
			}
			e1=e2;
		}
		return true;
	}

	private void generateOdd(){
		int i=0;
		int num = generateNumber(1, amount * amount);
		if(num%2==0) num++;
		do{
//			Log.i("Adding ODD", "" + num);
			sequenceListToInsert.add(num);
			num+=2;
			i++;
		}while(i!=amount);
	}

	private boolean checkOddSolution(){
		int i=0,e1,e2,diff;
		e2 = this.sequence.get(i).getValue();
		e1 = this.sequence.get(i+1).getValue();
		diff = e2-e1;
		diff = Math.abs(diff);
		if(e1%2!=1 || e2%2!=1 || diff==0){
			return false;
		}
		for(i=2;i<sequence.size();i++){
			e2 = this.sequence.get(i).getValue();
			Log.i("ODD TEST",""+e1+"---"+""+e2);
			if(Math.abs(e2-e1)!=diff || e1%2!=1 || e2%2!=1) {
				Log.i("RETURNING FALSE","FALSE");
				return false;
			}
			e1=e2;
		}
		return true;
	}

	private void generatePrime(){
		if(primes.isEmpty()) {
			primes.add(2);
			primes.add(3);
			primes.add(5);
			primes.add(7);
			primes.add(11);
			primes.add(13);
			primes.add(17);
			primes.add(19);
			primes.add(23);
			primes.add(29);
			primes.add(31);
			primes.add(41);
			primes.add(43);
			primes.add(47);
			primes.add(53);
			primes.add(59);
			primes.add(61);
			primes.add(67);
			primes.add(71);
			primes.add(73);
			primes.add(79);
			primes.add(83);
			primes.add(89);
			primes.add(97);
		}
		int pos = generateNumber(0,primes.size()-amount);
		int i;
		for(i=pos;i<=pos+3;i++){
			if(!sequenceListToInsert.contains(primes.get(i)))
				sequenceListToInsert.add((primes.get(i)));
//			Log.i("Adding PRIME", "" +primes.get(i));
		}
	}

	private boolean checkPrimeSolution(){
		int sequencePosition=0;
		int start = sequence.get(sequencePosition).getValue();
		sequencePosition++;
		if(primes.contains(start)){
			int pos = primes.indexOf(start);
//			Log.i("VALUE OF POS",">>"+pos);
			int i;
			for(i=pos+1;i<=pos+3;i++){
//				Log.i("VALUE OF POS",">>"+i);
//				Log.i("POSITION-POS2", "" + primes.get(i)+"-"+sequence.get(sequencePosition).getValue());
				if(primes.get(i)!=(sequence.get(sequencePosition).getValue()))
					return false;
				sequencePosition++;
			}
		}
		return true;
	}

	private boolean checkPrimeSolutionReverse(){
		int sequencePosition=sequence.size()-1;
		int start = sequence.get(sequencePosition).getValue();
		sequencePosition--;
		if(primes.contains(new Integer(start))){
			int pos = primes.indexOf(new Integer(start));
//			Log.i("VALUE OF POS",">>"+pos);
			int i;
			for(i=pos-1;i>=pos+3;i++){
//				Log.i("VALUE OF POS",">>"+i);
//				Log.i("POSITION-POS2", "" + primes.get(i)+"-"+sequence.get(sequencePosition).getValue());
				if(primes.get(i)!=(sequence.get(sequencePosition).getValue()))
					return false;
				sequencePosition++;
			}
		}
		return true;
	}

	private void generateMultiple(){
		int i = generateNumber(1,10);
		int count=1;
		while(count<=amount){
//			Log.i("Adding Multiple", "" + i * count);
			if(!sequenceListToInsert.contains(new Integer(i*count)))
				sequenceListToInsert.add(i*count);
			count++;
		}
	}

	private boolean checkMultipleSolution(){
		int multiple = Math.abs(sequence.get(1).getValue() - sequence.get(0).getValue());
		int i, newMultiple;
		for(i=1;i<sequence.size()-1;i++){
			newMultiple = Math.abs(sequence.get(i+1).getValue()-sequence.get(i).getValue());
			if(newMultiple!=multiple){
				return false;
			}
		}
		return true;
	}

	private void generatePower(){
		int i = generateNumber(2, 10);
		int count=1;
		while(count<5){
			Double sol = Math.pow(i,count);
			if(!sequenceListToInsert.contains(sol.intValue()))
				sequenceListToInsert.add(sol.intValue());
//			Log.i("Adding POWER", "" + sol);
			count++;
		}
	}

	private boolean checkPowerSolution(){
		ArrayList<Integer> checkList = new ArrayList<>();
		int start = sequence.get(0).getValue();
		int count=1;
		while(count<5){
			Double sol = Math.pow(start,count);
			checkList.add(sol.intValue());
			count++;
		}
		count =0;
		while(count<sequence.size()){
//			Log.i("COMPARING",""+checkList.get(count)+"---"+sequence.get(count).getValue());
			if(checkList.get(count)!=sequence.get(count).getValue()){
				return false;
			}
			count++;
		}
		return true;
	}

	private boolean checkPowerSolutionReverse(){
		ArrayList<Integer> checkList = new ArrayList<>();
		int start = sequence.get(sequence.size()-1).getValue();
		int count=1,pos=0;
		while(count<5){
			Double sol = Math.pow(start,count);
			checkList.add(sol.intValue());
			count++;
		}
		count =checkList.size()-1;
		while(count>=0){
			Log.i("COMPARING2",""+checkList.get(count)+"---"+sequence.get(pos).getValue());
			if(checkList.get(count)!=sequence.get(pos++).getValue()){
				return false;
			}
			count--;
		}
		return true;
	}

	private int generateNumber(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	public void resetGameScreenVariables(){
		this.elementList.clear();
		this.sequenceListToInsert.clear();
	}

}
