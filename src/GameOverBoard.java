import javax.swing.*;
import java.awt.*;

public class GameOverBoard extends JPanel implements IBoard {

    JButton newGame = new JButton("Play again?");

    public GameOverBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(null);
        setBackground(Color.BLACK);

        newGame.setBounds(100, 200, 300, 50);
        setVisible(true);

        newGame.addActionListener(e -> guiHandler.changeToStartBoard());
        add(newGame);
    }
}
