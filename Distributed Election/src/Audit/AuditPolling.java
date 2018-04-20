/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Database.CitizenRepository;
import Database.Parameter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuditPolling implements Runnable {
     private Connection connection;
    private Thread thread;

    public AuditPolling(Connection connection) {
        this.connection = connection;
        this.thread = new Thread(this);
        this.thread.start();
        
    }

    @Override
    public void run() {
         try {
             Message message = connection.ReceveMessage();
             if(message.getSide()==MessageSide.Polling && message.getKey()== MessageKey.Verification){
                String VoterId =message.getValue();
                Message messageresult;
                if(VoterIdExist(VoterId)){
                    messageresult = new Message(Message.MessageKey.Accept,Message.MessageSide.Audit,"");               
                }
                else{
                   messageresult = new Message(Message.MessageKey.Reject,Message.MessageSide.Audit,"");
                }
                connection.SendMessage(messageresult);
             }

         } catch (IOException ex) {
             Logger.getLogger(AuditPolling.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    private boolean VoterIdExist(String VoterId) {
        CitizenRepository CR = new CitizenRepository();       
        return(CR.Search(new Parameter("VoterId",VoterId)).size()>0);
    }
    
}
