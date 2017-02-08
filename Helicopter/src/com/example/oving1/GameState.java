package com.example.oving1;

import sheep.game.State;
import sheep.graphics.Font;
import android.content.res.Resources;
import android.graphics.*;
import android.view.MotionEvent;
import sheep.input.TouchListener;


public class GameState extends State implements TouchListener{

	//Declaring some useful variables and helicopter objects
	private Helicopter helicopter1;
	private Helicopter helicopter2;
	private Helicopter aircraft; //created a new and simple sprite in photoshop

	private int canvasHeight;
	private int canvasWidth;

	public GameState(Resources resources){

		//@helicopter1-----------------------------------------------------------------------------------------------
		//helicopter1 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.helicopter1), #old init used for task 1 so no animation
		helicopter1 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.spritesheet),
				100, 50, 	//sort of random set x and y startposistion of sprite
				100,		//fps, each frame in the animation  display for 100 ms before changing to the next frame
				4		    //frameCount, number of frames in animation
				);
		helicopter1.setSpeed(600, 600);
		helicopter1.update(System.currentTimeMillis());

		//@helicopter2-----------------------------------------------------------------------------------------------
		helicopter2 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.spritesheet),
				150, 300, 	//sort of random set x and y startposistion of sprite
				100,		//fps, each frame in the animation  display for 100 ms before changing to the next frame
				4		    //frameCount, number of frames in animation
				);
		helicopter2.setSpeed(600, 600);
		helicopter2.update(System.currentTimeMillis());

		//@aircraft-----------------------------------------------------------------------------------------------
		aircraft = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.aircraftsprite),
				300, 500, 	//sort of random set x and y startposistion of sprite
				100,		//fps, each frame in the animation  display for 100 ms before changing to the next frame
				4		    //frameCount, number of frames in animation
				);

		aircraft.setSpeed(600,600);
		aircraft.update(System.currentTimeMillis());
	}

	//gamestate is drawn on canvas
	@Override
	public void draw(Canvas canvas) {
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();

		//set background color and draw helicopter sprites on canvas
		canvas.drawColor(Color.MAGENTA); //matching the background of the helicopter sprite
		helicopter1.draw(canvas);
		helicopter2.draw(canvas);
		aircraft.draw(canvas);

		//print the position of the sprite in cordinates
		Font font = new Font(23, 52, 20, 20, Typeface.SANS_SERIF, Typeface.NORMAL);
		canvas.drawText("Helicopter1 is located at:", 20, 40, font);
		canvas.drawText(" x: " + helicopter1.getX() + " y: " + helicopter1.getY() , 20, 70, font);
	}


	//handling for when a user drags a touch along the screen, sets new positons of helicopter
	@Override
	public boolean onTouchMove(MotionEvent event) {
		helicopter1.setPosition(event.getX(), event.getY()); //touch function only for @helicopter1
		return true;
	}


	//handling for when user releases a touch from the screen, sets new positons of helicopter
	@Override
	public boolean onTouchUp(MotionEvent event) {
		helicopter1.setPosition(event.getX(), event.getY()); //touch function only for @helicopter1
		return true;
	}


	//updating gamestate
	@Override
	public void update(float dt) {
		helicopter1.update(System.currentTimeMillis());
		helicopter2.update(System.currentTimeMillis());
		aircraft.update(System.currentTimeMillis());

		//@helicopter1 check if sprite hits top/button or sides, then respond appropiate
		if(helicopter1.getX()>(canvasWidth-helicopter1.getHelicopterWidth()) || helicopter1.getX()<0){
			helicopter1.setSpeed(-helicopter1.getSpeed().getX(), helicopter1.getSpeed().getY());
			helicopter1.flipHelicopter();
		}
		if(helicopter1.getY()>(canvasHeight-helicopter1.getHelicopterHeight()) || helicopter1.getY()<0){
			helicopter1.setSpeed(helicopter1.getSpeed().getX(), -helicopter1.getSpeed().getY());
		}


		//@helicopter2 check if sprite hits top/button or sides, then respond appropiate
		if(helicopter2.getX()>(canvasWidth-helicopter2.getHelicopterWidth()) || helicopter2.getX()<0){
			helicopter2.setSpeed(-helicopter2.getSpeed().getX(), helicopter2.getSpeed().getY());
			helicopter2.flipHelicopter();
		}
		if(helicopter2.getY()>(canvasHeight-helicopter2.getHelicopterHeight()) || helicopter2.getY()<0){
			helicopter2.setSpeed(helicopter2.getSpeed().getX(), -helicopter2.getSpeed().getY());
		}


		//@aircraft check if sprite hits top/button or sides, then respond appropiate
		if(aircraft.getX()>(canvasWidth-aircraft.getHelicopterWidth()) || aircraft.getX()<0)
		{
			aircraft.setSpeed(-aircraft.getSpeed().getX(), aircraft.getSpeed().getY());
			aircraft.flipHelicopter();
		}
		if(aircraft.getY()>(canvasHeight-aircraft.getHelicopterHeight()) || aircraft.getY()<0)
		{
			aircraft.setSpeed(aircraft.getSpeed().getX(), -aircraft.getSpeed().getY());
		}


		//@aircraftcheck if hits @helicopter2, then respond appropiate
		if(aircraft.getSpriteRect().intersect(helicopter2.getSpriteRect()) || helicopter2.getSpriteRect().intersect(aircraft.getSpriteRect())){
			helicopter2.setSpeed(-helicopter2.getSpeed().getX(), -helicopter2.getSpeed().getY());
			helicopter2.flipHelicopter();
			aircraft.setSpeed(-aircraft.getSpeed().getX(), -aircraft.getSpeed().getY());
			aircraft.flipHelicopter();
		}

		//@helicopter1 check if hits @helicopter2, then respond appropiate
		if(helicopter1.getSpriteRect().intersect(helicopter2.getSpriteRect()) || helicopter2.getSpriteRect().intersect(helicopter1.getSpriteRect())){
			helicopter1.setSpeed(-helicopter1.getSpeed().getX(), -helicopter1.getSpeed().getY());
			helicopter1.flipHelicopter();
			helicopter2.setSpeed(-helicopter2.getSpeed().getX(), -helicopter2.getSpeed().getY());
			helicopter2.flipHelicopter();
		}

		//@helicopter2 check if hits @aircraft, then respond appropiate
		if(helicopter1.getSpriteRect().intersect(aircraft.getSpriteRect()) || aircraft.getSpriteRect().intersect(helicopter1.getSpriteRect())){
			helicopter1.setSpeed(-helicopter1.getSpeed().getX(), -helicopter1.getSpeed().getY());
			helicopter1.flipHelicopter();
			aircraft.setSpeed(-aircraft.getSpeed().getX(), -aircraft.getSpeed().getY());
			aircraft.flipHelicopter();
		}

	}


}