package components.pages;

import javax.swing.*;
import java.awt.*;

public class PageInventoryPanel {

    public static JPanel createInventoryPage() {
        JPanel pan = new JPanel();

        pan.add(new JLabel("coucou"));
        pan.setBackground(Color.MAGENTA);

        return pan;
    }
}
