package com.cognizant.portfolio_management.DailySharePrice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.portfolio_management.DailySharePrice.controller.AuthClient;
import com.cognizant.portfolio_management.DailySharePrice.model.AuthResponse;
import com.cognizant.portfolio_management.DailySharePrice.model.ShareDetails;
import com.cognizant.portfolio_management.DailySharePrice.respository.ShareRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ShareDetailsServiceTest {
	
	@InjectMocks
	ShareDetailsService service;
	
	@Mock
	private ShareRepository repository;
	
	
	@Mock
	private AuthClient authClient;
	
	@Test
	public void testGetAllShares() {
		
		List<ShareDetails> fundList=new ArrayList<>();
		fundList.add(new ShareDetails("1001","FundName1", 50000.0));
		fundList.add(new ShareDetails("1002","FundName2", 60000.0));
		when(repository.findAll()).thenReturn(fundList);
		assertEquals(fundList, service.getAllShares());
	}

	@Test
	public void testGetSharebyName() {
		ShareDetails shareDetails = new ShareDetails("1002","FundName2", 60000.0);
		when(repository.findByShareName("FundName2")).thenReturn(shareDetails);
		assertEquals(shareDetails, service.getSharebyName("FundName2"));
	}

	@Test
	public void testGetSharebyIdList() {
		List<ShareDetails> fundList=new ArrayList<>();
		fundList.add(new ShareDetails("1001","FundName1", 50000.0));
		fundList.add(new ShareDetails("1002","FundName2", 60000.0));
		List<String> shareId=new ArrayList<>();
		shareId.add(fundList.get(0).getShareId());
		shareId.add(fundList.get(1).getShareId());
		List<Double> sharePrices=new ArrayList<>();
		sharePrices.add(50000.0);
		sharePrices.add(60000.0);
		when(repository.findByShareId(shareId)).thenReturn(fundList);
		assertEquals(sharePrices, service.getSharebyIdList(shareId));
	}

	@Test
	public void testIsSessionValid() {
		String token="token";
		AuthResponse authResponse = new AuthResponse("1005", "rajeev", true);
		when(authClient.getValidity(token)).thenReturn(authResponse);
		assertEquals(true, service.isSessionValid(token));
	}

}
