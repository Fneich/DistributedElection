/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;


import Communications.Connection;
import Communications.Hoster;
import Communications.Message;
import Communications.Message.MessageKey;
import Communications.Message.MessageSide;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author Fneich
 */
public class Site1 {
     public static void main(String args[]) throws IOException, InterruptedException{
         Hoster h = new Hoster("","localhost",10000,MessageSide.Voter);        
         Connection con = h.getConnection();
         while(con==null){con = h.getConnection();}
 
         
         System.out.println("Site1");
          System.out.println("------------------------------------");
          Scanner sc = new Scanner(System.in);
          sc.next();
          con.SendMessage(new Message(MessageKey.Disconnect,MessageSide.Voter,"hello"));
    }
}
