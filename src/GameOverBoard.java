import javax.swing.*;
import java.awt.*;

public class GameOverBoard extends JPanel implements IBoard {
    Font fn = new Font("Tahoma", Font.BOLD, 30);
    protected JLabel label = new JLabel("GAME OVER");
    protected JButton newGame = new JButton("Play again?");
    protected JButton lvl = new JButton("Change degree of difficulty");

    public GameOverBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(null);
        setBackground(Color.BLACK);

        label.setFont(fn);
        label.setForeground(Color.RED);
        label.setBounds(150,100,300,50);
        lvl.setBounds(100,350,300,50);
        newGame.setBounds(100, 400, 300, 50);
        setVisible(true);

        newGame.addActionListener(e -> guiHandler.changeToGameBoard());
        lvl.addActionListener(e -> guiHandler.changeToStartBoard());

        add(newGame);
        add(lvl);
        add(label);
    }
}
