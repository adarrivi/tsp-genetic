package com.neural.tsp.view;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.neural.tsp.problem.TspProblemProperties;

public class TspPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final TspPanel INSTANCE = new TspPanel();

	private TspProblemProperties problemProperties;
	private static final int SIZE_OFFSET = 100;

	public static void main(String[] args) {
		INSTANCE.run();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				INSTANCE.setVisible(true);
			}
		});
	}

	private void run() {
		setTSProblemParameters();
		createPanel();
	}

	private void setTSProblemParameters() {
		problemProperties = new TspProblemProperties();
	}

	private void createPanel() {
		setTitle("Genetic Tsp");
		Rectangle dialogPos = new Rectangle(100, 100,
				problemProperties.getMapSize() + SIZE_OFFSET,
				problemProperties.getMapSize() + SIZE_OFFSET);

		setBounds(dialogPos);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		TspContainer tspContainer = new TspContainer(problemProperties);
		tspContainer.draw();
		getContentPane().add(tspContainer);
	}
}
