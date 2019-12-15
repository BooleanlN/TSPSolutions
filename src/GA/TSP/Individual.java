package GA.TSP;

/**
 * Created by 13633 on 2019/12/9.
 */
public class Individual {
    private int[] chromosome;
    private double fitness = -1;
    private double total = 0;
    Individual(int chromosomeSize){
        this.chromosome = new int[chromosomeSize];
        for (int i = 0; i < chromosomeSize ; i++) {
            this.chromosome[i] = i;
        }
    }
    Individual(int[] chromosome){
        this.chromosome = chromosome;
    }
    // 子代初始化
    Individual(int chromosomeSize, int flag){
        this.chromosome = new int[chromosomeSize];
        for (int i = 0; i < chromosomeSize ; i++) {
            this.chromosome[i] = -1;
        }
    }
    public void setGene(int index,int val){
        this.chromosome[index] = val;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }
    public int getChromosomeLength(){
        return this.chromosome.length;
    }
    public int getGene(int index){
        return this.chromosome[index];
    }
    public void setFitness(double fitness){
        this.fitness = fitness;
    }
    public double getFitness() {
        return fitness;
    }
    public boolean containGene(int gene){
        for (int i = 0; i < chromosome.length; i++) {
            if(chromosome[i] == gene)return true;
        }
        return false;
    }
    public void setTotal(double total){
        this.total = total;
    }
    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < this.chromosome.length; i++) {
            out += this.chromosome[i];
        }
        return out + "\tfitness："+this.fitness+"\ttotal："+this.total;
    }
}
