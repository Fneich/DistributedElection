/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;


import Communications.Connection;
import Communications.Hoster;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Communications.Site;
import Database.ElectRepository;
import com.google.gson.Gson;
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
        private List<Site> PollingSites;
        private Thread threadAuditMaster;
        

    public AuditMaster(int id) throws IOException {
        this.Id=id;
        PollingSites = new ArrayList<Site>();

        this.threadAuditMaster = new Thread(this);
        threadAuditMaster.start();
    }

    public List<Site> getPollingSites() {
        return PollingSites;
    }
        public void addPollingSite(){
            Site p1 = new Site("localhost",2000,MessageSide.Audit,MessageSide.Polling,null);
            Site p2 = new Site("localhost",3000,MessageSide.Audit,MessageSide.Polling,null);
            Site p3 = new Site("localhost",4000,MessageSide.Audit,MessageSide.Polling,null);
            Site p4 = new Site("localhost",5000,MessageSide.Audit,MessageSide.Polling,null);
            Site p5 = new Site("localhost",6000,MessageSide.Audit,MessageSide.Polling,null);

            this.PollingSites.add(p1);
            this.PollingSites.add(p2);
            this.PollingSites.add(p3);
            this.PollingSites.add(p4);
            this.PollingSites.add(p5);

        }

    @Override
    public void run() {
        Hoster hoster = new Hoster("","localhost",this.Id*10000,MessageSide.Audit);
        while(true){
            try {
                if(hoster.getConnection()!=null){ 
                   if(hoster.getConnection().getConnectionSide()==Message.MessageSide.Voter){
                    AuditVoter audit= new AuditVoter(hoster.getConnection());                  
                   }
                   if(hoster.getConnection().getConnectionSide()==Message.MessageSide.Polling){
                       
                   }
                   
                   hoster.setConnection(null);
                  
                }
                
            } catch (IOException ex) {
                Logger.getLogger(AuditMaster.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
   
    public void StartElection() throws IOException, InterruptedException{
        Message message = new Message(MessageKey.Begin,MessageSide.Audit,"");
        //Message Disconnectmessage = new Message(MessageKey.Disconnect,MessageSide.Audit,"");
        for(Site s:PollingSites){
            s.connect();
            s.getConnection().SendMessage(message);
            ElectRepository ER = new ElectRepository();
            Gson g =new Gson();
           String elects =g.toJson(ER.getAll());
            Message ElectsMessage = new Message(MessageKey.Information,MessageSide.Audit,elects);       
            s.getConnection().SendMessage(ElectsMessage);
            Thread.sleep(2000);
            //s.getConnection().SendMessage(Disconnectmessage);
            s.getConnection().closeConnection();
        }
    }

   public void EndElection(){
       
       
    }


}
    

