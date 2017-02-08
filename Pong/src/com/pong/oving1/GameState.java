package com.pong.oving1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;


public class GameState extends State implements TouchListener{

	private int canvasHeight;
	private int canvasWidth;
	private Sprite paddle1;
	private Sprite paddle2;
	private Sprite pingpongBall;
	private Image pongPaddle1;
	private Image pongPaddle2;
	private Image ballImage;
	private int countPlayer1;
	private int countPlayer2;
	private Font fnt;
	private int speedX;

	public GameState() {

		fnt = new Font(255, 255, 255, 40, Typeface.SANS_SERIF, Typeface.NORMAL);

		countPlayer1 = 0;
		countPlayer2 = 0;
		speedX = 180;
		pongPaddle1 = new Image(R.drawable.pongpaddle);
		pongPaddle2 = new Image(R.drawable.pongpaddle);
		ballImage = new Image(R.drawable.pingpong);

		paddle1 = new Sprite(pongPaddle1);
		paddle2 = new Sprite(pongPaddle2);
		pingpongBall = new Sprite(ballImage);

		pingpongBall.setPosition(200, 200);
		paddle1.setPosition(225, 80);
		paddle2.setPosition(225, 610);
		pingpongBall.setSpeed(speedX, 280);
	}



	@Override
	public boolean onTouchMove(MotionEvent event) {
		if(event.getY()< canvasHeight/2){
			paddle1.setPosition(event.getX(), paddle1.getY());
			return true;
		}
		if(event.getY()> canvasHeight/2){
			paddle2.setPosition(event.getX(), paddle2.getY());
			return true;
		}
		return false;
	}


	@Override
	public void update(float dt) {
		pingpongBall.update(dt);
		paddle1.update(dt);
		paddle2.update(dt);
		//check for winning player, score = 21
		if(countPlayer1==21){
			getGame().popState();
			getGame().pushState(new GameOver(1));
		}
		if(countPlayer2==21){
			getGame().popState();
			getGame().pushState(new GameOver(2));
		}

		//check for pingpong hitting sidewalls
		if(pingpongBall.getX()>(canvasWidth-ballImage.getWidth()) || pingpongBall.getX()<0)
		{
			pingpongBall.setSpeed(-pingpongBall.getSpeed().getX(), pingpongBall.getSpeed().getY());

		}
		if(pingpongBall.getY()>(canvasHeight-ballImage.getHeight()) || pingpongBall.getY()<0)
		{
			pingpongBall.setSpeed(pingpongBall.getSpeed().getX(), -pingpongBall.getSpeed().getY());
		}


		//check for pingpong hitting one of the paddles
		if(pingpongBall.collides(paddle1)){
			pingpongBall.setSpeed(pingpongBall.getSpeed().getX(), -pingpongBall.getSpeed().getY());
		}
		if(pingpongBall.collides(paddle2)){
			pingpongBall.setSpeed(pingpongBall.getSpeed().getX(), -pingpongBall.getSpeed().getY());
		}



		//check if ping pong ball is behind the paddles, which yields +1 point
		if(pingpongBall.getY()<paddle1.getY()-40){
			pingpongBall.setPosition(canvasHeight/2, canvasWidth/2);
			countPlayer1++;
			this.speedX+=5;
		}
		if(pingpongBall.getY()>paddle2.getY()+40){
			pingpongBall.setPosition(canvasHeight/2, canvasWidth/2);
			countPlayer2++;
			this.speedX+=5;
		}



	}

	@Override
	public void draw(Canvas canvas) {
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		canvas.drawColor(Color.BLACK);

		canvas.drawText("" + countPlayer2, 390, 300, fnt);
		canvas.drawText("" + countPlayer1, 390, 400, fnt);
		canvas.drawRect(0, 338, 480, 342, sheep.graphics.Color.WHITE);


		pingpongBall.draw(canvas);
		paddle1.draw(canvas);
		paddle2.draw(canvas);

	}

}
