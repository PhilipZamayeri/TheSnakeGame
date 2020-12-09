import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class GameBoard extends JPanel implements IBoard {
    GameLogic gl = new GameLogic();
    JPanel scorePanel = new JPanel();
    JPanel basePanel = new JPanel();
    JLabel scoreTxt = new JLabel("Score: ");
    Layout position;

    int rows = 15;
    int columns = 15;
    int unitSize = 25;
    List<Layout> snake = new ArrayList<>();
    JLabel[][] labels = new JLabel[rows][columns];

    Layout apple = new Layout(0, 0);
    int lengthOfSnake = 3;
    Boolean haveEaten = false;
    char direction;
    boolean moved = true;
    int point = 1;
    int score = 0;

    JLabel showScore = new JLabel("Score: " + score);
    String bodyPart = "\u2584";
    String appleBit = "\u2058";

    javax.swing.Timer timer; // Ambition att byta till Timer
    //Timer timer;

    static final String VK_LEFT = "VK_LEFT";
    static final String VK_RIGHT = "VK_RIGHT";
    static final String VK_DOWN = "VK_DOWN";
    static final String VK_UP = "VK_UP";

    private GuiHandler guiHandler;

    GameBoard(){}

//    public GameBoard(GuiHandler guiHandler){}

    public GameBoard(GuiHandler guiHandler) {
        this.guiHandler = guiHandler;
        gl.addLabels(basePanel, labels, rows, columns, unitSize);
        board(guiHandler);
        snake.clear();
        gl.createSnake(lengthOfSnake, snake, position);
        markStartPosition();
        gl.shuffleApplePosition(labels, apple, position, appleBit, rows, columns);
        setKeyBindings();

        ActionListener time = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gl.move(direction, position);
                moved = true;
                updateSnake();
            }
        };
        timer = new javax.swing.Timer(100, time);
        timer.start();
    }



    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), VK_UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), VK_DOWN);

        actionMap.put(VK_LEFT, new KeyAction(VK_LEFT));
        actionMap.put(VK_RIGHT, new KeyAction(VK_RIGHT));
        actionMap.put(VK_UP, new KeyAction(VK_UP));
        actionMap.put(VK_DOWN, new KeyAction(VK_DOWN));
    }

    @Override
    public void board(GuiHandler guiHandler) {
        basePanel.setLayout(new GridLayout(rows, columns));
        setLayout(new BorderLayout());
        add(scorePanel, BorderLayout.NORTH);
        add(basePanel, BorderLayout.CENTER);
        //basePanel.setBackground(Color.black);
        scorePanel.add(scoreTxt);
    }


//    public void createSnake() {
//        for (int i = 0; i < lengthOfSnake; i++) {
//            snake.add(new Layout(0, i));
//            if (i == lengthOfSnake - 1) position = new Layout(snake.get(i));
//        }
//    }

    public void markStartPosition() {
        for (int i = 0; i < snake.size(); i++) {
            var row = snake.get(i).row;
            var column = snake.get(i).column;
            labels[row][column].setText(bodyPart);
        }
        direction = 'R';
    }


    public void updateSnake() {
        if (position.isEqualsTo(apple)) {
            haveEaten = true;
            addPoint();
            gl.shuffleApplePosition(labels, apple, position, appleBit, rows, columns);
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
                reset();
                guiHandler.changeToGameOverBoard();
            }
            labels[position.row][position.column].setText(bodyPart);
        } catch (IndexOutOfBoundsException e) { //går in i väggen
            JOptionPane.showMessageDialog(null, "Game over! \nYour Score: " + score);
            reset();
            guiHandler.changeToGameOverBoard();
        }
    }

    public void addPoint() {
        score += point;
        showScore.setText("Score: " + score);
    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j].setText("");
            }
        }
        snake.clear();
        gl.createSnake(lengthOfSnake, snake, position);
        markStartPosition();
        gl.shuffleApplePosition(labels, apple, position, appleBit, rows, columns);
        timer.stop();
        score = -1;
        addPoint();
    }

    private class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            switch (actionEvt.getActionCommand()) {
                case VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
            System.out.println(actionEvt.getActionCommand() + " pressed");
        }
    }

}
