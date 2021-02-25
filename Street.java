import java.util.*;

public class Street {
    public Queue<Car> queue;
    public int time;
    public int end;

    public Street(int time, int end) {
        this.time = time;
        this.end = end;
        queue = new ArrayDeque<Car>();
    }
}
