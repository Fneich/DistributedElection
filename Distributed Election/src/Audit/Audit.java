/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Blockchain.Voter;
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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Fneich
 */
public class Audit implements Runnable{
    private Socket socket;
    private Thread thread;
     private  BufferedReader socketReader = null;
     private BufferedWriter socketWriter = null;
    @Override
    public void run() {
      try{

      socketReader = new BufferedReader(new InputStreamReader(
          socket.getInputStream()));
      socketWriter = new BufferedWriter(new OutputStreamWriter(
          socket.getOutputStream()));
       System.out.println("I wait a registration");
      String inMsg = socketReader.readLine();
      
          System.out.println("I receve a registration");
        System.out.println("Received from  client: " + inMsg);
       // if (inMsg.equalsIgnoreCase("bye")) {
       // break;
      //}
        Message message = Message.fromJson(inMsg);
        
        if(message.getSide()==MessageSide.Voter && message.getKey()==MessageKey.Regitration){VoterService(message);}

      
      socket.close();
      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public Audit(Socket socket) {
        this.socket = socket;
        this.thread =new Thread(this);
        this.thread.start();
    }
    
    public void VoterService(Message message) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException{
    
    
      AsymetricEncryption AE = new AsymetricEncryption();
      Gson g=new Gson();
      Message YourKey = new Message(MessageKey.PublicKey,MessageSide.Audit,g.toJson(AE.getGenerateKeys().getPublicKey().getEncoded()) );
      socketWriter.write(YourKey.toJson());
      socketWriter.newLine();
      socketWriter.flush();    
      String m = socketReader.readLine();
      Message YourInfo = Message.fromJson(m);
      String info=AE.decryptText(YourInfo.getValue());
      Voter v =Voter.fromJson(info); 
      String votingid =Data.getVotingId(v);
      System.out.println(votingid );
      m = socketReader.readLine();
      Message MyKey = Message.fromJson(m);
      if(MyKey.getKey()==MessageKey.PublicKey){
          AsymetricEncryption AE2 = new AsymetricEncryption();
          String votingidString = String.valueOf(votingid); 
          java.lang.reflect.Type listType = new TypeToken<byte[]>(){}.getType();
          byte[] b =g.fromJson(MyKey.getValue(),listType);
          String VotingIdEncrypted =AE2.encryptText(votingidString, b);
          Message YourId = new Message(MessageKey.Information,MessageSide.Audit,VotingIdEncrypted);
          socketWriter.write(YourId.toJson());
          socketWriter.newLine();
          socketWriter.flush();
      }
    
    }
    
}
