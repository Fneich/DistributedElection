/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Fneich
 */
public class Block {
    
    private  ArrayList<Vote> Votes=new ArrayList<>();
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
    public void AddVote(Vote vote){
        this.Votes.add(vote);
        this.ReCalculateHash();
    }
    public int getHash(){return this.Hash;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.Votes);
        hash = 37 * hash + this.PreviousHash;
        hash = 37 * hash + this.Hash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Block other = (Block) obj;
        return true;
    }

    public ArrayList<Vote> getVotes() {
        return Votes;
    }
    
}
