package com.cognizant.calculateNetworth.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthResponseTest {
	
	AuthResponse authResponse=new AuthResponse();
	
	@Test
	void testSetUid() {
		authResponse.setUid("uid1");
		assertEquals("uid1",authResponse.getUid());
	}

	@Test
	void testSetName() {
		authResponse.setName("name");
		assertEquals("name",authResponse.getName());
	}

	@Test
	void testSetValid() {
		authResponse.setValid(true);
		assertEquals(true,authResponse.isValid());
	}
	@Test
	void testAllArgsConstructor() {
		AuthResponse authResponse2 =new AuthResponse("1","abcd",true);
		assertEquals(true,authResponse2.isValid());
	}

}
