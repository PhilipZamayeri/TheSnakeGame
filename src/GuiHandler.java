import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuiHandler extends JFrame {

    protected JPanel mainPanel = new JPanel(new BorderLayout());
    protected BoardFactory bf = new BoardFactory();
    protected int totalScore; // ny
//    protected List<Integer> highScoreList = new ArrayList<>(); // ny
    IOHandler ioHandler = new IOHandler();

    public GuiHandler() {
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.START));
        mainPanel.setSize(500, 500);
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(500, 500);
        add(mainPanel);
        setVisible(true);

    }

    public void changeToGameBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.GAME));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void changeToGameOverBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.GAME_OVER));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void changeToStartBoard() {
        mainPanel.removeAll();
        mainPanel.add(bf.createBoard(this, BoardFactory.Phase.START));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

//    public List<Integer> readScoreFromFile() {
//        String fileName = "src\\resources\\highscore.txt";
//        highScoreList.clear();
//
//        try (Scanner scanner = new Scanner(new File(fileName));) {
//
//            while(scanner.hasNext()) {
//                int number = scanner.nextInt();
//                highScoreList.add(number);
//            }
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        return highScoreList;
//    }
//
//    public void writeScoreToFile(int score) {
//        String fileName = "src\\resources\\highscore.txt";
//
//        try (PrintWriter out = new PrintWriter(new FileWriter(fileName, true));) {
//
//            out.println(score);
//
//        } catch(IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}

