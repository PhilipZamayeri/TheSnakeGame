import javax.swing.*;
import java.awt.*;

/**
 * Created by Philip Zamayeri
 * Date: 2020-12-08
 * Time: 09:40
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class GameOverBoard extends JPanel implements IBoard {

    JButton newGame = new JButton("Play again?");

    public GameOverBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(null);
        add(newGame);
        setBackground(Color.gray);
        setSize(500,500);

        newGame.setBounds(50, 150, 300, 50);
        newGame.setBackground(Color.BLACK);
        newGame.setOpaque(true);

        setVisible(true);
        setSize(400, 500);

        newGame.addActionListener(e -> {
            guiHandler.changeToStartBoard();
        });
    }
}
