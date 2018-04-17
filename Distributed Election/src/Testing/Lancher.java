/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Audit.AuditProgram;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Fneich
 */
public class Lancher {
     public static void main(String args[]) throws IOException{
     ServerSocket serverSocket = new ServerSocket(0);
    System.out.println("listening on port " + serverSocket.getLocalPort());
     }
}
