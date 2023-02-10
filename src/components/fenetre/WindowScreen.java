package components.fenetre;

import components.menus.MenuBarSample;
import components.menus.SplitPaneSample;
import components.menus.ToolBarSample;
import components.menus.TreeSample;
import components.pages.*;
import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WindowScreen extends JFrame {
    private final int screenW;
    private final int screenH;
    private final Font font;
    private JPanel panel;
    public static int userId;
    public static String store;

    public WindowScreen(int width, int height) {
        super("Istore");

        this.screenW = (int) (width*0.5);
        this.screenH = (int) (height*0.5);
        this.font = new Font("Arial", Font.PLAIN, 12);

        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        this.setIconImage(new ImageIcon("src/assets/logo.png").getImage());
        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.add(new WelcomePanel(this.screenW ,this.screenH, this, new InscriptionPanel(this.screenW/2, this.screenH/2, contentPane), new ConnexionPanel(this, this.screenW/2, this.screenH/2, contentPane)));

    }

    public static void pageConnexionRedraw(JFrame frame, JPanel pan, int screenW, int screenH) {
        pan.removeAll();
        frame.setJMenuBar(null);
        pan.add(new WelcomePanel(screenW*2 ,screenH*2, frame, new InscriptionPanel(screenW, screenH, pan), new ConnexionPanel(frame, screenW, screenH, pan)));
        pan.updateUI();
    }

    public static void pageInventoryRedraw(JFrame frame, JPanel pan, int screenW, int screenH) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH, userId));
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexion.getNameStoreId(WindowScreen.userId), frame, pan, screenW, screenH), new InventoryPanel(screenW, screenH, frame, pan), screenW));
        pan.updateUI();
    }

    public static void pageUpdateRedraw(JFrame frame, JPanel pan, int screenW, int screenH, int id) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH, userId));
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexion.getNameStoreId(WindowScreen.userId), frame, pan, screenW, screenH), new UpdateUserPanel(frame, pan, screenW, screenH, id), screenW));
        pan.updateUI();
    }

    public static void storeCreateDeleteRedraw(JFrame frame, JPanel pan, int screenW, int screenH) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH, userId));
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexion.getNameStoreId(WindowScreen.userId), frame, pan, screenW, screenH), new DeleteCreateStorePanel(screenW, screenH), screenW));
        pan.updateUI();
    }

    public static void itemCreateDeleteRedraw(JFrame frame, JPanel pan, int screenW, int screenH) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH, userId));
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexion.getNameStoreId(WindowScreen.userId), frame, pan, screenW, screenH), new DeleteCreateItemPanel(screenW, screenH), screenW));
        pan.updateUI();
    }
}
