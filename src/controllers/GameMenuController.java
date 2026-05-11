package controllers;

import views.GameMenuView;
import views.GameWindow;

public class GameMenuController {

    private GameMenuView view;
    private GameWindow window;

    public GameMenuController(GameMenuView view, GameWindow window) {

        this.view = view;
        this.window = window;

        gameMenuListener();
    }

    private void gameMenuListener() {

        view.getBtnPlay().addActionListener(e -> {

            window.showPanel("GAME");

        });
    }
}
