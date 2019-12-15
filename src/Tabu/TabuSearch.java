package Tabu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by 13633 on 2019/12/15.
 */
public class TabuSearch {
    private int tabuSize; //禁忌表大小
    private int generation; // 迭代次数
    private Queue<Individual> tabu; // 禁忌表

    public TabuSearch(int tabuSize, int generation) {
        this.tabuSize = tabuSize;
        this.generation = generation;
        this.tabu = new LinkedList<Individual>(); // 使用队列构造禁忌表
    }
    public boolean isInTabu(Individual order){
        for (Individual individual:tabu) {
            if(individual.equal(order)){
                return true;
            }
        }
        return false;
    }
    public boolean addTabu(Individual order){
        tabu.add(order);
        return true;
    }
    public Individual tabuSearch(City[] cities){
        Individual individual = new Individual(cities.length);
        individual.setFitness(cities);
        Individual bestSolution = individual;
        for (int i = 0; i < generation ; i++) {
            Individual currentIndividual = individual;
            for (int j = 0; j < cities.length ; j++) {// 产生领域解
                Individual newInd = currentIndividual.shuffle(1);
                newInd.setFitness(cities);
                if(!isInTabu(newInd)&&newInd.getFitness() <= individual.getFitness()){
                    individual = newInd;
                    System.out.println(individual.print());
                }
            }
            if(individual.getFitness()<bestSolution.getFitness()){
                if(tabu.size() == tabuSize){
                    tabu.remove();
                }
                bestSolution = individual;
            }
            addTabu(individual);
            // 加入禁忌表

        }
        return bestSolution;
    }
}
