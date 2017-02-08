package com.pong.oving1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class GameStart extends State implements TouchListener{

	private Font fnt;
	private Font fnt2;

	public GameStart(){
		fnt = new Font(255, 255, 255, 45, Typeface.SANS_SERIF, Typeface.BOLD);
		fnt.setTextAlign(Align.CENTER);
		fnt2 = new Font(255, 255, 255, 25, Typeface.SANS_SERIF, Typeface.BOLD);
		fnt2.setTextAlign(Align.CENTER);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvas.drawText("Weclome to Pong!", canvas.getWidth()/2, 240, fnt);
		canvas.drawText("Touch anywhere to begin", canvas.getWidth()/2, 300, fnt2);

	}

	@Override
	public boolean onTouchUp(MotionEvent event) {

		getGame().popState();
		getGame().pushState(new GameState());
		return false;
	}





}
