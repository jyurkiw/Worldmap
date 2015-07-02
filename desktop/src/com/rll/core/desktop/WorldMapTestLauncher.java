package com.rll.core.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rll.test.ActualSimplexTest;
import com.rll.test.TestEdgeExpansionPolygonClusterWorldMap;

public class WorldMapTestLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new TestEdgeExpansionPolygonClusterWorldMap(true, 2), config);
		new LwjglApplication(new ActualSimplexTest(true, 5), config);
	}
}
