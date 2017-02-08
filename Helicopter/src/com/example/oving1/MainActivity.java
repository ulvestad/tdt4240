package com.example.oving1;

import android.os.Bundle;
import android.app.Activity;
import sheep.game.*;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create a new game using sheep documation
        Game game = new Game(this, null);
        //push gamestate onto gamestack
        game.pushState(new GameState(game.getResources()));
        //set the activity to the view
        setContentView(game);

    }

}
