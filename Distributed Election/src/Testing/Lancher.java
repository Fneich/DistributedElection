/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Audit.AuditProgram;
import PollingStation.PollingMaster;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Fneich
 */
public class Lancher {
     public static void main(String args[]) throws IOException{
            PollingMaster pm1 =new PollingMaster(1);
            PollingMaster pm2 =new PollingMaster(2);
            PollingMaster pm3 =new PollingMaster(3);
            PollingMaster pm4 =new PollingMaster(4);
            PollingMaster pm5 =new PollingMaster(5);
     }
}
