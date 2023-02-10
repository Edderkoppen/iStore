package components.menus;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;
import controller.WhiteListController;

import javax.swing.*;

public class MenuBarSample extends JMenuBar {
    private JFrame frame;
    private JPanel pan;
    private  int screenW;
    private int screenH;
    private int user;

    public MenuBarSample(JFrame frame, JPanel pan, int screenW, int screenH, int user) {
        super();
        this.frame = frame;
        this.pan = pan;
        this.screenW = screenW;
        this.screenH = screenH;
        this.user = user;

        JMenu menuUser = WindowScreen.userId != 0 ? new JMenu(DatabaseConnexion.getUserInfosFromId(WindowScreen.userId).get(1)) : null;
        JMenuItem itemDisconnect = new JMenuItem("Déconnexion");
        JMenuItem itemUpdateMe = new JMenuItem("Mettre à jour");
        JMenuItem itemDeleteMe = new JMenuItem("Supprimer");

        menuUser.setMnemonic('U');

        itemDisconnect.setMnemonic('D');
        itemDisconnect.addActionListener(event -> {
            Object[] options = {"Oui", "Annuler"};
            int x = JOptionPane.showOptionDialog(this, "Voulez-vous vous déconnecter ?", "Déconnexion", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,  options[1]);
            if(x == 0) {
                WindowScreen.pageConnexionRedraw(frame, pan, screenW, screenH);
            }
        });

        itemUpdateMe.setMnemonic('M');
        itemUpdateMe.addActionListener(event -> {
            WindowScreen.pageUpdateRedraw(frame, pan, screenW, screenH, WindowScreen.userId);
        });

        itemDeleteMe.setMnemonic('S');
        itemDeleteMe.addActionListener(event -> {
            Object[] options = {"Je suis sur", "Annuler"};
            int x = JOptionPane.showOptionDialog(this, "Vous allez supprimer votre compte utilisateur. Cette action est irréversible !", "Attention", JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,  options[1]);
            if(x == 0) {
                DatabaseConnexion.deleteUser(WindowScreen.userId);
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
            String email = JOptionPane.showInputDialog(frame, "Email de l'utilisateur à supprimer :");
            String verif = DatabaseConnexion.getEmail(email);
            if(email != null) {
                if(verif != null) {
                    DatabaseConnexion.deleteUserFromEmail(email);
                    JOptionPane.showMessageDialog(frame, "L'utilisateur " + email + " est supprimé", "Succes", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "L'utilisateur " + email + " ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
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
            String store = JOptionPane.showInputDialog(frame, "Nom du magasin à ajouter :");
            if(store != null) {
                DatabaseConnexion.createStore(store);
                JOptionPane.showMessageDialog(frame, store + " a été ajouté à la liste des magasins", "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        itemDeleteStore.setMnemonic('S');
        itemDeleteStore.addActionListener(event -> {
            String store = JOptionPane.showInputDialog(frame, "Nom du store à supprimer :");
            String verif = DatabaseConnexion.getStore(store);
            if(store != null) {
                if(verif != null) {
                    DatabaseConnexion.deleteStoreFromInventory(store);
                    DatabaseConnexion.deleteStore(store);
                    JOptionPane.showMessageDialog(frame, "'" + store + "' supprimé de la liste des magasins", "Succes", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "\'" + store + "\' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        itemAttributeStore.setMnemonic('E');
        itemAttributeStore.addActionListener(event -> {
            String employee = JOptionPane.showInputDialog(frame, "Email de l'employé à qui fournir l'accès au magasin :");
            String store = JOptionPane.showInputDialog(frame, "Magasin concerné :");
            String verifEmployee = DatabaseConnexion.getEmail(employee);
            String verifStore = DatabaseConnexion.getStore(store);

            if(employee != null && store != null) {
                if(verifEmployee != null && verifStore != null) {
                    int id = DatabaseConnexion.getStoreId(store);
                    DatabaseConnexion.updateStoreAttribution(id, employee);
                    JOptionPane.showMessageDialog(frame, employee + " a désormais accès au magasin " + store, "Succes", JOptionPane.INFORMATION_MESSAGE);
                    WindowScreen.pageInventoryRedraw(frame, pan, screenW, screenH);
                } else if(verifEmployee == null) {
                    JOptionPane.showMessageDialog(frame, "\'" + employee + "\' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "\'" + store + "\' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        itemAttributeItem.setMnemonic('I');
        itemAttributeItem.addActionListener(event -> {
            String item = JOptionPane.showInputDialog(frame, "Nom de la marchandise à enregistrer en magasin :");
            String quantity = JOptionPane.showInputDialog(frame, "Quantité disponible :");
            String store = JOptionPane.showInputDialog(frame, "Magasin concerné :");

            String verifItem = DatabaseConnexion.getItem(item);
            String verifStore = DatabaseConnexion.getStore(store);

            if(item != null && store != null && quantity != null) {
                if(quantity.matches("\\d{1,5}")) {
                    if(verifItem != null && verifStore != null) {

                        int idStore = DatabaseConnexion.getStoreId(store);
                        int idItem = DatabaseConnexion.getItemId(item);
                        DatabaseConnexion.updateInventory(idStore, idItem, Integer.parseInt(quantity));

                        JOptionPane.showMessageDialog(frame, item + " est enregistré dans l'inventaire du magasin '" + store + "'", "Succes", JOptionPane.INFORMATION_MESSAGE);
                        WindowScreen.pageInventoryRedraw(frame, pan, screenW, screenH);
                    } else if(verifItem == null) {
                        JOptionPane.showMessageDialog(frame, "\'" + item + "\' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "\'" + store + "\' ne semble pas exister", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Mauvais format pour la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);

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
            String item = JOptionPane.showInputDialog(frame, "Nom de la marchandise à ajouter :");
            String itemPrice = JOptionPane.showInputDialog(frame, "Prix de la marchandise (au format x.x) :");
            if(item != null && itemPrice != null) {
                DatabaseConnexion.createItem(item, Double.parseDouble(itemPrice));
                JOptionPane.showMessageDialog(frame, item + " a été ajouté à la liste des marchandises", "Succes", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        itemDeleteItem.setMnemonic('S');
        itemDeleteItem.addActionListener(event -> {
            String item = JOptionPane.showInputDialog(frame, "Nom de la marchandise à supprimer :");
            String verif = DatabaseConnexion.getItem(item);
            if(item != null) {
                if(verif != null) {
                    DatabaseConnexion.deleteItemFromInventory(item);
                    DatabaseConnexion.deleteItem(item);
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
            String email = JOptionPane.showInputDialog(frame, "Email a whitelister :");
            if(email != null) {
                if(email.matches("([a-zA-Z]{1,30}(.|)[a-zA-Z]{1,30}@[a-zA-Z]{1,30}.(com|fr))")) {
                    WhiteListController.insertEmail(email);
                    JOptionPane.showMessageDialog(frame, email + " inséré dans la whitelist", "Succes", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Impossible d'insérer " + email + " dans la whitelist. Mauvais format.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        itemDeleteWhitelist.setMnemonic('S');
        itemDeleteWhitelist.addActionListener(event -> {
            String email = JOptionPane.showInputDialog(frame, "Email a supprimer :");
            boolean exists = WhiteListController.deleteEmail(email);
            if(exists) {
                JOptionPane.showMessageDialog(frame, email + " supprimé de la whitelist", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, email + " ne semble pas exister dans la whitelist", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuWhitelist.add(itemAddWhitelist);
        menuWhitelist.add(itemDeleteWhitelist);

        this.add(menuUser);

        if(DatabaseConnexion.getRoleFromId(WindowScreen.userId) == 1) {
            this.add(menuEmployee);
            this.add(menuStore);
            this.add(menuItem);
            this.add(menuWhitelist);
        }
    }
}
