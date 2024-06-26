package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGeneratorClass implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private SecretKey clave;

	public Key getClave() {
		return clave;
	}

	public void setClave(SecretKey clave) {
		this.clave = clave;
	}

	public KeyGeneratorClass() {

	}

	public static void generate() throws Exception {
		ObjectOutputStream oos = null;
		File ficheroSecret = null;
		KeyGeneratorClass secretKeyObj = null;
		KeyGenerator keyGen;
//
		try {
			secretKeyObj = new KeyGeneratorClass();
			keyGen = KeyGenerator.getInstance(Config.ALGORITMO_KEYGEN);
			keyGen.init(Config.KEY_SIZE);
			SecretKey sk = keyGen.generateKey();
			secretKeyObj.setClave(sk);
			ficheroSecret = new File(Config.KEY_FILE_NAME);
			oos = new ObjectOutputStream(new FileOutputStream(ficheroSecret));
			oos.writeObject(secretKeyObj);
			return;
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage() + " Error de Algoritmo no encontrado");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage() + " Error de entrada o salida");
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException ex) {
				throw new Exception(ex.getMessage() + " claveObjeto es null");
			}
		}
	}
}