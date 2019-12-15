package Ant;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by 13633 on 2019/12/11.
 */
public class Ant {
    private int[] visitCitys;
    private int currentCity;
    private double fitness = 0;
    // 初始化起始城市
    Ant(int city_num){
        Random random = new Random();
        this.visitCitys = new int[city_num];
        for (int i = 0; i < this.visitCitys.length ; i++) {
            visitCitys[i] = -1;
        }
        this.visitCitys[0] = random.nextInt(city_num);
        this.currentCity = this.visitCitys[0];
    }
    // 寻找下一个城市
    public int goNext(City[] cities,double[][] concentrations,double alpha,double beta){
        HashSet<Integer> set= new HashSet<>();
        for (int i = 0; i < this.visitCitys.length ; i++) {
            if(this.visitCitys[i] != -1){
                set.add(this.visitCitys[i]);
            }
        }
        double max_predicts = 0.0;
        int next_city = this.currentCity;

        for (int i = 0; i < cities.length ; i++) {
            if(!set.contains(i)){
                double yita = 1 / cities[this.currentCity].distance(cities[i]);
                double predict = Math.pow(concentrations[this.currentCity][i],alpha)
                        + Math.pow(yita,beta);
                if(predict > max_predicts){
                    max_predicts  = predict;
                    next_city = i;
                }
            }
        }
//        this.currentCity = next_city;
        return next_city;
    }

    public int[] getVisitCitys() {
        return visitCitys;
    }

    public int getCurrentCity() {
        return currentCity;
    }

    public double getFitness() {
        return fitness;
    }

    //遍历所有城市
    public double goThrough(City[] cities,double[][] concentrations,double alpha,double beta){
        for (int i = 1; i < cities.length ; i++) {
            int nextCity = goNext(cities,concentrations,alpha,beta);

            this.fitness += cities[this.currentCity].distance(cities[nextCity]);
            this.currentCity = nextCity;
            this.visitCitys[i] = nextCity;

        }
        return this.fitness;
    }
    //
    public void setVisitCitys(int city_num) {
        Random random = new Random();
        for (int i = 0; i < this.visitCitys.length ; i++) {
            visitCitys[i] = -1;
        }
        this.visitCitys[0] = random.nextInt(city_num);
        this.currentCity = this.visitCitys[0];
    }

    public void setCurrentCity(int currentCity) {
        this.currentCity = currentCity;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public String getVisitOrder(){
        String order = "result：";
        for (int i = 0; i < this.visitCitys.length ; i++) {
            order += this.visitCitys[i];
        }
        return order;
    }
    public double getTotalLen(City[] cities){
        double total = 0;
        for (int i = 0; i < this.visitCitys.length -1; i++) {
            total += cities[this.visitCitys[i]].distance(cities[this.visitCitys[i+1]]);
        }
        return total;
    }
}
