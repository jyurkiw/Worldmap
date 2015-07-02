package com.rll.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rll.core.Game;
import com.rll.core.util.Random;
import com.rll.core.util.WorldMapTestCameraController;
import com.rll.core.worldmap.EdgeExpansionPolygonClusterWorldMap;
import com.rll.core.worldmap.Line;

public class TestEdgeExpansionPolygonClusterWorldMap extends Game {
	private ShapeRenderer sr;
	private EdgeExpansionPolygonClusterWorldMap world;
	
	public TestEdgeExpansionPolygonClusterWorldMap(boolean zoomOut, int mul) {
		super(zoomOut, mul);
	}
	
	@Override
	public void create() {
		super.create();
		
		Random.setInstanceSeed(1435378288875L);
		
		sr = new ShapeRenderer();
		world = new EdgeExpansionPolygonClusterWorldMap();
		world.generate();
		controller = new WorldMapTestCameraController(viewport, world);
		Gdx.input.setInputProcessor(controller);
		
		Rectangle r = world.getWorldMapBoundingRect();
		Vector2 rpos = new Vector2();
		r.getPosition(rpos);
		System.out.println(String.format("Bounding Rect => x: %.2f, maxX: %.2f, y: %.2f, maxY: %.2f, w: %.2f, h: %.2f, posX: %.2f, posY: %.2f", r.getX(), r.getX() + r.getWidth(), r.getY(), r.getY() + r.getHeight(), r.getWidth(), r.getHeight(), rpos.x, rpos.y));
		System.out.println("Height: " + r.height + " remainder: " + (10 - (r.height % 10)));

		System.out.println(String.format("x: %.2f, y: %.2f", 0 - r.getWidth() / 2, 0 - r.getHeight() / 2));
		
		System.out.println("Instance seed: " + Random.getInstanceSeed());
	}
	
	@Override
	public void render() {
		super.render();
		
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(camera.combined);
		
		sr.setColor(Color.GREEN);
		sr.polygon(world.getOuterPoly().getVertices());
		
		sr.setColor(Color.RED);
		for(Polygon p : world.getPolys())
			sr.polygon(p.getVertices());
		
		sr.setColor(Color.BLUE);
		for(Line l : world.getExpansionLines())
			sr.line(l.x1, l.y1, l.x2, l.y2);
		
		sr.setColor(Color.ORANGE);
		WorldMapTestCameraController cont = (WorldMapTestCameraController)controller;
		sr.x(cont.dx + 8, cont.dy + 8, 16);
		
		sr.setColor(Color.BLACK);
		Rectangle wbr = world.getWorldMapBoundingRect();
		
		float mx = wbr.getX(), my = wbr.getY(), ax = wbr.getX() + wbr.getWidth(), ay = wbr.getY() + wbr.getHeight();
		
		for (float xi = wbr.getX(); xi < wbr.getX() + wbr.getWidth(); xi+=100)
			sr.line(xi, my, xi, ay);
		
		for (float yi = wbr.getY(); yi < wbr.getY() + wbr.getHeight(); yi+=100)
			sr.line(mx, yi, ax, yi);
		
		sr.rect(wbr.getX(), wbr.getY(), wbr.getWidth(), wbr.getHeight());
		
		sr.setColor(Color.RED);
		sr.x(wbr.getX() + wbr.getWidth() / 2, wbr.getY() + wbr.getHeight() / 2, 50);
		sr.x(wbr.getX(), wbr.getY(), 50);
		
		sr.end();
	}
}
