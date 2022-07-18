package com.cognizant.calculateNetworth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cognizant.calculateNetworth.model.Asset;
import com.cognizant.calculateNetworth.model.MutualFundDetails;
import com.cognizant.calculateNetworth.model.SellObjectMap;
import com.cognizant.calculateNetworth.model.StockDetails;
import com.cognizant.calculateNetworth.service.AssetService;
import com.cognizant.calculateNetworth.service.SellAssetService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class NetworthContoller {

	@Autowired
	private ShareDetailsFiegnProxy proxy;

	@Autowired
	private MutualFundDetailsFeignProxy mutualFundProxy;

	@Autowired
	private AssetService assetservice;

	@Autowired
	private SellAssetService sellService;
	
	
	
	@GetMapping("/")
	public ResponseEntity<?> home(){
		log.info("START");
		log.info("welcome");
		log.info("END");
		return new ResponseEntity<>("welcome", HttpStatus.OK);
	}

	@GetMapping("/NetWorth/calculateNetworth/{id}")
	public double getAsset(@RequestHeader("Authorization") String token,@PathVariable(value = "id") int id) 
	{
		double netWorth = 0.0;
		if(sellService.isSessionValid(token)) {
		List<String> stockAssetList = new ArrayList<>();
		List<String> mutualFundAssetList = new ArrayList<>();
		List<Double> stockValueList = new ArrayList<>();
		List<Double> mutualFundValueList = new ArrayList<>();
		List<Asset> assets = assetservice.getAllAssetForPortfolio(id);
		// List<StockDetails> shareslist = proxy.findAll();
		for (Asset a : assets) {
			if (a.getType().equals("Share")) {
				stockAssetList.add(a.getAssetid());
			} else {
				mutualFundAssetList.add(a.getAssetid());
			}
		}
		if (!stockAssetList.isEmpty()) {
			stockValueList = proxy.finddailyShareById(token,stockAssetList);
		}
		if (!mutualFundAssetList.isEmpty()) {
			mutualFundValueList = mutualFundProxy.getMutualDetailsById(token,mutualFundAssetList);
		}
		int stockCounter = 0;
		int mfCounter = 0;
		for (Asset a : assets) {
			if (a.getType().equals("Share")) {
				netWorth += a.getUnits() * stockValueList.get(stockCounter);
				stockCounter++;
			} else {
				netWorth += a.getUnits() * mutualFundValueList.get(mfCounter);
				mfCounter++;
			}
		}
		}
		return netWorth;

	}

	@PostMapping("/NetWorth/SellAssets/{portfolioId}")
	public void sellStock(@RequestHeader("Authorization") String token, @RequestBody SellObjectMap sell, @PathVariable(value = "portfolioId") int portfolioId) {
		if (sellService.isSessionValid(token)) {
			System.out.println(portfolioId);
			System.out.println(sell.getType());
//		Map<String, Integer> stockIdList = sell.getStockIdList();
//		Map<String, Integer> mfIdList = sell.getMfAssetList();
			sellService.deleteStockAssetWithUnits(sell, portfolioId);
//		if (sell.getType().equals("MF")) {
//			sellService.deleteMutualFundAssetWithUnits(sell, portfolioId);
//		}
		}
//		return getAsset(token,sell.getPid());
	}
 
	@GetMapping("/NetWorth/GetAllAssets/{portfolioId}")
	public List<Asset> getAllAssets(@RequestHeader("Authorization") String token,@PathVariable(value = "portfolioId") int portfolioId) {
		if(sellService.isSessionValid(token)) {
			return assetservice.getAllAssetForPortfolio(portfolioId);
		}
		return null;
	}



	
}
