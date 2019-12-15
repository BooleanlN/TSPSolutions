package Ant;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by 13633 on 2019/12/11.
 */
public class AntColony {
    private Ant[] ants;

    public AntColony(Ant[] ants) {
        this.ants = ants;
    }

    public AntColony(int colony_size,int city_nums){
        this.ants = new Ant[colony_size];
        for (int i = 0; i < colony_size ; i++) {
            Ant ant = new Ant(city_nums);
            this.ants[i] = ant;
        }
    }

    public Ant[] getAnts(){
        return this.ants;
    }

    public Ant getAnt(int offset){
        return this.ants[offset];
    }

    public int size(){
        return ants.length;
    }
    public Ant getSmallestAnt(){
        Arrays.sort(this.ants, new Comparator<Ant>() {
            @Override
            public int compare(Ant o1, Ant o2) {
                if(o1.getFitness() > o2.getFitness())return 1;
                else if(o1.getFitness() < o2.getFitness()) return  -1;
                return 0;
            }
        });
        return this.ants[0];
    }

}
