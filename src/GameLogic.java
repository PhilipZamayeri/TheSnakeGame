import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {

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
    String appleBit = "\u25C9";

    public GameLogic(GameBoard gameBoard, GuiHandler guiHandler) {
        this.gameBoard = gameBoard;
        this.guiHandler = guiHandler;
    }

    public void move(char direction, Layout position) {
        if (direction == 'U') position.row--;
        if (direction == 'D') position.row++;
        if (direction == 'L') position.column--;
        if (direction == 'R') position.column++;
    }

    public void markStartPosition(JLabel[][] labels) {
        for (Layout layout : snake) {
            var row = layout.row;
            var column = layout.column;
            labels[row][column].setBackground(Color.GREEN);
        }
        direction = 'R';
    }

    public void shuffleApplePosition(JLabel[][] labels, int rows, int columns) {
        labels[apple.row][apple.column].setText("");
        int row;
        int col;
        Random random = new Random();
        do {
            row = random.nextInt(rows);
            col = random.nextInt(columns);
        } while (labels[row][col].getBackground() == Color.GREEN);
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
            labels[snake.get(0).row][snake.get(0).column].setBackground(Color.BLACK);
            labels[apple.row][apple.column].setText(appleBit);
            snake.remove(0);
        }

        try {
            if (labels[position.row][position.column].getBackground() == Color.GREEN) {
                System.out.println("Game over! \nYour Score: " + score);
                gameBoard.reset();
                guiHandler.changeToGameOverBoard();
            }
            labels[position.row][position.column].setBackground(Color.GREEN);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Game over! \nYour Score: " + score);
            gameBoard.reset();
            guiHandler.changeToGameOverBoard();
        }
    }

    public void addPoint() {
        score += point;
        showScore.setText("Score: " + score);
    }
}
