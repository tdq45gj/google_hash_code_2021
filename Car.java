import java.util.*;

public class Car {
    public Queue<Street> path;

    public Car(int numStreets) {
        this.path = new ArrayDeque<Street>(numStreets);
    }
}
