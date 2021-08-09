package com.example.restservice.Services;

import com.example.restservice.Models.BlockChainPayload;
import com.example.restservice.Models.BlockPayload;
import com.example.restservice.Models.CryptoBlock;
import com.example.restservice.Models.CryptoBlockChain;
import com.example.restservice.Models.MultipleLinksData;
import com.example.restservice.Models.WhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    WhiteListService whiteListService;

    public Boolean checkKeyWhiteList(String apiKey){
        
        String[] whiteList = new String[] {"18181818181818"};

        for (String element : whiteList) {

            return element.equals(apiKey);
        }

        return false;
    }
}
