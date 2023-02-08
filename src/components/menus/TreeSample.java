package components.menus;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class TreeSample extends JTree {

    public static JTree createTree(String nameStore, JFrame frame, JPanel pan, int screenW, int screenH) {
        DefaultMutableTreeNode store = new DefaultMutableTreeNode("Magasins");
        ArrayList<String> storeInfos = DatabaseConnexion.getStoreInfos(nameStore);
        ArrayList<String> storeName = DatabaseConnexion.getStoreName();
        ArrayList<String> storeVerified = new ArrayList<>();

        DefaultMutableTreeNode storeNameTree = null;

        for (String name : storeInfos) {
            if(storeName.contains(name) && !storeVerified.contains(name)) {
                storeVerified.add(name);
                storeNameTree = new DefaultMutableTreeNode(name);
                store.add(storeNameTree);

            } else if (storeNameTree != null && !storeVerified.contains(name)){
                storeNameTree.add(new DefaultMutableTreeNode(name));
            }
        }

        JTree storeTree = new JTree(store);
        storeTree.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = storeTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = storeTree.getPathForLocation(e.getX(), e.getY());
                if(selRow > 1 && e.getClickCount() == 2) {
                    Object lastNode = selPath.getLastPathComponent();
                    String stringLastNode = valueOf(lastNode);

                    if(stringLastNode.matches("[a-z-A-Z]{1,30}\\s[a-z-A-Z]{1,30}")) {
                        String[] splitString = stringLastNode.split("\\s");
                        ArrayList<String> informations = DatabaseConnexion.getUserInfos(splitString[0], splitString[1]);
                        WindowScreen.pageEmployeeRedraw(frame, pan, screenW, screenH, informations.get(1), informations.get(2));
                    }

                } else if(selRow == 1 && e.getClickCount() == 2) {

                    Object lastNode = selPath.getLastPathComponent();
                    String stringLastNode = valueOf(lastNode);
                    WindowScreen.pageInventoryRedraw(frame, pan, screenW, screenH);

                }
            }
        });
        return storeTree;
    }
}
