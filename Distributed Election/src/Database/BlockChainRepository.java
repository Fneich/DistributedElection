/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Blockchain.Block;

/**
 *
 * @author Fneich
 */
public class BlockChainRepository extends DocumentRepository<Block>{
    
    public BlockChainRepository() {
        super(Block.class,"BlockChain");
    }
    
}
