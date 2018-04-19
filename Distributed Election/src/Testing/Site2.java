/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.Connecter;
import Communications.Connection;
import Communications.Message;
import Communications.Message.MessageSide;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Fneich
 */
public class Site2 {
     public static void main(String args[]) throws IOException{
       

         Connecter c =new Connecter("","localhost",MessageSide.Audit);
         c.ConnectTo(10000);
         Connection con = c.getConnection();
         con.CreateSender(10000);
       System.out.println("Site2");
       System.out.println("------------------------------------");
       while(con.getLastMessage()==null){
           
       }
       System.out.println(con.getLastMessage().getValue());
       con.setLastMessage(null);
    }
}
