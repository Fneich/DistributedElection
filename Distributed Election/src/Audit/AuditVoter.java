/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Blockchain.Voter;
import Communications.Connection;
import Communications.Message;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class AuditVoter implements Runnable {
     private Connection connection;
    private Thread thread;

    @Override
    public void run() {
      try{

       //socketReader = new BufferedReader(new InputStreamReader(connection.get.getInputStream()));
       //socketWriter = new BufferedWriter(new OutputStreamWriter(connection.getActiveSocket().getOutputStream()));
        System.out.println("I wait a registration");
        
        Message message = connection.WaitMessage();
    
        System.out.println("I receve a registration");
        System.out.println("Received from  client: " + message.getValue());
        
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Regitration){VoterService();}
       // if(message.getSide()==Message.MessageSide.Polling && message.getKey()==Message.MessageKey.Information){VoterService(message);}

      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public AuditVoter(Connection connection) throws IOException {
        this.connection = connection;
        this.thread =new Thread(this);
        this.thread.start();
    }
    
    public void VoterService() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException{
    
    
      AsymetricEncryption AE = new AsymetricEncryption();
      Gson g=new Gson();
      Message YourKey = new Message(Message.MessageKey.PublicKey,Message.MessageSide.Audit,g.toJson(AE.getGenerateKeys().getPublicKey().getEncoded()) );
      connection.SendMessage(YourKey);     
      Message YourInfo = connection.WaitMessage();
      String info=AE.decryptText(YourInfo.getValue());
      Voter v =Voter.fromJson(info);
      String votingid =Data.getVotingId(v);
      System.out.println(votingid );
        Message MyKey = connection.WaitMessage();
      if(MyKey.getKey()==Message.MessageKey.PublicKey){
          AsymetricEncryption AE2 = new AsymetricEncryption();
          String votingidString = String.valueOf(votingid); 
          java.lang.reflect.Type listType = new TypeToken<byte[]>(){}.getType();
          byte[] b =g.fromJson(MyKey.getValue(),listType);
          String VotingIdEncrypted =AE2.encryptText(votingidString, b);
          Message YourId = new Message(Message.MessageKey.Information,Message.MessageSide.Audit,VotingIdEncrypted);
          connection.SendMessage(YourId); 

      }
    
    }
}
