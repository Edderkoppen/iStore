package components.fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowScreen extends JFrame {
    private final int screenW;
    private final int screenH;
    private final Font font;

    public WindowScreen(int width, int height) {
        super("Istore");

        this.screenW = width*3/4;
        this.screenH = height*3/4;
        this.font = new Font("Arial", Font.PLAIN, 12);

        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        this.setIconImage(new ImageIcon("src/assets/icons/test.png").getImage());
        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setJMenuBar(createMenuBar());

        JPanel contentPane = (JPanel) this.getContentPane();

        contentPane.setLayout(new BorderLayout());
        contentPane.add(createToolBar(), BorderLayout.NORTH);
        contentPane.add(createSplitPane());

    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuUser = new JMenu("Action");
        JMenu menuStore = new JMenu("Magasin");

        menuUser.setMnemonic('U');
        menuStore.setMnemonic('M');

        JMenuItem itemInfosUser = new JMenuItem("Se connecter");
        JMenuItem itemUpdateUser = new JMenuItem("S'inscrire");
        JMenuItem itemDeleteUser = new JMenuItem("Delete");
        JMenuItem itemSeeStore = new JMenuItem("Informations");

//        itemInfosUser.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemInfosUser.addActionListener(this::itemInfosUserListener);

        itemUpdateUser.setMnemonic('I');
//        itemUpdateUser.setIcon(new ImageIcon("src/assets/icons/about.png"));;
        itemUpdateUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        itemUpdateUser.addActionListener(this::itemUpdateListener);

        itemDeleteUser.setMnemonic('D');
        itemDeleteUser.addActionListener(this::itemDeleteListener);

        itemSeeStore.setMnemonic('I');
        itemSeeStore.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemSeeStore.addActionListener(this::menuStoreListener);

        menuUser.add(itemInfosUser);
        menuUser.addSeparator();
        menuUser.add(itemUpdateUser);
        menuUser.addSeparator();
        menuUser.add(itemDeleteUser);

        menuStore.add(itemSeeStore);

        menuBar.add(menuUser);
        menuBar.add(menuStore);
        return menuBar;
    }



    private JToolBar createToolBar() {

        JToolBar toolBar = new JToolBar();

        JButton buttonNew = new JButton(new ImageIcon("src/assets/icons/new.png"));
        JButton btnSave = new JButton( new ImageIcon( "src/assets/icons/save.png" ) );
        JButton btnSaveAs = new JButton( new ImageIcon( "src/assets/icons/save_as.png" ) );
        JButton btnCopy = new JButton( new ImageIcon( "src/assets/icons/copy.png") );
        JButton btnCut = new JButton( new ImageIcon( "src/assets/icons/cut.png") );
        JButton btnPaste = new JButton( new ImageIcon( "src/assets/icons/paste.png") );
        JButton btnExit = new JButton( new ImageIcon( "src/assets/icons/exit.png") );

        buttonNew.setToolTipText("Nouveau");
        buttonNew.addActionListener(this::toolBarNewListener);

        btnSave.setToolTipText( "Enregistrer" );
        btnSaveAs.setToolTipText( "Save As..." );
        btnCopy.setToolTipText( "Copier" );
        btnCut.setToolTipText( "Couper" );
        btnPaste.setToolTipText( "Coller" );
        btnExit.setToolTipText( "Quitter" );

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


    private JSplitPane createSplitPane() {

        JScrollPane leftPane = new JScrollPane(new JTree());
        JScrollPane centralPane = new JScrollPane();
        JScrollPane rightPane = new JScrollPane(new JTree());

        JPopupMenu test = this.createPopupMenu();

        leftPane.setPreferredSize(new Dimension((int) (this.screenW*0.15), 0));

        centralPane.setPreferredSize(new Dimension((int) (this.screenW*0.60), 0));

        leftPane.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    test.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        } );

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, centralPane);

        return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane, rightPane);
    }


    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem mnuUndo = new JMenuItem( "Undo" );
        mnuUndo.setIcon( new ImageIcon( "src/assets/icons/undo.png" ) );
        mnuUndo.setMnemonic( 'U' );
        mnuUndo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK) );
        popupMenu.add(mnuUndo);

        JMenuItem mnuRedo = new JMenuItem( "Redo" );
        mnuRedo.setIcon( new ImageIcon( "src/assets/icons/redo.png" ) );
        mnuRedo.setMnemonic( 'R' );
        mnuRedo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK) );
        popupMenu.add(mnuRedo);

        popupMenu.addSeparator();

        JMenuItem mnuCopy = new JMenuItem( "Copy" );
        mnuCopy.setIcon( new ImageIcon( "src/assets/icons/copy.png" ) );
        mnuCopy.setMnemonic( 'C' );
        mnuCopy.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK) );
        popupMenu.add(mnuCopy);

        JMenuItem mnuCut = new JMenuItem( "Cut" );
        mnuCut.setIcon( new ImageIcon( "src/assets/icons/cut.png" ) );
        mnuCut.setMnemonic( 't' );
        mnuCut.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK) );
        popupMenu.add(mnuCut);

        JMenuItem mnuPaste = new JMenuItem( "Paste" );
        mnuPaste.setIcon( new ImageIcon( "src/assets/icons/paste.png" ) );
        mnuPaste.setMnemonic( 'P' );
        mnuPaste.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK) );
        popupMenu.add(mnuPaste);


        return popupMenu;
    }


    private void itemInfosUserListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Connecté");
    }

    private void itemUpdateListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Inscrit" );
    }

    private void itemDeleteListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Delete" );
    }

    private void menuStoreListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Store");
    }

    private void toolBarNewListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Nouveau");
    }


}
