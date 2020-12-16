import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOHandler {

    public List<Integer> readScoreFromFile() {
        String fileName = "src\\resources\\highscore.txt";
        List<Integer> highScoreList = new ArrayList<>();
        highScoreList.clear();

        try (Scanner scanner = new Scanner(new File(fileName));) {

            while (scanner.hasNext()) {
                int number = scanner.nextInt();
                highScoreList.add(number);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return highScoreList;
    }

    public void writeScoreToFile(int score) {
        String fileName = "src\\resources\\highscore.txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName, true));) {

            out.println(score);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
