package game;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    private final int width=1600;
    private final int height = 800;
    public Application()
    {
        add(new Board());
        setSize(width,height);
        setTitle("SmackIt");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }

}
