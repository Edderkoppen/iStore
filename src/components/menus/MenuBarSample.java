package components.menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBarSample extends JFrame {
    private final int screenW;
    private final int screenH;


    public MenuBarSample(int width, int height) {
        super("Istore");

        this.screenW = width*3/4;
        this.screenH = height*3/4;

        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setJMenuBar(createMenuBar());
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuAction = new JMenu("Action");

        menuAction.setMnemonic('A');

        JMenuItem itemConnexion = new JMenuItem("Se connecter");
        JMenuItem itemInscription = new JMenuItem("S'inscrire");

        itemConnexion.setMnemonic('C');
        itemConnexion.setIcon(new ImageIcon("src/assets/icons/about.jpg"));
        itemConnexion.addActionListener(this::itemConnexionListener);

        itemInscription.setMnemonic('I');
        itemInscription.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        itemInscription.addActionListener(this::itemInscriptionListener);

        menuAction.add(itemConnexion);
        menuAction.addSeparator();
        menuAction.add(itemInscription);


        menuBar.add(menuAction);
        return menuBar;
    }


    private void itemConnexionListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Connection" );
    }


    private void itemInscriptionListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Inscription" );
    }
}
