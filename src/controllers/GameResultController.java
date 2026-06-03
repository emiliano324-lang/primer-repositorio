package controllers;

import utils.ScreenManager;
import views.GameResultView;

public class GameResultController {
	
	GameResultView view;
	
	public GameResultController(GameResultView view) {
		this.view = view;
		
		registerListeners();
	}

	public void registerListeners() {
		view.getBtnExit().addActionListener(e -> ScreenManager.showPanel("MENU"));
	}
	
}
