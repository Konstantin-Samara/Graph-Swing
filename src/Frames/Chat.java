package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Chat extends JFrame {
    Writeable writeable;
    Chat(boolean mode, int x, int width){
        if (!mode) {setTitle("Чат с Машиной");}
        else {setTitle("Чат с Игроком");}
        x = x+width+20;
        int y = 50;
        int height = 600;
        width = 300;
        setBounds(x,y,width,height);
        JButton send1 = new JButton("Игрок1-отправить");
        JButton send2 = new JButton("Игрок2-отправить");
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
            buttonPanel.add(send1);
            if (mode){buttonPanel.add(send2);}
            add(buttonPanel,BorderLayout.PAGE_END);
            JTextField mesField = new JTextField();
            JTextArea listMes = new JTextArea();
            listMes.setEditable(false);
            JPanel mainPanel = new JPanel(new GridLayout(2,1));
            send1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listMes.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd  hh:mm:ss"))+" Игрок1 :\n  "+mesField.getText()+"\n");
                    mesField.setText("");

                    if (!mode){
                        listMes.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd  hh:mm:ss"))+" Машина :\n  Мне нечего Вам ответить и играть я тоже\n  не умею. Я вообще очень бестолковый бот(((\n");

                    }
                    save(listMes.getText(),mode);
                    System.out.println(listMes.getText());
                }
            });
            send2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listMes.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd  hh:mm:ss"))+" Игрок2 :\n  "+mesField.getText()+"\n");
                    mesField.setText("");
                    save(listMes.getText(),mode);
                    System.out.println(listMes.getText());

                }

            });

            mainPanel.add(listMes);
            mainPanel.add(mesField);
            add(mainPanel);
            listMes.setText(read(mode));

        setVisible(true);

    }
    public static void save(String str, boolean hh){
        String fileName = "hVSh.out";
        if (!hh) {fileName = "hVScpu.out";}
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(str);
            objectOutputStream.close();}
        catch(FileNotFoundException e) {System.out.println("Файл не найден.");}
        catch(IOException e) {System.out.println("Ошибка ввода-вывода данных.");}

    }
    public static String read(boolean hh){
        String str="";
        String fileName = "hVSh.out";
        if (!hh) {fileName = "hVScpu.out";}

        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream(fileName));
            str = (String) objectInputStream.readObject();
            objectInputStream.close();
            return str;}
        catch(FileNotFoundException e) {System.out.println("Файл не найден.");}
        catch(IOException e) {System.out.println("Ошибка ввода-вывода данных.");}
        catch(ClassNotFoundException e) {System.out.println("Не найден класс - ObjectInputStream.");}

        return  str;
    }
}
