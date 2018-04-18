/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.ClientConnection;
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
         SocketListener sl =new SocketListener(3000);
         System.out.println("Site1");
          System.out.println("------------------------------------");
    }
}
