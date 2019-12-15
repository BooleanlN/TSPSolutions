package Ant;

import java.io.*;

/**
 * Created by 13633 on 2019/12/11.
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
                        String[] arr = line.split(" ");
                        City city = new City(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                        if(index == size)return cities;
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
//        for (int i = 0; i < cities.length; i++) {
//            System.out.println(cities[i].getX());
//        }
        AntAlgorithm anta = new AntAlgorithm(100,0.9,5,
                0.7,100,500,51);
        AntColony antColony = anta.initAntColony();
        anta.antMove(antColony,cities);


        int generation = 1;
        double bestAnswer = antColony.getSmallestAnt().getFitness();
        String order = antColony.getSmallestAnt().getVisitOrder();
        while (!anta.isTerminated(generation)){
            System.out.println(antColony.getSmallestAnt().getFitness());
            if(antColony.getSmallestAnt().getFitness() < bestAnswer){
                bestAnswer = antColony.getSmallestAnt().getFitness();
                order = antColony.getSmallestAnt().getVisitOrder();
            }
            anta.updateConcentration(antColony,cities);
            antColony = anta.initAntColony();

            anta.antMove(antColony,cities);
            generation++;

        }
        System.out.println("best Solution：");
        System.out.println("城市访问顺序："+order);
        System.out.println("最短路径长度："+bestAnswer);
    }
}
