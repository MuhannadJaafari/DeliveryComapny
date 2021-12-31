package Trucks;

import DataStructures.Pair;
import Services.DistanceCalculator;
import State.State;

public class TruckMover {
    public void move(State state, char c) {
        Pair<Integer,Integer> truckPosition = state.getTruckPosition();
        int x = truckPosition.first(), y = truckPosition.second();
        if (state.checkIfValid(c)) {
            x += DistanceCalculator.dx.get(c);
            y += DistanceCalculator.dy.get(c);
            state.setTruckPosition(new Pair<>(x,y));
        }
    }
}

