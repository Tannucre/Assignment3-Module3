import java.util.Scanner;
import java.util.Random;

class SquareCalculator extends Thread {

    int number;

    SquareCalculator(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("Square: " + (number * number));
    }
}

class CubeCalculator extends Thread {

    int number;

    CubeCalculator(int number) {
        this.number = number;
    }

    public void run() {
        System.out.println("Cube: " + (number * number * number));
    }
}

class RandomNumberGenerator extends Thread {

    volatile boolean running = true;

    void stopSimulation() {
        running = false;
    }

    public void run() {

        Random random = new Random();

        while (running) {

            int number = random.nextInt(100) + 1;

            System.out.println("Generated: " + number);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

            if (!running) {
                break;
            }

            try {
                if (number % 2 == 0) {

                    SquareCalculator square =
                            new SquareCalculator(number);

                    square.start();
                    square.join();

                } else {

                    CubeCalculator cube =
                            new CubeCalculator(number);

                    cube.start();
                    cube.join();
                }

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("Simulation terminated");
    }
}

public class IoTSensorMonitoring {

    public static void main(String[] args)
            throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        RandomNumberGenerator generator = null;

        while (true) {

            System.out.println("\n1. Start Simulation");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {

                if (generator == null || !generator.isAlive()) {

                    generator = new RandomNumberGenerator();
                    generator.start();

                } else {
                    System.out.println("Simulation is already running");
                }

            } else if (choice == 2) {

                if (generator != null && generator.isAlive()) {
                    generator.stopSimulation();
                    generator.join();
                }

                System.out.println("Program exited");
                break;

            } else {

                System.out.println("Invalid choice");
            }
        }

        sc.close();
    }
}