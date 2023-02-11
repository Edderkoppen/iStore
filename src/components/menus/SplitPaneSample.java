package components.menus;

import javax.swing.*;
import java.awt.*;

public class SplitPaneSample extends JSplitPane {

    /**
     * Permet de créer un panneau séparé en deux.
     *
     * @param tree      Le contenu du panneau de gauche (une arbre demandé).
     * @param pan       Le contenu du paneau de droite.
     * @param screenW   La largeur totale de la fenetre.
     *
     * @return le panneau séparé.
     */
    public static JSplitPane createSplitPane(JTree tree, JPanel pan, int screenW) {

        JScrollPane leftPane = new JScrollPane(tree);
        JScrollPane rightPane = new JScrollPane(pan);

        leftPane.setPreferredSize(new Dimension((int) (screenW*0.40), 0));

        return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
    }
}
