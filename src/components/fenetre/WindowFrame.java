package components.fenetre;

import components.menus.PageInscription;
import components.menus.Test;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Objects;

public class WindowFrame extends JFrame {
    private final int width;
    private final int height;

    public WindowFrame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void showWindow(JPanel panel) throws UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        this.setTitle("IStore");
        this.setSize(width*3/4, height*3/4);
        this.setLocationRelativeTo(null); // this.setLocation((this.width-this.width*3/4)/2, (this.height-this.height*3/4)/2); Pour positionner la frame au centre de la page.
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PageInscription pageInscription = new PageInscription(this.height, this.width);
        Test pageTest = new Test(this.height, this.width);

        this.add(pageInscription.getPanel());
        this.add(pageTest.getPanel());

        this.setContentPane(panel);

        this.setVisible(true);
    }


    public JPanel choosePanel(String currentPage) {

        PageInscription pageInscription = new PageInscription(this.height, this.width);
        Test pageTest = new Test(this.height, this.width);

        if (Objects.equals(currentPage, "test")) {
            return  pageTest.getPanel();

        } else {
            return pageInscription.getPanel();
        }
    }
}
