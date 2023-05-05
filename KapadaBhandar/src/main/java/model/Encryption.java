package model;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	 private static final String ALGORITHM = "AES";
	 private static final String KEY = "ourcourseworkkey";

	public static String encrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedValue = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encryptedValue);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String decrypt(String value) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedValue = Base64.getDecoder().decode(value.getBytes());
            byte[] decryptedValue = cipher.doFinal(decodedValue);
            return new String(decryptedValue);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}