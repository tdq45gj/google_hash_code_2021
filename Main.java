import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.*; // Import the Scanner class to read text files

public class Main {
    private static int duration;
    private static int numIntersections;
    private static int numStreets;
    private static int numCars;
    private static List<Car> cars;
    private static Map<Integer, Intersection> intersections;
    private static Map<String, Street> streets;

    private static void initializeFromFile(String fileName) {
        System.out.println("Starting to read in file");
        try {
            // start reading a file
            File inputFile = new File(fileName);
            Scanner fileScanner = new Scanner(inputFile);
            // read the first line
            String[] firstLineVars = fileScanner.nextLine().split(" ");
            duration = Integer.valueOf(firstLineVars[0]);
            numIntersections = Integer.valueOf(firstLineVars[1]);
            numStreets = Integer.valueOf(firstLineVars[2]);
            numCars = Integer.valueOf(firstLineVars[3]);

            intersections = new HashMap<>(numIntersections);
            streets = new HashMap<>(numStreets);
            cars = new ArrayList<>(numCars);

            // read in intersections
            for (int i = 0; i < numStreets; i++) {
                String[] streetVars = fileScanner.nextLine().split(" ");
                int start = Integer.valueOf(streetVars[0]);
                int end = Integer.valueOf(streetVars[1]);
                String name = streetVars[2];
                int time = Integer.valueOf(streetVars[3]);

                Street street = new Street(time, start, end, name);
                streets.put(name, street);

                if (!intersections.containsKey(start)) {
                    intersections.put(start, new Intersection());
                }

                if (!intersections.containsKey(end)) {
                    intersections.put(end, new Intersection());
                }

                intersections.get(end).inStreets.add(street);
            }

            for (int i = 0; i < numCars; i++) {
                String[] carVars = fileScanner.nextLine().split(" ");
                Car car = new Car(Integer.valueOf(carVars[0]));
                int minTime = 0;

                for (int j = 1; j < carVars.length; j++) {
                    Street street = streets.get(carVars[j]);
                    minTime += street.time;
                    car.path.offer(street);
                }

                if (minTime <= duration) {
                    car.minTime = minTime;
                    cars.add(car);
                    streets.get(carVars[1]).queue.offer(car);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }


    public static void solveGcd() {
        Collections.sort(cars);
        for (Car c : cars) {

        }
    }

    // public static void run(Car c) {
    //     Street s = c.street.poll();
    //     int time = 0;
    //     while (s != null) {
    //         time += s.time;
    //         s = c.street.poll();
    //         Schedule lastSchedule = intersections.getValue(s.start);
    //         Schedule schedule = new Schedule(s.name, time);
    //     }
    // }

    public static void solveRandomly() {
        System.out.println("Starting to solve..");

        for (Car car : cars) {
            Street street = car.path.poll();
            while (street != null) {
                street.count++;
                street = car.path.poll();
            }
        }

        int cycle = duration / 1000;

        for (Map.Entry<Integer, Intersection> entry : intersections.entrySet()) {
            Intersection i = entry.getValue();
            List<Schedule> schedules = i.schedules;
            int total = 0;

            for (Street s : i.inStreets) {
                if (s.count != 0) {
                    schedules.add(new Schedule(s.name, s.count / s.time));
                }
                total += s.count;
            }

            if (schedules.size() > 0) {
                for (Schedule schedule : schedules) {
                    schedule.time =(int) (((float) schedule.time / (float) total) * cycle);
                    if (schedule.time == 0) {
                        schedule.time = 1;
                    }
                }
            } else {
                schedules.add(new Schedule(i.inStreets.get(0).name, duration));
            }

            Collections.shuffle(schedules);
        }
    }


    public static void write(String fileName) {
        System.out.println("Starting to write results to file...");
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            fileWriter.write("" + numIntersections);
            fileWriter.write("\n");

            for (Map.Entry<Integer, Intersection> entry : intersections.entrySet()) {
                fileWriter.write("" + entry.getKey() + "\n");
                fileWriter.write("" + entry.getValue().schedules.size() + "\n");

                for (Schedule schedule : entry.getValue().schedules) {
                    fileWriter.write(schedule.toString());
                    fileWriter.write("\n");
                }

                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        initializeFromFile(args[0] + ".txt");
        solveGcd();
        solveRandomly();
        write(args[0] + ".out");
    }
}
