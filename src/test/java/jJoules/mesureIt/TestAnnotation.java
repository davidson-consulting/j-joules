/**
 * 
 */
package jJoules.mesureIt;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author sanoussy
 *
 */
@MesureIt
class TestAnnotation {
	
	@Test
	void notMesureItTest() throws InterruptedException {
		assertTrue(true);
	}
	
	@Test
	@MesureIt
	@DisplayName("testMesureIt")
	void mesureItTest() throws InterruptedException {
		assertTrue(true);
	}
}
