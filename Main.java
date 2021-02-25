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

            // read in intersections
            for (int i = 0; i < numStreets; i++) {
                String[] streetVars = fileScanner.nextLine().split(" ");
                int start = Integer.valueOf(streetVars[0]);
                int end = Integer.valueOf(streetVars[1]);
                String name = streetVars[2];
                int time = Integer.valueOf(streetVars[3]);

                Street street = new Street(time, end, name);
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
                for (int j = 1; j < carVars.length; j++) {
                    car.path.offer(streets.get(carVars[j]));
                }
                streets.get(carVars[1]).queue.offer(car);
                System.out.println(streets.get(carVars[1]).queue);
                // Car toPrint = streets.get(carVars[1]).queue.poll();
                // while (toPrint != null) {
                //     System.out.println(toPrint);
                //     toPrint = streets.get(carVars[1]).queue.poll();
                // }
            }



            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void solve() {
        System.out.println("Starting to solve..");

    }

    // public static void write(String fileName) {
    //     System.out.println("Starting to write results to file...");
    //     try {
    //         FileWriter fileWriter = new FileWriter(fileName);
    //
    //         fileWriter.write("" + numIntersections);
    //         fileWriter.write("\n");
    //
    //         for (Map.Entry<Integer, Intersection> entry : intersections.entrySet()) {
    //             fileWriter.write("" + entry.key + "\n");
    //             fileWriter.write("" + entry.schedules.size() + "\n");
    //
    //             for (Schedule schedule : entry.schedules) {
    //                 fileWriter.write(schedule);
    //                 fileWriter.write("\n");
    //             }
    //
    //             fileWriter.flush();
    //         }
    //         fileWriter.close();
    //     } catch (IOException e) {
    //         System.out.println(e);
    //     }
    // }

    public static void main(String[] args) {
        initializeFromFile("a.txt");
    }
}
