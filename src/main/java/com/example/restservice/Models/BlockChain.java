package com.example.restservice.Models;

import com.example.restservice.Controllers.RestController;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.lang.String;
@Getter
@Setter

public class BlockChain {
    private static final Logger logger= LoggerFactory.getLogger(BlockChain.class);
    private final String index;
    private Date date = null;
    private final BlockPayload data;
    private final String precedingHash;
    private final String hash;

    public BlockChain(String index, Date date, BlockPayload data, String precedingHash, String hash) {
        this.index = index;
        this.data = data;
        this.precedingHash = precedingHash;
        this.date = date;
        this.hash = hash;
    }
}