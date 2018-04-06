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
    
    private  ArrayList<Vote> Votes=new ArrayList<>();
    private String PreviousHash;
    private String Hash;
    private long timeStamp;
    private int nonce;

    public String getPreviousHash() {
        return PreviousHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }
    
    public Block(String previoushash){
        this.PreviousHash=previoushash;
        this.timeStamp = new Date().getTime();
        this.Hash = this.ReCalculateHash();
        
    }
    public String ReCalculateHash(){
        String calculatedhash = HashUtil.applySha256( 
   this.PreviousHash + Long.toString(this.timeStamp) + Integer.toString(this.nonce)  
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
        this.timeStamp = timeStamp;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
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
			nonce ++;
			this.Hash = this.ReCalculateHash();
                       
		}
		System.out.println("Block Mined!!! : " + this.nonce);
	}
   
    
}

