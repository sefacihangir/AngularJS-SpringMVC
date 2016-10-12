package com.artsoft.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.Base64Utils;

import com.mysql.jdbc.util.Base64Decoder;

public class PasswordUtil {
	
	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
	private static final byte[] KEY_VALUE = new byte[]{'T','h','i','s','i','s','S','e','c','r','e','t','K','e','y'};
	
	public static final String encryptPassword(String password){
		Key key;
		String encryptedPassword = "";
		
		try {
			key = generateKey();
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byteData = cipher.doFinal(password.getBytes());
			encryptedPassword = Base64Utils.encode(byteData).toString(); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encryptedPassword;
	}
	
	
	public static final String decryptPassword(String password){
		
		String decryptedPassword = "";
	    SecureRandom random = new SecureRandom();
	    byte salt[] = new byte[8];
	    random.nextBytes(salt);
		
	    System.out.println("HERE");
	    
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
			
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secret);
			decryptedPassword = new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return decryptedPassword;
	}
	
	
	private static Key generateKey() throws Exception{
		Key key = new SecretKeySpec(KEY_VALUE, ALGORITHM);
		return key;
	}
	
}
