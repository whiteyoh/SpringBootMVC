package com.example.restservice.Models;

import com.example.restservice.Exceptions.BlockChainExceptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.text.BadLocationException;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

@Getter
@Setter
@NoArgsConstructor

@Component
public class CryptoBlock {
    private static final Logger logger= LoggerFactory.getLogger(CryptoBlock.class);
    private int index;
    private String timestamp;
    private String data;
    private String precedingHash;
    private String hash;

    String getSHA256Hash(CryptoBlock row) throws BlockChainExceptions {

        String data2 = row.getIndex() + row.getPrecedingHash() + row.getTimestamp() + row.getData();
        logger.info("SHA string: " + data2);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data2.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            throw new BlockChainExceptions("Sha exception");
        }
    }

    private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

}
