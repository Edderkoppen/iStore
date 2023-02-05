package components.menus;

import javax.swing.*;
import java.awt.*;

public class SplitPaneSample {
    public static JSplitPane createSplitPane(JTree tree, JPanel pan, int screenW) {

        JScrollPane leftPane = new JScrollPane(tree);
        JScrollPane rightPane = new JScrollPane(pan);

        leftPane.setPreferredSize(new Dimension((int) (screenW*0.25), 0));

        return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
    }
}
