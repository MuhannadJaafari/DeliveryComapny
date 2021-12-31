package Heuristics;

import Company.CityMap;
import DataStructures.Pair;
import State.State;
import Math.EuclideanDistance;
public class FirstHeuristic implements Heuristic{
    @Override
    public int calc(State state) {
        EuclideanDistance euclideanDistance = new EuclideanDistance();
        Pair<Integer, Integer> currentTruckLocation = state.getTruckPosition();
        Pair<Integer, Integer> startingPointLocation = CityMap.getInstance().startingPoint;
        return euclideanDistance.get(currentTruckLocation, startingPointLocation);
    }
}
