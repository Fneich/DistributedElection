/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Communications.ClientConnection;
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
       
       SocketSender ss = new SocketSender(49744);
       System.out.println("Site2");
       System.out.println("------------------------------------");
    }
}
