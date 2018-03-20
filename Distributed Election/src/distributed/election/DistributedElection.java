/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributed.election;

import Blockchain.*;


/**
 *
 * @author Fneich
 */
public class DistributedElection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Vote v1=new Vote(new Voter("sami"),new Elect(1));
        Vote v2=new Vote(new Voter("ahmad"),new Elect(2));
        Vote v3=new Vote(new Voter("ali"),new Elect(1));
        Vote v4=new Vote(new Voter("jamil"),new Elect(3));
        Vote v5=new Vote(new Voter("khalil"),new Elect(2));
        Vote v6=new Vote(new Voter("ahmad"),new Elect(1));
        Vote v7=new Vote(new Voter("mounir"),new Elect(3));
        Block b1=new Block(0);b1.AddVote(v1);b1.AddVote(v2);b1.AddVote(v7);
        Block b2=new Block(b1.getHash());b2.AddVote(v3);b2.AddVote(v4);
        Block b3=new Block(b2.getHash());b3.AddVote(v5);b3.AddVote(v6);
        System.out.println("Block 1:"+b1.getHash());
        System.out.println("Block 2:"+b2.getHash());
        System.out.println("Block 3:"+b3.getHash());
    }
    
}
