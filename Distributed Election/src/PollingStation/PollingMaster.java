/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;


import Audit.AuditMaster;
import Audit.AuditVoter;
import Communications.Connection;
import Communications.Hoster;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fneich
 */
public class PollingMaster implements Runnable {
        private int Id;
        private List<Connection> Connections;
        private Thread threadPollingMaster;
        

    public PollingMaster(int id) throws IOException {
        this.Id=id;
        Connections = new ArrayList<Connection>();

        this.threadPollingMaster = new Thread(this);
        threadPollingMaster.start();
    }


    @Override
    public void run() {
        Hoster hoster = new Hoster("","localhost",(this.Id+1)*1000,Message.MessageSide.Polling);
        while(true){
            if(hoster.getConnection()!=null){
                PollingVoter audit= new PollingVoter(hoster.getConnection());
                Connections.add(hoster.getConnection());
                hoster.setConnection(null);
            }
        }
        
    }
    }