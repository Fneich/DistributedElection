/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Audit.Data;
import Blockchain.Voter;
import Communications.Message;
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
public class PollingVoter implements Runnable{
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
      String inMsg = socketReader.readLine();
        System.out.println("Received from  client: " + inMsg);
       // if (inMsg.equalsIgnoreCase("bye")) {
       // break;
      //}
        Message message = Message.fromJson(inMsg);
        
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Result){ResultService(message);}
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Vote){VoteService(message);}
      
      socket.close();
      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public PollingVoter(Socket socket) {
        this.socket = socket;
        this.thread =new Thread(this);
        this.thread.start();
    }

    private void ResultService(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void VoteService(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
  
}
