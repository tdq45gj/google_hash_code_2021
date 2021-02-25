import java.util.*;

public class Street {
    public Queue<Car> queue;
    public int time;
    public int end;
    public String name;
    
    public int count;

    public Street(int time, int end, String name) {
        this.time = time;
        this.end = end;
        queue = new ArrayDeque<Car>();
        this.name = name;
    }

    @Override public String toString() {
        return this.name;
    }
}
