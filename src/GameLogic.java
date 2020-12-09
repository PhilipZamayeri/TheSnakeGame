import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
//    int rows;
//    int columns;

    GameBoard gameBoard;
    GuiHandler guiHandler;
    Layout position;
    List<Layout> snake = new ArrayList<>();
    Layout apple = new Layout(0, 0);
    int lengthOfSnake = 3;
    Boolean haveEaten = false;
    char direction;
    boolean moved = true;
    int point = 1;
    int score = 0;

    JLabel showScore = new JLabel("Score: " + score);
    String bodyPart = '\u2584' + "";
    String appleBit = '\u2058' + "";

    public void createSnake() {
        for (int i = 0; i < lengthOfSnake; i++) {
            snake.add(new Layout(0, i));
            if (i == lengthOfSnake - 1) position = new Layout(snake.get(i));
        }
    }

    public void markStartPosition(JLabel[][]labels) {
        for (int i = 0; i < snake.size(); i++) {
            var row = snake.get(i).row;
            var column = snake.get(i).column;
            labels[row][column].setText(bodyPart);
        }
        direction = 'R';
    }

    public void shuffleApplePosition(JLabel[][] labels,int rows, int columns) {
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

    public void updateSnake(JLabel[][] labels, int rows, int columns) {
        if (position.isEqualsTo(apple)) {
            haveEaten = true;
            addPoint();
            shuffleApplePosition(labels, rows, columns);
        } else haveEaten = false;

        snake.add(new Layout(position));

        if (!haveEaten) {
            labels[snake.get(0).row][snake.get(0).column].setText("");
            labels[apple.row][apple.column].setText(appleBit);
            snake.remove(0);
        }

        try { //läggs i en egen metod istället för try+catch
            if (labels[position.row][position.column].getText().equals(bodyPart)) { //går in i sig själv
                JOptionPane.showMessageDialog(null, "Game over! \nYour Score: " + score);
                gameBoard.reset();
                guiHandler.changeToGameOverBoard();
            }
            labels[position.row][position.column].setText(bodyPart);
        } catch (IndexOutOfBoundsException e) { //går in i väggen
            JOptionPane.showMessageDialog(null, "Game over! \nYour Score: " + score);
            gameBoard.reset();
            guiHandler.changeToGameOverBoard();
        }
    }

    public void addPoint() {
        score += point;
        showScore.setText("Score: " + score);
    }
//    public void reset(JLabel[][]labels, int rows, int columns) {
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                labels[i][j].setText("");
//            }
//        }
//        snake.clear();
//        createSnake();
//        markStartPosition(labels);
//        shuffleApplePosition(labels,rows,columns);
//        gameBoard.timer.stop();
//        score = -1;
//        addPoint();
//    }
}
