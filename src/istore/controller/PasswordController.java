package istore.controller;

import java.security.MessageDigest;

public class PasswordController {

    /**
     * Hash une chaine de caractère.
     *
     * @param password      Mot de passe à hasher.
     *
     * @return le hash du mot de passe.
     */
    public static String hashPassword(String password) {
        StringBuffer hexData = new StringBuffer();

        try {
            MessageDigest obj = MessageDigest.getInstance("SHA-256");
            obj.update(password.getBytes());
            byte[] byteArray = obj.digest();

            for (byte b : byteArray) {
                hexData.append(Integer.toHexString(0xFF & b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexData.toString();
    }


    public static void main(String[] args) {
        System.out.println(PasswordController.hashPassword("1234i"));
    }
}
