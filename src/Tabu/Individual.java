package Tabu;

import java.util.Random;

/**
 * Created by 13633 on 2019/12/15.
 */
public class Individual {
    private int[] path;
    private double fitness;

    public Individual(int[] path, double fitness) {
        this.path = path;
        this.fitness = fitness;
    }
    public Individual(int pathLength){
        this.path = new int[pathLength];
        for (int i = 0; i < pathLength ; i++) {
            path[i] = i;
        }
    }
    public String print(){
        String out = "";
        for (int i = 0; i < this.path.length; i++) {
            out += this.path[i];
        }
        return "Soluation："+out+"\tfitness："+this.fitness;
    }

    public Individual shuffle(int seed) {
        Random rand = new Random();
        Individual tempIndividual = new Individual(path.length);
        int[] tempPath = tempIndividual.getPath();
        for (int i = 0; i < path.length; i++) {
            tempPath[i] = path[i];
        }
        for (int i = 0; i < seed; i++) {
            int randIndex = rand.nextInt(path.length-1);
            int randIndex2 = rand.nextInt(path.length-1);
            int temp = tempPath[randIndex];
            tempPath[randIndex] = tempPath[randIndex2];
            tempPath[randIndex2] = temp;
        }
        return tempIndividual;
    }
    public void setFitness(City[] cities){
        double totalLen = 0;
        for (int i = 0; i < path.length-1 ; i++) {
            totalLen += cities[path[i]].distance(cities[path[i+1]]);
        }
        this.fitness = totalLen;
    }

    public double getFitness() {
        return fitness;
    }

    public int[] getPath() {
        return path;
    }
    public boolean equal(Individual individual){
        if(individual.getFitness()==this.getFitness()){
            int[] path = individual.getPath();
            for (int i = 0; i < path.length ; i++) {
                if(path[i]!=this.path[i])return false;
            }
            return true;
        }
        return false;
    }
}
