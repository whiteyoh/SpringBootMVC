package com.example.restservice.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor

@Component
public class BlockChainPayload {
    private static final Logger logger= LoggerFactory.getLogger(BlockChainPayload.class);
    private ArrayList<BlockPayload> chain;
    private String apiKey;

    public BlockChainPayload(ArrayList chain, String apiKey) {
        this.chain = chain;
        this.apiKey = apiKey;
    }
}
