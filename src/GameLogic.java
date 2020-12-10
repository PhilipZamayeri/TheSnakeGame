import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
//    List<Layout> snake = new ArrayList<>();
//    int lengthOfSnake = 3;
//    GameBoard gb = new GameBoard();
    Layout position;

    protected GameBoard gameBoard;
    protected GuiHandler guiHandler;
    protected Layout position;
    protected List<Layout> snake = new ArrayList<>();
    protected Layout apple = new Layout(0, 0);
    protected int lengthOfSnake = 3;
    protected Boolean haveEaten = false;
    protected char direction;
    protected boolean moved = true;
    protected int point = 1;
    protected int score = 0;

    protected JLabel showScore = new JLabel("Score: " + score);
    protected String bodyPart = '\u2584' + "";
    protected String appleBit = '\u2058' + "";

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
        for (int i = 0; i < snake.size(); i++) {
            var row = snake.get(i).row;
            var column = snake.get(i).column;
            labels[row][column].setText(bodyPart);
        }
        direction = 'R';
    }

    public void shuffleApplePosition(JLabel[][] labels, int rows, int columns) {
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
//    public void createSnake(int lengthOfSnake, List<Layout> snake, Layout position) {
//        for (int i = 0; i < lengthOfSnake; i++) {
//            snake.add(new Layout(0, i));
//            if (i == lengthOfSnake - 1) position = new Layout(snake.get(i));
//        }
//    }

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

        try {
            if (labels[position.row][position.column].getText().equals(bodyPart)) {
                System.out.println("Game over! \nYour Score: " + score);
                gameBoard.reset();
                guiHandler.changeToGameOverBoard();
            }
            labels[position.row][position.column].setText(bodyPart);
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
