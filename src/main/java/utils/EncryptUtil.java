package utils;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtil {
    public static String encrypt(String origin) {
        String encrypted = BCrypt.hashpw(origin,
                BCrypt.gensalt());
        return encrypted;
    }

    public static boolean check(String origin,
                                String encrypted) {
        try {
        return BCrypt.checkpw(origin, encrypted);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}