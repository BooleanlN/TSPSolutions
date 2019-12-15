package GA.TSP;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by 13633 on 2019/12/9.
 */
public class Population {
    private Individual[] population;
    private double populationFitness = -1;

    Population(int populationSize){
        this.population = new Individual[populationSize];
    }
    Population(int populationSize, int chromosomeLength){
        this.population = new Individual[populationSize];
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual(chromosomeLength);
            this.population[i] = individual;
        }
    }

    public Individual[] getPopulation() {
        return population;
    }

    public void setPopulation(Individual[] population) {
        this.population = population;
    }
    /*
    * 返回指定排序的个体的适应度
     */
    public Individual getFittest(int offset){
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if(o1.getFitness()>o2.getFitness())
                    return -1;
                else if(o1.getFitness() < o2.getFitness())
                    return 1;
                return 0;
            }
        });
        return this.population[offset];
    }

    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    public double getPopulationFitness() {
        return populationFitness;
    }
    public int size(){
        return population.length;
    }
    // 打乱种群
    public void shuffle(){
        Random ran = new Random();
        for (int i = this.population.length - 1; i > 0; i--) {
            int index = ran.nextInt(i + 1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }
    public void setIndividual(int index,Individual individual){
        this.population[index] = individual;
    }
    public Individual getIndividual(int index){
        return population[index];
    }
}
