package com.rll.core.worldmap;

import com.badlogic.gdx.math.Vector2;

/**
 * Simple line class on a 2D plane.
 * @author jyurkiw
 * @version 1.0
 */
public class Line {
	public float x1, x2, y1, y2;
	
	public Line(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Line(float[] array) {
		x1 = array[0];
		y1 = array[1];
		x2 = array[2];
		y2 = array[3];
	}
	
	/**
	 * Get the midpoint between xy1 and xy2.
	 * @return The midpoint.
	 */
	public Vector2 getMidpoint() {
		return new Vector2((x1 + x2) / 2, (y1 + y2) / 2);
	}
	
	/**
	 * Get the midpoint between xy1 and xy2.
	 * @param x1 x1
	 * @param y1 y1
	 * @param x2 x2
	 * @param y2 y2
	 * @return The midpoint.
	 */
	public static Vector2 getMidpoint(float x1, float y1, float x2, float y2) {
		return new Vector2((x1 + x2) / 2, (y1 + y2) / 2);
	}
	
	/**
	 * Determine if two points fall on the same side of this line.
	 * @param a Point a.
	 * @param b Point b.
	 * @return true if both lines fall on the same side of the line.
	 */
	public boolean areSameSide(Vector2 a, Vector2 b) {
		return getSide(a) == getSide(b);
	}
	
	/**
	 * Determine if two points fall on the same side of this line.
	 * @param x1 x1
	 * @param y1 y1
	 * @param x2 x2
	 * @param y2 y2
	 * @return true if both lines fall on the same side of the line.
	 */
	public boolean areSameSide(float x1, float y1, float x2, float y2) {
		return getSide(x1, y1) == getSide(x2, y2);
	}
	
	/**
	 * Determine what side of this line the the point falss on.
	 * @param point The point.
	 * @return 1 if it falls on the right, -1 if it falls on the left, and 0 if it falls on the line.
	 */
	public int getSide(Vector2 point) {
		return getSide(point.x, point.y);
	}
	
	/**
	 * Determine what side of this line the the point falss on.
	 * @param x X
	 * @param y Y
	 * @return 1 if it falls on the right, -1 if it falls on the left, and 0 if it falls on the line.
	 */
	public int getSide(float x, float y) {
		float value = ((x2 - x1) * (y - y1)) - ((x - x1) * (y2 - y1));
		
		if (value > 0) return 1;		// right
		else if (value < 0) return -1;	// left
		else return 0;					// on the line
	}
	
	public float[] getPoints() {
		return new float[] {x1, y1, x2, y2};
	}
	
	/**
	 * Get the squared length of a line.
	 * @param x1 x1
	 * @param y1 y1
	 * @param x2 x2
	 * @param y2 y2
	 * @return The squared distance of the line. This is faster than finding the true length because we skip the expensive square root calculation.
	 */
	public static float dst2(float x1, float y1, float x2, float y2) {
		return (x2 - x1)*(x2 - y1) + (y2 - y1)*(y2 - y1);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line o = (Line)obj;
			
			return x1 == o.x1 && y1 == o.y1 && x2 == o.x2 && y2 == o.y2;
		} else return false;
	}
}
