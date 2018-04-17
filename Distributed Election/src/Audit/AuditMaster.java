/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Communications.Connection;
import Communications.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuditMaster implements Runnable {
        private int Id;
        private List<Connection> PollingStations;
        private Thread threadAuditMaster;
        

    public AuditMaster(int id) throws IOException {
        this.Id=id;
        PollingStations = new ArrayList<Connection>();

        this.threadAuditMaster = new Thread(this);
        threadAuditMaster.start();
    }


    @Override
    public void run() {
        
         ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(this.Id*10000, 100,InetAddress.getByName("localhost"));
            System.out.println("AuditMaster started  at:  " + serverSocket);
         while (true) {
            System.out.println("Waiting for a  connection...");
            final Socket activeSocket = serverSocket.accept();
            System.out.println("Received a  connection from  " + activeSocket);
               BufferedReader   socketReader = new BufferedReader(new InputStreamReader(
          activeSocket.getInputStream()));
            String inMsg = socketReader.readLine();
        System.out.println("Received from  client: " + inMsg);

            Message message = Message.fromJson(inMsg);
            if(message.getKey()==Message.MessageKey.Connect){
                
            BufferedWriter socketWriter= new BufferedWriter(new OutputStreamWriter(
            activeSocket.getOutputStream()));
             Message accept = new Message(Message.MessageKey.Accept,Message.MessageSide.Audit,"");
            socketWriter.write(accept.toJson());
            socketWriter.newLine();
            socketWriter.flush();
            }
            Audit audit= new Audit(activeSocket);
            
            
         }
        }
         catch (UnknownHostException ex) {
            Logger.getLogger(AuditMaster.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuditMaster.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    }
    

