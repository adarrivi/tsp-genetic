package com.neural.tsp.model;

import org.encog.ml.genetic.genes.IntegerGene;
import org.encog.ml.genetic.genome.BasicGenome;
import org.encog.ml.genetic.genome.Chromosome;

public class TravelerGenome extends BasicGenome {

	private static final long serialVersionUID = 1L;

	private Chromosome travelerChromosome;
	private City[] cities;

	public TravelerGenome(City[] cities) {
		this.cities = cities;
	}

	public void createGenome() {
		createRandomPath();
		encodePathIntoChromosome();
		encode();
	}

	private void createRandomPath() {
		final int path[] = new int[cities.length];
		final boolean taken[] = new boolean[cities.length];

		for (int i = 0; i < path.length; i++) {
			taken[i] = false;
		}

		for (int i = 0; i < path.length - 1; i++) {
			int indexCandidate;
			do {
				indexCandidate = (int) (Math.random() * path.length);
			} while (taken[indexCandidate]);
			path[i] = indexCandidate;
			taken[indexCandidate] = true;
			if (i == path.length - 2) {
				indexCandidate = 0;
				while (taken[indexCandidate]) {
					indexCandidate++;
				}
				path[i + 1] = indexCandidate;
			}
		}
		setOrganism(path);
	}

	private void encodePathIntoChromosome() {
		int[] path = (int[]) getOrganism();
		travelerChromosome = new Chromosome();
		for (int i = 0; i < path.length; i++) {
			IntegerGene gene = new IntegerGene();
			gene.setValue(path[i]);
			travelerChromosome.getGenes().add(gene);
		}
		getChromosomes().add(travelerChromosome);
	}

	@Override
	public void decode() {
		Chromosome chromosome = this.getChromosomes().get(0);
		int[] organism = new int[chromosome.size()];

		for (int i = 0; i < chromosome.size(); i++) {
			IntegerGene gene = (IntegerGene) chromosome.get(i);
			organism[i] = gene.getValue();
		}
		setOrganism(organism);
	}

	@Override
	public void encode() {
		Chromosome chromosome = this.getChromosomes().get(0);
		int[] organism = (int[]) getOrganism();
		for (int i = 0; i < chromosome.size(); i++) {
			IntegerGene gene = (IntegerGene) chromosome.get(i);
			gene.setValue(organism[i]);
		}
	}

}
