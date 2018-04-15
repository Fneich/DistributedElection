/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Elect;

/**
 *
 * @author Fneich
 */
public class ElectRepository extends DocumentRepository<Elect>{
    
    public ElectRepository() {
       super(Elect.class, "Elect");
    }
    
}
