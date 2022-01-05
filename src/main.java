

import java.io.*;
import java.util.concurrent.TimeUnit;

import Company.DeliveryCompany;
import Heuristics.FirstHeuristic;
import Heuristics.SecondHeuristic;
import Heuristics.ThirdHeuristic;
import Input.AlgorithmChooser;
import Services.FileReader;
import Trucks.AStarTruck;
import Trucks.UfcTruck;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileReader fileReader = new FileReader();
        fileReader.readInput("C:\\Users\\mo_ma\\Desktop\\test.txt");
        DeliveryCompany deliveryCompany = DeliveryCompany.getInstance();
        deliveryCompany.cityMap.setDimensions(fileReader.getDimensions().first(),fileReader.getDimensions().second());
        deliveryCompany.setStartingPoint(fileReader.getStartingPoint());
        deliveryCompany.setPackagesStartingPoints(fileReader.getListOfPackagesStartingPoint());
        deliveryCompany.setPackagesDeliveryPoints(fileReader.getListOfPackagesDeliveringPoint());
        deliveryCompany.setRestOfMaze(fileReader.getRestOfMaze());
        deliveryCompany.setTruckType(AlgorithmChooser.get());
        long startTime = System.nanoTime();
        deliveryCompany.start();

        /* … The code being measured starts … */

        // sleep for 5 seconds
        //TimeUnit.SECONDS.sleep(5);

        /* … The code being measured ends … */

        long endTime = System.nanoTime();

        // get the difference between the two nano time valuess
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}
