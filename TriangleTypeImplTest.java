package test.struct.impl;

import static org.junit.Assert.assertTrue;

import com.trade.domain.Triangle.TriangleType;
import com.trade.struct.impl.TriangleTypeImpl;
import org.junit.Test;

public class TriangleTypeImplTest {
	
	TriangleTypeImpl triangleTypeImpl = TriangleTypeImpl.getInstance();
	
	@Test
	public void testValidGetTriangleTypeWithLongValue() {
		assertTrue(triangleTypeImpl.getTriangleType(7L, 7L, 14L) == TriangleType.ISOSCELES);
		assertTrue(triangleTypeImpl.getTriangleType(7L, 7L, 7L) == TriangleType.EQUILATERAL);
		assertTrue(triangleTypeImpl.getTriangleType(7L, 5L, 4L) == TriangleType.SCALENE);
		
		assertTrue(triangleTypeImpl.getTriangleType(9223372036854775807L, 
														9223372036854775807L, 14L) == TriangleType.ISOSCELES);
		assertTrue(triangleTypeImpl.getTriangleType(9223372036854775807L, 9223372036854775807L, 
														9223372036854775807L) == TriangleType.EQUILATERAL);
		assertTrue(triangleTypeImpl.getTriangleType(9223372036854775807L, 
								6223372036854775807L, 7223372036854775807L) == TriangleType.SCALENE);
	}
	
	@Test
	public void testValidGetTriangleTypeWithStringValue() {
		assertTrue(triangleTypeImpl.getTriangleType("7", "7", "14") == TriangleType.ISOSCELES);
		assertTrue(triangleTypeImpl.getTriangleType("7", "7", "7") == TriangleType.EQUILATERAL);
		assertTrue(triangleTypeImpl.getTriangleType("7", "5", "4") == TriangleType.SCALENE);
		
		assertTrue(triangleTypeImpl.getTriangleType("9223372036854775807", 
														"9223372036854775807", "14") == TriangleType.ISOSCELES);
		assertTrue(triangleTypeImpl.getTriangleType("9223372036854775807", "9223372036854775807", 
														"9223372036854775807") == TriangleType.EQUILATERAL);
		assertTrue(triangleTypeImpl.getTriangleType("9223372036854775807", 
				"6223372036854775807", "7223372036854775807") == TriangleType.SCALENE);

	}
	
	@Test
	public void testTriangleWithPrefixZero() {
		assertTrue(triangleTypeImpl.getTriangleType("000007", 
				"0007", "000000000014") == TriangleType.ISOSCELES);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithZero() {
		triangleTypeImpl.getTriangleType(7, 0, 14);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithNegativeValue() {
		triangleTypeImpl.getTriangleType(7, 7, -14);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityViolation() {
		triangleTypeImpl.getTriangleType(7, 11, 19);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithNullString() {
		triangleTypeImpl.getTriangleType("7", "7", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithEmptyString() {
		triangleTypeImpl.getTriangleType("7", "7", "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithZeroStringValue() {
		triangleTypeImpl.getTriangleType("7", "0", "13");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithZerosStringValue() {
		triangleTypeImpl.getTriangleType("00002", "00001", "000000");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleInequalityWithNegativeStringValue() {
		triangleTypeImpl.getTriangleType("7", "10", "-13");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleWithInvalidNumberString() {
		triangleTypeImpl.getTriangleType("17", "18B", "17");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTriangleWithUnicodeString() {
		triangleTypeImpl.getTriangleType("17", "18", "1&#0327");
	}	

}
