package Frames;

import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel {
    private boolean mode;
    private int win_size;
    private int size;
    private int x;
    private int y;
    private int height;
    private int width;
    GameMap(boolean mode, int win_size, int size, int x, int y, int height, int width) {
        this.mode = mode;
        this.win_size = win_size;
        this.size = size;
        this.x = x;
        this.y = y;
        this.height = height-60;
        this.width = width-10;
        setBounds(x, y, this.width, this.height);
        this.setBackground(Color.GREEN);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    private void render(Graphics g) {
        int cellHeight = height / size;
        int cellWidth = width / size;
        g.setColor(Color.BLACK);
        for (int h = 0; h < size; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int w = 0; w < size; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, height);
        }
    }


}