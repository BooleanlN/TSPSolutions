package GA.chapter1;

/**
 * Created by 13633 on 2019/12/9.
 */
public class Individual {
    private int[] chromosome;
    private double fitness = -1;
    Individual(int[] chromosome){
        this.chromosome = chromosome;
    }
    Individual(int chromosomeSize){
        this.chromosome = new int[chromosomeSize];
        for (int i = 0; i < chromosomeSize ; i++) {
            if(0.5 < Math.random()){
                this.setGene(i,1);
            }else{
                this.setGene(i,0);
            }
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

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < this.chromosome.length; i++) {
            out += this.chromosome[i];
        }
        return out + "\tfitness："+this.fitness;
    }
}
