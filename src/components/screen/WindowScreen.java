package components.screen;

import components.menus.MenuBarSample;
import components.menus.SplitPaneSample;
import components.menus.TreeSample;
import components.pages.*;
import controller.DatabaseConnexionController;

import javax.swing.*;
import java.awt.*;

public class WindowScreen extends JFrame {
    private final int screenW;
    private final int screenH;
    private final Font font;
    public static int userId;
    public static int idTmp;

    public WindowScreen(int width, int height) {
        super("Istore");

        this.screenW = (int) (width*0.5);
        this.screenH = (int) (height*0.5);
        this.font = new Font("Arial", Font.PLAIN, 12);

        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        this.setIconImage(new ImageIcon("src/assets/images/logo.png").getImage());
        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.add(new WelcomePage(this.screenW ,this.screenH,  new InscriptionPage(this.screenW/2, this.screenH/2, contentPane), new ConnexionPage(this, this.screenW/2, this.screenH/2, contentPane)));
    }


    /**
     * Redessine la fenetre et affiche la page de connexion.
     *
     * @param frame     Fenetre principale.
     * @param pan       Panel sur lequel redessiner.
     * @param screenW   Largeur de l'écran.
     * @param screenH   Longueur de l'écran.
     */
    public static void pageConnexionRedraw(JFrame frame, JPanel pan, int screenW, int screenH) {
        pan.removeAll();
        frame.setJMenuBar(null);
        pan.add(new WelcomePage(screenW*2 ,screenH*2, new InscriptionPage(screenW, screenH, pan), new ConnexionPage(frame, screenW, screenH, pan)));
        pan.updateUI();
    }


    /**
     * Redessine la fenetre et affiche la page d'inventaire.
     *
     * @param frame     Fenetre principale.
     * @param pan       Panel sur lequel redessiner.
     * @param screenW   Largeur de l'écran.
     * @param screenH   Longueur de l'écran.
     */
    public static void pageInventoryRedraw(JFrame frame, JPanel pan, int screenW, int screenH) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH));
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexionController.getNameStoreId(WindowScreen.userId), frame, pan, screenW, screenH), new InventoryPage(screenW, screenH, frame, pan), screenW));
        pan.updateUI();
    }


    /**
     * Redessine la fenetre et affiche la page de mise a jour utilisateur.
     *
     * @param frame     Fenetre principale.
     * @param pan       Panel sur lequel redessiner.
     * @param screenW   Largeur de l'écran.
     * @param screenH   Longueur de l'écran.
     * @param id        L'id de l'utilisateur à mettre à jour.
     */
    public static void pageUpdateRedraw(JFrame frame, JPanel pan, int screenW, int screenH, int id) {
        pan.removeAll();
        frame.setJMenuBar(new MenuBarSample(frame, pan, screenW, screenH));
        pan.add(SplitPaneSample.createSplitPane(TreeSample.createTree(DatabaseConnexionController.getNameStoreId(WindowScreen.userId), frame, pan, screenW, screenH), new UpdateUserPage(frame, pan, screenW, screenH, id), screenW));
        pan.updateUI();
    }
}
