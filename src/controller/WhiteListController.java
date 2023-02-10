package controller;

import java.io.*;
import java.util.ArrayList;

public class WhiteListController {
    public static ArrayList<String> getElements() {
        ArrayList<String> content = new ArrayList<>();

        try {
            File file = new File("src/whitelist.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;

            while((line = br.readLine()) != null) {
                System.out.println("Ligne : " + line);
                content.add(line);
            }
            fr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static void insertEmail(String email) {
        File file = new File("src/whitelist.txt");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + email);
            bw.close();

            System.out.println("Modification terminée!");

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


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