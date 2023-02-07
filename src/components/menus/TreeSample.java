package components.menus;

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

    public static JTree createTree(String nameStore) {
        DefaultMutableTreeNode store = new DefaultMutableTreeNode("Magasins");
        ArrayList<String> storeInfos = DatabaseConnexion.getStoreInfos(nameStore);
        ArrayList<String> storeName = DatabaseConnexion.getStoreName();

        DefaultMutableTreeNode storeNameTree = null;

        for (String name : storeInfos) {
            if(storeName.contains(name)) {
                storeNameTree = new DefaultMutableTreeNode(name);
                store.add(storeNameTree);

            } else if (storeNameTree != null){
                storeNameTree.add(new DefaultMutableTreeNode(name));
            }
        }

        JTree test = new JTree(store);
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = test.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = test.getPathForLocation(e.getX(), e.getY());
                if(selRow == 2) {
                    if(e.getClickCount() == 2) {
                        Object lastNode = selPath.getLastPathComponent();
                        String stringLastNode = valueOf(lastNode);
                        if(stringLastNode.matches("[a-z-A-Z]{1,30}\\s[a-z-A-Z]{1,30}")) {
                            System.out.println(stringLastNode);
                        }

                    }
                }
            }
        };

        test.addMouseListener(ml);
//        test.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                if(e.getClickCount() == 2) {
//                    System.out.println("test ?");
//                }
//
//            }
//        });
//        return new JTree(store);
        return test;
    }
}
