import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiHandler extends JFrame {

    protected JPanel mainPanel = new JPanel(new BorderLayout());
    protected BoardFactory bf = new BoardFactory();
    protected int totalScore; // ny
    protected List<Integer> highScoreList = new ArrayList<>(); // ny

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

    public void changeToStartBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.START));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}

