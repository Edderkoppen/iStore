package components.fenetre;

import connexion.DatabaseConnexion;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

        this.setIconImage(new ImageIcon("src/assets/icons/test.png").getImage());
        this.setSize(screenW, screenH);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.darkGray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setJMenuBar(createMenuBar());

        JPanel contentPane = (JPanel) this.getContentPane();
        this.panel = new JPanel();
        this.panel.add(new JLabel("coucou"));
        this.panel.setBackground(Color.blue);

        contentPane.setLayout(new BorderLayout());
        contentPane.add(createToolBar(), BorderLayout.NORTH);
        contentPane.add(createSplitPane(createNode(), panel));

//        this.setContentPane(this.panel);
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuUser = new JMenu("Action");
        JMenu menuStore = new JMenu("Magasin");
        JMenu test = new JMenu(DatabaseConnexion.testData().get(0));

        menuUser.setMnemonic('U');
        menuStore.setMnemonic('M');

        JMenuItem itemInfosUser = new JMenuItem("Se connecter");
        JMenuItem itemUpdateUser = new JMenuItem("S'inscrire");
        JMenuItem itemDeleteUser = new JMenuItem("Delete");
        JMenuItem itemSeeStore = new JMenuItem("Informations");

        itemInfosUser.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemInfosUser.addActionListener(this::itemInfosUserListener);

        itemUpdateUser.setMnemonic('I');
        itemUpdateUser.setIcon(new ImageIcon("src/assets/icons/about.png"));;
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

        menuStore.add(itemSeeStore);
        menuStore.addSeparator();
        menuStore.add(itemDeleteUser);

        menuBar.add(menuUser);
        menuBar.add(menuStore);
        menuBar.add(test);


        return menuBar;
    }

    private JPanel createForm(JPanel pan) {


        int labelW = 150;
        int labelH = 100;
        int buttonW = 150;
        int buttonH = 100;

        JLabel nameLabel = new JLabel("Prénom :", SwingConstants.CENTER);
        JLabel surnameLabel = new JLabel("Nom :", SwingConstants.CENTER);
        JLabel emailLabel = new JLabel("Email :", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("Mot de passe :", SwingConstants.CENTER);

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton submitButton = new JButton("Se connecter");

        nameLabel.setBounds(100, 50, labelW, labelH);
        nameField.setBounds(100, 70, labelW, labelH);

//        submitButton.setBounds(((this.screenW)/2)-(buttonW/2), (int) (this.screenH*0.5 - buttonH/2), buttonW, buttonH);

        pan.setLayout(null);
        pan.setBackground(Color.CYAN);
        pan.add(nameLabel);
        pan.add(nameField);
//        pan.add(submitButton);
//        pan.add(surnameLabel);
//        pan.add(surnameField);
//        pan.add(emailLabel);
//        pan.add(emailField);
//        pan.add(passwordLabel);
//        pan.add(passwordField);

        return pan;
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
        btnSave.setToolTipText( "Enregistrer" );
        btnSaveAs.setToolTipText( "Save As..." );
        btnCopy.setToolTipText( "Copier" );
        btnCut.setToolTipText( "Couper" );
        btnPaste.setToolTipText( "Coller" );
        btnExit.setToolTipText( "Quitter" );

        buttonNew.addActionListener(event -> changePanel(this.panel, event));

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


    private JTree createNode() {
        DefaultMutableTreeNode store = new DefaultMutableTreeNode("Magasins");
        ArrayList<String> storeInfos = DatabaseConnexion.querieStoreInfos();
        ArrayList<String> storeName = DatabaseConnexion.querieStoreName();

        DefaultMutableTreeNode storeNameTree = null;

        for (String name : storeInfos) {
            if(storeName.contains(name)) {
                storeNameTree = new DefaultMutableTreeNode(name);
                store.add(storeNameTree);

            } else {
                storeNameTree.add(new DefaultMutableTreeNode(name));
            }
        }

        return new JTree(store);
    }



    private JSplitPane createSplitPane(JTree tree, JPanel pan) {

        JScrollPane leftPane = new JScrollPane(tree);
        JScrollPane rightPane = new JScrollPane(pan);

        leftPane.setPreferredSize(new Dimension((int) (this.screenW*0.25), 0));
        return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, rightPane);
    }


    private void itemInfosUserListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, DatabaseConnexion.testData().get(0));
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

    private void changePanel(JPanel pan, ActionEvent event) {
        this.panel.removeAll();
        createForm(this.panel);
        this.panel.setBackground(Color.MAGENTA);
        this.panel.updateUI();
    }
}
