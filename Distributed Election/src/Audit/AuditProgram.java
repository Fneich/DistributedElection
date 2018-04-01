/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audit;

import java.io.IOException;

/**
 *
 * @author Fneich
 */
public class AuditProgram {
    public static void main(String args[]) throws IOException{
        Data.LoadCitizensData();
        AuditMaster auditMaster = new AuditMaster();
        Thread threadAuditMaster = new Thread(auditMaster);
        threadAuditMaster.start();

    }
}
