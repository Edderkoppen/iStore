package components.fenetre;

import components.menus.MenuBarSample;
import components.menus.SplitPaneSample;
import components.menus.ToolBarSample;
import components.menus.TreeSample;

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
        this.panel = new JPanel();
        this.panel.add(new JLabel("coucou"));
        this.panel.setBackground(Color.blue);

        int coucou = 2;

        if(coucou == 1) {
            pageConnexion(contentPane);

        } else {
            test(contentPane);
        }


    }


    private void pageConnexion(JPanel test) {
        this.setJMenuBar(MenuBarSample.createMenuBar());
        test.add(ToolBarSample.createToolBar(test), BorderLayout.NORTH);
        test.add(SplitPaneSample.createSplitPane(TreeSample.createTree(), panel, this.screenW));
    }

    private void test(JPanel test2) {
        test2.removeAll();
        test2.add(new JLabel("wesh"));
        test2.setBackground(Color.CYAN);
        test2.updateUI();

    }
}
