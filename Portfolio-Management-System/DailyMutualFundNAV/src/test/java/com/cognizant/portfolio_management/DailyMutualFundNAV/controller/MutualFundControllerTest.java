package com.cognizant.portfolio_management.DailyMutualFundNAV.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portfolio_management.DailyMutualFundNAV.model.AuthResponse;
import com.cognizant.portfolio_management.DailyMutualFundNAV.model.MutualFund;
import com.cognizant.portfolio_management.DailyMutualFundNAV.service.MutualFundService;

@SpringBootTest
@AutoConfigureMockMvc
class MutualFundControllerTest {

	@Mock
	MutualFundService service;
	
	@InjectMocks
	MutualFundController controller;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	void testgetAllMutualFundTokenValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(true);
		List<MutualFund> fundList=new ArrayList<>();
		fundList.add(new MutualFund("1001","FundName1", 50000.0));
		fundList.add(new MutualFund("1002","FundName2", 60000.0));
		when(service.getAllMutualFund()).thenReturn(fundList);
		assertEquals(HttpStatus.OK, controller.getAllMutualFund(token).getStatusCode());
	}
	
	@Test
	void testgetAllMutualFundTokenInvalid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(false);
		List<MutualFund> fundList=new ArrayList<>();
		fundList.add(new MutualFund("1001","FundName1", 50000.0));
		fundList.add(new MutualFund("1002","FundName2", 60000.0));
		when(service.getAllMutualFund()).thenReturn(fundList);
		assertEquals(HttpStatus.UNAUTHORIZED, controller.getAllMutualFund(token).getStatusCode());
	}
	

	@Test
    public void testHome() throws Exception {
        ResultActions actions = mvc.perform(get("/"));
        actions.andExpect(status().isOk());        
    }
	
	
	
	@Test
	void testGetDailyMutualFundNavTokenValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(true);
		MutualFund mutualFund = new MutualFund("1001","FundName1", 50000.0);
		when(service.getMutualFundByName("FundName1")).thenReturn(mutualFund);
		assertEquals(mutualFund.getMutualFundId(), controller.getDailyMutualFundNav(token,"FundName1").getMutualFundId());
	}
	
	@Test
	void testGetDailyMutualFundNavTokenInValid() throws Exception{
		String token="token";
		when(service.isSessionValid(token)).thenReturn(false);		
		assertEquals(null, controller.getDailyMutualFundNav(token,"FundName1"));
	}
	
	
	@Test
	void testGetDailyMutualFundNavByIdTokenValid() throws Exception{
		String token="token";
		List<String> ids = new ArrayList<>();
		List<Double> ids2 = new ArrayList<>();
		ids.add("AMZ");
		when(service.isSessionValid(token)).thenReturn(true);
		List<Double> navById = controller.getDailyMutualFundNavById(token, ids);
		assertEquals(ids2, navById);
	}
	
	@Test
	void testGetDailyMutualFundNavByIdTokenInValid() throws Exception{
		String token="token";
		List<String> ids = new ArrayList<>();
		ids.add("AMZ");
		when(service.isSessionValid(token)).thenReturn(false);
		assertEquals(null, controller.getDailyMutualFundNavById(token, ids));
	}
}
