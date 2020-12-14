import javax.swing.*;
import java.awt.*;

public class StartBoard extends JPanel implements IBoard {
    protected static int delay;
    protected JButton newGame = new JButton("New Game");
    protected ButtonGroup group;
    protected JRadioButton easy;
    protected JRadioButton medium;
    protected JRadioButton hard;

    public StartBoard(GuiHandler guiHandler) {
        board(guiHandler);
    }

    @Override
    public void board(GuiHandler guiHandler) {
        group = new ButtonGroup();
        easy = new JRadioButton("Easy");
        medium = new JRadioButton("Medium");
        hard = new JRadioButton("Hard");

        group.add(easy);
        group.add(medium);
        group.add(hard);

        easy.setForeground(Color.GREEN);
        medium.setForeground(Color.ORANGE);
        hard.setForeground(Color.RED);

        setLayout(null);
        setBackground(Color.BLACK);


        easy.setBounds(200,50,300,50);
        medium.setBounds(200,80,300,50);
        hard.setBounds(200,110,300,50);
        newGame.setBounds(100, 400, 300, 50);
        newGame.setVisible(false);

        easy.addActionListener(e -> easy());
        medium.addActionListener(e -> medium());
        hard.addActionListener(e -> hard());
        newGame.addActionListener(e -> guiHandler.changeToGameBoard());

        add(newGame);
        add(easy);
        add(medium);
        add(hard);

    }

    public void easy(){
        delay = 100;
        newGame.setVisible(true);
    }

    public void medium(){
        delay = 75;
        newGame.setVisible(true);
    }

    public void hard(){
        delay = 50;
        newGame.setVisible(true);
    }
}
