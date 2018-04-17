/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import Audit.Audit;
import Audit.AuditMaster;
import java.io.IOException;
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
