package Ant;

import java.io.*;

/**
 * Created by 13633 on 2019/12/11.
 */
public class AntAlgorithm {
    private int ant_nums = 0; // 蚂蚁数目,即蚁群大小
    private double info_factor = 0; // 信息素重要程度 alpha
    private double heuristic_factor = 0; // 启发函数重要程度beta
    private double info_emission_factor = 0; // 信息挥发因子
    private double info_const = 0; // 信息素释放总量
    private int max_iteration; // 最大迭代次数
    private double[][] info_concentration;
    private int city_nums;

    public AntAlgorithm(int ant_nums, double info_factor, double heuristic_factor,
                        double info_emission_factor, double info_const, int max_iteration,int city_nums) {
        this.ant_nums = ant_nums;
        this.info_factor = info_factor;
        this.heuristic_factor = heuristic_factor;
        this.info_emission_factor = info_emission_factor;
        this.info_const = info_const;
        this.max_iteration = max_iteration;
        this.city_nums = city_nums;
        this.info_concentration = new double[city_nums][city_nums];
        for (int i = 0; i < city_nums ; i++) {
            for (int j = 0; j < city_nums ; j++) {
                this.info_concentration[i][j] = 1;
            }
        }
    }

    // 终止条件
    public boolean isTerminated(int iteration){
        return iteration>max_iteration;
    }
    // 初始化蚁群
    public AntColony initAntColony(){
        return new AntColony(this.ant_nums,this.city_nums);
    }
    // 构建解空间
    public void antMove(AntColony antColony,City[] cities){
        Ant[] ants = antColony.getAnts();
        for (int i = 0; i < antColony.size() ; i++) {
            ants[i].goThrough(cities,info_concentration,this.info_factor,this.heuristic_factor);
        }
    }
    public void updateConcentration(AntColony antColony,City[] cities){
        // 所有路径信息素进行一次衰退
        for (int i = 0; i < city_nums ; i++) {
            for (int j = 0; j < city_nums; j++) {
                info_concentration[i][j] *= info_emission_factor;
            }
        }
        Ant ant = antColony.getSmallestAnt();
        double total = ant.getFitness();
        int[] visitCitys = ant.getVisitCitys();
        // 根据当前最优路径更新信息素
        for (int j = 0; j < visitCitys.length-1 ; j++) {
            int start = visitCitys[j];
            int end = visitCitys[j+1];
            double len = cities[start].distance(cities[end]);
            info_concentration[start][end] += this.info_const/len;
        }
    }

    public double[][] getInfo_concentration() {
        return info_concentration;
    }
}
