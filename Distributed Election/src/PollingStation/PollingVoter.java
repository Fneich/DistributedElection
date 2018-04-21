/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Audit.Data;
import Blockchain.Vote;
import Blockchain.Voter;
import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Encryption.AsymetricEncryption;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class PollingVoter implements Runnable{
     private Connection connection;
    private Thread thread;

    @Override
    public void run() {
      try{
         
        Message message = connection.WaitMessage();       
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Result){ResultService(message);}
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Vote){VoteService(message);}
       if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Elects){ElectsService(message);}
      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public PollingVoter(Connection connection) {
        this.connection=connection;
        this.thread =new Thread(this);
        this.thread.start();
    }

    private void ResultService(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void ElectsService(Message electmessage) throws IOException, InterruptedException {
       Gson g = new Gson();
       if(electmessage.getKey()==MessageKey.Elects && electmessage.getSide()==MessageSide.Voter){
        Message response = new Message(MessageKey.Elects,MessageSide.Polling,g.toJson(PollingMaster.Elects) );
        this.connection .SendMessage(response);
        Thread.sleep(5000);
        this.connection.closeConnection();
       
       }
   
    }
    private void VoteService(Message message) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IOException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InterruptedException {
      AsymetricEncryption AE = new AsymetricEncryption();
      Gson g=new Gson();
      Message YourKey = new Message(Message.MessageKey.PublicKey,Message.MessageSide.Audit,g.toJson(AE.getGenerateKeys().getPublicKey().getEncoded()) );
      connection.SendMessage(YourKey);     
      Message YourInfo = connection.WaitMessage();
      String info=AE.decryptText(YourInfo.getValue());
      Vote v =Vote.fromJson(info);
      String votingid =v.getVoteId();
      System.out.println(votingid );
      String resultString =String.valueOf( verification(votingid));
      Message result = new Message(Message.MessageKey.Result,Message.MessageSide.Polling,resultString);
      connection.SendMessage(result);
      if(verification(votingid)){
          AddVote(v);
      }
      
      }
    

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean verification(String VoterId){
            Random r = new Random();
            int randomNum = r.nextInt(PollingMaster.AuditSites.size());
            PollingAudit pollingAudit= new PollingAudit(PollingMaster.AuditSites.get(randomNum).getConnection()); 
            while(pollingAudit.verification!=0){System.out.println("wait");}
            if(pollingAudit.verification==1){return true;}
            if(pollingAudit.verification==-1){return false;}
            
        return true;
    }
    public void AddVote(Vote v){
      
    }
}
