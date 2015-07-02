package com.rll.core.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rll.core.worldmap.EdgeExpansionPolygonClusterWorldMap;

public class WorldMapTestCameraController extends DraggableCameraController {
	private EdgeExpansionPolygonClusterWorldMap world;
	private Viewport viewport;
	
	public float dx = 0;
	public float dy = 0;
	
	public WorldMapTestCameraController(Viewport viewport, EdgeExpansionPolygonClusterWorldMap world) {
		super(viewport.getCamera());
		this.world = world;
		this.viewport = viewport;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		boolean t = super.touchDown(screenX, screenY, pointer, button);
		
		dx = camera.position.x - viewport.getScreenWidth() / 2 + screenX;
		dy = camera.position.y - screenY + (viewport.getScreenHeight() / 2);
		
		return t;
	}
}
