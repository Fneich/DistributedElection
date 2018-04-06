/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fneich
 */
public class Test {
    static List<Block> blockChain = new ArrayList<Block>();
    
     private static boolean isBlockChainValid(List blockChain) {
 if (blockChain.size() > 1) {
  for (int i = 1; i <= blockChain.size()-1; i++) {
   Block currentBlock = (Block)blockChain.get(i-1);
   Block nextBlock = (Block)blockChain.get(i);
    String hashTarget = new String(new char[2]).replace('\0', '0');
   if (!(currentBlock.getHash().equals(currentBlock.ReCalculateHash()))) {
    return false;
  }
   if (!(nextBlock.getPreviousHash().equals(currentBlock.getHash()))) {
    return false;
  }
   if(!currentBlock.getHash().substring( 0, 2).equals(hashTarget)) {
	System.out.println("This block hasn't been mined");
	return false;
	}
   
   }
 }
 return true;
}
    
     public static void addBlock(Block b) {
  blockChain.add(b);
  System.out.println(isBlockChainValid(blockChain));
 }
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
     
     
     
     
     Block b1 = new Block("0");
     b1.AddVote(v1);b1.AddVote(v2);
     addBlock(b1); 
     blockChain.get(0).mineBlock(2);

     Block b2=new Block(b1.ReCalculateHash());
     b2.AddVote(v3);b2.AddVote(v4);b2.AddVote(v5);
     addBlock(b2);
     blockChain.get(1).mineBlock(2);
     
     
     Block b3=new Block(b2.ReCalculateHash());
     b3.AddVote(v6);
     b3.AddVote(v7);
     addBlock(b3);
   blockChain.get(2).mineBlock(2);
     blockChain.get(0).mineBlock(2);
     System.out.println(isBlockChainValid(blockChain));
    }
}
