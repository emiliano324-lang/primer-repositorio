package controllers;

import utils.ScreenManager;
import views.GameResultView;

public class GameResultController {
	
	private GameResultView view;
	
	public GameResultController(GameResultView view) {
		this.view = view;
		
		registerListeners();
	}

	public void registerListeners() {
		view.getBtnExit().addActionListener(e -> ScreenManager.showPanel("MENU"));
	}
	
	public GameResultView getView() {
		return view;
	}
	
}
