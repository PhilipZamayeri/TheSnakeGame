import javax.swing.*;
import java.awt.*;

public class GuiHandler extends JFrame {
    protected JPanel mainPanel = new JPanel(new BorderLayout());
    protected BoardFactory bf = new BoardFactory();

    public GuiHandler() {
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.START));
        mainPanel.setSize(500, 500);
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(500, 500);
        add(mainPanel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void changeToStartBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.START));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void changeToGameBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.GAME));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void changeToGameOverBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.GAME_OVER));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}

