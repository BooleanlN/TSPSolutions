package GA.TSP;

/**
 * Created by 13633 on 2019/12/9.
 */
public class City {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public City(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public double distance(City city){
        return Math.sqrt(Math.abs(Math.pow(city.getX() - x,2)+Math.pow(city.getY()-y,2)));
    }
}
