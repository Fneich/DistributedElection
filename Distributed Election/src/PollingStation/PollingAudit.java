/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
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
    public int verification=0;
    @Override
    public void run() {
      try{
          
          Message message = new Message(MessageKey.Verification,MessageSide.Polling,"");
          connection.SendMessage(message);
          System.out.println("I send a verification");
          System.out.println("I wait result");
          Message messageresult = connection.WaitMessage();
          System.out.println("Received the result ");      
        if(message.getSide()==Message.MessageSide.Audit && message.getKey()==Message.MessageKey.Accept){verification=1;}
        else{verification=-1;}
        
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

 

 
}
