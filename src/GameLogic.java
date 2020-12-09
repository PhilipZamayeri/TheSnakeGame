import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Philip Zamayeri
 * Date: 2020-12-08
 * Time: 14:17
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class GameLogic {

    public void move(char direction, Layout position) {
        if (direction == 'U') position.row--;
        if (direction == 'D') position.row++;
        if (direction == 'L') position.column--;
        if (direction == 'R') position.column++;
    }

    public void addLabels(JPanel basePanel, JLabel[][] labels, int rows, int columns, int unitSize) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                labels[i][j] = new JLabel("", SwingConstants.CENTER);
                //labels[i][j].setBorder(new EtchedBorder()); // om detta används sätt fontsize till 20
                //labels[i][j].setMinimumSize(new Dimension(unitSize, unitSize));
                //labels[i][j].setMaximumSize(new Dimension(unitSize, unitSize));
                labels[i][j].setPreferredSize(new Dimension(unitSize, unitSize));
                labels[i][j].setFont(new Font("Andale Mono", Font.BOLD, 30)); // sätt till 20 om border nyttjas
                basePanel.add(labels[i][j]);
                //labels[i][j].setForeground(Color.GREEN);
            }
        }
    }

    public void shuffleApplePosition(JLabel[][] labels,Layout apple, Layout position, String appleBit, int rows, int columns) {
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

}
