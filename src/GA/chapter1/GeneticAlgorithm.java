package GA.chapter1;

import com.sun.org.apache.bcel.internal.generic.POP;

/**
 * Created by 13633 on 2019/12/9.
 */
public class GeneticAlgorithm {
    private double crossRate; // 交叉率
    private double mutateRate; // 变异率
    private int populationSize; // 种群大小
    private int elitismCount; //精英数

    public GeneticAlgorithm(double crossRate, double mutateRate, int populationSize, int elitismCount) {
        this.crossRate = crossRate;
        this.mutateRate = mutateRate;
        this.populationSize = populationSize;
        this.elitismCount = elitismCount;
    }
    // 初始化种群
    public Population initPopulation(int chromosomeLength){
        return new Population(this.populationSize,chromosomeLength);

    }
    // 计算个体适应度
    public double calcFitness(Individual individual){
        int correctCount = 0;
        for (int i = 0; i < individual.getChromosomeLength() ; i++) {
            if(individual.getGene(i) == 1) correctCount++;
        }
        double fitness = (double) correctCount / individual.getChromosomeLength();

        individual.setFitness(fitness);
        return fitness;
    }
    // 计算种群适应度
    public void evalPopulation(Population population){
        double populationFitness = 0;
        for (Individual individual:population.getPopulation()) {
            populationFitness += calcFitness(individual);
        }
        population.setPopulationFitness(populationFitness);
    }
    // 终止条件
    public boolean isTerminateConditionMet(Population population){
        for (Individual indivual:population.getPopulation()) {
            if(indivual.getFitness() == 1)
                return true;
        }
        return false;
    }
    // 轮盘赌选择一个适应度高的母代
    private Individual selectParent(Population population){
        Individual[] individuals = population.getPopulation();

        double populationFitness = population.getPopulationFitness();
        double routtlePos = Math.random() * populationFitness;

        //
        double point = 0.0;
        for (Individual individual:individuals) {
            point += individual.getFitness();
            if(point >= routtlePos)
                return individual;
        }
        return individuals[population.size() - 1];
    }
    // 交叉
    public Population crossoverPopulation(Population population){
        Population newPopulation = new Population(population.size());

        for (int i = 0; i <population.size() ; i++) {
            Individual parent1 = population.getFittest(i);
            if(Math.random() < crossRate &&  i >= elitismCount){
                Individual child = new Individual(parent1.getChromosomeLength());
                Individual parent2 = selectParent(population);
                for (int j = 0; j <parent1.getChromosomeLength() ; j++) {
                    if(Math.random() > 0.5){
                        child.setGene(j,parent1.getGene(j));
                    }else{
                        child.setGene(j,parent2.getGene(j));
                    }
                }
                newPopulation.setIndividual(i,child);
            }else{
                newPopulation.setIndividual(i,parent1);
            }
        }
        return newPopulation;
    }
    //变异
    public Population mutation(Population population){
        int size = population.size();
        Population newPopulation = new Population(this.populationSize);
        for (int i = 0; i < size; i++) {
            Individual individual = population.getFittest(i);
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (i > this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutateRate > Math.random()) {
                        // Get new gene
                        int newGene = 1;
                        if (individual.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // Mutate gene
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            newPopulation.setIndividual(i, individual);
        }
        return newPopulation;
    }
}
