package controllers;

import views.GameMenuView;
import views.GameWindow;

public class GameManuController {

    private GameMenuView view;
    private GameWindow window;

    public GameManuController(GameMenuView view, GameWindow window) {

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
