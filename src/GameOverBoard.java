import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Font;
import java.util.Collections;
import java.util.List;

public class GameOverBoard extends JPanel implements IBoard {

    protected JLabel scoreLabelTop = new JLabel();
    protected JLabel scoreLabelBot = new JLabel();
    protected JLabel highScoreLabelFirst = new JLabel();
    protected JLabel highScoreLabelSecond = new JLabel();
    protected JLabel highScoreLabelThird = new JLabel();
    protected JButton newGame = new JButton("Play again?");
    protected int totalScore; // ny
    protected List<Integer> highScores; // ny
    protected Font scoreFont = new Font("Monospaced", Font.BOLD, 18);
    protected Font gameOverFont = new Font("Monospaced", Font.BOLD, 28);

    public GameOverBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(null);
        setBackground(Color.BLACK);
        scoreLabelTop.setFont(gameOverFont);
        scoreLabelTop.setForeground(Color.RED);
        scoreLabelBot.setFont(gameOverFont);
        scoreLabelBot.setForeground(Color.RED);

        highScoreLabelFirst.setFont(scoreFont);
        highScoreLabelFirst.setForeground(Color.WHITE);
        highScoreLabelSecond.setFont(scoreFont);
        highScoreLabelSecond.setForeground(Color.WHITE);
        highScoreLabelThird.setFont(scoreFont);
        highScoreLabelThird.setForeground(Color.WHITE);

        highScoreLabelFirst.setAlignmentX(CENTER_ALIGNMENT);

        scoreLabelTop.setBounds(100, 50, 300, 50);
        scoreLabelBot.setBounds(100, 100, 300, 50);
        newGame.setBounds(100, 350, 300, 50);
        highScoreLabelFirst.setBounds(150, 150, 300, 50);
        highScoreLabelSecond.setBounds(150, 200, 300, 50);
        highScoreLabelThird.setBounds(150, 250, 300, 50);
        setVisible(true);
        totalScore = guiHandler.getTotalScore();
        scoreLabelTop.setText("GAME OVER!");
        scoreLabelBot.setText("Your score: " + totalScore);
        highScores = guiHandler.highScoreList; // ny
        printHighScore();
        newGame.addActionListener(e -> guiHandler.changeToGameBoard());
        add(scoreLabelTop);
        add(scoreLabelBot);
        add(highScoreLabelFirst);
        add(highScoreLabelSecond);
        add(highScoreLabelThird);
        add(newGame);

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
