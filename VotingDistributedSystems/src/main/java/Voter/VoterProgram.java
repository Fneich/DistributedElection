/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voter;

import Blockchain.Voter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;



/**
 *
 * @author Fneich
 */
public class VoterProgram {
    public static int voterId=0;
     public static void main(String args[]) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, InvalidKeySpecException{
         
         
         
         
         
         while(true){
    try{
        Voter voter = new Voter(UUID.randomUUID(),"","scfd","kdkdd");
       VoterRegistrationRepository vr = new VoterRegistrationRepository(voter);
       vr.getVotingId();
       break;
    }catch(UnknownHostException ex){System.out.println("No Audit Available !!");} 
    }
}
}
