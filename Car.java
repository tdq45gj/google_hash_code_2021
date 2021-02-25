import java.util.*;

public class Car {
    public Queue<Street> path;
    public int currTime;
    public int minTime;

    public Car(int numStreets) {
        this.path = new ArrayDeque<Street>(numStreets);
    }

    @Override public String toString() {
        return this.path.toString();
    }


}
