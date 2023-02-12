package istore.components.menus;

import istore.components.screen.WindowScreen;
import istore.controller.DatabaseConnexionController;
import istore.controller.WhiteListController;

import javax.swing.*;

public class MenuBarSample extends JMenuBar {
    private JFrame frame;
    private JPanel pan;
    private  int screenW;
    private int screenH;

    public MenuBarSample(JFrame frame, JPanel pan, int screenW, int screenH) {
        super();
        this.frame = frame;
        this.pan = pan;
        this.screenW = screenW;
        this.screenH = screenH;

        JMenu menuUser = WindowScreen.userId != 0 ? new JMenu(DatabaseConnexionController.getUserInfosFromId(WindowScreen.userId).get(1)) : null;
        JMenuItem itemDisconnect = new JMenuItem("Déconnexion");
        JMenuItem itemUpdateMe = new JMenuItem("Mettre à jour");
        JMenuItem itemDeleteMe = new JMenuItem("Supprimer");

        menuUser.setMnemonic('U');

        itemDisconnect.setMnemonic('D');
        itemDisconnect.addActionListener(event -> {
            Object[] options = {"Oui", "Annuler"};
            int x = JOptionPane.showOptionDialog(this, "Voulez-vous vous déconnecter ?", "Déconnexion", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,  options[1]);
            if(x == 0) {
                WindowScreen.idTmp = 0;
                WindowScreen.pageConnexionRedraw(this.frame, this.pan, this.screenW, this.screenH);
            }
        });

        itemUpdateMe.setMnemonic('M');
        itemUpdateMe.addActionListener(event -> {
            WindowScreen.pageUpdateRedraw(this.frame, this.pan, this.screenW, this.screenH, WindowScreen.userId);
        });

        itemDeleteMe.setMnemonic('S');
        itemDeleteMe.addActionListener(event -> {
            Object[] options = {"Je suis sur", "Annuler"};
            int x = JOptionPane.showOptionDialog(this, "Vous allez supprimer votre compte utilisateur. Cette action est irréversible !", "Attention", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,  options[1]);
            if(x == 0) {
                DatabaseConnexionController.deleteUser(WindowScreen.userId);
            }
        });

        menuUser.add(itemDisconnect);
        menuUser.addSeparator();
        menuUser.add(itemUpdateMe);
        menuUser.addSeparator();
        menuUser.add(itemDeleteMe);


        JMenu menuEmployee = new JMenu("Employé");
        JMenuItem itemDeleteUser = new JMenuItem("Supprimer");

        menuEmployee.setMnemonic('E');

        itemDeleteUser.setMnemonic('S');
        itemDeleteUser.addActionListener(event -> {
            String email = JOptionPane.showInputDialog(this, "Email de l'utilisateur à supprimer :");
            String verif = DatabaseConnexionController.getEmail(email);
            if(email != null) {
                if(verif != null) {
                    DatabaseConnexionController.deleteUserFromEmail(email);
                    JOptionPane.showMessageDialog(this, "L'utilisateur " + email + " est supprimé", "Succes", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "L'utilisateur " + email + " ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuEmployee.add(itemDeleteUser);


        JMenu menuStore = new JMenu("Magasin");
        JMenuItem itemCreateStore = new JMenuItem("Créer");
        JMenuItem itemDeleteStore = new JMenuItem("Supprimer");
        JMenuItem itemAttributeStore = new JMenuItem("Donner accès employé");
        JMenuItem itemAttributeItem = new JMenuItem("Ajouter un item existant");

        menuStore.setMnemonic('M');

        itemCreateStore.setMnemonic('C');
        itemCreateStore.addActionListener(event -> {
            String store = JOptionPane.showInputDialog(this, "Nom du magasin à ajouter :");
            if(store != null) {
                DatabaseConnexionController.createStore(store);
                JOptionPane.showMessageDialog(this, store + " a été ajouté à la liste des magasins", "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        itemDeleteStore.setMnemonic('S');
        itemDeleteStore.addActionListener(event -> {
            String store = JOptionPane.showInputDialog(this, "Nom du store à supprimer :");
            String verif = DatabaseConnexionController.getStore(store);
            if(store != null) {
                if(verif != null) {
                    DatabaseConnexionController.deleteStoreFromInventory(store);
                    DatabaseConnexionController.deleteStore(store);
                    JOptionPane.showMessageDialog(this, "'" + store + "' supprimé de la liste des magasins", "Succes", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "'" + store + "' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        itemAttributeStore.setMnemonic('E');
        itemAttributeStore.addActionListener(event -> {

            String employee = JOptionPane.showInputDialog(this, "Email de l'employé à qui fournir l'accès au magasin :");
            String store = JOptionPane.showInputDialog(this, "Magasin concerné :");
            String verifEmployee = DatabaseConnexionController.getEmail(employee);
            String verifStore = DatabaseConnexionController.getStore(store);

            if(employee != null && store != null) {
                if(verifEmployee != null && verifStore != null) {

                    int id = DatabaseConnexionController.getStoreId(store);

                    DatabaseConnexionController.updateStoreAttribution(id, employee);
                    JOptionPane.showMessageDialog(this, employee + " a désormais accès au magasin " + store, "Succes", JOptionPane.INFORMATION_MESSAGE);
                    WindowScreen.pageInventoryRedraw(this.frame, this.pan, this.screenW, this.screenH);

                } else if(verifEmployee == null) {
                    JOptionPane.showMessageDialog(this, "'" + employee + "' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "'" + store + "' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        itemAttributeItem.setMnemonic('I');
        itemAttributeItem.addActionListener(event -> {
            String item = JOptionPane.showInputDialog(this, "Nom de la marchandise à enregistrer en magasin :");
            String quantity = JOptionPane.showInputDialog(this, "Quantité disponible :");
            String store = JOptionPane.showInputDialog(this, "Magasin concerné :");

            String verifItem = DatabaseConnexionController.getItem(item);
            String verifStore = DatabaseConnexionController.getStore(store);

            if(item != null && store != null && quantity != null) {
                if(quantity.matches("\\d{1,5}")) {
                    if(verifItem != null && verifStore != null) {

                        int idStore = DatabaseConnexionController.getStoreId(store);
                        int idItem = DatabaseConnexionController.getItemId(item);

                        DatabaseConnexionController.updateInventory(idStore, idItem, Integer.parseInt(quantity));
                        JOptionPane.showMessageDialog(this, item + " est enregistré dans l'inventaire du magasin '" + store + "'", "Succes", JOptionPane.INFORMATION_MESSAGE);
                        WindowScreen.pageInventoryRedraw(this.frame, this.pan, this.screenW, this.screenH);

                    } else if(verifItem == null) {
                        JOptionPane.showMessageDialog(this, "'" + item + "' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(this, "'" + store + "' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Mauvais format pour la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuStore.add(itemCreateStore);
        menuStore.addSeparator();
        menuStore.add(itemDeleteStore);
        menuStore.addSeparator();
        menuStore.add(itemAttributeStore);
        menuStore.addSeparator();
        menuStore.add(itemAttributeItem);


        JMenu menuItem = new JMenu("Marchandise");
        JMenuItem itemCreateItem = new JMenuItem("Créer");
        JMenuItem itemDeleteItem = new JMenuItem("Supprimer");

        menuItem.setMnemonic('I');

        itemCreateItem.setMnemonic('C');
        itemCreateItem.addActionListener(event -> {

            String item = JOptionPane.showInputDialog(this, "Nom de la marchandise à ajouter :");
            String itemPrice = JOptionPane.showInputDialog(this, "Prix de la marchandise (au format xx.xx) :");

            if(item != null && itemPrice != null) {
                DatabaseConnexionController.createItem(item, Double.parseDouble(itemPrice));
                JOptionPane.showMessageDialog(this, item + " a été ajouté à la liste des marchandises", "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        itemDeleteItem.setMnemonic('S');
        itemDeleteItem.addActionListener(event -> {

            String item = JOptionPane.showInputDialog(this, "Nom de la marchandise à supprimer :");
            String verif = DatabaseConnexionController.getItem(item);

            if(item != null) {
                if(verif != null) {

                    DatabaseConnexionController.deleteItemFromInventory(item);
                    DatabaseConnexionController.deleteItem(item);
                    JOptionPane.showMessageDialog(frame, "'" + item + "' supprimé de la liste des marchandises", "Succes", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(frame, "\'" + item + "\' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuItem.add(itemCreateItem);
        menuItem.addSeparator();
        menuItem.add(itemDeleteItem);


        JMenu menuWhitelist = new JMenu("Whitelist");
        JMenuItem itemAddWhitelist = new JMenuItem("Ajouter");
        JMenuItem itemDeleteWhitelist = new JMenuItem("Supprimer");

        menuWhitelist.setMnemonic('W');

        itemAddWhitelist.setMnemonic('A');
        itemAddWhitelist.addActionListener(event -> {

            String email = JOptionPane.showInputDialog(this, "Email a whitelister :");

            if(email != null) {
                if(email.matches("([a-zA-Z]{1,30}(.|)[a-zA-Z]{1,30}@[a-zA-Z]{1,30}.(com|fr))")) {

                    WhiteListController.insertEmail(email);
                    JOptionPane.showMessageDialog(this, email + " inséré dans la whitelist", "Succes", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(this, "Impossible d'insérer " + email + " dans la whitelist. Mauvais format.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        itemDeleteWhitelist.setMnemonic('S');
        itemDeleteWhitelist.addActionListener(event -> {

            String email = JOptionPane.showInputDialog(this, "Email a supprimer :");
            boolean exists = WhiteListController.deleteEmail(email);

            if(exists) {
                JOptionPane.showMessageDialog(this, email + " supprimé de la whitelist", "Succes", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, email + " ne semble pas exister dans la whitelist", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuWhitelist.add(itemAddWhitelist);
        menuWhitelist.add(itemDeleteWhitelist);

        this.add(menuUser);

        if(DatabaseConnexionController.getRoleFromId(WindowScreen.userId) == 1) {
            this.add(menuEmployee);
            this.add(menuStore);
            this.add(menuItem);
            this.add(menuWhitelist);
        }
    }
}
