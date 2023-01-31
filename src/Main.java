import components.fenetre.WindowFrame;

import javax.swing.*;
import java.awt.Dimension;

public class Main {
    public static void main(String[] args) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        String currentPage = "";

        WindowFrame frame = new WindowFrame(width, height);

        frame.showWindow(frame.choosePanel(currentPage));
    }
}