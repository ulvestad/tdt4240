package com.pong.oving1;

import java.util.Observable;

public class OberservableClass extends Observable{

	private int score1;
	private int score2;

	public OberservableClass(int score1, int score2) {
		this.score1 = score1;
		this.score2 = score2;

	}

	public void setValue(int score1, int score2){
		this.score1 = score1;
		this.score2 = score2;
		setChanged();
		notifyObservers();
	}

	public int getScore1(){
		return score1;
	}
	public int getScore2(){
		return score2;
	}


}
