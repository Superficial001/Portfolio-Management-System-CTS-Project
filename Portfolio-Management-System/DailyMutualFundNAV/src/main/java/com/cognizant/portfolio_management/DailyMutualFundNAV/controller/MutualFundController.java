package com.cognizant.portfolio_management.DailyMutualFundNAV.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognizant.portfolio_management.DailyMutualFundNAV.exception.MutualFundNotFoundException;
import com.cognizant.portfolio_management.DailyMutualFundNAV.model.MutualFund;
import com.cognizant.portfolio_management.DailyMutualFundNAV.service.MutualFundService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class MutualFundController {
	
	
	@Autowired
	private MutualFundService service;
	
	
	
	@GetMapping("/")
	public ResponseEntity<?> home(){
		log.info("START");
		log.info("welcome");
		log.info("END");
		return new ResponseEntity<>("welcome", HttpStatus.OK);
	}
	
	
	@GetMapping("/dailyAllMutualFundNav")
	public ResponseEntity<List<MutualFund>> getAllMutualFund(@RequestHeader("Authorization") String token){
		try {
		if(service.isSessionValid(token)) {
			//return service.getAllMutualFund();
			return ResponseEntity.ok(service.getAllMutualFund());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}catch(Throwable e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	

	@GetMapping("/dailyMutualFundNav/name/{mutualFundName}")
	public MutualFund getDailyMutualFundNav(@RequestHeader("Authorization") String token,@PathVariable String mutualFundName) throws MutualFundNotFoundException{
		if(service.isSessionValid(token)) {
			return service.getMutualFundByName(mutualFundName);
		}
		return null;
	}
	
	@GetMapping("/dailyMutualFundNav/{mutualFundId}")
	public List<Double> getDailyMutualFundNavById(@RequestHeader("Authorization") String token,@PathVariable(value="mutualFundId") List<String> mutualFundIdList) throws MutualFundNotFoundException{
		if(service.isSessionValid(token)) {
			return service.getMutualFundByIdList(mutualFundIdList);
		}
		return null;
	}
}
