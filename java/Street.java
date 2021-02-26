import java.util.*;

public class Street {
    public Queue<Car> queue;
    public int time;
    public int start;
    public int end;
    public String name;

    public int count;

    public Street(int time, int start, int end, String name) {
        this.time = time;
        this.start = start;
        this.end = end;
        queue = new ArrayDeque<Car>();
        this.name = name;
    }

    @Override public String toString() {
        return this.name;
    }
}
