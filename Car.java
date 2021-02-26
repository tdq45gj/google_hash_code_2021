import java.util.*;

public class Car implements Comparable<Car>{
    public Queue<Street> path;
    public int currTime;
    public int minTime;

    public Car(int numStreets) {
        this.path = new ArrayDeque<Street>(numStreets);
    }

    @Override public String toString() {
        return this.path.toString();
    }


    @Override
    public int compareTo(Car c) {
        if (this.minTime > c.minTime) {
            return 1;
        } else if (this.minTime < c.minTime) {
            return -1;
        }
        return 0;
    }
}
