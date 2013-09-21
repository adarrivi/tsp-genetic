package com.neural.tsp.model;

import org.encog.ml.genetic.genome.CalculateGenomeScore;
import org.encog.ml.genetic.genome.Genome;

public class Country implements CalculateGenomeScore {

	private City[] cities;

	public Country(City[] cities) {
		this.cities = cities;
	}

	@Override
	public double calculateScore(Genome genome) {
		double totalDistance = 0.0;
		int[] path = (int[]) genome.getOrganism();
		for (int i = 0; i < cities.length - 1; i++) {
			City city1 = cities[path[i]];
			City city2 = cities[path[i + 1]];
			totalDistance += city1.getDistanceTo(city2);
		}
		return totalDistance;
	}

	@Override
	public boolean shouldMinimize() {
		return true;
	}

	public City[] getCities() {
		return cities;
	}

}
