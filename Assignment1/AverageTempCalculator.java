import java.util.Scanner;

public class AverageTempCalculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

        //get number of days
        System.out.print("Enter number of days: ");
        int numDays = scanner.nextInt();

        //for decimal numbers and makes array for numdays
        double[] temperatures = new double[numDays];
        double sum = 0;

        //enter temperatures
        for (int i = 0; i < numDays; i++) {
            System.out.print("Enter number for day " +(i + 1) + ":");
            temperatures[i] = scanner.nextDouble();
            sum += temperatures[i];
        }

        //calculate average temperature
        double average = sum / numDays;
        System.out.printf("Average temperature: %.2f%n", average);

        //how many days above average temp
        int aboveAverageTemp = 0;
        for (double temp : temperatures) {
            if (temp > average) {
                aboveAverageTemp++;
            }
        }

        System.out.println("Number of days above average: "+ aboveAverageTemp);
    }
}
}
