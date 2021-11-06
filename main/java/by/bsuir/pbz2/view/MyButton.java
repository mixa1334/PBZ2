package laba2Package.Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MyButton extends JButton {
    public MyButton(File pathToFile) {
        this();
        setIcon(new ImageIcon(pathToFile.getAbsolutePath()));
        setContentAreaFilled(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setContentAreaFilled(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setContentAreaFilled(false);
            }
        });
    }

    public MyButton(String text) {
        this();
        setText(text);
    }

    public MyButton() {
        setBorderPainted(false);
        setFocusable(false);
        setBackground(Color.LIGHT_GRAY);
    }
}
