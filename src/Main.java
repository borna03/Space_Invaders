import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("SpaceInvaders");

        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}