package com.neural.tsp.model;

public class Traveler {

	private int[] path;
	private double distance;
	private int iteration;

	public Traveler(int[] path, double distance, int iteration) {
		this.path = path;
		this.distance = distance;
		this.iteration = iteration;
	}

	public int[] getPath() {
		return path;
	}

	public double getDistance() {
		return distance;
	}

	public void logTravelerInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("Iteration: ").append(iteration);
		sb.append(", Path Length: ").append(distance);
		System.out.println(sb.toString());
	}

}
