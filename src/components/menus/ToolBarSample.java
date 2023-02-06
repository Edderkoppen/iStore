package components.menus;

import components.fenetre.WindowScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToolBarSample {
    public static JToolBar createToolBar(JPanel pan) {

        JToolBar toolBar = new JToolBar();

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

        buttonNew.addActionListener(event -> changePanel(pan,700, 500, event));

        toolBar.add(buttonNew);
        toolBar.addSeparator();
        toolBar.add( btnSave );
        toolBar.addSeparator();
        toolBar.add( btnSaveAs );
        toolBar.addSeparator();
        toolBar.add( btnCopy );
        toolBar.addSeparator();
        toolBar.add( btnCut );
        toolBar.addSeparator();
        toolBar.add( btnPaste );
        toolBar.addSeparator();
        toolBar.add( btnExit );
        toolBar.addSeparator();

        return toolBar;
    }

    private static void changePanel(JPanel pan, int screenW, int screenH, ActionEvent event) {
        pan.removeAll();
        WindowScreen.pageInventory(pan, screenW, screenH);
        pan.updateUI();
    }
}
