package com.rll.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rll.core.util.DraggableCameraController;

public class Game extends ApplicationAdapter {
	protected OrthographicCamera camera;
	protected Viewport viewport;
	protected DraggableCameraController controller;
	
	protected int vMinW = 800, vMinH = 600, vMaxW = 1920, vMaxH = 1280, vMul = 5;
	private boolean zoomOut = true;
	
	public Game(boolean zoomOut, int mul) {
		this.zoomOut = zoomOut;
		this.vMul = mul;
	}
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, (w / h) * 320, 320);
		
		camera.update();
		
		if (zoomOut) {
			vMinW = vMaxW * vMul;
			vMaxW = vMinW;
			
			vMinH = vMaxH * vMul;
			vMaxH = vMinH;
		} else vMul = 1;
		
		viewport = new ExtendViewport(vMinW, vMinH, vMaxW, vMaxH, camera);
		
		if (!zoomOut) controller = new DraggableCameraController(camera);
		else controller = new DraggableCameraController(camera, vMul);
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glLineWidth(1);
	}
	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.ApplicationAdapter#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}
