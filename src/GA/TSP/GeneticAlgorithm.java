package GA.TSP;


import java.util.Arrays;

/**
 * Created by 13633 on 2019/12/9.
 */
public class GeneticAlgorithm {
    private double crossRate; // 交叉率
    private double mutateRate; // 变异率
    private int populationSize; // 种群大小
    private int elitismCount; //精英数
    protected int tournamentSize; // 锦标赛参与数

    public GeneticAlgorithm(double crossRate, double mutateRate, int populationSize, int elitismCount,int tournamentSize) {
        this.crossRate = crossRate;
        this.mutateRate = mutateRate;
        this.populationSize = populationSize;
        this.elitismCount = elitismCount;
        this.tournamentSize = tournamentSize;
    }
    // 初始化种群
    public  Population initPopulation(int chromosomeLength){
        return new Population(this.populationSize,chromosomeLength);

    }
    // 计算个体适应度
    public double calcFitness(Individual individual,City[] cities){
        double totalDistance = 0;
        int[] order = individual.getChromosome();
        for (int i = 0; i < order.length-1 ; i++) {
            totalDistance+=cities[order[i]].distance(cities[order[i+1]]);
        }
        individual.setTotal(totalDistance);
        double fitness =  1/totalDistance;
        individual.setFitness(fitness);
        return fitness;
    }
    // 计算种群适应度
    public void evalPopulation(Population population,City[] cities){
        Individual[] individuals = population.getPopulation();
        double fitness,totalFitness = 0;
        for (Individual individual:individuals) {
            totalFitness +=calcFitness(individual,cities);
        }
        fitness = totalFitness/population.size();
        population.setPopulationFitness(fitness);
    }
    // 终止条件
    public boolean isTerminateConditionMet(Population population){
        return true;
    }
    // 锦标赛方法选择一个适应度高的母代
    private Individual selectParent(Population population){
        Population players = new Population(tournamentSize);
        population.shuffle();
        // 选择参加锦标赛的选手
        for (int i = 0; i < tournamentSize ; i++) {
            Individual player = population.getIndividual(i);
            players.setIndividual(i,player);
        }
        return players.getFittest(0);
    }
    // 交叉
    public Population crossoverPopulation(Population population){
        Population newPopulation = new Population(population.size());
        for (int i = 0; i <population.size() ; i++) {
            Individual parent1 = population.getFittest(i);
            if(Math.random() < crossRate && i>=elitismCount){
                int offspringChromosome[] = new int[parent1.getChromosomeLength()];
                Arrays.fill(offspringChromosome, -1);
                Individual child = new Individual(offspringChromosome);
                Individual parent2 = selectParent(population);
                int point1 = (int)(Math.random() * parent2.getChromosomeLength());
                int point2 = (int)(Math.random() * parent2.getChromosomeLength());
                final int start = Math.min(point1,point2);
                final int end = Math.max(point1,point2);
                // 随机截取parent1基因
                for (int k=start;k<end;k++){
                    child.setGene(k,parent1.getGene(k));
                }
                // parent2中寻找子染色体中未包含的基因
                for (int j = 0; j < parent2.getChromosomeLength(); j++) {
                    int parent2Gene = j+end;
                    if(parent2Gene >= parent2.getChromosomeLength())
                        parent2Gene-=parent2.getChromosomeLength();
                    if(!child.containGene(parent2.getGene(parent2Gene))){
                        for (int k = 0; k < child.getChromosomeLength() ; k++) {
                            if(child.getGene(k) == -1){
                                child.setGene(k,parent2.getGene(parent2Gene));
                                break;
                            }
                        }
                    }
                }
             //   System.out.println(child.toString());
                newPopulation.setIndividual(i,child);
            }else{
                newPopulation.setIndividual(i,parent1);
            }
        }
        return newPopulation;
    }
    //变异
    public Population mutation(Population population){
        Population newPopulation = new Population(population.size());
        for (int i = 0; i < population.size() ; i++) {
            Individual individual = population.getFittest(i);
            if(i >= elitismCount){
                for (int j = 0; j < individual.getChromosomeLength() ; j++) {
                    if(Math.random()<mutateRate){
                        // 选取随机位置进行交换
                        int newPos = (int)(Math.random() * individual.getChromosomeLength());
                        int gene1 = individual.getGene(j);
                        int gene2 = individual.getGene(newPos);

                        individual.setGene(j,gene2);
                        individual.setGene(newPos,gene1);
                    }
                }
            }
            newPopulation.setIndividual(i,individual);
        }
        return newPopulation;
    }
}
