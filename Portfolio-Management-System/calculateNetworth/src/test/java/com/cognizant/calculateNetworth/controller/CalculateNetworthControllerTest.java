package com.cognizant.calculateNetworth.controller;

 

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.calculateNetworth.controller.NetworthContoller;
import com.cognizant.calculateNetworth.model.Asset;
import com.cognizant.calculateNetworth.model.AuthResponse;
import com.cognizant.calculateNetworth.model.SellObjectMap;
import com.cognizant.calculateNetworth.service.SellAssetService;
import com.cognizant.calculateNetworth.*;

@AutoConfigureMockMvc
@SpringBootTest
class CalculateNetworthControllerTest {
    @Autowired
    private NetworthContoller controller;

    @Mock
	private AuthClient authClient;

    @Mock
    private SellAssetService sellAssetService;
    	
    @Autowired
    private MockMvc mvc;
    
    @Test
    void contextLoads() {
       assertNotNull(controller);
   }
	
	

    
    @Test
    public void testGetAsset() throws Exception {
//        ResultActions actions = mvc.perform(get("/NetWorth/calculateNetworth/101"));
//        actions.andExpect(status().isOk());
        //actions.andExpect(jsonPath("$.shareId").value("AMZ"));

    	AuthResponse authResponse = new AuthResponse("1","test",true);
    	when(sellAssetService.isSessionValid("token")).thenReturn(true);
    	assertEquals(605900,controller.getAsset("token", 1005));
    }

 

    @Test
    public void testSellStock() throws Exception {
    	String token = "token";
    	String pid = "1005";
    	int pidInt = 1005;
    	
    	when(sellAssetService.isSessionValid("token")).thenReturn(true);
    	
    	List<Asset> allAssets = controller.getAllAssets(token, pidInt);
    	
    	SellObjectMap sell = new SellObjectMap();
    	sell.setPid(pidInt);
    	HashMap< String, Integer> sellStock = new HashMap<String, Integer>();
    	HashMap< String, Integer> sellMf = new HashMap<String, Integer>();
    
    	
    	Asset saleableStock = null;
    	Asset saleableMf = null;
    	
    	for(Asset a: allAssets) {
    		if(a.getType().equalsIgnoreCase("Share")) {
    			saleableStock  = a;
    			break;
    		}
    	}
    	
    	for(Asset a: allAssets) {
    		if(a.getType().equalsIgnoreCase("MF")) {
    			saleableMf  = a;
    			break;
    		}
    	}
    	
    	sellStock.put(saleableStock.getAssetid(), 1);
    	sellMf.put(saleableMf.getAssetid(), 1);
    	sell.setMfAssetList(sellMf);
    	sell.setStockIdList(sellStock);
    	double res = controller.sellStock("token", sell);
    	assertEquals(res,res);
    }
    
    
    @Test
    public void testHome() throws Exception {
        ResultActions actions = mvc.perform(get("/"));
        actions.andExpect(status().isOk());        
    }


}
 