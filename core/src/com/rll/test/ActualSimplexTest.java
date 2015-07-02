package com.rll.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.rll.core.Game;
import com.rll.core.util.Random;
import com.rll.core.worldmap.EdgeExpansionPolygonClusterWorldMap;
import com.rll.simplex.Simplex;
import com.rll.simplex.SimplexConfiguration;
import com.rll.tilemap.SimpleTilemapManager;
import com.sudoplay.joise.Joise;

public class ActualSimplexTest extends Game {

	private TiledMap map;
	private TiledMapRenderer renderer;
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture tiles;
	private SimpleTilemapManager stm;
	
	private Joise baseTerrain, detailTerrain, doodads;
	private double baseZoomLevel, detailZoomLevel, detailWeight;
	private Rectangle worldBoundaries;
	
	private EdgeExpansionPolygonClusterWorldMap world;
	
	private int tilemapWidth, tilemapHeight;
	private final int tileSize = 32;
	private final String tileSet = "SimpleTilemap.png";
	
	public ActualSimplexTest(boolean zoomOut, int mul) {
		super(zoomOut, mul);
	}
	
	@Override
	public void create() {
		super.create();
		
		Random.setInstanceSeed(1435378288875L);
		stm = new SimpleTilemapManager();
		world = new EdgeExpansionPolygonClusterWorldMap();
		world.generate();
		
		worldBoundaries = world.getWorldMapBoundingRect();
		
		tilemapHeight = worldBoundaries.height < 320 ? (int)worldBoundaries.height : 320;
		tilemapWidth = worldBoundaries.width < 320 ? (int)worldBoundaries.width : 320;
		baseZoomLevel = 750;
		detailZoomLevel = 250;
		detailWeight = 0.25;
		
		font = new BitmapFont();
		batch = new SpriteBatch();
		
		tiles = new Texture(Gdx.files.internal(tileSet));
		TextureRegion[] splitTiles = TextureRegion.split(tiles, tileSize, tileSize)[0];
		map = new TiledMap();
		MapLayers layers = map.getLayers();
		TiledMapTileLayer layer = new TiledMapTileLayer(tilemapWidth, tilemapHeight, tileSize, tileSize);
		
		//baseTerrain = Simplex.createSimplexGenerator(6, 2, SimpleTilemapManager.MIN * (1 - detailWeight), SimpleTilemapManager.MAX * (1 - detailWeight), 100);
		//detailTerrain = Simplex.createSimplexGenerator(6, 2, SimpleTilemapManager.MIN * detailWeight, SimpleTilemapManager.MAX * detailWeight, 100);
		//doodads = Simplex.createSimplexGenerator(8, 2, 0, 100, 100);
		
		SimplexConfiguration baseConfiguration = new SimplexConfiguration();
		baseConfiguration.setMin(baseConfiguration.getMin() * 1 - detailWeight);
		baseConfiguration.setMax(baseConfiguration.getMax() * 1 - detailWeight);
		baseTerrain = Simplex.createSimplexGenerator(baseConfiguration);
		
		SimplexConfiguration detailConfiguration = new SimplexConfiguration();
		detailConfiguration.setMin(baseConfiguration.getMin() * detailWeight);
		detailConfiguration.setMax(baseConfiguration.getMax() * detailWeight);
		detailTerrain = Simplex.createSimplexGenerator(detailConfiguration);
		
		SimplexConfiguration doodadsConfiguration = new SimplexConfiguration();
		doodadsConfiguration.setOctaves(8);
		doodadsConfiguration.setFrequency(2);
		doodadsConfiguration.setMin(0);
		doodadsConfiguration.setMax(100);
		doodads = Simplex.createSimplexGenerator(doodadsConfiguration);
		
		for (int x = 0; x < tilemapWidth; x++) {
			for (int y = 0; y < tilemapHeight; y++) {
				Cell cell = new Cell();
				
				double baseHeight = baseTerrain.get(x / baseZoomLevel, y / baseZoomLevel) + detailTerrain.get(x / detailZoomLevel, y / detailZoomLevel);
				//double doodadHeight = doodads.get(x / detailZoomLevel, y / detailZoomLevel);
				//int tileIndex = stm.get(baseHeight, doodadHeight, world.getTileOffset(x * 6, y * 6));
				//int tileIndex = stm.getTerrain(baseHeight, world.getTileOffset(x * 6, y * 6));
				int tileIndex = stm.getWorldMapTile(world.getTileOffset(x*8, y*8));
				
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
