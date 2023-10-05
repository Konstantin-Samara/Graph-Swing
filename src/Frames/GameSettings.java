package Frames;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettings extends JFrame{
    boolean mode = true;

    GameSettings(GameWindow gameWindow) {
        setBackground(Color.GREEN);
        int del_X = gameWindow.getWidth()/5;
        int del_Y = gameWindow.getHeight()/4;
        int WINDOW_HEIGHT = gameWindow.getHeight()-del_Y;
        int WINDOW_WIDTH = gameWindow.getWidth()-del_X;
        int WINDOW_POS_X = gameWindow.getLocation().x+del_X/2;
        int WINDOW_POS_Y = gameWindow.getLocation().y+del_X/2;
        String WINDOW_NAME = "Игровые установки";
        setBounds(WINDOW_POS_X,WINDOW_POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(WINDOW_NAME);
        setResizable(false);
            JLabel labelMode = new JLabel("Выберите режим игры");
                JRadioButton humanVShumanButton = new JRadioButton("Игрок против Игрока",true);
                JRadioButton humanVScpuButton = new JRadioButton("Игрок против Машины",false);
            ButtonGroup modeButtonGroup = new ButtonGroup();
                modeButtonGroup.add(humanVShumanButton);
                modeButtonGroup.add(humanVScpuButton);
        JPanel topPanel = new JPanel(new GridLayout(2,1));
            topPanel.add(labelMode);
            JPanel topButtonsPanel = new JPanel(new GridLayout(2,1));
            topButtonsPanel.add(humanVShumanButton);
            topButtonsPanel.add(humanVScpuButton);
            topPanel.add(topButtonsPanel);
        JPanel middlePanel = new JPanel(new GridLayout(2,1));
            JLabel labelSize = new JLabel("Установите размер поля : ");
            JSlider sliderSize = new JSlider(3,10,3);
            middlePanel.add(labelSize);
            middlePanel.add(sliderSize);
            add(middlePanel);
        JPanel downPanel = new JPanel(new GridLayout(2,1));
            JLabel labelWin = new JLabel("Установите длину для победы : ");
            JSlider sliderWin = new JSlider(3,10,3);
            downPanel.add(labelWin);
            downPanel.add(sliderWin);
        JPanel bottomPanel = new JPanel(new GridLayout(1,1));
            JButton buttonStart = new JButton("Начать");
            bottomPanel.add(buttonStart);
        JPanel mainPanel = new JPanel(new GridLayout(4,1));
            mainPanel.add(topPanel);
            mainPanel.add(middlePanel);
            mainPanel.add(downPanel);
            mainPanel.add(bottomPanel);
        add(mainPanel);
        sliderSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelSize.setText("Установите размер поля : "+sliderSize.getValue());
            }
        });
        sliderWin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWin.setText("Установите длину для победы : "+sliderWin.getValue());
            }
        });
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (humanVScpuButton.isSelected()) {
                    mode = false;
                    setTitle("Игрок против Машины");
                }
                else {setTitle("Игрок против Игрока");
                }
                mainPanel.setVisible(false);
                JButton chatButton = new JButton("Чат");
                add(new GameMap(mode, sliderWin.getValue(), sliderSize.getValue(),getLocation().x,
                        getLocation().y, WINDOW_HEIGHT, WINDOW_WIDTH),BorderLayout.CENTER);
                add(chatButton,BorderLayout.PAGE_END);
                chatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Chat(mode, getLocation().x, WINDOW_WIDTH);
                    }
                });
            }

        });


    }
}
