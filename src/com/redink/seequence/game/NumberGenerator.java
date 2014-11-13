package com.redink.seequence.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import com.redink.seequence.R;
import com.redink.seequence.ScreenManager;

public class NumberGenerator {

	private ScreenManager manager;
	private Context context;
	private List<NumberElement> elementList;

	public List<NumberElement> getList() {
		return this.elementList;
	}

	public NumberGenerator(ScreenManager manager) {
		this.manager = manager;
		this.context = manager.getContext();
		this.elementList = new ArrayList<NumberElement>();
	}



	public boolean readyElements(int amount, RectF drawableArea) {
		try {
			Bitmap img = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.unselected);

			float width = drawableArea.width() / amount;
			float height = drawableArea.height() / amount;

			for (int i = 0; i < amount; ++i) {
				for (int j = 0; j < amount; ++j) {
					this.elementList.add(new NumberElement(img, (width * i) + 35, (height * j) + 211,
							width, height, manager));
				}
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}
}