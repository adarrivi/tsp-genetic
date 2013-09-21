package com.neural.tsp.problem;

import java.util.Observable;

import org.encog.ml.genetic.BasicGeneticAlgorithm;
import org.encog.ml.genetic.GeneticAlgorithm;
import org.encog.ml.genetic.crossover.SpliceNoRepeat;
import org.encog.ml.genetic.genes.Gene;
import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.Chromosome;
import org.encog.ml.genetic.genome.Genome;
import org.encog.ml.genetic.mutate.MutateShuffle;
import org.encog.ml.genetic.population.BasicPopulation;
import org.encog.ml.genetic.population.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neural.tsp.model.City;
import com.neural.tsp.model.Country;
import com.neural.tsp.model.Traveler;
import com.neural.tsp.model.TravelerGenome;

public class GeneticTspSolver extends Observable {

	private Logger LOG = LoggerFactory.getLogger(GeneticTspSolver.class);

	private TspProblemProperties problemProperties;
	private GeneticAlgorithm geneticAlgorithm;
	private City cities[];
	private Country country;

	public GeneticTspSolver(City[] cities,
			TspProblemProperties problemProperties) {
		this.cities = cities;
		this.problemProperties = problemProperties;
	}

	public void init() {
		geneticAlgorithm = new BasicGeneticAlgorithm();
		country = new Country(cities);
		geneticAlgorithm.setCalculateScore(country);
		geneticAlgorithm.setMutationPercent(problemProperties
				.getMutationPercent());
		geneticAlgorithm.setPercentToMate(problemProperties.getPercentToMate());
		geneticAlgorithm.setMatingPopulation(problemProperties
				.getMatingPopulationPercent());
		geneticAlgorithm.setCrossover(new SpliceNoRepeat(cities.length
				/ problemProperties.getCrossoverSlices()));
		geneticAlgorithm.setMutate(new MutateShuffle());
		setRandomPopulation();
	}

	private void setRandomPopulation() {
		Population population = new BasicPopulation(
				problemProperties.getPopulationSize());
		geneticAlgorithm.setPopulation(population);

		for (int i = 0; i < problemProperties.getPopulationSize(); i++) {
			final TravelerGenome genome = new TravelerGenome(cities);
			genome.createGenome();
			geneticAlgorithm.getPopulation().add(genome);
			geneticAlgorithm.calculateScore(genome);
		}
		population.claim(geneticAlgorithm);
		population.sort();
	}

	public void solve() {
		int sameSolutionCount = 0;
		int iteration = 1;
		double lastSolution = Double.MAX_VALUE;
		while (sameSolutionCount < problemProperties.getMaxSameSolution()) {
			geneticAlgorithm.iteration();
			Genome bestGenome = geneticAlgorithm.getPopulation().getBest();
			Traveler traveler = new Traveler((int[]) bestGenome.getOrganism(),
					bestGenome.getScore(), iteration++);
			notifyNewTravelerFound(traveler);
			if (Math.abs(lastSolution - traveler.getDistance()) < 1.0) {
				sameSolutionCount++;
			} else {
				sameSolutionCount = 0;
			}

			lastSolution = traveler.getDistance();
		}
		LOG.debug("Good solution found:");
		displaySolution();
	}

	private void notifyNewTravelerFound(Traveler traveler) {
		traveler.logTravelerInfo();
		setChanged();
		notifyObservers(traveler);
	}

	private void displaySolution() {
		boolean first = true;
		Chromosome bestChromosome = geneticAlgorithm.getPopulation().getBest()
				.getChromosomes().get(0);
		StringBuffer sb = new StringBuffer();
		for (Gene gene : bestChromosome.getGenes()) {
			if (!first) {
				sb.append(">");
			}
			sb.append(((IntegerGene) gene).getValue());
			first = false;
		}
		LOG.debug(sb.toString());
	}
}
