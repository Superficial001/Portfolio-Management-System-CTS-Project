package com.cognizant.portfolio_management.DailyMutualFundNAV.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MutualFundTest {
	
	MutualFund mutualFund=new MutualFund();
	
	@Test
	void testSetMutualFundId() {
		mutualFund.setMutualFundId("101");
		assertEquals("101", mutualFund.getMutualFundId());
	}

	@Test
	void testSetMutualFundName() {
		mutualFund.setMutualFundName("abc");
		assertEquals("abc", mutualFund.getMutualFundName());
	}

	@Test
	void testSetMutualFundValue() {
		mutualFund.setMutualFundValue(10.0);
		assertEquals(10.0, mutualFund.getMutualFundValue());
	}


	@Test
	void testMutualFund() {
		MutualFund mf=new MutualFund();
	}

}
