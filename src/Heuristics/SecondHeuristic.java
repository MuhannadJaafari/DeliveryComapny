package Heuristics;

import Company.CityMap;
import DataStructures.Pair;
import State.State;
import Math.EuclideanDistance;

public class SecondHeuristic implements Heuristic {
    private int packageIndex = 0;

    @Override
    public int calc(State state) {
        int ans = 0;
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        Pair<Integer, Integer> firstPackageLocation = CityMap.getInstance().packagesStartingPoints.get(packageIndex);
        Pair<Integer, Integer> firstDeliveryPackageLocation = CityMap.getInstance().packagesDeliveryPoints.get(packageIndex);
        Pair<Integer, Integer> currentTruckLocation = state.getTruckPosition();
        Pair<Integer, Integer> startingPointLocation = CityMap.getInstance().startingPoint;
        int distanceFromCurrentTruckLocationToPackageLocation = 0, distanceFromPackageLocationToPackageDeliveryLocation = 0;
        if (state.getServedPackages().contains(packageIndex)) {
            return (euclideanDistance.get(currentTruckLocation, startingPointLocation) * (state.getNumberOfPackages() + 1));
        } else if (state.getPackages().contains(packageIndex)) {
            return (euclideanDistance.get(currentTruckLocation, firstDeliveryPackageLocation) * (state.getNumberOfPackages() + 1))
                    + euclideanDistance.get(firstDeliveryPackageLocation, startingPointLocation) * state.getNumberOfPackages();
        } else {
            return (euclideanDistance.get(currentTruckLocation, firstPackageLocation) * (state.getNumberOfPackages() + 1)) +
                    (euclideanDistance.get(firstPackageLocation, firstDeliveryPackageLocation) * (state.getNumberOfPackages() + 2)) +
                    euclideanDistance.get(firstDeliveryPackageLocation, startingPointLocation) * (state.getNumberOfPackages() + 1);
        }
    }

    public void setPackageIndex(int packageIndex) {
        this.packageIndex = packageIndex;
    }
}
