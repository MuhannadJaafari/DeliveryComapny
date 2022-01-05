package Company;

import DataStructures.Pair;
import State.State;
import State.PathPrinter;
import Trucks.Truck;
import Trucks.TruckMover;

import java.util.LinkedList;
import java.util.List;

public class DeliveryCompany {
    private static DeliveryCompany deliveryCompany = null;
    public int minCost = -1;
    public State state;
    public CityMap cityMap;
    public Truck truck;
    public State finalState = null;
    public List<State> visitedStates = new LinkedList<State>();
    public TruckMover truckMover;
    public int numberOfNodes = 0;

    private DeliveryCompany() {
        this.truckMover = new TruckMover();
        this.cityMap = CityMap.getInstance();
        this.state = new State();
    }

    public void setStartingPoint(Pair<Integer, Integer> startingPoint) {
        this.cityMap.setStartingPoint(startingPoint);
        this.state.setTruckPosition(startingPoint.clone());

    }

    public void setPackagesStartingPoints(List<Pair<Integer, Integer>> packagesStartingPoints) {
        this.cityMap.setPackagesStartingPoints(packagesStartingPoints);
    }

    public void setPackagesDeliveryPoints(List<Pair<Integer, Integer>> packagesDeliveryPointsPoints) {
        this.cityMap.setPackagesDeliveryPoints(packagesDeliveryPointsPoints);
    }

    public void setRestOfMaze(List<Pair<Pair<Integer, Integer>, Character>> restOfMaze) {
        this.cityMap.setRestOfMaze(restOfMaze);
    }

    public void setTruckType(Truck truck) {
        this.truck = truck;
        this.truck.pushState(this.state);
    }

    public void start() {
        this.state.solve();
        PathPrinter.print(this.finalState);
        System.out.println("Number of nodes is " + this.numberOfNodes);
        System.out.println("Minimum Cost is " + this.minCost);
    }

    public static DeliveryCompany getInstance() {
        if (DeliveryCompany.deliveryCompany == null) {
            DeliveryCompany.deliveryCompany = new DeliveryCompany();
        }
        return deliveryCompany;
    }

    public boolean checkIfVisited(State state) {
        boolean ok1 = false,ok2 = false;
        for (State st : this.visitedStates) {
            ok1 = false;
            ok2 = false;
            if (st.getNumberOfDeliveredPackages() == state.getNumberOfDeliveredPackages()
                    && state.getTruckPosition().equals(st.getTruckPosition())
                    && st.getNumberOfPackages() == state.getNumberOfPackages()) {
                for (int index : st.getPackages()) {
                    if (!state.getPackages().contains(index)) {
                        ok1 = true;
                        break;
                    }
                }
                for (int index : st.getServedPackages()) {
                    if (!state.getServedPackages().contains(index)) {
                        ok2 = true;
                        break;
                    }
                }
                if(ok1 || ok2) continue;
                if (state.getCost() < st.getCost()) {
                    this.visitedStates.remove(st);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
