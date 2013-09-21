package com.neural.tsp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.neural.tsp.model.Traveler;
import com.neural.tsp.problem.RandomTravellingSalesmanProblem;
import com.neural.tsp.problem.TspProblemProperties;

public class TspContainer extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private CountryPanel countryPanel;
	private TspProblemProperties problemProperties;
	private RandomTravellingSalesmanProblem problem;
	private JButton restartButton;

	public TspContainer(TspProblemProperties problemProperties) {
		this.problemProperties = problemProperties;
		setLayout(null);
		setVisible(true);
	}

	public void startNewRandomProblem() {
		problem = new RandomTravellingSalesmanProblem(problemProperties);
		problem.init();
		problem.addTravelerObserver(this);
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				problem.solve();
			}
		});
		thread.start();

	}

	@Override
	public void update(Observable o, Object traveler) {
		showPath((Traveler) traveler);
	}

	private void showPath(Traveler traveler) {
		countryPanel.setCities(problem.getCities());
		countryPanel.setTraveler(traveler);
		countryPanel.repaint();
	}

	public void draw() {
		createRestartButton();
		createCountryPanel();
	}

	private void createCountryPanel() {
		countryPanel = new CountryPanel(problemProperties.getMapSize(), 10, 34);
		add(countryPanel);
	}

	private void createRestartButton() {
		restartButton = new JButton("Restart");
		restartButton.setBounds(10, 11, 89, 23);
		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startNewRandomProblem();

			}
		});
		add(restartButton);
	}
}
