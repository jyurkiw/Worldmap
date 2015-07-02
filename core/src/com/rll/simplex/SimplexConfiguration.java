package com.rll.simplex;

import com.rll.core.util.Random;

public class SimplexConfiguration {
	private long octaves;
	private double frequency;
	private double min;
	private double max;
	private long samples;
	private long seed;
	
	public SimplexConfiguration() {
		octaves = 6;
		frequency = 2;
//		min = -100;
//		max = 100;
		min = -100;
		max = 100;
		samples = 100;
		seed = Random.getInstanceSeed();
	}

	/**
	 * @return the octaves
	 */
	public long getOctaves() {
		return octaves;
	}

	/**
	 * @param octaves the octaves to set
	 */
	public void setOctaves(long octaves) {
		this.octaves = octaves;
	}

	/**
	 * @return the frequency
	 */
	public double getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}

	/**
	 * @return the samples
	 */
	public long getSamples() {
		return samples;
	}

	/**
	 * @param samples the samples to set
	 */
	public void setSamples(long samples) {
		this.samples = samples;
	}

	/**
	 * @return the seed
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	public void setSeed(long seed) {
		this.seed = seed;
	}
}
