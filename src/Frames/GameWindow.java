package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    static public final int WINDOW_HEIGHT = 700;
    static public final int WINDOW_WIDTH = 700;
    static public final int WINDOW_POS_Y = 25;
    static public final int WINDOW_POS_X = 100;
    static public final String WINDOW_NAME = "Игра в крестики-нолики";
    public GameWindow(){
        setBounds(WINDOW_POS_X,WINDOW_POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(WINDOW_NAME);
        setResizable(false);
//        GameMap gameMap = new GameMap();
        GameSettings gameSettings = new GameSettings(this);
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JPanel controlPanel = new JPanel(new GridLayout(1,2));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameSettings.setVisible(true);
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        add(controlPanel,BorderLayout.PAGE_END);
        setVisible(true);
    }
    void startNewGame(int mode, int size_x, int size_y, int win_len){
        System.out.println("Получены настройки.");
    };
}
