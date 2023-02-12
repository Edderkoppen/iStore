package istore.components.menus;

import istore.components.screen.WindowScreen;
import istore.controller.DatabaseConnexionController;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class TreeSample extends JTree {

    /**
     * Creation d'une arborescence des magasins et des employés leur étant associés.
     *
     * @param nameStore     Nom du magasin dont le contenu doit etre affiché, tous en cas d'administrateur.
     * @param frame         Fenetre initiale.
     * @param pan           Panneau sur lequel affichier l'arbre.
     * @param screenW       Largeur totale de la fenetre.
     * @param screenH       Hauteur totale de la fenetre.
     *
     * @return une arborescence contenant un ou plusieurs magasin avec les employés associés.
     */
    public static JTree createTree(String nameStore, JFrame frame, JPanel pan, int screenW, int screenH) {
        DefaultMutableTreeNode store = new DefaultMutableTreeNode("Magasins");
        int role = DatabaseConnexionController.getRoleFromId(WindowScreen.userId);

        ArrayList<String> storeInfos = role == 1 ? DatabaseConnexionController.getAllStoreInfos() : DatabaseConnexionController.getStoreInfos(nameStore);
        ArrayList<String> storeName = DatabaseConnexionController.getStoreName();
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
            @Override
            public void mousePressed(MouseEvent e) {
                int selRow = storeTree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = storeTree.getPathForLocation(e.getX(), e.getY());
                if(selRow > 1 && e.getClickCount() == 2) {
                    Object lastNode = selPath.getLastPathComponent();
                    String stringLastNode = valueOf(lastNode);

                    if(stringLastNode.matches("[a-z-A-Z]{1,30}\\s[a-z-A-Z]{1,30}")) {
                        String[] splitString = stringLastNode.split("\\s");
                        ArrayList<String> informations = DatabaseConnexionController.getUserInfos(splitString[0], splitString[1]);

                        if(role == 2) {
                            JOptionPane.showMessageDialog(frame, "Prénom : " + informations.get(1) + "\nNom : " + informations.get(2) + "\nAdresse mail : " + informations.get(0), "Informations employé", JOptionPane.INFORMATION_MESSAGE);

                        } else if(role == 1) {
                            WindowScreen.idTmp = DatabaseConnexionController.getIdFromName(informations.get(1));
                            Object[] options = {"Ok", "Modifier"};
                            int x = JOptionPane.showOptionDialog(frame, "Prénom : " + informations.get(1) + "\nNom : " + informations.get(2) + "\nAdresse mail : " + informations.get(0), "Informations employé", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,  options[0]);

                            if(x == 1) {
                                WindowScreen.pageUpdateRedraw(frame, pan, screenW, screenH, DatabaseConnexionController.getIdFromName(informations.get(1)));
                            }
                        }
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
