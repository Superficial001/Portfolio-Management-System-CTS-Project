package com.cognizant.calculateNetworth.service;

import java.util.Map;

import com.cognizant.calculateNetworth.model.SellObjectMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.calculateNetworth.controller.AuthClient;
import com.cognizant.calculateNetworth.model.Asset;
import com.cognizant.calculateNetworth.model.AuthResponse;
import com.cognizant.calculateNetworth.reposotory.AssetRepository;
@Service
public class SellAssetService {
	
	@Autowired
	private AssetRepository repository;
	
	@Autowired
	private AuthClient authClient;

	public void deleteStockAssetWithUnits(SellObjectMap sell, int portfolioId) {
		Asset a = repository.findByPortfolioidAndAssetidAndType(portfolioId, sell.getAssetid(), sell.getType());
		int units = a.getUnits() - Integer.parseInt(sell.getUnits());
		if (units > 0) {
			a.setUnits(units);
			repository.save(a);
		} else {
			repository.delete(a);
		}
	}
	
	public Boolean isSessionValid(String token) {
		try {
			AuthResponse authResponse = authClient.getValidity(token);
		} catch (Exception e) {
			return false;
		} 
		return true;
	}
	

}
