package GA.TSP;

import java.io.*;

/**
 * Created by 13633 on 2019/12/9.
 */
public class main {
    public static City[] initCitys(int size){
        File file = new File("E:\\algorithm\\src\\GA\\TSP\\tsp.txt");
        City[] cities = new City[size];
        int index = 0;
        if(file.exists()){
            try {
                InputStreamReader readerr = new InputStreamReader(new FileInputStream(file),"UTF8");
                BufferedReader bfreader = new BufferedReader(readerr);
                String line;
                try {
                    while ((line = bfreader.readLine())!=null){
                       // System.out.println(line);
                        if(index == size)return cities;
                        System.out.println(index);
                        String[] arr = line.split(" ");
                        City city = new City(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                        cities[index] = city;
                        index++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }
    public static void main(String[] args) {
        City[] cities = initCitys(51);
        System.out.println(cities.length);
        GeneticAlgorithm gene = new GeneticAlgorithm(0.98,0.001,
                100,15,6);

        Population population = gene.initPopulation(cities.length);

        //评估
        int generation = 1;
        gene.evalPopulation(population,cities);
        while(gene.isTerminateConditionMet(population)&&generation<2000){

            System.out.println(generation+"\tBest Solution of The population：" + population
                    .getFittest(0).toString()+"\t");
            //交叉
            population = gene.crossoverPopulation(population);
            //变异
            population = gene.mutation(population);
            //再次评估
            gene.evalPopulation(population,cities);
            generation++;
        }
    }
}
