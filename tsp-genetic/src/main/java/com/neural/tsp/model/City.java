package com.neural.tsp.model;


public class City {

	private int xPos;
	private int yPos;

	public City(final int x, final int y) {
		this.xPos = x;
		this.yPos = y;
	}

	public int getXPos() {
		return this.xPos;
	}

	public int getYPos() {
		return this.yPos;
	}

	public int getDistanceTo(final City city) {
		final int xdiff = xPos - city.getXPos();
		final int ydiff = yPos - city.getYPos();
		return (int) Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}
}
