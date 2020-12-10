import javax.swing.*;

/**
 * created by Mimi Santana
 * Date: 2020-12-09
 * Time: 20:27
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class BoardFactory {

    public JPanel createBoard(GuiHandler guiHandler, Phase phase) {
        if (phase == Phase.START) {
            return new StartBoard(guiHandler);
        } else if (phase == Phase.GAME) {
            return new GameBoard(guiHandler);
        } else if (phase == Phase.GAME_OVER) {
            return new GameOverBoard(guiHandler);
        } else {
            return null;
        }
    }
    enum Phase {
        START,
        GAME,
        GAME_OVER
    }
}
