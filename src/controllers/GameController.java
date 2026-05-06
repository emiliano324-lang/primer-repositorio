package controllers;

import views.GameView;

public class GameController {

	private GameView view;

	public GameView getView(GameView view) {
		return view;
	}
	
	public GameController(GameView view) {
		this.view = view;
		
	}
	
	
}
