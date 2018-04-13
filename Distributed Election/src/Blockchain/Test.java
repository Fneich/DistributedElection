/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Fneich
 */
public class Test {
   
     public static void main(String args[]){
       
     
     
     Elect e1 = new Elect(1,"Donald","Trump","Republican");
     Elect e2 = new Elect(2,"Hillary","Clinton","Democratic");
     Vote v1 = new Vote("2398hr9rh892u3",e1);
     Vote v2 = new Vote("2398seffs892u3",e2);
     Vote v3 = new Vote("2398hr9rh892u3",e2);
     Vote v4 = new Vote("2sf892u3",e1);
     Vote v5 = new Vote("2398hr9rh892u3",e1);
     Vote v6 = new Vote("239eyety",e1);
     Vote v7 = new Vote("23iutyjtyjr9rh892u3",e2);
     
     Block b1 = new Block(v1,v2);
     Block b2 = new Block(v3,v4,v5);
     Block b3 = new Block(v6,v7);

     BlockChain BCH = new BlockChain(1,b1,b2,b3);
     System.out.println("Done !!");

    System.out.println(HashUtil.applySha256(""));
     
    }
}
