/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Audit.AuditProgram;
import PollingStation.PollingMaster;
import Voter.Application.JVoterMenuForm;
import Voter.VoterProgram;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class Lancher {
     public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, InvalidKeySpecException{
            PollingMaster pm1 =new PollingMaster(1);
            PollingMaster pm2 =new PollingMaster(2);
            PollingMaster pm3 =new PollingMaster(3);
            PollingMaster pm4 =new PollingMaster(4);
            PollingMaster pm5 =new PollingMaster(5);
           Thread.sleep(2000);
            //AuditProgram.main(null);
           // VoterProgram.main(null);
     }
}
