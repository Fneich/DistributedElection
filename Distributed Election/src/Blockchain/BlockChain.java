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
public class BlockChain {
    
     private List<Block> BlockChain;
     private int Difficulity;

    public BlockChain(int Difficulity) {
        this.Difficulity = Difficulity;
        this.BlockChain = new ArrayList<Block>();
    }

    public List<Block> getBlockChain() {
        return BlockChain;
    }

    public int getDifficulity() {
        return Difficulity;
    }

    public void setBlockChain(List<Block> blockChain) {
        this.BlockChain = blockChain;
    }

    public void setDifficulity(int Difficulity) {
        this.Difficulity = Difficulity;
    }
    
    public Block getFirstBlock(){
        if(this.BlockChain.size()>0){
            return this.BlockChain.get(0);
        }
        else{return null;}
    }
    
    public Block getLastBlock(){
        int Count=this.BlockChain.size();
        if(Count>0){
            return this.BlockChain.get(Count-1);
        }
        else{return null;}   
    }
    
    
    public boolean isFirstBlockValid(){
        Block FirstBlock=this.BlockChain.get(0);
        if(FirstBlock==null){
        return false;
        }
        if(FirstBlock.getPreviousHash()!=null){
            return false;
        }
        if(FirstBlock.getHash()==null ||  !FirstBlock.getHash().equals(FirstBlock.ReCalculateHash())){
            return false;
        }
        return true;
    }
    
    
    public  boolean isBlockValid(Block block,Block previousBlock) {
        
        return false;
    }    
    
    
    public  boolean isBlockChainValid(List blockChain) {
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
    
    public  void addBlock(Block b) {
        this.BlockChain.add(b);
        System.out.println(isBlockChainValid(this.BlockChain));
    }
}
