package components.menus;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBarSample extends JFrame {
    private final int screenW;
    private final int screenH;
    private final Font font;

    public MenuBarSample(int width, int height) {
        super("Istore");

        this.screenW = width*3/4;
        this.screenH = height*3/4;
        this.font = new Font("Arial", Font.PLAIN, 12);

        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        this.setIconImage(new ImageIcon("src/assets/icons/cut.png").getImage());
        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setJMenuBar(createMenuBar());
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuUser = new JMenu("Utilisateur");
        JMenu menuStore = new JMenu("Magasin");

        menuUser.setMnemonic('U');
        menuStore.setMnemonic('M');

        JMenuItem itemInfosUser = new JMenuItem("Informations");
        JMenuItem itemUpdateUser = new JMenuItem("Update");
        JMenuItem itemDeleteUser = new JMenuItem("Delete");
        JMenuItem itemSeeStore = new JMenuItem("Informations");

        itemInfosUser.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemInfosUser.addActionListener(this::itemInfosUserListener);

        itemUpdateUser.setMnemonic('P');
        itemUpdateUser.setIcon(new ImageIcon("src/assets/icons/about.png"));;
        itemUpdateUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        itemUpdateUser.addActionListener(this::itemUpdateListener);

        itemDeleteUser.setMnemonic('D');
        itemDeleteUser.addActionListener(this::itemDeleteListener);

        itemSeeStore.setMnemonic('I');
        itemSeeStore.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemSeeStore.addActionListener(this::menuStoreListener);

        menuUser.add(itemInfosUser);
        menuUser.addSeparator();
        menuUser.add(itemUpdateUser);
        menuUser.addSeparator();
        menuUser.add(itemDeleteUser);

        menuStore.add(itemSeeStore);

        menuBar.add(menuUser);
        menuBar.add(menuStore);
        return menuBar;
    }


    private void itemInfosUserListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Infos");
    }

    private void itemUpdateListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Update" );
    }

    private void itemDeleteListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Delete" );
    }

    private void menuStoreListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Store");
    }
}
