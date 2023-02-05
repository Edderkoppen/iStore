package components.fenetre;

import components.menus.MenuBarSample;
import components.menus.SplitPaneSample;
import components.menus.ToolBarSample;
import components.menus.TreeSample;
import components.pages.PageConnexionPanel;

import javax.swing.*;
import java.awt.*;

public class WindowScreen extends JFrame {
    private final int screenW;
    private final int screenH;
    private final Font font;
    private JPanel panel;


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

        contentPane.add(new PageConnexionPanel(this.screenW, this.screenH));
//        pageConnexion(contentPane);


    }

    private void pageConnexion(JPanel pan) {
        this.setJMenuBar(MenuBarSample.createMenuBar());
        pan.add(ToolBarSample.createToolBar(pan), BorderLayout.NORTH);
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(), new PageConnexionPanel((int) (screenW*0.66), (int) (screenH*0.66)), this.screenW));
    }

}
