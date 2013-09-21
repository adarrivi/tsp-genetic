package com.neural.tsp.problem;

import java.util.Observer;

import com.neural.tsp.model.City;

public class RandomTravellingSalesmanProblem {

	private City cities[];
	private TspProblemProperties problemProperties;
	private GeneticTspSolver problemSolver;

	public RandomTravellingSalesmanProblem(
			TspProblemProperties problemProperties) {
		this.problemProperties = problemProperties;
	}

	public void init() {
		setRandomCities();
		problemSolver = new GeneticTspSolver(cities, problemProperties);
		problemSolver.init();
	}

	private void setRandomCities() {
		cities = new City[problemProperties.getCities()];
		for (int i = 0; i < cities.length; i++) {
			int xPos = (int) (Math.random() * problemProperties.getMapSize());
			int yPos = (int) (Math.random() * problemProperties.getMapSize());
			cities[i] = new City(xPos, yPos);
		}
	}

	public void addTravelerObserver(Observer observer) {
		problemSolver.addObserver(observer);
	}

	public void solve() {
		problemSolver.solve();
	}

	public City[] getCities() {
		return cities;
	}

}
