/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Fneich
 */
public class Block {
    
    private  ArrayList<Vote> Votes;
    private String PreviousHash;
    private String Hash;
    private long TimeStamp;
    private int Nonce;

    public String getPreviousHash() {
        return PreviousHash;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

    public int getNonce() {
        return Nonce;
    }
    
    public Block(String previoushash){
        this.Votes=new ArrayList<>();
        this.PreviousHash=previoushash;
        this.TimeStamp = new Date().getTime();
        this.Hash = this.ReCalculateHash();
        
    }
    public Block(String previoushash,long timeStamp){
        this.Votes=new ArrayList<>();
        this.PreviousHash=previoushash;
        this.TimeStamp = timeStamp;
        this.Hash = this.ReCalculateHash();
        
    }
     public Block(ArrayList<Vote> votes,String previoushash){
        this.Votes=votes;
         this.PreviousHash=previoushash;
        this.TimeStamp = new Date().getTime();
        this.Hash = this.ReCalculateHash(); 
    }
     
      public Block(ArrayList<Vote> votes,String previoushash,long timeStamp){
        this.Votes=votes;
         this.PreviousHash=previoushash;
        this.TimeStamp = timeStamp;
        this.Hash = this.ReCalculateHash(); 
    }
      
      
       public Block(Vote ...votes){
        this.Votes=new ArrayList<>();
        for(Vote vote:votes){
            this.Votes.add(vote);
        }
        this.TimeStamp = new Date().getTime();
        this.Hash = this.ReCalculateHash(); 
    } 
    
     
    public String ReCalculateHash(){
        String calculatedhash = HashUtil.applySha256( 
        this.PreviousHash + Long.toString(this.TimeStamp) + Integer.toString(this.Nonce)  
                + this.Votes
   );
 return calculatedhash;
    }

    public void setVotes(ArrayList<Vote> Votes) {
        this.Votes = Votes;
    }

    public void setPreviousHash(String PreviousHash) {
        this.PreviousHash = PreviousHash;
    }

    public void setHash(String Hash) {
        this.Hash = Hash;
    }

    public void setTimeStamp(long timeStamp) {
        this.TimeStamp = timeStamp;
    }

    public void setNonce(int nonce) {
        this.Nonce = nonce;
    }
    public void AddVote(Vote vote){
        this.Votes.add(vote);
        this.Hash =this.ReCalculateHash();
    }
    public String getHash(){return this.Hash;}


    public ArrayList<Vote> getVotes() {
        return Votes;
    }
    
    public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!this.Hash.substring( 0, difficulty).equals(target)) {
			Nonce ++;
			this.Hash = this.ReCalculateHash();
                       
		}
		System.out.println("Block Mined!!! : " + this.Nonce);
	}
   
    
}

