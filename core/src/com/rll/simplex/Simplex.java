package com.rll.simplex;

import com.rll.core.util.Random;
import com.sudoplay.joise.Joise;
import com.sudoplay.joise.module.ModuleAutoCorrect;
import com.sudoplay.joise.module.ModuleBasisFunction;
import com.sudoplay.joise.module.ModuleFractal;

public class Simplex {
	public static Joise createSimplexGenerator(long octaves, double frequency, double min, double max, long samples, long seed) {
		ModuleFractal gen = new ModuleFractal();
		gen.setAllSourceBasisTypes(ModuleBasisFunction.BasisType.SIMPLEX);
		gen.setNumOctaves(octaves);
		gen.setFrequency(frequency);
		gen.setType(ModuleFractal.FractalType.RIDGEMULTI);
		gen.setSeed(seed);
		
		ModuleAutoCorrect correct = new ModuleAutoCorrect();
		correct.setSource(gen);
		correct.setRange(min, max);
		correct.setSamples(samples);
		correct.calculate();
		
		return new Joise(correct);
	}
	
	public static Joise createSimplexGenerator(long octaves, double frequency, double min, double max, long samples) {
		return createSimplexGenerator(octaves, frequency, min, max, samples, Random.next());
	}
	
	public static Joise createSimplexGenerator() {
		return createSimplexGenerator(2, 2.0, -100.0, 100.0, 100, Random.next());
	}
	
	public static Joise createSimplexGenerator(SimplexConfiguration conf) {
		return createSimplexGenerator(conf.getOctaves(), conf.getFrequency(), conf.getMin(), conf.getMax(), conf.getSamples(), conf.getSeed());
	}
}
