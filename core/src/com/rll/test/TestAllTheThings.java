package com.rll.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.rll.core.Game;
import com.rll.core.util.Random;
import com.rll.core.worldmap.EdgeExpansionPolygonClusterWorldMap;
import com.rll.simplex.Simplex;
import com.rll.tilemap.SimpleTilemapManager;
import com.sudoplay.joise.Joise;

public class TestAllTheThings extends Game {
	private TiledMap map;
	private TiledMapRenderer renderer;
	private Texture tiles;
	private BitmapFont font;
	private SpriteBatch batch;
	private SimpleTilemapManager stm;
	
	private Joise baseTerrain, detailTerrain, doodads;
	private double baseZoomLevel, detailZoomLevel, detailWeight;
	
	private EdgeExpansionPolygonClusterWorldMap world;
	
	private final int tilemapWidth = 320, tilemapHeight = 320;
	
	public TestAllTheThings(boolean zoomOut, int mul) {
		super(zoomOut, mul);
	}
	
	@Override
	public void create() {
		super.create();
		
		stm = new SimpleTilemapManager();
		
		baseZoomLevel = 750.0;
		detailZoomLevel = 250.0;
		detailWeight = 0.25;
		
		baseTerrain = Simplex.createSimplexGenerator(6, 2, SimpleTilemapManager.MIN * (1 - detailWeight), SimpleTilemapManager.MAX * (1 - detailWeight), 100);
		detailTerrain = Simplex.createSimplexGenerator(6, 2, SimpleTilemapManager.MIN * detailWeight, SimpleTilemapManager.MAX * detailWeight, 100);
		doodads = Simplex.createSimplexGenerator(8, 2, 0, 100, 100);
		
		Random.setInstanceSeed(1435378288874L);
		
		world = new EdgeExpansionPolygonClusterWorldMap();
		world.generate();

		font = new BitmapFont();
		batch = new SpriteBatch();

		tiles = new Texture(Gdx.files.internal("SimpleTilemap.png"));
		TextureRegion[] splitTiles = TextureRegion.split(tiles, 32, 32)[0];
		map = new TiledMap();
		MapLayers layers = map.getLayers();
		TiledMapTileLayer layer = new TiledMapTileLayer(tilemapWidth, tilemapHeight, 32, 32);
		
		for (int x = 0; x < tilemapWidth; x++) {
			for (int y = 0; y < tilemapHeight; y++) {
				Cell cell = new Cell();
				
				double baseHeight = baseTerrain.get(x / baseZoomLevel, y / baseZoomLevel) + detailTerrain.get(x / detailZoomLevel, y / detailZoomLevel);
				double doodadHeight = doodads.get(x / detailZoomLevel, y / detailZoomLevel);
				int tileIndex = stm.get(baseHeight, doodadHeight, world.getTileOffset(x / (float)baseZoomLevel, y / (float)baseZoomLevel));
				cell.setTile(new StaticTiledMapTile(splitTiles[tileIndex]));
				
				layer.setCell(x, y, cell);
			}
		}
		layers.add(layer);
		
		renderer = new OrthogonalTiledMapRenderer(map);
	}
	
	@Override
	public void render() {
		super.render();
		
		camera.update();
		renderer.setView(camera);
		renderer.render();
		batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		batch.end();
	}
}
