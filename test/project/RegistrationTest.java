package project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import jdk.jfr.Registered;

class RegistrationTest {

	private Person p1;
	private Person p2;
	private Registration reg;
	private Calendar date;

	void setup() {
		p1 = new Person("John", "Doe", 1990);
		p2 = new Person("Jane", "Doe", 1990);
		reg = new Registration();
		date = Calendar.getInstance();

		p1.setPhoneNumber("999-888-777");
	}

	@Test
	void testRegistration() {
		setup();
		assertNotEquals(reg, null);
	}

	@Test
	void testCheckForValidCard() {
		setup();
		reg.register(p1);
		assertEquals(reg.checkForValidCard(), true);
	}

	@Test
	void testGetIssuedDate() {
		setup();
		reg.register(p1);
		assertEquals(reg.getIssuedDate(), date.get(Calendar.YEAR));
	}
}
