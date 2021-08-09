package com.example.restservice.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 

public class SingleLinkData {
    private String index;
    private String data;
    private String hash;
    private String previousHash;
    private String timestamp;
}
