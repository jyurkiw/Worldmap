package com.rll.tilemap;

import com.rll.core.util.Random;
import com.rll.core.worldmap.EdgeExpansionPolygonClusterWorldMap;

public class SimpleTilemapManager {
	public static final int TREE = 0;
	public static final int GRASS = 1;
	public static final int S_WATER = 2;
	public static final int D_WATER = 3;
	public static final int DIRT = 4;
	public static final int HILL = 5;
	public static final int MOUNTAIN = 6;
	public static final int BUSH = 7;
	public static final int T_GRASS = 8;
	
	public static final double MIN = -100.0;
	public static final double MAX = 100.0;
	
	public double deepline;
	public double waterline;
	public double hillline;
	public double mountainline;
	
	public double shortline;
	public double tallline;
	
	public SimpleTilemapManager(int deep, int water, int hills, int mountains, int shortl, int tall) {
		deepline = (double)deep;
		waterline = (double)water;
		hillline = (double)hills;
		mountainline = (double)mountains;
		
		shortline = (double)shortl;
		tallline = (double)tall;
	}
	
	public SimpleTilemapManager() {
		deepline = -50.0;
		waterline = -0.1;
		hillline = 60.0;
		mountainline = 75.0;
		
		shortline = 50.0;
		tallline = 75.0;
	}
	
	public int getTerrain(double height) {
		if (height < deepline) return D_WATER;
		else if (height < waterline) return S_WATER;
		else if (height < hillline) return GRASS;
		else if (height < mountainline) return HILL;
		else return MOUNTAIN;
	}
	
	public int getTerrain(double height, double worldMapOffset) {
		if (height + worldMapOffset < deepline) return D_WATER;
		else if (height + worldMapOffset < waterline) return S_WATER;
		else if (height + worldMapOffset < hillline) return GRASS;
		else if (height + worldMapOffset < mountainline) return HILL;
		else return MOUNTAIN;
	}
	
	public int getWorldMapTile(float height) {
		if (height > 0) return GRASS;
		else if (height == 0) return S_WATER;
		else return D_WATER;
	}
	
	public int getDoodad(double height, int underTile) {
		if (underTile == GRASS || underTile == DIRT) {
			if (height > tallline) return TREE;
			else if (height > shortline) return BUSH;
			else return underTile;
		} else return underTile;
	}
	
	public int get(double terrainHeight, double doodadHeight) {
		int t = getTerrain(terrainHeight);
		return getDoodad(doodadHeight, t);
	}
	
	public int get(double terrainHeight, double doodadHeight, double worldMapOffset) {
		return get(terrainHeight + worldMapOffset, doodadHeight + worldMapOffset);
	}
}
