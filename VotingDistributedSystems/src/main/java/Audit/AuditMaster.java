/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AuditMaster implements Runnable {

    @Override
    public void run() {
         ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(12900, 100,InetAddress.getByName("localhost"));
            System.out.println("AuditMaster started  at:  " + serverSocket);
         while (true) {
            System.out.println("Waiting for a  connection...");
            final Socket activeSocket = serverSocket.accept();
            System.out.println("Received a  connection from  " + activeSocket);
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
    

