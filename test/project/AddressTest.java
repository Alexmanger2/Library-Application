package project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddressTest {

	private Address address;
	private Address address2;
	private Address address3;

	void setup() {
		address = new Address("testStreet", "testCity", "testZip", "testState");
	}

	@Test
	void testAddressStringStringStringString() {
		address = new Address("testStreet", "testCity", "testZip", "testState");
		address2 = new Address("testStreet", "testCity", "testZip", "testState");
		address3 = new Address("testStreet3", "testCity3", "testZip3", "testState3");

		assertEquals(address, address2);
		assertNotEquals(address, address3);

		assertEquals("testStreet", address.getStreet());
		assertEquals("testCity", address.getCity());
		assertEquals("testZip", address.getZip());
		assertEquals("testState", address.getState());

		assertTrue(address.getCity() instanceof String);

	}

	@Test
	void testSetStreet() {
		setup();
		address.setStreet("testStreet");
		assertEquals("testStreet", address.getStreet());
	}

	@Test
	void testSetCity() {
		setup();
		address.setCity("testCity");
		assertEquals("testCity", address.getCity());
	}

	@Test
	void testSetZip() {
		setup();
		address.setZip("12345");
		assertEquals("12345", address.getZip());
	}

	@Test
	void testSetState() {
		setup();
		address.setState("NY");
		assertEquals("NY", address.getState());
	}

	@Test
	void testToString() {
		setup();
		String testString = "Address [street=" + address.getStreet() + ", city=" + address.getCity() + ", zip="
				+ address.getZip() + ", state=" + address.getState() + "]";

		assertEquals(address.toString(), testString);

	}

}
