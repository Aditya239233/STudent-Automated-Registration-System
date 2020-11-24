package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.Serializable;

public class PasswordManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			byte byteData[] = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

}
