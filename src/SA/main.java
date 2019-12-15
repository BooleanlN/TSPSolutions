package SA;

import java.io.*;

/**
 * Created by 13633 on 2019/12/15.
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
                        if(index==size)return cities;
                        String[] arr = line.split(" ");
                        City city = new City(Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                        cities[index] = city;
                        index++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
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
        SimulateAnneal anneal = new SimulateAnneal(400,0.95,1000);
        Individual solve  = anneal.SA(cities);
        System.out.println(solve.getFitness());
    }
}
