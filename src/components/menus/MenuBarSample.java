package components.menus;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBarSample extends JMenuBar {
    private JFrame frame;
    private JPanel pan;
    private  int screenW;
    private int screenH;
    private int user;

    public MenuBarSample(JFrame frame, JPanel pan, int screenW, int screenH, int user) {
        super();
        this.frame = frame;
        this.pan = pan;
        this.screenW = screenW;
        this.screenH = screenH;
        this.user = user;

        JMenu menuAction = new JMenu("Action");
        JMenu menuStore = new JMenu("Magasin");
        JMenu menuEmployee = new JMenu("Employés");
        JMenu menuUser = null;


        if(user != 0) {
            menuUser = new JMenu(DatabaseConnexion.getUserInfosFromId(user).get(1));
        }
        menuStore.setMnemonic('M');

        JMenuItem itemConnect = new JMenuItem("Se connecter");
        JMenuItem itemDisconnect = new JMenuItem("Déconnexion");
        JMenuItem itemSee = new JMenuItem("Voir");
        JMenuItem itemDeleteUser = new JMenuItem("Delete");
        JMenuItem itemSeeStore = new JMenuItem("Informations");
        JMenuItem itemDelete = new JMenuItem("Supprimer");
        JMenuItem itemUpdate = new JMenuItem("Mettre à jour");

        itemConnect.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemConnect.addActionListener(event -> WindowScreen.getFieldConnexion(frame, event));

        itemDisconnect.setMnemonic('C');
        itemDisconnect.setIcon(new ImageIcon("src/assets/icons/about.png"));;
        itemDisconnect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        itemDisconnect.addActionListener(event -> WindowScreen.pageConnexionRedraw(frame, pan, screenW, screenH));

        itemSee.addActionListener(event -> WindowScreen.pageUpdateRedraw(frame, pan, screenW, screenH));

        itemDeleteUser.setMnemonic('D');

        itemSeeStore.setMnemonic('I');
        itemSeeStore.setIcon(new ImageIcon("src/assets/icons/about.png"));

        itemDelete.addActionListener(event -> {

            Object[] options = {"Je suis sur", "Annuler"};
            int x = JOptionPane.showOptionDialog(this, "Vous allez supprimer votre compte utilisateur. Cette action est irréversible !", "Attention", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,  options[1]);
            if(x == 0) {
                DatabaseConnexion.deleteUser(WindowScreen.userId);
            }
        });
        itemUpdate.addActionListener(event -> {
            WindowScreen.pageUpdateRedraw(frame, pan, screenW, screenH);
        });

        menuAction.add(itemConnect);
        menuAction.addSeparator();
        menuAction.add(itemDisconnect);

        menuStore.add(itemSeeStore);
        menuStore.addSeparator();
        menuStore.add(itemDeleteUser);

        menuEmployee.add(itemSee);

        menuUser.add(itemUpdate);
        menuUser.addSeparator();
        menuUser.add(itemDelete);

        this.add(menuAction);
        this.add(menuStore);
        this.add(menuEmployee);

        if(menuUser != null) {
            this.add(menuUser);
        }
    }
}
