/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;


import Communications.Connection;
import Communications.Hoster;
import Communications.Message;
import Communications.Message.MessageSide;
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
        private List<Connection> Connections;
        private Thread threadAuditMaster;
        

    public AuditMaster(int id) throws IOException {
        this.Id=id;
        Connections = new ArrayList<Connection>();

        this.threadAuditMaster = new Thread(this);
        threadAuditMaster.start();
    }


    @Override
    public void run() {
        Hoster hoster = new Hoster("","localhost",this.Id*10000,MessageSide.Audit);
        while(true){
            try {
                if(hoster.getConnection()!=null){           
                   AuditVoter audit= new AuditVoter(hoster.getConnection());
                   Connections.add(hoster.getConnection());
                   hoster.setConnection(null);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(AuditMaster.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    }
    

