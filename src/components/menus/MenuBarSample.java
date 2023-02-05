package components.menus;

import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBarSample extends JPanel {
    private static JFrame frame;

    public static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuUser = new JMenu("Action");
        JMenu menuStore = new JMenu("Magasin");
        JMenu test = new JMenu(DatabaseConnexion.getUserInfos().get(0));

        menuUser.setMnemonic('U');
        menuStore.setMnemonic('M');

        JMenuItem itemInfosUser = new JMenuItem("Se connecter");
        JMenuItem itemUpdateUser = new JMenuItem("S'inscrire");
        JMenuItem itemDeleteUser = new JMenuItem("Delete");
        JMenuItem itemSeeStore = new JMenuItem("Informations");

        itemInfosUser.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemInfosUser.addActionListener(event -> itemInfosUserListener(event));

        itemUpdateUser.setMnemonic('I');
        itemUpdateUser.setIcon(new ImageIcon("src/assets/icons/about.png"));;
        itemUpdateUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        itemUpdateUser.addActionListener(event -> itemUpdateListener(event));

        itemDeleteUser.setMnemonic('D');
        itemDeleteUser.addActionListener(event -> itemDeleteListener(event));

        itemSeeStore.setMnemonic('I');
        itemSeeStore.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemSeeStore.addActionListener(event -> menuStoreListener(event));

        menuUser.add(itemInfosUser);
        menuUser.addSeparator();
        menuUser.add(itemUpdateUser);

        menuStore.add(itemSeeStore);
        menuStore.addSeparator();
        menuStore.add(itemDeleteUser);

        menuBar.add(menuUser);
        menuBar.add(menuStore);
        menuBar.add(test);


        return menuBar;
    }

    private static void itemInfosUserListener(ActionEvent event) {
        JOptionPane.showMessageDialog(frame, DatabaseConnexion.getUserInfos().get(0));
    }

    private static void itemUpdateListener(ActionEvent event) {
        JOptionPane.showMessageDialog(frame, "Inscrit" );
    }

    private static void itemDeleteListener(ActionEvent event) {
        JOptionPane.showMessageDialog(frame, "Delete" );
    }

    private static void menuStoreListener(ActionEvent event) {
        JOptionPane.showMessageDialog(frame, "Store");
    }

    private void toolBarNewListener(ActionEvent event) {
        JOptionPane.showMessageDialog(frame, "Nouveau");
    }
}
