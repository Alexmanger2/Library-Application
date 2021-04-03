package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddressTest {

	@Test
	void testAddressStringStringStringString() {
	Address address = new Address("testStreet", "testCity", "testZip", "testState");
	Address address2 = new Address("testStreet", "testCity", "testZip", "testState");
	Address address3 = new Address("testStreet3", "testCity3", "testZip3", "testState3");
	
	assertEquals(address, address2);
	assertNotEquals(address, address3);
	
	assertEquals("testStreet", address.getStreet());
	assertEquals("testCity", address.getCity());
	assertEquals("testZip", address.getZip());
	assertEquals("testState", address.getState());
	
	}

	@Test
	void testSetStreet() {
		Address address = new Address();
		address.setStreet("testStreet");
		
		assertEquals("testStreet", address.getStreet());
	}

	@Test
	void testSetCity() {
		Address address = new Address();
		address.setCity("testCity");
		assertEquals("testCity", address.getCity());
	}

	@Test
	void testSetZip() {
		Address address = new Address();
		address.setZip("12345");
		assertEquals("12345", address.getZip());
	}

	@Test
	void testSetState() {
		Address address = new Address();
		address.setState("NY");
		assertEquals("NY", address.getState());
	}

	@Test
	void testToString() {
		Address address = new Address("testStreet", "testCity", "testZip", "testState");
		
		String testString = "Address [street=" + address.getStreet() + ", city=" + address.getCity() + 
				", zip=" + address.getZip() + ", state=" + address.getState() + "]";
		
		assertEquals(address.toString(), testString);
		
	}

}
