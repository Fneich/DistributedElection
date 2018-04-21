/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;


public class AsymetricEncryption  {
    

    private GenerateKeys generateKeys;
    private Cipher cipher;

    public GenerateKeys getGenerateKeys() {
        return generateKeys;
    }

    
    public AsymetricEncryption() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		generateKeys = new GenerateKeys(1024);
		generateKeys.createKeys();
                this.cipher = Cipher.getInstance("RSA");
	}
    public String encryptText(String msg) 
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, 
			BadPaddingException, InvalidKeyException {
		this.cipher.init(Cipher.ENCRYPT_MODE, generateKeys.getPublicKey());
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}
       public String encryptText(String msg,byte[] keyBytes) 
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, 
			BadPaddingException, InvalidKeyException, InvalidKeySpecException {
           
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		this.cipher.init(Cipher.ENCRYPT_MODE, kf.generatePublic(spec));
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}

	public String decryptText(String msg)
		throws InvalidKeyException, UnsupportedEncodingException, 
		IllegalBlockSizeException, BadPaddingException {
		this.cipher.init(Cipher.DECRYPT_MODE, generateKeys.getPrivateKey());
                String s = new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");


		return s;
	}
    
    
    
}
