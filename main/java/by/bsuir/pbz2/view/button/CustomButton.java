package by.bsuir.pbz2.view.button;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CustomButton extends JButton {
    public CustomButton(File pathToFile) {
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

    public CustomButton(String text) {
        this();
        setText(text);
    }

    public CustomButton() {
        setBorderPainted(false);
        setFocusable(false);
        setBackground(Color.LIGHT_GRAY);
    }
}
