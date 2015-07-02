package com.rll.core.worldmap;

import com.badlogic.gdx.math.ConvexHull;
import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.rll.core.util.Random;

public class EdgeExpansionPolygonClusterWorldMap implements WorldMap {
	private Polygon outerPoly;
	private Array<Polygon> polys;
	private Array<Line> expansionLines;
	private ConvexHull ch;
	private EdgeExpansionPolygonClusterConfiguration config;
	
	public EdgeExpansionPolygonClusterWorldMap() {
		config = new EdgeExpansionPolygonClusterConfiguration();
	}
	
	public EdgeExpansionPolygonClusterWorldMap(EdgeExpansionPolygonClusterConfiguration config) {
		this.config = config;
	}
	
	@Override
	public float getTileOffset(float x, float y) {
		if (outerPoly.contains(x, y)) {
			if (!goodPoint(x, y))
				return config.getINSIDE_NOISE_OFFSET();
			
			return config.getPARTIAL_NOISE_OFFSET();
		} else return config.getOUTSIDE_NOISE_OFFSET();
	}
	
	@Override
	public float getTileOffset(int x, int y) {
		Rectangle rect = outerPoly.getBoundingRectangle();
		float translatedX = rect.getX() + (float)x;
		float translatedY = rect.getY() + (float)y;
		
		if (outerPoly.contains(translatedX, translatedY)) {
			if (!goodPoint(translatedX, translatedY))
				return config.getINSIDE_NOISE_OFFSET();
			
			return config.getPARTIAL_NOISE_OFFSET();
		} else return config.getOUTSIDE_NOISE_OFFSET();
	}

	@Override
	public void generate() {
		polys = new Array<Polygon>();
		expansionLines = new Array<Line>();
		ch = new ConvexHull();
		
		polys.add(getPolygon());
		
		FloatArray f = new FloatArray();

		while (polys.size < config.getNUM_POLYS()) {
			f = new FloatArray();
			
			for(Polygon poly : polys) f.addAll(poly.getVertices(), 0, poly.getVertices().length);
			outerPoly = new Polygon(ch.computePolygon(f.toArray(), false).toArray());
			
			Line expansionLine = getExpansionLine();
			
			Vector2 c = new Vector2();
			float[] ps = outerPoly.getVertices();
			GeometryUtils.polygonCentroid(ps, 0, ps.length, c);
			
			Polygon newP = getPolygon(new FloatArray(expansionLine.getPoints()), c.x, c.y);
			if (newP != null) polys.add(newP);
		}
		
		for(Polygon poly : polys) f.addAll(poly.getVertices(), 0, poly.getVertices().length);
		outerPoly = new Polygon(ch.computePolygon(f.toArray(), false).toArray());
	}

	/**
	 * Create a polygon from a random point cloud with x points where x is a value defined in the config object.
	 * @return A polygon.
	 */
	private Polygon getPolygon() {
		return getPolygon(new FloatArray(), 0, 0);
	}
	
	/**
	 * Create a new polygon from a random point cloud, with x points where x is a value defined in the config object,
	 * that fall on the opposite side of the expansion line from the given polygon centroid.
	 * @param f The float array to fill. May optionally contain an expansion line in the first four elements.
	 * @param cx Centroid x position.
	 * @param cy Centroid y position.
	 * @return A new polygon.
	 */
	private Polygon getPolygon(FloatArray f, float cx, float cy) {
		Line checkLine = null;
		Vector2 mid = new Vector2();
		float x, y;
		int attempts = 0;
		
		if (f.size > 0) {
			checkLine = new Line(f.toArray());
			mid = checkLine.getMidpoint();
		}
		
		while (f.size < config.getPRE_HULL_POINT_COUNT()) {
			int r = (int) Random.next(config.getMIN_DIST_FROM_MID(), config.getMAX_DIST_FROM_MID());
			float ang = (float) (360.0 * Random.nextDouble());
			
			x = mid.x + r * MathUtils.cosDeg(ang);
			y = mid.y + r * MathUtils.sinDeg(ang);
			
			Vector2 m = null;
			if (f.size > 2)
				m = Line.getMidpoint(f.get(f.size - 2), f.get(f.size - 1), x, y);
			
			if ((checkLine == null || !checkLine.areSameSide(cx, cy, x, y)) && goodPoint(x, y) && (m == null || goodPoint(m.x, m.y))) {
				f.add(x);
				f.add(y);
			}
			attempts++;
			
			if (attempts > config.getMAX_PT_ATTEMPTS()) return null;
		}

		Polygon p = new Polygon(ch.computePolygon(f, false).toArray()); 
		
		if (goodPoly(p)) return p;
		else return null;
	}
	
	/**
	 * Get a valid expansion line.
	 * @return A valid expansion line.
	 */
	private Line getExpansionLine() {
		Line expansionLine = null;
		
		while (expansionLine == null) {
			int pIdx = (int)Random.next((long)polys.size);
			Polygon p = polys.get(pIdx);
			float[] f = p.getVertices();
			int vIdx = (int)Random.next((f.length - 1) / 2) * 2;
			
			if (vIdx % 2 != 0) System.out.println(vIdx + " " + f.length / 2);
			float x1 = f[vIdx];
			float y1 = f[vIdx + 1];
			float x2 = f[vIdx + 2];
			float y2 = f[vIdx + 3];
			
			Line ex = new Line(x1, y1, x2, y2);
			if (!expansionLines.contains(ex, false)) expansionLine = ex;
		}
		
		expansionLines.add(expansionLine);
		return expansionLine;
	}
	
	/**
	 * Does the point fall inside an existing polygon?
	 * @param x
	 * @param y
	 * @return True if the point falls inside an existing polygon.
	 */
	private boolean goodPoint(float x, float y) {
		for (Polygon poly : polys)
			if (poly.contains(x, y)) return false;
		
		return true;
	}
	
	/**
	 * Does the poly fall inside an existing polygon?
	 * @param p Polygon to check.
	 * @return True if there is overlap.
	 */
	private boolean goodPoly(Polygon p) {
		for(Polygon poly : polys)
			if (Intersector.overlapConvexPolygons(p, poly)) return false;
		
		return true;
	}

	/**
	 * @return the outerPoly
	 */
	public Polygon getOuterPoly() {
		return outerPoly;
	}

	/**
	 * @param outerPoly the outerPoly to set
	 */
	public void setOuterPoly(Polygon outerPoly) {
		this.outerPoly = outerPoly;
	}

	/**
	 * @return the polys
	 */
	public Array<Polygon> getPolys() {
		return polys;
	}

	/**
	 * @param polys the polys to set
	 */
	public void setPolys(Array<Polygon> polys) {
		this.polys = polys;
	}

	/**
	 * @return the expansionLines
	 */
	public Array<Line> getExpansionLines() {
		return expansionLines;
	}

	/**
	 * @param expansionLines the expansionLines to set
	 */
	public void setExpansionLines(Array<Line> expansionLines) {
		this.expansionLines = expansionLines;
	}
	
	/**
	 * @return the world map bounding rectangle
	 * @return
	 */
	public Rectangle getWorldMapBoundingRect() {
		return outerPoly.getBoundingRectangle();
	}
}
