package com.example.restservice.Models;

import com.example.restservice.Exceptions.BlockChainExceptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor

@Component
public class CryptoBlockChain {
    private static final Logger logger= LoggerFactory.getLogger(CryptoBlockChain.class);
    private int index;
    private String timestamp;
    private String data;
    private String precedingHash;
    private String computeHash;
    private CryptoBlock firstBlock;
    private JSONArray dataArray;
    private BlockChainPayload payloadData;

    public String getFirstCryptBlockChain() throws Exception {
        try{
            final CryptoBlock finalBlock = this.firstBlock;
            JSONObject jo = new JSONObject();
            jo.put("index", finalBlock.getIndex());
            jo.put("timestamp", finalBlock.getTimestamp());
            jo.put("previousHash", 0);
            jo.put("data",finalBlock.getData());
            jo.put("hash",finalBlock.getHash());
            return jo.toString();
        }catch(Exception e){
            throw new BlockChainExceptions("getFirstCryptoBlockChain: it didnt work");
        }
    }

    public String beginChain(BlockChainPayload data) {
        try{
            // Build first piece of chain
            payloadData = data;
            System.out.println("begin chain data: " + data);
            JSONArray chainArray = new JSONArray();

            CryptoBlock cryptoBlock = new CryptoBlock();
            cryptoBlock.setIndex(0);
            cryptoBlock.setPrecedingHash("0");
            cryptoBlock.setData("Opening Block");
            cryptoBlock.setTimestamp(LocalDate.now().toString());
            String hash = cryptoBlock.getSHA256Hash(cryptoBlock);
            cryptoBlock.setHash(hash);

            JSONObject chainJsonArray = this.blockToJson(cryptoBlock);
            chainArray.put(chainJsonArray);

            this.dataArray = chainArray;

            this.parsePostBlock(cryptoBlock.getHash(),cryptoBlock.getIndex(),data);
            return this.dataArray.toString();

        } catch(Exception e){
            throw new Error(e.getMessage());
        }
    }

    private String parsePostBlock(String previousHash, int index, BlockChainPayload data) throws Exception {

        for (BlockPayload element : data.getChain()) {
            int newIndex = index + 1;
            index++;
            CryptoBlock cryptoBlock = new CryptoBlock();
            cryptoBlock.setIndex(newIndex);
            cryptoBlock.setPrecedingHash(previousHash);
            cryptoBlock.setData(element.getCustomData());
            cryptoBlock.setTimestamp(LocalDate.now().toString());
            String hash = cryptoBlock.getSHA256Hash(cryptoBlock);
            cryptoBlock.setHash(hash);
            this.dataArray.put(blockToJson(cryptoBlock));
            newIndex++;
            previousHash = hash;
        }
        return this.dataArray.toString();
        //System.out.println(this.dataArray);
    }
    private JSONObject blockToJson(CryptoBlock data) throws Exception {
        try{
            final CryptoBlock block = data;
            JSONObject jo = new JSONObject();
            jo.put("index", data.getIndex());
            jo.put("timestamp", data.getTimestamp());
            jo.put("previousHash", data.getPrecedingHash());
            jo.put("data",data.getData());
            jo.put("hash",data.getHash());
            System.out.println(jo);
            return jo;
        }catch(Exception e){
            throw new Exception();
        }
    }
}
