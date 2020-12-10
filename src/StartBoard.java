import javax.swing.*;
import java.awt.*;

public class StartBoard extends JPanel implements IBoard {
    protected JButton newGame = new JButton("New Game");

    public StartBoard(GuiHandler guiHandler) {
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
