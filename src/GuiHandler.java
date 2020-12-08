import javax.swing.*;
import java.awt.*;

 public class GuiHandler extends JFrame {
        JPanel mainPanel = new JPanel(new BorderLayout());

        public GuiHandler(){
//            mainPanel.add(new StartBoard(this));
            mainPanel.setSize(500,500);
            add(mainPanel);
            setTitle("Snake");
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setSize(500,500);
        }

        public void changeToGameBoard(){
            mainPanel.removeAll();
//            mainPanel.add(new GameBoard(this));
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void changeToGameOverBoard(){
            mainPanel.removeAll();
 //           mainPanel.add(new GameOverBoard(this));
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        public void changeToStartBoard(){
            mainPanel.removeAll();
//            mainPanel.add(new StartBoard(this));
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}
