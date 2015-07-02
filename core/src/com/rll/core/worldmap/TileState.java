package com.rll.core.worldmap;

/**
 * State of a tile.
 * Outside: Does not fall in the outer convex polygon.
 * Inside: Falls inside an inner polygon.
 * Partial: Falls in the outer convex polygon, but not in an inner polygon.
 * @author jyurkiw
 * @version 1.0
 */
public enum TileState {
	OUTSIDE,
	INSIDE,
	PARTIAL
}
