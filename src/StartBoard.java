import javax.swing.*;
import java.awt.*;

/**
 * Created by Philip Zamayeri
 * Date: 2020-12-08
 * Time: 09:40
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class StartBoard extends JPanel implements IBoard{
    JButton newGame = new JButton("Nytt spel");



    public StartBoard(GuiHandler guiHandler){
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(null);
        setBackground(Color.BLACK);

        newGame.setBounds(100, 200, 300, 50);
        setVisible(true);

        newGame.addActionListener(e -> guiHandler.changeToGameBoard());
        add(newGame);
    }
}
