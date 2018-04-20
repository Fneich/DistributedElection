/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Communications.Connection;
import Communications.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public class PollingAudit implements Runnable{
     private Connection connection;
    private Thread thread;
    @Override
    public void run() {
      try{

          System.out.println("I wait a registration");
        
        Message message = connection.WaitMessage();
    
        System.out.println("I receve a registration");
        System.out.println("Received from  client: " + message.getValue());
        
        if(message.getSide()==Message.MessageSide.Voter && message.getKey()==Message.MessageKey.Regitration){VoterService();}
      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public PollingAudit(Connection connection) {
        this.connection=connection;
        this.thread =new Thread(this);
        this.thread.start();
    }

    private void Verification(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
