package com.neural.tsp.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.neural.tsp.model.City;
import com.neural.tsp.model.Traveler;

public class CountryPanel extends JPanel {
	private static final Color CITY_COLOR = Color.red;
	private static final Color PATH_COLOR = Color.blue;
	private static final int BORDER_OFFSET = 20;
	private static final long serialVersionUID = 1L;

	private City[] cities;
	private Traveler traveler;
	private Graphics drawer;

	public CountryPanel(int mapSize, int xPos, int yPos) {
		int realPanelSize = (BORDER_OFFSET * 2) + mapSize;
		setBounds(xPos, yPos, realPanelSize, realPanelSize);
	}

	public void setCities(City[] cities) {
		this.cities = cities;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		drawer = graphics;
		reDraw();
	}

	public void reDraw() {
		clearPanel();
		drawCities();
		drawTravelerPath();
	}

	private void clearPanel() {
		drawer.clearRect(0, 0, getWidth(), getHeight());
	}

	private void drawCities() {
		if (cities != null) {
			for (int i = 0; i < cities.length; i++) {
				drawCity(i);
			}
		}
	}

	private void drawCity(int index) {
		City city = cities[index];
		drawCityAsDot(city);
		char[] chars = fixedLenthString(Integer.toString(index), 2)
				.toCharArray();
		int xPosition = city.getXPos() + BORDER_OFFSET;
		int yPosition = city.getYPos() + BORDER_OFFSET;
		drawer.setColor(CITY_COLOR);
		drawer.drawChars(chars, 0, 2, xPosition, yPosition);
	}

	private void drawCityAsDot(City city) {
		int xPosition = city.getXPos() + BORDER_OFFSET;
		int yPosition = city.getYPos() + BORDER_OFFSET;
		drawer.setColor(PATH_COLOR);
		int radius = 5;
		drawer.fillOval(xPosition - radius, yPosition - radius, radius * 2,
				radius * 2);
	}

	private String fixedLenthString(String string, int length) {
		return String.format("%1$" + length + "s", string);
	}

	private void drawTravelerPath() {
		if (cities != null || traveler != null) {
			drawer.setColor(PATH_COLOR);
			traveler.logTravelerInfo();
			for (int i = 0; i < traveler.getPath().length - 1; i++) {
				int city1Index = traveler.getPath()[i];
				int city2Index = traveler.getPath()[i + 1];
				City city1 = cities[city1Index];
				City city2 = cities[city2Index];
				drawer.drawLine(city1.getXPos() + BORDER_OFFSET,
						city1.getYPos() + BORDER_OFFSET, city2.getXPos()
								+ BORDER_OFFSET, city2.getYPos()
								+ BORDER_OFFSET);
			}
		}
	}
}
