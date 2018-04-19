/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Audit.Data;
import Blockchain.Voter;
import Communications.Connection;
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
     private Connection connection;
    private Thread thread;

    @Override
    public void run() {
      try{



        Message message = connection.ReceveMessage();
        
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Result){ResultService(message);}
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Vote){VoteService(message);}
      
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

    private void VoteService(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
  
}
