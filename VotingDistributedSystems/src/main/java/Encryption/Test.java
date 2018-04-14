/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class Test {
    public static void main(String[] args) 
            throws NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException,
            InvalidKeySpecException{
    AsymetricEncryption AE1 = new AsymetricEncryption();
     AsymetricEncryption AE2 = new AsymetricEncryption();
        try {
            byte[] keyBytes = AE2.getGenerateKeys().getPublicKey().getEncoded();
            String s=AE1.encryptText("Hello",keyBytes);
            System.out.println(s);
            
            
            
            
            String s1=AE2.decryptText(s);
            System.out.println(s1);
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            System.out.println("Error");            
//Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
