/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Fneich
 */
public class Block {
    
    private  ArrayList<IVote> Votes=new ArrayList<>();
    private  int PreviousHash;
    private int Hash;
    
    public Block(int previoushash){
        this.PreviousHash=previoushash;
        this.ReCalculateHash();
    }
    private void ReCalculateHash(){
        Object[] contents ={this.Votes.hashCode(),this.PreviousHash};
      this.Hash = Arrays.hashCode(contents);
    }
    public void AddVote(IVote vote){
        this.Votes.add(vote);
        this.ReCalculateHash();
    }
    public int getHash(){return this.Hash;}
    
}
