/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import Communications.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fneich
 */
public class AuditProgram {
    
   
    
    public static void main(String args[]) throws IOException{

        Data.LoadCitizensData();
        AuditMaster auditMaster = new AuditMaster(Integer.parseInt(args[0]));

        //Data.GenerateVoterId();
        //Data.SaveCitizensData();
    }
}
