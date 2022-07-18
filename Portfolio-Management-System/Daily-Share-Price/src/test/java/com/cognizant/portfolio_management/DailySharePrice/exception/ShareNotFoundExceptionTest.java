package com.cognizant.portfolio_management.DailySharePrice.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShareNotFoundExceptionTest {

	@BeforeEach
	void setUp() throws Exception {
		ShareNotFoundException ex = new ShareNotFoundException("Share Not Found");
	}

	@Test
	void testShareNotFoundException() {
		assertEquals("Share Not Found","Share Not Found");
	}

}
