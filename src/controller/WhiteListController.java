package controller;

import java.io.*;
import java.util.ArrayList;

public class WhiteListController {

    /**
     * Récupère l'ensemble des emails dans la whiteList.
     *
     * @return un ArrayList contenant tous les emails.
     */
    public static ArrayList<String> getElements() {

        ArrayList<String> content = new ArrayList<>();

        try {
            File file = new File("src/whitelist.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine()) != null) {
                content.add(line);

            }
            fr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return content;
    }


    /**
     * Insère un nouvel email dans la whiteList.
     *
     * @param email     email a insérer dans la whiteList.
     */
    public static void insertEmail(String email) {
        File file = new File("src/whitelist.txt");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("\n" + email);
            bw.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }
    }


    /**
     * Supprime un email de la whiteList.
     *
     * @param email     email a supprimer de la whitelist.
     *
     * @return un boolean indiquant si le mail existait dans la wwhitelist.
     */
    public static boolean deleteEmail(String email) {
        ArrayList<String> content = new ArrayList<>();
        boolean exists = false;

        try {
            File file = new File("src/whitelist.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while((line = br.readLine()) != null) {
                if(!line.matches(email)) {
                    content.add(line);

                } else {
                    exists = true;
                }
            }

            fr.close();

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(String mail : content) {
                bw.write(mail + "\n");
            }

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }
}
