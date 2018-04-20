/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Fneich
 */
public class AuditProgram {
    
   
    
    public static void main(String args[]) throws IOException, InterruptedException{

        Data.LoadCitizensData();
        Random r=new Random();
        
        AuditMaster auditMaster = new AuditMaster(2);
        auditMaster.addPollingSite();
        auditMaster.StartElection();
        //Data.GenerateVoterId();
        //Data.SaveCitizensData();
    }
}
