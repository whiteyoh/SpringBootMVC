package com.example.restservice.Services;

import com.example.restservice.Exceptions.BlockChainExceptions;
import com.example.restservice.Models.BlockChain;
import com.example.restservice.Models.BlockChainPayload;
import com.example.restservice.Models.BlockPayload;
import com.example.restservice.Models.CryptoBlock;
import com.example.restservice.Models.CryptoBlockChain;
import com.example.restservice.Models.MultipleLinksData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BlockChainService {
    private static final Logger logger= LoggerFactory.getLogger(BlockChainService.class);
    @Autowired
    CryptoBlockChain cryptoBlockChain;
    private CryptoBlock blockChain;
    private CryptoBlock firstBlock;


    public String createChain(BlockChainPayload data) throws Exception {
        String array = this.startBlock(data);
        return array;
    }

    public Boolean verifyChainHandler(MultipleLinksData data) throws BlockChainExceptions {
        return MultipleLinksData.validateBlockChain(data);
    }
    private String startBlock(BlockChainPayload data) throws Exception {
        try{
            return cryptoBlockChain.beginChain(data);
            //return cryptoBlockChain.getDataArray();
        }catch(Exception e){
            System.out.println("---- startBlockError ------");
            throw new Exception(e.getMessage());
        }
    }

    public Boolean validateBlock(ArrayList<BlockPayload> blocks) {
        //System.out.println(blocks.toString());
        try {
            for (BlockPayload singleBlock : blocks){
                try {
                    Integer.parseInt(String.valueOf(singleBlock.getIndex()));
                } catch(Exception e) {
                    return false;
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }


    public String getBlock(){
        try{
            return cryptoBlockChain.getFirstCryptBlockChain();
        }catch(Exception e){
            return "whoops";
        }
    }
}
