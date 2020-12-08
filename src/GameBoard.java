import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Philip Zamayeri
 * Date: 2020-12-08
 * Time: 09:40
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class GameBoard extends JPanel /*implements IBoard*/ {
    JPanel scorePanel = new JPanel();
    JLabel scoreTxt = new JLabel("Score: ");
    Layout position;

    int rows = 15;
    int columns = 15;
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

//    @Override
//    public void board(GuiHandler guiHandler) {
//        basePanel.setLayout(new GridLayout(rows, columns));
//        setLayout(new BorderLayout());
//        add(scorePanel, BorderLayout.NORTH);
//        add(basePanel, BorderLayout.CENTER);
//        scorePanel.add(scoreTxt);
//    }

    public void move(char direction, Layout position) {
        if (direction == 'N') position.row--;
        if (direction == 'S') position.row++;
        if (direction == 'W') position.column--;
        if (direction == 'E') position.column++;
    }

    public void addLabels() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                labels[i][j].setBorder(new EtchedBorder()); // om detta används sätt fontsize till 20
                labels[i][j].setMinimumSize(new Dimension(unitSize, unitSize));
                labels[i][j].setMaximumSize(new Dimension(unitSize, unitSize));
                labels[i][j].setPreferredSize(new Dimension(unitSize, unitSize));
                labels[i][j].setFont(new Font("Verdana", Font.BOLD, 20)); // sätt till 20 om border nyttjas
                basePanel.add(labels[i][j]);
            }
        }
    }

    public void createSnake() {
        for (int i = 0; i < lengthOfSnake; i++) {
            snake.add(new Layout(0, i));
            if (i == lengthOfSnake - 1) position = new Layout(snake.get(i));
        }
    }
    public void markStartPosition(){
        for (int i = 0; i < snake.size() ; i++) {
            var row = snake.get(i).row;
            var column = snake.get(i).column;
            labels[row][column].setText(bodyPart);

        }
        direction = 'E';

    }

    public void shuffleApplePosition() {
        labels[apple.row][apple.column].setForeground(null);
        int row;
        int col;
        Random random = new Random();
        while (true) {
            row = random.nextInt(rows);
            col = random.nextInt(columns);
            if (position.row != row || position.column != col) break;
        }
        apple.newPos(row, col);
        labels[apple.row][apple.column].setText(appleBit);
        labels[apple.row][apple.column].setForeground(Color.red);
    }

    public void changeDirection(int keyStroke) {
        if(moved) {
            if (keyStroke == KeyEvent.VK_UP && direction != 'S') direction = 'N';
            else if (keyStroke == KeyEvent.VK_DOWN && direction != 'N') direction = 'S';
            else if (keyStroke == KeyEvent.VK_LEFT && direction != 'E') direction = 'W';
            else if (keyStroke == KeyEvent.VK_RIGHT && direction != 'W') direction = 'E';
        }
        moved = false;
        if (!moved) {
//            gameOver();
        }
    }

    public void updateSnake() {
        if (position.isEqualsTo(apple)) {
            haveEaten = true;
            addPoint();
            shuffleApplePosition();
        } else haveEaten = false;

        snake.add(new Layout(position));

        if (!haveEaten) {
            labels[snake.get(0).row][snake.get(0).column].setText("");
            labels[apple.row][apple.column].setText(appleBit);
            snake.remove(0);
        }

        try {
            if(labels[position.row][position.column].getText().equals(bodyPart)){
                JOptionPane.showMessageDialog(null,"Nu gick du visst in i dig själv.\nDin Score: "+score);
                reset();
            }
            labels[position.row][position.column].setText(bodyPart);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,"Nu gick du visst utanför banan.\nDin Score: "+score);
            reset();
        }
    }

    public void addPoint(){
        score += point;
        showScore.setText("Score: " + score);
    }

    public void reset(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j].setText("");
            }
        }
        snake.clear();
        createSnake();
        markStartPosition();
        shuffleApplePosition();
        timer.stop();
        score = -1;
        addPoint();
    }

}
