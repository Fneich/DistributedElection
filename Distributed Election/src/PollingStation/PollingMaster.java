/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Audit.Audit;
import Audit.AuditMaster;
import Communications.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class PollingMaster implements Runnable{
        @Override
    public void run() {
         ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(11900, 100,InetAddress.getByName("localhost"));
            System.out.println("PollingMaster started  at:  " + serverSocket);
         while (true) {
            System.out.println("Waiting for a  connection...");
            final Socket activeSocket = serverSocket.accept();
            System.out.println("Received a  connection from  " + activeSocket);
             BufferedReader socketReader = new BufferedReader(new InputStreamReader(activeSocket.getInputStream()));

        String inMsg = socketReader.readLine();
        System.out.println("Received from  client: " + inMsg);
       // if (inMsg.equalsIgnoreCase("bye")) {
       // break;
      //}
            Message message = Message.fromJson(inMsg);
            if(message.getSide()==Message.MessageSide.Audit && message.getKey()==Message.MessageKey.Connect){
                
                PollingAudit audit= new PollingAudit(activeSocket);
            
            }
            
            
            
         }
        }
         catch (UnknownHostException ex) {
            Logger.getLogger(AuditMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuditMaster.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
