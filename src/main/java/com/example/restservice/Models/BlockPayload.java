package com.example.restservice.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 

public class BlockPayload {
    private int index;
    private String date;
    private String sender;
    private String recipient;
    private String customData;
}
