package com.cognizant.portfolio_management.DailySharePrice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.portfolio_management.DailySharePrice.model.ShareDetails;
import com.cognizant.portfolio_management.DailySharePrice.service.ShareDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class ShareDetailsController {
	
	
	@Autowired
	private ShareDetailsService service;
	
	
	@GetMapping("/")
	public ResponseEntity<?> home(){
		log.info("START");
		log.info("welcome");
		log.info("END");
		return new ResponseEntity<>("welcome", HttpStatus.OK);
	}
	
	
	
	@GetMapping("/dailyAllSharePrice")
	public List<ShareDetails> getAllDailySharePrice(@RequestHeader("Authorization") String token){
		if(service.isSessionValid(token)) {
			return service.getAllShares();
		}
		return null;
	}
	

	@GetMapping("/dailySharePrice/name/{shareName}")
	public ShareDetails getDailySharePrice(@RequestHeader("Authorization") String token,@PathVariable String shareName){
		if(service.isSessionValid(token)) {
			return service.getSharebyName(shareName);
		}
		return null;
	}
	
	
	
	@GetMapping("/dailySharePrice/{shareId}")
	public List<Double> getDailySharePriceByIDList(@RequestHeader("Authorization") String token,@PathVariable(value="shareId") List<String> shareId){
		if(service.isSessionValid(token)) {
			return service.getSharebyIdList(shareId);
		}
		return null;
	}
	
}
