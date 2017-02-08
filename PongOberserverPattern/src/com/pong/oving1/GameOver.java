package com.pong.oving1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;


public class GameOver extends State implements TouchListener{

	private Font fnt;
	private Font fnt2;
	private int winner;

	public GameOver(int winner){
		this.winner = winner;
		fnt = new Font(255, 255, 255, 35, Typeface.SANS_SERIF, Typeface.BOLD);
		fnt2 = new Font(255, 255, 255, 20, Typeface.SANS_SERIF, Typeface.BOLD);
		fnt.setTextAlign(Align.CENTER);
		fnt2.setTextAlign(Align.CENTER);
	}


	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		canvas.drawText("Congratulations to", canvas.getWidth()/2, 240, fnt);
		canvas.drawText("Player"+winner+" for winning!", canvas.getWidth()/2, 300, fnt);
		canvas.drawText("Touch anywhere to begin a new round", canvas.getWidth()/2, 470, fnt2);
	}

	@Override
	public boolean onTouchUp(MotionEvent event) {

		getGame().popState();

		OberservableClass ov = new OberservableClass(0,0);
		GameState gs = new GameState(ov);
		ov.addObserver(gs);

		getGame().pushState(gs);
		return true;
	}





}
