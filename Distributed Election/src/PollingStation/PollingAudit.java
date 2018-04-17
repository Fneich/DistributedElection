/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

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
        
        if(message.getSide()==Message.MessageSide.Audit && message.getKey()==Message.MessageKey.Begin){PollingProgram.Status=1;}
        if(message.getSide()==Message.MessageSide.Audit && message.getKey()==Message.MessageKey.End){PollingProgram.Status=0;}
      
      socket.close();
      this.thread.join();
    }catch(Exception e){
      e.printStackTrace();
    }
    }

    public PollingAudit(Socket socket) {
        this.socket = socket;
        this.thread =new Thread(this);
        this.thread.start();
    }

    private void Verification(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
