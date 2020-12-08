import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip Zamayeri
 * Date: 2020-12-08
 * Time: 09:40
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class GameBoard extends JPanel implements IBoard {
    JPanel scorePanel = new JPanel();
    JLabel scoreTxt = new JLabel("Score: ");
    Layout position;

    int rows = 25;
    int columns = 25;
    int unitSize = 25;
    List<Layout> snake = new ArrayList<>();
    JLabel[][] labels = new JLabel[rows][columns];

    Layout apple = new Layout(0,0);
    int lengthOfSnake = 3;
    Boolean haveEaten = false;
    char direction = 'E';
    boolean moved = true;
    int point = 1;
    int score = 0;

    JLabel showScore = new JLabel("Score: " + score);
    String bodyPart = '\u25A1'+"";
    String appleBit = '\u03CC'+"";

    javax.swing.Timer timer; // Ambition att byta till Timer
    //Timer timer;


    public GameBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        setLayout(new BorderLayout());
        add(scorePanel, BorderLayout.NORTH);
        scorePanel.add(scoreTxt);


    }
}
