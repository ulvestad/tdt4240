package com.example.oving1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import sheep.game.Sprite;

public class Helicopter extends Sprite {

	//Declaring some useful variables
	private Bitmap bitmap;
	private Rect sourceRect;
	private int spriteWidth;    //width of the sprite, need for calulation to cut out frame
	private int spriteHeight;   //height of the sprite, same as for width
	private float x;            //X(top left) coordinate of the sprite/object
	private float y;			//Y(top left) coordinate of the sprite/object
	private int frameNr;        //number of frames is the animation of the sprite
	private int currentFrame;   //variable for keeping track of current frame
	private long frameTicker;   //the time of the last frame update
	private int framePeriod;    //milliseconds between each frame (1000/fps), as speciied for the task 100ms


	//constrctor for Helicopter
	public Helicopter(Bitmap bitmap, float x, float y, int fps, int frameCount){
		currentFrame = 0;
		frameNr = frameCount;
		spriteWidth = bitmap.getWidth()/frameCount;
		spriteHeight = bitmap.getHeight();
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		framePeriod = 1000/fps;
		frameTicker = 0l;
		flipHelicopter();
	}


	//draw the sprites on the canvas
	@Override
	public void draw(Canvas canvas) {
		setX(getX() + getSpeed().getX()/100);
		setY(getY() + getSpeed().getY()/100);

		Rect destRect = new Rect((int)x, (int)y, (int)x + spriteWidth, (int)y + spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
	}

	//update canvs
	public void update(long gameTime) {
	    if (gameTime > frameTicker + framePeriod) {
	    	frameTicker = gameTime;
	    	currentFrame++; //inrease the frame
	    	if (currentFrame >= frameNr) {
	    		currentFrame = 0;
	    	}
	    }
	    //define the rectangle to cut out sprite
	    this.sourceRect.left = currentFrame * spriteWidth;
	    this.sourceRect.right = this.sourceRect.left + spriteWidth;

	}



	//getter and setter methods for different variables needed
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public int getHelicopterHeight(){
		return spriteHeight;
	}
	public int getHelicopterWidth(){
		return spriteWidth;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	public Rect getSpriteRect(){
		return new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
	}


	//method for flipping the sprite using Matrix
	@SuppressWarnings("deprecation")
	public void flipHelicopter(){
		Matrix mirrorMatrix = new Matrix();
		mirrorMatrix.preScale(-1, 1);
		Bitmap turnMap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mirrorMatrix, false);
		turnMap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
		bitmap = new BitmapDrawable(turnMap).getBitmap();

	}
}