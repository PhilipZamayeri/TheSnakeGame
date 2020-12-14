import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GameOverBoard extends JPanel implements IBoard {

    protected JPanel scorePanel = new JPanel();
    protected JLabel scoreLabel = new JLabel();
    protected JPanel highScorePanel = new JPanel();
    protected JLabel highScoreLabelFirst = new JLabel();
    protected JLabel highScoreLabelSecond = new JLabel();
    protected JLabel highScoreLabelThird = new JLabel();
    protected JPanel buttonPanel = new JPanel();
    protected JButton newGame = new JButton("Play again?");
    protected int totalScore; // ny
    protected List<Integer> highScores; // ny

    public GameOverBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(new GridLayout(3,0));

        setBackground(Color.WHITE);

        scoreLabel.setBounds(100, 200, 300, 50);
        newGame.setBounds(100, 200, 300, 50);
        setVisible(true);
        totalScore = guiHandler.getTotalScore();
        scoreLabel.setText("GAME OVER!\n Your score: " + totalScore);
        highScores = guiHandler.highScoreList; // ny
        printHighScore();
        newGame.addActionListener(e -> guiHandler.changeToGameBoard());

        scorePanel.add(scoreLabel);
        add(scorePanel, CENTER_ALIGNMENT);
        highScorePanel.setLayout(new GridLayout(3,0));
        highScorePanel.add(highScoreLabelFirst);
        highScorePanel.add(highScoreLabelSecond);
        highScorePanel.add(highScoreLabelThird);
        add(highScorePanel, CENTER_ALIGNMENT);
        buttonPanel.add(newGame);
        add(buttonPanel, CENTER_ALIGNMENT);
        printList(); // kan tas bort. Enbart för testning


    }

    // ny
    public void printHighScore() {
        highScores.sort(Collections.reverseOrder());
        highScoreLabelFirst.setText("1st place: " + highScores.get(0));
        if (highScores.size() > 1) {
            highScoreLabelSecond.setText("2nd place: " + highScores.get(1));
        }
        if (highScores.size() > 2) {
            highScoreLabelThird.setText("3rd place: " + highScores.get(2));
        }
    }

    public void printList() {
        System.out.println(highScores);
    }

}
