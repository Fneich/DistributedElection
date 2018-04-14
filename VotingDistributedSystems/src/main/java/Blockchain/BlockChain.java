/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fneich
 */
public class BlockChain {
    
     private List<Block> Blocks;
     private int Difficulity;

    public BlockChain(int Difficulity) {
        this.Difficulity = Difficulity;
        this.Blocks = new ArrayList<Block>();
    }
    
     public BlockChain(int Difficulity,Block... blocks) {
        this.Difficulity = Difficulity;
        this.Blocks = new ArrayList<Block>();
        int count=0;
        for(Block block:blocks){
            if(count==0){
                this.createGenesisBlock(block);
                count=1;
            }
            else
            {
                this.addBlock(block);
            }
        }
        
    }

    public List<Block> getBlocks() {
        return Blocks;
    }

    public int getDifficulity() {
        return Difficulity;
    }

    public void setBlockChain(List<Block> blockChain) {
        this.Blocks = blockChain;
    }

    public void setDifficulity(int Difficulity) {
        this.Difficulity = Difficulity;
    }
    
    public Block getFirstBlock(){
        if(this.Blocks.size()>0){
            return this.Blocks.get(0);
        }
        else{return null;}
    }
    
    public Block getLastBlock(){
        int Count=this.Blocks.size();
        if(Count>0){
            return this.Blocks.get(Count-1);
        }
        else{return null;}   
    }
    
    
    public boolean isFirstBlockValid(){
        Block FirstBlock=this.Blocks.get(0);
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
    
    
    public  boolean isBlockValid(Block previousBlock,Block block) {
        if(block != null && previousBlock !=null){
            if(block.getPreviousHash()==null || !block.getPreviousHash().equals(previousBlock.getHash())){
                return false;
            }
            if(block.getHash()==null || !block.ReCalculateHash().equals(block.getHash())){
                return false;
            }
            return true;
        }
        return false;
    }    
    
    
    public  boolean isBlockChainValid(List blockChain) {
        if(!this.isFirstBlockValid()){return false;}
        if (blockChain.size() > 1) {
            for (int i = 1; i <= blockChain.size()-1; i++) {
                Block previousBlock = (Block)blockChain.get(i-1);
                Block currentBlock = (Block)blockChain.get(i);
                String hashTarget = new String(new char[this.Difficulity]).replace('\0', '0');
                if(!isBlockValid(previousBlock,currentBlock)){
                    return false;
                }
                if(!currentBlock.getHash().substring( 0, this.Difficulity).equals(hashTarget)) {
                    return false;
                }
   
            }
        }   
        return true;
    }
    
    public boolean BlockChainIsEmpty(){
        if(this.Blocks.size()>0)
        {
            return false;
        }
        else{
            return true;
        }
    }
    
    public  void addBlock(Block b) {
        b.setPreviousHash(this.getLastBlock().getHash());
        b.setHash( b.ReCalculateHash());
        b.mineBlock(this.Difficulity);
        this.Blocks.add(b);
        System.out.println(isBlockChainValid(this.Blocks));
    }
    
    public boolean createGenesisBlock(Block GenesisBlock){
        if(this.BlockChainIsEmpty())
        {
            GenesisBlock.setPreviousHash(null);
            GenesisBlock.setHash(GenesisBlock.ReCalculateHash());
            GenesisBlock.mineBlock(this.Difficulity);
            this.Blocks.add(GenesisBlock);
            return true;     
        }
        else{return false;}
    }
    
    public String toString(){
        
        return (new Gson()).toJson(this);
    }
    
}
