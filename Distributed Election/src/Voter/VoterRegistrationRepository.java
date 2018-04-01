/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Voter;

import Blockchain.Voter;

/**
 *
 * @author Fneich
 */
public class VoterRegistrationRepository {
    private Voter voter;

    public VoterRegistrationRepository(Voter voter) {
        this.voter = voter;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
    
    public int Register(){
    int VoterId=0;
    return VoterId;
    
    
    }
    
}
