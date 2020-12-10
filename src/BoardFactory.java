import javax.swing.*;

public class BoardFactory {

    public JPanel createBoard(GuiHandler guiHandler, Phase phase) {
        return switch (phase) {
            case START -> new StartBoard(guiHandler);
            case GAME -> new GameBoard(guiHandler);
            case GAME_OVER -> new GameOverBoard(guiHandler);
        };
    }

    enum Phase {
        START,
        GAME,
        GAME_OVER
    }
}
