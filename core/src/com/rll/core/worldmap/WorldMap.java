package com.rll.core.worldmap;

/**
 * WorldMap interface.
 * @author jyurkiw
 * @version 1.0
 */
public interface WorldMap {
	/**
	 * Check the TileState at the given x, y coordinate.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return -1 to 0.
	 */
	float getTileOffset(float x, float y);
	
	/**
	 * Check the tile state at the given x, y coordinate taking the WorldMap's bounding rectangle offsets into account. 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return -1 to 0.
	 */
	float getTileOffset(int x, int y);
	
	/**
	 * (Re)Generate the WorldMap. 
	 */
	void generate();
}
