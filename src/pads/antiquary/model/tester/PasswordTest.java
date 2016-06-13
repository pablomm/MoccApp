package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import org.junit.Test;

import pads.antiquary.model.misc.Password;

public class PasswordTest {

	@Test
	public void testpassword1() throws Exception {
		String storeP = Password.getSaltedHash("qwerty");
		assertTrue(Password.check("qwerty", storeP));
		assertFalse(Password.check("qwertssy", storeP));
	}

	@Test(expected = Exception.class)
	public void testpassword2() throws Exception {
		Password.getSaltedHash("");
	}

	@Test(expected = Exception.class)
	public void testpassword3() throws Exception {
		assertTrue(Password.check("", "asdsdas"));
	}

}
