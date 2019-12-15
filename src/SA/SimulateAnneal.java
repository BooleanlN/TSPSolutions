package SA;

import java.util.Random;

/**
 * Created by 13633 on 2019/12/15.
 */
public class SimulateAnneal {
    private double T; //初始温度
    private double delta; // 降温系数
    private int generation; // 内迭代次数
    private static final int k = 30; // Metropolis 常数项
    private static final double MinTemper = 1e-8; // 终止温度
    public SimulateAnneal(double t, double delta, int generation) {
        T = t;
        this.delta = delta;
        this.generation = generation;
    }
    // 算法主体
    public Individual SA(City[] cities){
        Individual solve = new Individual(cities.length);
        solve.setFitness(cities);
        Individual bestSolution = solve;
        while (T>MinTemper){
            for (int i = 0; i < generation ; i++) {
                Individual tempSolution = solve.shuffle(1);
                tempSolution.setFitness(cities);
                double bias = tempSolution.getFitness() - solve.getFitness();
                if(bias < 0){
                    solve = tempSolution;
                   // solve.print();
                }else if(Math.exp(-bias/(k*T))>Math.random()){ // Metropolis准则接收新解
                    solve = tempSolution;
                  //  solve.print();
                }
            }
            System.out.println(solve.print());
            if(solve.getFitness() < bestSolution.getFitness())bestSolution = solve;
            T*=delta;
        }
        return bestSolution;
    }
}
