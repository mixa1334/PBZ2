package by.bsuir.pbz2.view.dialogs;

import javax.swing.*;

public class CustomDialog extends JDialog {
    private final JFrame jFrame;

    public CustomDialog(final JFrame jFrame, final String nameOfDialog) {
        super(jFrame, nameOfDialog, true);
        this.jFrame = jFrame;
        getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setResizable(false);
    }

    public void setCustomDialogLocation() {
        setLocation(jFrame.getLocation().x + jFrame.getSize().width / 2 - getSize().width / 2,
                jFrame.getLocation().y + jFrame.getSize().height / 2 - getSize().height / 2);
    }
}