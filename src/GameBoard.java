import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel implements IBoard {

    protected GameLogic gl;
    protected JPanel scorePanel = new JPanel();
    protected JPanel basePanel = new JPanel();

    protected int rows = 15;
    protected int columns = 15;
    protected int unitSize = 25;
    protected JLabel[][] labels = new JLabel[rows][columns];

    protected Timer timer;
    protected int speed = 120;

    static final String VK_LEFT = "VK_LEFT";
    static final String VK_RIGHT = "VK_RIGHT";
    static final String VK_DOWN = "VK_DOWN";
    static final String VK_UP = "VK_UP";

    //nya keycommands
    static final String VK_SPACE = "VK_SPACE";
    static final String VK_ENTER = "VK_ENTER";


    public GameBoard(GuiHandler guiHandler) {
        gl = new GameLogic(this, guiHandler);
        addLabels();
        board(guiHandler);
        gl.snake.clear();
//        gl.speed();
        createSnake();
        gl.markStartPosition(labels);
        gl.shuffleApplePosition(labels, rows, columns);
        setKeyBindings();

        ActionListener time = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gl.move(gl.direction, gl.position);
                gl.moved = true;
                gl.updateSnake(labels, rows, columns);
//                repaint();
            }
        };
        timer = new Timer(speed, time);
        timer.start();
//            if (gl.score % 2 == 0) {
//                speed -= 4;
//                System.out.println(speed);
//        }
        gl.speed(speed, time);
////        System.out.println(speed);
    }

    private void setKeyBindings() {
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), VK_LEFT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), VK_RIGHT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), VK_UP);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), VK_DOWN);

        //Testa paus
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0),VK_SPACE);

        actionMap.put(VK_LEFT, new KeyAction(VK_LEFT));
        actionMap.put(VK_RIGHT, new KeyAction(VK_RIGHT));
        actionMap.put(VK_UP, new KeyAction(VK_UP));
        actionMap.put(VK_DOWN, new KeyAction(VK_DOWN));

        //Testa paus
        actionMap.put(VK_SPACE, new KeyAction(VK_SPACE));

    }

    @Override
    public void board(GuiHandler guiHandler) {
        basePanel.setLayout(new GridLayout(rows, columns));
        setLayout(new BorderLayout());
        add(scorePanel, BorderLayout.NORTH);
        add(basePanel, BorderLayout.CENTER);
        //basePanel.setBackground(Color.black);
        scorePanel.add(gl.showScore);
    }

    public void addLabels() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                //labels[i][j].setBorder(new EtchedBorder());
                labels[i][j].setPreferredSize(new Dimension(unitSize, unitSize));
                labels[i][j].setFont(new Font("Andale Mono", Font.BOLD, 20));
                basePanel.add(labels[i][j]);
            }
        }
    }

    public void createSnake() {
        for (int i = 0; i < gl.lengthOfSnake; i++) {
            gl.snake.add(new Layout(0, i));
            if (i == gl.lengthOfSnake - 1) gl.position = new Layout(gl.snake.get(i));
        }
    }

    public void reset() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j].setText("");
            }
        }
        gl.snake.clear();
        createSnake();
        gl.markStartPosition(labels);
        gl.shuffleApplePosition(labels, rows, columns);
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