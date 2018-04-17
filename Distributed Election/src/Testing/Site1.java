/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.ClientConnection;
import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import Communications.ServerConnection;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Fneich
 */
public class Site1 {
     public static void main(String args[]) throws IOException{
           // ClientConnection c = new ClientConnection("localhost",10000,MessageSide.Audit);
            Connection c = new Connection("localhost",10000,MessageSide.Voter);
            c.OpenConnection();
            
            Scanner sc=new Scanner(System.in);
            while(true){              
            Message message=new Message(MessageKey.Vote,MessageSide.Audit,sc.nextLine());
            c.SendMessage(message);
            }

    }
}
