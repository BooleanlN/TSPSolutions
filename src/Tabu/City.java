package Tabu;

/**
 * Created by 13633 on 2019/12/15.
 */
public class City {
    private int x;
    private int y;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
    public double distance(City city){
        return Math.sqrt(Math.pow(this.x - city.x,2)+Math.pow(this.y-city.y,2));
    }
}
