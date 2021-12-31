package Heuristics;

import Company.CityMap;
import DataStructures.Pair;
import State.State;
import Math.EuclideanDistance;
public class SecondHeuristic implements Heuristic{
    private int packageIndex = 0;
    @Override
    public int calc(State state) {
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        Pair<Integer, Integer> firstPackageLocation = CityMap.getInstance().packagesStartingPoints.get(this.packageIndex);
        Pair<Integer, Integer> firstDeliveryPackageLocation = CityMap.getInstance().packagesDeliveryPoints.get(this.packageIndex);
        Pair<Integer, Integer> currentTruckLocation = state.getTruckPosition();
        Pair<Integer, Integer> startingPointLocation = CityMap.getInstance().startingPoint;
        return euclideanDistance.get(currentTruckLocation, firstPackageLocation) +
                euclideanDistance.get(firstPackageLocation, firstDeliveryPackageLocation) +
                euclideanDistance.get(firstDeliveryPackageLocation, startingPointLocation);
    }
    public void setPackageIndex(int packageIndex){
        this.packageIndex = packageIndex;
    }
}
