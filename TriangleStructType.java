package com.trade.struct;

import com.trade.domain.Triangle;
import com.trade.domain.Triangle.TriangleType;

public interface TriangleStructType {
	
	TriangleType getTriangleType(long sideA, long sideB, long sideC );
	
	TriangleType getTriangleType(String sideA, String sideB, String sideC );
	
	TriangleType determineTriangleType(Triangle triangle);

}
