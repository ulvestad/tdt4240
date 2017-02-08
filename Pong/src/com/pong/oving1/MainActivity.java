package com.pong.oving1;

import sheep.game.Game;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);

         //create a new game using sheep documation
         Game game = new Game(this, null);
         //push gamestate onto gamestack
         game.pushState(new GameStart());
         //set the activity to the view
         setContentView(game);
    }


}
