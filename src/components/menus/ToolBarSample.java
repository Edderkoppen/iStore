package components.menus;

import components.fenetre.WindowScreen;

import javax.swing.*;

public class ToolBarSample extends JToolBar {
    private JPanel pan;
    private JFrame frame;

    public ToolBarSample(JFrame frame, JPanel pan) {
        super();
        this.pan = pan;
        this.frame = frame;

        this.setOrientation(SwingConstants.VERTICAL);

        JButton buttonNew = new JButton(new ImageIcon("src/assets/icons/new.png"));
        JButton btnSave = new JButton( new ImageIcon( "src/assets/icons/save.png" ) );
        JButton btnSaveAs = new JButton( new ImageIcon( "src/assets/icons/save_as.png" ) );
        JButton btnCopy = new JButton( new ImageIcon( "src/assets/icons/copy.png") );
        JButton btnCut = new JButton( new ImageIcon( "src/assets/icons/cut.png") );
        JButton btnPaste = new JButton( new ImageIcon( "src/assets/icons/paste.png") );
        JButton btnExit = new JButton( new ImageIcon( "src/assets/icons/exit.png") );

        buttonNew.setToolTipText("Nouveau");
        btnSave.setToolTipText( "Enregistrer" );
        btnSaveAs.setToolTipText( "Save As..." );
        btnCopy.setToolTipText( "Copier" );
        btnCut.setToolTipText( "Couper" );
        btnPaste.setToolTipText( "Coller" );
        btnExit.setToolTipText( "Quitter" );

//        buttonNew.addActionListener(event -> WindowScreen.pageInventoryRedraw(this.frame, this.pan,700, 500));

        this.add(buttonNew);
        this.addSeparator();
        this.add( btnSave );
        this.addSeparator();
        this.add( btnSaveAs );
        this.addSeparator();
        this.add( btnCopy );
        this.addSeparator();
        this.add( btnCut );
        this.addSeparator();
        this.add( btnPaste );
        this.addSeparator();
        this.add( btnExit );
        this.addSeparator();
    }
}
