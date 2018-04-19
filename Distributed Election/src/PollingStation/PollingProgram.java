/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PollingStation;

import java.io.IOException;

/**
 *
 * @author Fneich
 */
public class PollingProgram {
    public static int Status=0;
    public static void main(String args[]) throws IOException{
        PollingMaster pm =new PollingMaster(1);
    }
    
}
