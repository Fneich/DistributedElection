/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.ClientConnection;
import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageSide;
import Communications.ServerConnection;
import java.io.IOException;

/**
 *
 * @author Fneich
 */
public class Site2 {
     public static void main(String args[]) throws IOException{
       
         //ServerConnection c = new ServerConnection("localhost",MessageSide.Voter);
         Connection c = new Connection("localhost",10000,MessageSide.Voter);
        c.OpenConnection();
        System.out.println(c.getPort());
         while(true){
        Message message=c.WaitMessage();
        System.out.println(message.getValue());
         }

    }
}
