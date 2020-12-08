import javax.swing.*;
import java.awt.*;

 public class GuiHandler extends JFrame {
        JPanel mainPanel = new JPanel(new BorderLayout());

        public GuiHandler(){
            mainPanel.add(new StartBoard(this));
            mainPanel.setSize(500,500);
            setTitle("Snake");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setSize(500,500);
            add(mainPanel);
            setVisible(true);

        }

        public void changeToGameBoard(){
            mainPanel.removeAll();
            mainPanel.add(new GameBoard(this));
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void changeToGameOverBoard(){
            mainPanel.removeAll();
            mainPanel.add(new GameOverBoard(this));
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void changeToStartBoard(){
            mainPanel.removeAll();
            mainPanel.add(new StartBoard(this));
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

