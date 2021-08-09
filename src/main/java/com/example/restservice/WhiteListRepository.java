package com.example.restservice;

import com.example.restservice.Models.WhiteList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteListRepository extends CrudRepository<WhiteList, Integer>
{
}