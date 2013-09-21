package com.neural.tsp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Traveler {

	private static final Logger LOG = LoggerFactory.getLogger(Traveler.class);

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
		LOG.debug("Iteration: {}, Patch Length: {}", iteration, distance);
	}

}
