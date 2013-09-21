package com.neural.tsp.problem;

public class TspProblemProperties {
	private static final int DEFAULT_CITIES = 50;
	private static final int DEFAULT_MAP_SIZE = 250;
	public static final int DEFAULT_POPULATION_SIZE = 1000;
	public static final double DEFAULT_MUTATION_PERCENT = 0.1;
	public static final double DEFAULT_PERCENT_TO_MATE = 0.24;
	public static final double DEFAULT_MATING_POPULATION_PERCENT = 0.5;
	public static final int DEFAULT_MAX_SAME_SOLUTION = 50;
	public static final int DEFAULT_CROSSOVER_SLICES = 3;

	private int cities = DEFAULT_CITIES;
	private int mapSize = DEFAULT_MAP_SIZE;
	private double mutationPercent = DEFAULT_MUTATION_PERCENT;
	private double percentToMate = DEFAULT_PERCENT_TO_MATE;
	private double matingPopulationPercent = DEFAULT_MATING_POPULATION_PERCENT;
	private int crossoverSlices = DEFAULT_CROSSOVER_SLICES;
	private int maxSameSolution = DEFAULT_MAX_SAME_SOLUTION;
	private int populationSize = DEFAULT_POPULATION_SIZE;

	public double getMutationPercent() {
		return mutationPercent;
	}

	public void setMutationPercent(double mutationPercent) {
		this.mutationPercent = mutationPercent;
	}

	public double getPercentToMate() {
		return percentToMate;
	}

	public void setPercentToMate(double percentToMate) {
		this.percentToMate = percentToMate;
	}

	public double getMatingPopulationPercent() {
		return matingPopulationPercent;
	}

	public void setMatingPopulationPercent(double matingPopulationPercent) {
		this.matingPopulationPercent = matingPopulationPercent;
	}

	public int getCrossoverSlices() {
		return crossoverSlices;
	}

	public void setCrossoverSlices(int crossoverSlices) {
		this.crossoverSlices = crossoverSlices;
	}

	public int getMaxSameSolution() {
		return maxSameSolution;
	}

	public void setMaxSameSolution(int maxSameSolution) {
		this.maxSameSolution = maxSameSolution;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getCities() {
		return cities;
	}

	public void setCities(int cities) {
		this.cities = cities;
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}
}
