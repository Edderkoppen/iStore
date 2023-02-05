package components.menus;

import connexion.DatabaseConnexion;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class TreeSample {
    public static JTree createTree() {
        DefaultMutableTreeNode store = new DefaultMutableTreeNode("Magasins");
        ArrayList<String> storeInfos = DatabaseConnexion.getStoreInfos();
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

        return new JTree(store);
    }
}
