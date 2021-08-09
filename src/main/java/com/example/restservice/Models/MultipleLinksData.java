package com.example.restservice.Models;

import com.example.restservice.Exceptions.BlockChainExceptions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@NoArgsConstructor

@Component
public class MultipleLinksData {
    private static final Logger logger= LoggerFactory.getLogger(MultipleLinksData.class);
    private ArrayList<SingleLinkData> data;
    private String apiKey;
    private static SingleLinkData lastBlock;
    private static SingleLinkData thisBlock;

    @Autowired
    CryptoBlock cryptoBlock;

    public MultipleLinksData(ArrayList data,String apiKey) {
        this.data = data;
        this.apiKey = apiKey;
    }

    public static boolean validateBlockChain(MultipleLinksData data) throws BlockChainExceptions {
        for (SingleLinkData singleBlock : data.getData()){
            int index = Integer.parseInt(singleBlock.getIndex());
            if(index == 0){
                lastBlock = singleBlock;
            } else {
                 // after being the first i can do some
                thisBlock = singleBlock;
                logger.info("thistimetsampt: " + thisBlock.getTimestamp());
                logger.info("lastblock: " + lastBlock.getIndex() + " - " + lastBlock.getHash());
                logger.info("previous hash: " + thisBlock.getPreviousHash());
                logger.info("this hash: " + thisBlock.getHash());
                thisBlock = singleBlock;
                CryptoBlock cryptoBlock = new CryptoBlock();
                cryptoBlock.setIndex(Integer.parseInt(thisBlock.getIndex()));
                cryptoBlock.setPrecedingHash(thisBlock.getPreviousHash());
                cryptoBlock.setData(thisBlock.getData());
                cryptoBlock.setTimestamp(thisBlock.getTimestamp());
                String hash = cryptoBlock.getSHA256Hash(cryptoBlock);

                int cIndex = cryptoBlock.getIndex();
                String cPrescedingHash = cryptoBlock.getPrecedingHash();
                String cData = cryptoBlock.getData();
                String cTimestamp = thisBlock.getTimestamp();
                if(cIndex != Integer.parseInt(thisBlock.getIndex())){
                    System.out.println("int mismatch");
                } else {
                    System.out.println(thisBlock.getIndex());
                }

                if(cPrescedingHash != thisBlock.getPreviousHash()){
                    logger.info("prev hash mismatch");
                } else {
                    System.out.println(thisBlock.getPreviousHash());
                }

                if(cTimestamp != thisBlock.getTimestamp()){
                    logger.info("timestampmismatch");
                } else {

                    logger.info(thisBlock.getTimestamp());
                }

                if(cData != thisBlock.getData()){
                    logger.info("data mismatch");
                } else {
                    logger.info(thisBlock.getData());
                }


                logger.info("I am the creatd hash: " + hash);
                logger.info(System.lineSeparator());
                if(!thisBlock.getHash().equals(hash)){
                    return false;
                    //throw new BlockChainExceptions(thisBlock.getHash() + " does not equal " + hash);
                } else {
                    logger.info(thisBlock.getHash() + " does equal " + cryptoBlock.getSHA256Hash(cryptoBlock));
                }


                if(!lastBlock.getHash().equals(thisBlock.getPreviousHash())){
                    System.out.println(lastBlock.getHash() + " " + thisBlock.getPreviousHash());
                    return false;
                    //throw new BlockChainExceptions("last has isnt the same as this previous hash");
                }

                lastBlock = singleBlock;
                return true;
            }


        }
        return false;
    }
}
