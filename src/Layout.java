/**
 * Created by Philip Zamayeri
 * Date: 2020-12-08
 * Time: 14:17
 * Project: TheSnakeGame
 * Copyright: MIT
 */
public class Layout {
    int row;
    int column;

    public Layout(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Layout(Layout layout){
        this.row = layout.row;
        this.column = layout.column;
    }

    public void newPos(int row, int column){
        this.row = row;
        this.column = column;
    }

    public boolean isEqualsTo(Layout layout){
        return layout.row == row && layout.column == column;
    }
}
