package components.pages;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class InventoryPanel extends JPanel{
    private final int panelW;
    private final int panelH;

    public InventoryPanel(int panelW, int panelH, JFrame frame, JPanel pan) {
        super(new GridBagLayout());
        this.panelW = panelW;
        this.panelH = panelH;

        this.setBackground(Color.getHSBColor(250, 210, 50));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(100, 0, 100, 30)));

        JPanel itemPanel = new JPanel();
        itemPanel.setBackground(Color.getHSBColor(250, 210, 50));

        itemPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 25, 0));
        itemPanel.setPreferredSize(new Dimension(100, 30));

        ArrayList<String> informations = DatabaseConnexion.getStoreInventory(DatabaseConnexion.getNameStoreId(WindowScreen.userId));

        if(!informations.isEmpty()) {
            int infoNumber = 0;
            String tmp = null;
                for (String infos : informations) {
                    infoNumber++;

                    if(infos.matches("([a-z-A-Z]{1,50}|[a-z-A-Z]{1,50}\\s[a-z-A-Z]{1,50})")) {
                        tmp = infos;
                    }

                    if (infoNumber == 2) {
                        itemPanel.add(new JLabel(infos + " €"));
                    }

                    if(infoNumber == 3) {
                        String finalTmp = tmp;
                        JButton decrease = new JButton("-");
                        decrease.addActionListener(event -> {
                            DatabaseConnexion.updateInventoryQuantity(DatabaseConnexion.getItemQuantity(finalTmp), 1, finalTmp, "-");
                            WindowScreen.pageInventoryRedraw(frame, pan, panelW, panelH);

                        });

                        itemPanel.add(decrease);
                        itemPanel.add(new JLabel(infos));
                        JButton increase = new JButton("+");
                        JTextField manual = new JTextField();
                        manual.setPreferredSize(new Dimension(30, 30));
                        increase.addActionListener(event -> {
                            DatabaseConnexion.updateInventoryQuantity(DatabaseConnexion.getItemQuantity(finalTmp), 1, finalTmp, "+");
                            WindowScreen.pageInventoryRedraw(frame, pan, panelW, panelH);

                        });

                        itemPanel.add(increase);
                        itemPanel.add(manual);
                        manual.addActionListener(event -> {
                            if(manual.getText().matches("-(\\d{1,5}|\\s\\d{1,5})")) {
                                if(!manual.getText().replaceAll("\\D", "").matches("")) {
                                    if(DatabaseConnexion.getItemQuantity(finalTmp) - Integer.parseInt(manual.getText().replaceAll("\\D", "")) >= 0) {
                                        DatabaseConnexion.updateInventoryQuantity(DatabaseConnexion.getItemQuantity(finalTmp), Integer.parseInt(manual.getText().replaceAll("\\D", "")), finalTmp, "-");
                                        WindowScreen.pageInventoryRedraw(frame, pan, panelW, panelH);

                                    } else {
                                        JOptionPane.showMessageDialog(this, "Vous ne pouvez pas avoir une quantité négative ", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            } else if (!manual.getText().replaceAll("\\D", "").matches("")){
                                DatabaseConnexion.updateInventoryQuantity(DatabaseConnexion.getItemQuantity(finalTmp), Integer.parseInt(manual.getText().replaceAll("\\D", "")), finalTmp, "+");
                                WindowScreen.pageInventoryRedraw(frame, pan, panelW, panelH);
                            }
                            manual.setText(null);
                        });
                        infoNumber = 0;
                        this.add(itemPanel);
                        itemPanel = new JPanel();
                        itemPanel.setBackground(Color.getHSBColor(250, 210, 50));
                        itemPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 0));
                        itemPanel.setPreferredSize(new Dimension(100, 30));
                    }

                    if(infoNumber == 1) {
                        itemPanel.add(new JLabel(infos));
                    }

                }
            this.add(itemPanel);
        }
    }
}
