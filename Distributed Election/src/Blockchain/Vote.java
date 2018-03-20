/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockchain;



public class Vote implements IVote{
    
private IVoter Voter;
private IElect Elect;

public Vote(IVoter voter,IElect elect){
this.Voter=voter;
this.Elect=elect;
}

    @Override
    public IVoter getVoter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IElect getElect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
