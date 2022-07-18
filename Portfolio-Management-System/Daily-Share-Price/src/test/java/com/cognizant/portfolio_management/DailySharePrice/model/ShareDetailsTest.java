package com.cognizant.portfolio_management.DailySharePrice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShareDetailsTest {

	private ShareDetails shareDetails;
	
	@BeforeEach
	void setUp() {
		shareDetails = new ShareDetails();
		shareDetails.setShareId("AMZ");
		shareDetails.setShareName("Amazon");
		shareDetails.setShareValue(470.0);
	}
	
	@Test
	public void allArgumentConstructorTest() {
		ShareDetails shareDetails2 = new ShareDetails(shareDetails.getShareId(), shareDetails.getShareName(), shareDetails.getShareValue());
		assertEquals(shareDetails2.getShareId(),shareDetails.getShareId());
	}
	

}
