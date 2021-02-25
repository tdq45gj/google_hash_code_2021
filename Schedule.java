import java.util.*;

public class Schedule {
    String name;
    int time;

    public Schedule(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override public String toString() {
        return this.name + " " + this.time;
    }
}
