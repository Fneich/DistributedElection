/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voter;

import Blockchain.Voter;
import java.io.IOException;
import java.net.UnknownHostException;



/**
 *
 * @author Fneich
 */
public class VoterProgram {
    public static int voterId=0;
     public static void main(String args[]) throws IOException, ClassNotFoundException{
         
         
         
         
         
         while(true){
    try{
       VoterRepository vr = new VoterRepository();
       vr.ConnectToAudit();
       break;
    }catch(UnknownHostException ex){System.out.println("No Audit Available !!");} 
    }
}
}
