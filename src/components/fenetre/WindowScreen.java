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

    public WindowScreen(int width, int height) {
        super("Istore");

        this.screenW = width*3/4;
        this.screenH = height*3/4;
        this.font = new Font("Arial", Font.PLAIN, 12);

        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        this.setIconImage(new ImageIcon("src/assets/logo.png").getImage());
        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel contentPane = (JPanel) this.getContentPane();

//        this.setJMenuBar(new MenuBarSample(contentPane, this.screenW, this.screenH, userId));
        contentPane.add(new ConnexionPanel(this, this.screenW,this.screenH, contentPane));

    }

    public static void pageEmployeeRedraw(JFrame frame, JPanel pan, int screenW, int screenH, ActionEvent event) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH, userId));
        pan.add(new ToolBarSample(frame, pan), BorderLayout.WEST);
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexion.getNameStoreId(WindowScreen.userId)), new EmployeePanel((int) (screenW*0.66), (int) (screenH*0.66), pan), screenW));
        pan.updateUI();
    }

    public static void pageConnexionRedraw(JFrame frame, JPanel pan, int screenW, int screenH, ActionEvent event) {
        pan.removeAll();
        frame.setJMenuBar(null);
        pan.add(new ConnexionPanel(frame, (int) (screenW*0.66), (int) (screenH*0.66), pan));
        pan.updateUI();
    }

    public static void pageInventoryRedraw(JFrame frame, JPanel pan, int screenW, int screenH, ActionEvent event) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH, userId));
        pan.add(new ToolBarSample(frame, pan), BorderLayout.WEST);
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexion.getNameStoreId(WindowScreen.userId)), new InventoryPanel((int) (screenW*0.66), (int) (screenH*0.66)), screenW));
        pan.updateUI();
    }

    public static void getFieldConnexion(JFrame frame, ActionEvent event) {
        JOptionPane.showMessageDialog(frame, DatabaseConnexion.getUserInfos().get(0));
    }
}
