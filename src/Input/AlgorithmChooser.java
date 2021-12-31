package Input;

import Heuristics.FirstHeuristic;
import Heuristics.SecondHeuristic;
import Heuristics.ThirdHeuristic;
import Trucks.AStarTruck;
import Trucks.Truck;
import Trucks.UfcTruck;
import java.util.Scanner;

public class AlgorithmChooser {
    public static Truck get(){
        System.out.println("Choose 1 for Uniform Cost Search Algorithm");
        System.out.println("Choose 2 for A* Algorithm");
        Scanner read = new Scanner(System.in);
        int type = read.nextInt();
        switch (type){
            case 1 : return new UfcTruck();
            case 2 :{
                System.out.println("Choose 1 for First Heuristic");
                System.out.println("Choose 2 for Second Heuristic");
                System.out.println("Choose 3 for Third Heuristic");
                type = read.nextInt();
                switch (type){
                    case 1 : return new AStarTruck(new FirstHeuristic());
                    case 2 : return new AStarTruck(new SecondHeuristic());
                    case 3 : return new AStarTruck(new ThirdHeuristic());
                    default:break;
                }
            }
            default:break;
        }
        return null;
    }
}
