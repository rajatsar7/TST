package com.trade.domain;

public class Triangle {
	
	public enum TriangleType {
		EQUILATERAL, 
		ISOSCELES,
		SCALENE;
	}
	
	public String sideA;
	public String sideB;
	public String sideC;
	public TriangleType type;
	
	public String getSideA() {
		return sideA;
	}
	public void setSideA(final String sideA) {
		this.sideA = sideA;
	}
	
	public String getSideB() {
		return sideB;
	}
	public void setSideB(final String sideB) {
		this.sideB = sideB;
	}
	
	public String getSideC() {
		return sideC;
	}
	public void setSideC(final String sideC) {
		this.sideC = sideC;
	}
	
	public TriangleType getType() {
		return type;
	}
	public void setType(final TriangleType type) {
		this.type = type;
	}

}
