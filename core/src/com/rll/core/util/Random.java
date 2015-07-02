package com.rll.core.util;

public class Random {
	private long seed;
	private long x;
	
	public static Random instance;
	
	public Random(long seed) {
		x = seed;
		this.seed = seed;
	}

	public Random() {
		x = System.currentTimeMillis();
		seed = x;
	}
	
	public long getNextLong() {
		x ^= (x << 21);
		x ^= (x >>> 35);
		x ^= (x << 4);
		return Math.abs(x);
	}
	
	public double getNextDouble() {
		return (double)next() / (double)Long.MAX_VALUE;
	}
	
	public static long next() {
		if (instance == null) instance = new Random();
		return instance.getNextLong();
	}
	
	public static long next(long max) {
		return next() % max;
	}
	
	public static long next(long min, long max) {
		return (next() % (max - min)) + min;
	}
	
	public static double nextDouble() {
		if (instance == null) instance = new Random();
		return instance.getNextDouble();
	}
	
	public static boolean coin() {
		return instance.getNextDouble() >= 0.5;
	}
	
	/**
	 * @return the seed
	 */
	public long getSeed() {
		return seed;
	}

	public static long getInstanceSeed() {
		if (instance == null) instance = new Random();
		return instance.getSeed();
	}

	/**
	 * @param seed the seed to set
	 */
	public void setSeed(long seed) {
		this.seed = seed;
	}
	
	public static void setInstanceSeed(long seed) {
		instance = new Random(seed);
	}
}
