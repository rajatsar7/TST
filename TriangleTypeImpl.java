package com.trade.struct.impl;

import com.trade.domain.Triangle;
import com.trade.domain.Triangle.TriangleType;
import com.trade.struct.TriangleStructType;

public class TriangleTypeImpl implements TriangleStructType {

	private static volatile TriangleTypeImpl triangleTypeImpl;

	private TriangleTypeImpl() {
	}

	public static synchronized TriangleTypeImpl getInstance() {
		if (triangleTypeImpl == null) {
			triangleTypeImpl = new TriangleTypeImpl();
		}
		return triangleTypeImpl;
	}

	@Override
	public TriangleType getTriangleType(long sideA, long sideB, long sideC) {

		if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
			throw new IllegalArgumentException("Length of traingle sides cannot be equal to or less than zero");
		}

		validateTriangleInequality(String.valueOf(sideA), String.valueOf(sideB), String.valueOf(sideC));

		return findTriangleType(String.valueOf(sideA), String.valueOf(sideB), String.valueOf(sideC));

	}

	private TriangleType findTriangleType(String sideA, String sideB, String sideC) {
		if ((sideA.equals(sideB)) && (sideA.equals(sideC))) {
			return TriangleType.EQUILATERAL;
		}
		if ((sideA.equals(sideB)) || (sideB.equals(sideC)) || (sideA.equals(sideC))) {
			return TriangleType.ISOSCELES;
		}
		return TriangleType.SCALENE;
	}

	private void validateTriangleInequality(String strA, String strB, String strC) {

		if ((!compareNumberString(addTwoNumberString(strA, strB), strC))
				|| (!compareNumberString(addTwoNumberString(strA, strB), strC))
				|| (!compareNumberString(addTwoNumberString(strA, strB), strC))) {
			throw new IllegalArgumentException("Triangle inequality violated");
		}

	}

	// Compare the sum of two sides is greater than or equal to third side.
	private boolean compareNumberString(String strAdd, String strCom) {
		if ((strAdd.length() > strCom.length()) || (strAdd.equals(strCom))) {
			return true;
		}
		if (strAdd.length() == strCom.length()) {
			int pos = 0;
			while (pos < strAdd.length()) {
				if (Integer.parseInt(strAdd.substring(pos, pos + 1)) > Integer
						.parseInt(strCom.substring(pos, pos + 1))) {
					return true;
				}
				if (Integer.parseInt(strAdd.substring(pos, pos + 1)) < Integer
						.parseInt(strCom.substring(pos, pos + 1))) {
					return false;
				}
				pos++;
			}
			return true;
		}
		return false;
	}

	// Add two large string of numbers
	private String addTwoNumberString(String strA, String strB) {
		// TODO Auto-generated method stub
		int lenA = strA.length() - 1;
		int lenB = strB.length() - 1;
		int carry = 0;
		StringBuilder sbr = new StringBuilder("");

		while (lenA >= 0 || lenB >= 0) {
			try {
				if (lenA >= 0 && lenB >= 0) {
					carry = carry + Integer.parseInt(strA.substring(lenA, lenA + 1))
							+ Integer.parseInt(strB.substring(lenB, lenB + 1));
				} else if (lenA >= 0) {
					carry = carry + Integer.parseInt(strA.substring(lenA, lenA + 1));
				} else {
					carry = carry + Integer.parseInt(strB.substring(lenB, lenB + 1));
				}

				if (carry >= 10) {
					sbr.insert(0, carry - 10);
					carry = 1;
				} else {
					sbr.insert(0, carry);
					carry = 0;
				}
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException("Triangle side has invalid value");
			}

			lenA--;
			lenB--;
		}
		if (carry != 0) {
			sbr.insert(0, String.valueOf(carry));
		}
		return sbr.toString();
	}
	
	private String trimPrecedingZeros(String side) {
		int pos = 0;
		while(pos < side.length()) {
			if(side.charAt(pos) != '0') {
				break;
			}
			pos++;
			if(pos == side.length()) {
				return "0";
			}
		}
		return side.substring(pos, side.length());
	}

	@Override
	public TriangleType getTriangleType(String sideA, String sideB, String sideC) {
		if ((sideA == null || sideA.length() == 0) || (sideB == null || sideB.length() == 0)
				|| (sideC == null || sideC.length() == 0)) {
			throw new IllegalArgumentException("Length of traingle sides cannot be null or empty");
		}
		
		sideA = trimPrecedingZeros(sideA);
		sideB = trimPrecedingZeros(sideB);
		sideC = trimPrecedingZeros(sideC);
		
		validateTriangleSides(sideA, sideB, sideC);
		validateTriangleInequality(sideA, sideB, sideC);

		return findTriangleType(sideA, sideB, sideC);
	}

	private void validateTriangleSides(String sideA, String sideB, String sideC) {

		if ((sideA.length() == 1 && sideA.equals("0")) || (sideB.length() == 1 && sideB.equals("0"))
				|| (sideC.length() == 1 && sideC.equals("0"))) {
			throw new IllegalArgumentException("Length of traingle sides cannot be equal to zero");
		}
		if ((sideA.charAt(0) == '-') || (sideB.charAt(0) == '-') || (sideC.charAt(0) == '-')) {
			throw new IllegalArgumentException("Length of traingle sides cannot be less than zero");
		}
	}

	@Override
	public TriangleType determineTriangleType(Triangle triangle) {
		return getTriangleType(triangle.getSideA(), triangle.getSideB(), triangle.getSideC());
	}

	public static void main(String[] args) {
		TriangleTypeImpl triangleTypeImpl = TriangleTypeImpl.getInstance();
		System.out.println(triangleTypeImpl.getTriangleType(7L, 7L, 14L));
		System.out.println(triangleTypeImpl.getTriangleType("36598683", "36598683", "36598683"));
		System.out.println(triangleTypeImpl.getTriangleType(13L, 5L, 8L));
		System.out.println(triangleTypeImpl.getTriangleType(7223372036854775807L, 
				7223372036854775807L, 9223372036854775807L));
		System.out.println(triangleTypeImpl.getTriangleType("7223372036854775808", "7223372036854775807", 
				"9223372036854775807"));
	}

}
