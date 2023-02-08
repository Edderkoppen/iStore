package controller;

import java.security.MessageDigest;
import java.util.Objects;

public class PasswordController {
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
}
