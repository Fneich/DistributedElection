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
public interface ICitizen {
    
      public int VotingRegister(Voter voter);
      public Boolean CanVoting(Voter voter);
}