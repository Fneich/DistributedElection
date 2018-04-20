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
import Communications.Message.MessageKey;
import Communications.Site;
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
        public static List<Site> AuditSites;
        private Thread threadPollingMaster;
        private int ElectionStatus =0;
        

    public PollingMaster(int id) throws IOException {
        this.Id=id;
        AuditSites = new ArrayList<Site>();

        this.threadPollingMaster = new Thread(this);
        threadPollingMaster.start();
    }
    public List<Site> getPollingSites() {
        return AuditSites;
    }
        public void addAuditSitesSite(){
            Site a1 = new Site("localhost",10000,Message.MessageSide.Audit,Message.MessageSide.Polling,null);
            Site a2 = new Site("localhost",20000,Message.MessageSide.Audit,Message.MessageSide.Polling,null);


            this.AuditSites.add(a1);
            this.AuditSites.add(a2);


        }

    @Override
    public void run() {
        Hoster hoster = new Hoster("","localhost",(this.Id+1)*1000,Message.MessageSide.Polling);
        while(true){
            if(hoster.getConnection()!=null){                
                if(hoster.getConnection().getConnectionSide()==Message.MessageSide.Voter){
                    PollingVoter audit= new PollingVoter(hoster.getConnection());                
                   }
                 System.out.println(hoster.getConnection().getConnectionSide());
                   if(hoster.getConnection().getConnectionSide()==Message.MessageSide.Audit){
                       if(hoster.getConnection().WaitMessage().getKey()==MessageKey.Begin){
                       ElectionStatus=1;
                       System.out.println("Election is Started");
                       }
                   }
                   
                   hoster.setConnection(null);
            }
        }
        
    }
    }