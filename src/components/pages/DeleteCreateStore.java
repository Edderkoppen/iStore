package components.pages;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;


public class DeleteCreateStore extends JPanel {
    private final int panelW;
    private final int panelH;
    private JFrame frame;
    public DeleteCreateStore(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 150;
        int heightComponent = 30;

        JLabel storeLabel = new JLabel("ISTORE");

        JButton deleteStoreButton = new JButton("Supprimer");
        JButton createStoreButton = new JButton("Ajouter");
        JLabel storeInfoNameLabel = new JLabel("Nom du magasin");

        JTextField storeField = new JTextField();

        storeLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        storeInfoNameLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.15) - heightComponent/2, widthComponent, heightComponent);

        deleteStoreButton.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        createStoreButton.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);

        storeField.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);

        this.add(storeLabel);
        this.add(storeInfoNameLabel);
        this.add(deleteStoreButton);
        this.add(createStoreButton);
        this.add(storeField);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int panelW  = (int)dimension.getWidth();
        int panelH = (int)dimension.getHeight();

        frame.setSize(panelW*3/4, panelH*3/4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        DeleteCreateStore main = new DeleteCreateStore(panelW*3/4,panelH*3/4);
        frame.add(main);
        frame.setVisible(true);
    }
}