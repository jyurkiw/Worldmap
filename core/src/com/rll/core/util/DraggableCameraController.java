package com.rll.core.util;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class DraggableCameraController implements InputProcessor {
	protected Camera camera;
	protected int x, y;
	private int dragMul;
	
	public DraggableCameraController(Camera camera) {
		this.camera = camera;
		dragMul = 1;
	}
	
	public DraggableCameraController(Camera camera, int dragMul) {
		this.camera = camera;
		this.dragMul = dragMul;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		System.out.println(String.format("Key %d pressed", keycode));
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		x = screenX;
		y = screenY;

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		x = screenX;
		y = screenY;
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int changeX = x - screenX;
		int changeY = y - screenY;
		
		camera.translate(changeX * dragMul, -changeY * dragMul, 0);
		camera.update();
		
		x = screenX;
		y = screenY;
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public Camera getCamera() {
		return camera;
	}
}
