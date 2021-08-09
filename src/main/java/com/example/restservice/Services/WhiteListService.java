package com.example.restservice.Services;

import com.example.restservice.Models.WhiteList;
import com.example.restservice.WhiteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class WhiteListService {
    @Autowired
    WhiteListRepository whiteListRepository;

    public List<WhiteList> getAllData()
    {
        List<WhiteList> whiteLists = new ArrayList<WhiteList>();
        whiteListRepository.findAll().forEach(whiteList -> whiteLists.add(whiteList));
        return whiteLists;
    }
}
