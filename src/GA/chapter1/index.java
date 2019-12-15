package GA.chapter1;

/**
 * Created by 13633 on 2019/12/9.
 */
public class index {
    public static void main(String[] args) {
        GeneticAlgorithm gen = new GeneticAlgorithm(0.95,0.01,100,20);
        Population population = gen.initPopulation(50);

        gen.evalPopulation(population);
        int generation = 1;
        System.out.println("begin");
        while (!gen.isTerminateConditionMet(population)){
            System.out.println(generation+"\tBest Solution of The population：" + population
                    .getFittest(0).toString());

            // crossover
            population = gen.crossoverPopulation(population);
            //mutation
            population = gen.mutation(population);
            //evaluate new population
            gen.evalPopulation(population);
            //increment the current generation
            generation++;
        }
        System.out.println(generation);
    }
}
