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
//    Layout position;

    int rows = 15;
    int columns = 15;
    int unitSize = 25;
//    List<Layout> snake = new ArrayList<>();
    JLabel[][] labels = new JLabel[rows][columns];

    javax.swing.Timer timer; // Ambition att byta till Timer
    //Timer timer;

    static final String VK_LEFT = "VK_LEFT";
    static final String VK_RIGHT = "VK_RIGHT";
    static final String VK_DOWN = "VK_DOWN";
    static final String VK_UP = "VK_UP";

    private GuiHandler guiHandler;

    public GameBoard(GuiHandler guiHandler) {
        this.guiHandler = guiHandler;
        addLabels();
        board(guiHandler);
        gl.snake.clear();
        gl.createSnake();
        gl.markStartPosition(labels);
        gl.shuffleApplePosition(labels, rows, columns);
        setKeyBindings();

        ActionListener time = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(gl.direction,gl.position);
                gl.moved = true;
                gl.updateSnake(labels, rows, columns);
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

    public void move(char direction, Layout position) {
        if (direction == 'U') position.row--;
        if (direction == 'D') position.row++;
        if (direction == 'L') position.column--;
        if (direction == 'R') position.column++;
    }

    public void addLabels() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                labels[i][j].setBorder(new EtchedBorder()); // om detta används sätt fontsize till 20
                labels[i][j].setMinimumSize(new Dimension(unitSize, unitSize));
                labels[i][j].setMaximumSize(new Dimension(unitSize, unitSize));
                labels[i][j].setPreferredSize(new Dimension(unitSize, unitSize));
                labels[i][j].setFont(new Font("Andale Mono", Font.BOLD, 20)); // sätt till 20 om border nyttjas
                basePanel.add(labels[i][j]);
            }
        }
    }


    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j].setText("");
            }
        }
        gl.snake.clear();
        gl.createSnake();
        gl.markStartPosition(labels);
        gl.shuffleApplePosition(labels,rows,columns);
        timer.stop();
        gl.score = -1;
        gl.addPoint();
    }
    private class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvt) {
            switch (actionEvt.getActionCommand()) {
                case VK_LEFT:
                    if (gl.direction != 'R') {
                        gl.direction = 'L';
                    }
                    break;
                case VK_RIGHT:
                    if (gl.direction != 'L') {
                        gl.direction = 'R';
                    }
                    break;
                case VK_UP:
                    if (gl.direction != 'D') {
                        gl.direction = 'U';
                    }
                    break;
                case VK_DOWN:
                    if (gl.direction != 'U') {
                        gl.direction = 'D';
                    }
                    break;
            }
            System.out.println(actionEvt.getActionCommand() + " pressed");
        }
    }

}
