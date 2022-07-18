package com.cognizant.portfolio_management.DailySharePrice.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.cognizant.portfolio_management.DailySharePrice.model.ShareDetails;
import com.cognizant.portfolio_management.DailySharePrice.service.ShareDetailsService;



@SpringBootTest
@AutoConfigureMockMvc
class MutualFundControllerTest {

	@Mock
	ShareDetailsService service;
	
	@InjectMocks
	ShareDetailsController controller;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	void testGetAllDailySharePriceTokenValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(true);
		List<ShareDetails> fundList=new ArrayList<>();
		fundList.add(new ShareDetails("1001","FundName1", 50000.0));
		fundList.add(new ShareDetails("1002","FundName2", 60000.0));
		when(service.getAllShares()).thenReturn(fundList);
		assertEquals(fundList, controller.getAllDailySharePrice(token));
	}
	
	@Test
	void testGetAllDailySharePriceTokenInValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(false);
		assertEquals(null, controller.getAllDailySharePrice(token));
	}
		
	@Test
    public void testHome() throws Exception {
        ResultActions actions = mvc.perform(get("/"));
        actions.andExpect(status().isOk());        
    }
	
	
	
	@Test
	void testGetDailySharePriceTokenValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(true);
		ShareDetails shareDetails = controller.getDailySharePrice(token,"Amazon");
//		assertEquals("Amazon", shareDetails.getShareName());
		assertEquals(null, shareDetails);
	}
	
	@Test
	void testGetDailySharePriceTokenInValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(false);
		assertEquals(null, controller.getDailySharePrice(token,"Amazon"));
	}
	
	@Test
	void testGetDailySharePriceByIDListTokenValid() throws Exception{
		String token="token";
		List<String> ids = new ArrayList<>();
		List<Double> ids2 = new ArrayList<>();
		ids.add("AMZ2");
		when(service.isSessionValid(token)).thenReturn(true);
		List<Double> navById = controller.getDailySharePriceByIDList(token, ids);
		assertEquals(ids2, navById);
	}
	@Test
	void testGetDailySharePriceByIDListTokenInValid() throws Exception{
		String token="token";
		List<String> ids = new ArrayList<>();
		List<Double> ids2 = new ArrayList<>();
		ids.add("AMZ2");
		when(service.isSessionValid(token)).thenReturn(false);
		List<Double> navById = controller.getDailySharePriceByIDList(token, ids);
		assertEquals(null, navById);
	}
}
