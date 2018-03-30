/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

/**
 *
 * @author Fneich
 */
public class Test {
     public static void main(String args[]){
     Elect e1 = new Elect(1,"Donald","Trump","Republican");
     Elect e2 = new Elect(2,"Hillary","Clinton","Democratic");
     Vote v1 = new Vote("2398hr9rh892u3",e1);
     Vote v2 = new Vote("2398hr9rh892u3",e2);
     Vote v3 = new Vote("2398hr9rh892u3",e2);
     Vote v4 = new Vote("2398hr9rh892u3",e1);
     Vote v5 = new Vote("2398hr9rh892u3",e1);
     Vote v6 = new Vote("2398hr9rh892u3",e1);
     Vote v7 = new Vote("2398hr9rh892u3",e2);
     Block b1 = new Block(0);
     b1.AddVote(v1);b1.AddVote(v2);
     Block b2=new Block(b1.getHash());
     b2.AddVote(v3);b2.AddVote(v4);b2.AddVote(v5);
     Block b3=new Block(b2.getHash());
     b3.AddVote(v6);
     b3.AddVote(v7);        
     System.out.println(b1.hashCode());
     System.out.println(b2.hashCode());
     System.out.println(b3.hashCode());        
     b1.getVotes().get(0).getElect().setLastName("sfsdfsdfsdf");
     System.out.println(b1.hashCode());
     System.out.println(b2.hashCode());
     System.out.println(b3.hashCode());
     
     
        
    }
}
