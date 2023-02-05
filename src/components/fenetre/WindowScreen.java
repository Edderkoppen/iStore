package components.fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;


public class WindowScreen extends JFrame {
    private final int screenW;
    private final int screenH;
    private final Font font;

    public WindowScreen(int width, int height) {
        super("Istore");

        this.screenW = width * 3 / 4;
        this.screenH = height * 3 / 4;
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

        JMenu menuUser = new JMenu("Utilisateur");
        JMenu menuStore = new JMenu("Magasin");

        menuUser.setMnemonic('U');
        menuStore.setMnemonic('M');

        JMenuItem itemInfosUser = new JMenuItem("Connexion");
        JMenuItem itemUpdateUser = new JMenuItem("Inscription");
        JMenuItem itemDeleteUser = new JMenuItem("Delete");
        JMenuItem itemSeeStore = new JMenuItem("Informations");

        itemInfosUser.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemInfosUser.addActionListener(this::itemInfosUserListener);

        itemUpdateUser.setMnemonic('P');
        itemUpdateUser.setIcon(new ImageIcon("src/assets/icons/about.png"));
        itemUpdateUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
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
        JButton btnSave = new JButton(new ImageIcon("src/assets/icons/save.png"));
        JButton btnSaveAs = new JButton(new ImageIcon("src/assets/icons/save_as.png"));
        JButton btnCopy = new JButton(new ImageIcon("src/assets/icons/copy.png"));
        JButton btnCut = new JButton(new ImageIcon("src/assets/icons/cut.png"));
        JButton btnPaste = new JButton(new ImageIcon("src/assets/icons/paste.png"));
        JButton btnExit = new JButton(new ImageIcon("src/assets/icons/exit.png"));

        buttonNew.setToolTipText("Nouveau");
        buttonNew.addActionListener(this::toolBarNewListener);

        btnSave.setToolTipText("Enregistrer");
        btnSaveAs.setToolTipText("Save As...");
        btnCopy.setToolTipText("Copier");
        btnCut.setToolTipText("Couper");
        btnPaste.setToolTipText("Coller");
        btnExit.setToolTipText("Quitter");

        toolBar.add(buttonNew);
        toolBar.addSeparator();
        toolBar.add(btnSave);
        toolBar.addSeparator();
        toolBar.add(btnSaveAs);
        toolBar.addSeparator();
        toolBar.add(btnCopy);
        toolBar.addSeparator();
        toolBar.add(btnCut);
        toolBar.addSeparator();
        toolBar.add(btnPaste);
        toolBar.addSeparator();
        toolBar.add(btnExit);
        toolBar.addSeparator();

        return toolBar;
    }


    private JSplitPane createSplitPane() {

        JScrollPane leftPane = new JScrollPane(new JTree());
        JScrollPane centralPane = new JScrollPane();
        JScrollPane rightPane = new JScrollPane(new JTree());

        JPopupMenu test = this.createPopupMenu();

        leftPane.setPreferredSize(new Dimension((int) (this.screenW * 0.15), 0));

        centralPane.setPreferredSize(new Dimension((int) (this.screenW * 0.60), 0));

        leftPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    test.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, centralPane);

        return new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane, rightPane);
    }


    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem mnuUndo = new JMenuItem("Undo");
        mnuUndo.setIcon(new ImageIcon("src/assets/icons/undo.png"));
        mnuUndo.setMnemonic('U');
        mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuUndo);

        JMenuItem mnuRedo = new JMenuItem("Redo");
        mnuRedo.setIcon(new ImageIcon("src/assets/icons/redo.png"));
        mnuRedo.setMnemonic('R');
        mnuRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuRedo);

        popupMenu.addSeparator();

        JMenuItem mnuCopy = new JMenuItem("Copy");
        mnuCopy.setIcon(new ImageIcon("src/assets/icons/copy.png"));
        mnuCopy.setMnemonic('C');
        mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuCopy);

        JMenuItem mnuCut = new JMenuItem("Cut");
        mnuCut.setIcon(new ImageIcon("src/assets/icons/cut.png"));
        mnuCut.setMnemonic('t');
        mnuCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuCut);

        JMenuItem mnuPaste = new JMenuItem("Paste");
        mnuPaste.setIcon(new ImageIcon("src/assets/icons/paste.png"));
        mnuPaste.setMnemonic('P');
        mnuPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuPaste);


        return popupMenu;
    }


    private void itemInfosUserListener(ActionEvent event) {
        //Créez des champs de texte pour le pseudo et le mot de passe
        JTextField pseudoField = new JTextField();
        JTextField passwordField = new JPasswordField();

        //Créez un tableau pour stocker les champs de texte
        JComponent[] inputs = new JComponent[]{
                new JLabel("Pseudo:"),
                pseudoField,
                new JLabel("Mot de passe:"),
                passwordField
        };

        //Affichez une boîte de dialogue pour collecter les informations d'identification
        int result = JOptionPane.showConfirmDialog(null, inputs, "Veuillez entrer vos informations de connexion", JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String pseudo = pseudoField.getText();
            String passwd = passwordField.getText();

            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/istore", "root", "root")) {

                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM user WHERE pseudo = ? AND password = ?");
                stmt.setString(1, pseudo);
                stmt.setString(2, passwd);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Coucou, " + rs.getString("pseudo"), "Bienvenue", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Information fausse =/", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Sa marche po ='(" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/istore";
    static final String USER = "root";
    static final String PASS = "root";
    private TextField emailField;
    private TextField passwordField;
    private TextField pseudoField;
    private TextField firstNameField;
    private TextField surnameField;
    private TextField idRoleField;

    private void itemUpdateListener(ActionEvent event) {
        Connection conn = null;
        PreparedStatement stmt = null;
        emailField = new TextField();
        passwordField = new TextField();
        pseudoField = new TextField();
        firstNameField = new TextField();
        surnameField = new TextField();
        idRoleField = new TextField();


        String email = emailField.getText();
        String password = passwordField.getText();
        String pseudo = pseudoField.getText();
        String firstName = firstNameField.getText();
        String surname = surnameField.getText();
        int idRole = 1;

        try {
            idRole = Integer.parseInt(idRoleField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre entier valide pour id_role", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Inserting a new user...");
            stmt = conn.prepareStatement("INSERT INTO user (email, password, pseudo, first_name, surname, id_role) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, pseudo);
            stmt.setString(4, firstName);
            stmt.setString(5, surname);
            stmt.setInt(6, idRole);

            stmt.executeUpdate();
            System.out.println("User inserted successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private void itemDeleteListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Delete");
    }

    private void menuStoreListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Store");
    }

    private void toolBarNewListener(ActionEvent event) {
        JOptionPane.showMessageDialog(this, "Nouveau");
    }

}