package com.example.restservice.Controllers;

import com.example.restservice.Exceptions.BlockChainExceptions;
import com.example.restservice.Models.*;
import com.example.restservice.Services.AuthenticationService;
import com.example.restservice.Services.BlockChainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	// use a formatter (google format) and run on save
	private static final Logger logger=LoggerFactory.getLogger(RestController.class);
	@Autowired
	//make these private
	private BlockChainService blockChainService;
	CryptoBlockChain cryptoBlockChain;
	CryptoBlock cryptoBlock;
	ParcelData parcelData;
	//AuthenticationService authenticationService;



	@GetMapping("/welcome")
	public String welcome(){
		return "hello world";
	}

	@PostMapping("/newItem")
	private String newItem(@RequestBody Product item){
		logger.debug("------ Entered POST /newItem with -----" + item.toString());
		logger.debug(item.getDescription());

		logger.debug("------ Leaving POST -------");
		return "everything is smashing";
	}










	@RequestMapping(value = "/payload")
	@ResponseBody
	public String apply(@RequestBody BlockChainPayload data) throws BlockChainExceptions {

		data = new BlockChainPayload(data.getChain(),data.getApiKey());
		//Boolean safeListPass = checkKeyWhiteList(data.getApiKey());

		// everytime its called it will create a new one
		// turn authentication service into singleton
		// static class, code is in memory

		// make these final
		final AuthenticationService authenticationService = new AuthenticationService();

		Boolean safeListPass = authenticationService.checkKeyWhiteList(data.getApiKey());
		if(!safeListPass){
			throw new BlockChainExceptions("401 - Unauthorized");
		}
		logger.info("------ validated api key -----");
		Boolean validBlock = blockChainService.validateBlock(data.getChain());
		try{
			if(validBlock){
				logger.info("------ i am valid ------");
				System.out.println("------ i am valid so trigger createChain ------");
				return blockChainService.createChain(data);

			}
		}catch(Exception e){
			logger.error(" rest controller invalid block");
			throw new BlockChainExceptions("Invalid block");
		}
		return "unexplained";
	}

	@RequestMapping(value = "/verify")
	@ResponseBody
	public String verify(@RequestBody MultipleLinksData data) throws Exception {
		Boolean safeListPass = checkKeyWhiteList(data.getApiKey());

		// out of box authentication  "Http authentication exception"
		// Spring authentication, authorisation header
		// spring filter header authentication
		// basic authentication header

		if(!safeListPass){
			throw new BlockChainExceptions("401 - Unauthorised");
		}
		data = new MultipleLinksData(data.getData(),data.getApiKey());
		Boolean validChain = blockChainService.verifyChainHandler(data);

		return(validChain)?"{'message':'pass'}":"{'message':'fail'}";

	}

	@RequestMapping(value = "/endpoint")
	@ResponseBody
	public String parcel(@RequestBody ParcelData data) throws BlockChainExceptions {
		return "unexplained";
	}

	public Boolean checkKeyWhiteList(String apiKey){
		String[] whiteList = new String[] {"18181818181818"};

		for (String element : whiteList) {

			return element.equals(apiKey);
		}

		return false;
	}
}
