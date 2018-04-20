/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voter;

import Blockchain.Voter;
import Communications.Connection;
import Communications.Message;
import Communications.Site;
import Voter.Application.JVoterMenuForm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
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
    public static List<Site> Audits;
    public static List<Site> Pollings;
     public static void main(String args[]) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, InvalidKeySpecException{
         
         
         addPollingSite();
         addAuditSite();
         JVoterMenuForm.main(null);
  
}

    private static void addPollingSite() {
            Site p1 = new Site("localhost",2000,Message.MessageSide.Voter,Message.MessageSide.Polling,null);
            Site p2 = new Site("localhost",3000,Message.MessageSide.Voter,Message.MessageSide.Polling,null);
            Site p3 = new Site("localhost",4000,Message.MessageSide.Voter,Message.MessageSide.Polling,null);
            Site p4 = new Site("localhost",5000,Message.MessageSide.Audit,Message.MessageSide.Polling,null);
            Site p5 = new Site("localhost",6000,Message.MessageSide.Audit,Message.MessageSide.Polling,null);
            Pollings = new ArrayList<Site>();
            Pollings.add(p1);
            Pollings.add(p2);
            Pollings.add(p3);
            Pollings.add(p4);
            Pollings.add(p5);
    }

    private static void addAuditSite() {
        Site a1 = new Site("localhost",10000,Message.MessageSide.Audit,Message.MessageSide.Polling,null);
        Audits  = new ArrayList<Site>();
        Audits.add(a1);
    }
}
