package State;

import Company.CityMap;
import Company.DeliveryCompany;
import DataStructures.Pair;
import Services.DistanceCalculator;
import Trucks.Truck;
import java.util.LinkedList;
import java.util.List;

public class State implements Cloneable {
    private CityMap cityMap;
    private int cost;
    private List<Integer> packages, servedPackages;
    private State parentState;
    public int indexOfNewlyTakenPackage, indexOfNewlyDeliveredPackage;
    private Pair<Integer, Integer> truckPosition;

    public State() {
        this.cityMap = CityMap.getInstance();
        this.packages = new LinkedList<>();
        this.servedPackages = new LinkedList<>();
        this.indexOfNewlyTakenPackage = -1;
        this.indexOfNewlyDeliveredPackage = -1;
    }

    public void solve() {
        if (this.isFinished()) {
            if (DeliveryCompany.getInstance().minCost > this.cost) {
                DeliveryCompany.getInstance().minCost = this.cost;
                DeliveryCompany.getInstance().finalState = this;
            }
            return;
        }
        this.expand();
        while(DeliveryCompany.getInstance().truck.hasStates()){
            Company.DeliveryCompany.getInstance().truck.getNewState().solve();
        }
    }

    private void expand() {
        Truck truck = DeliveryCompany.getInstance().truck;
        truck.move(this);
    }

    public void printState() {
        CityMap.getInstance().truckPosition = this.truckPosition;
        CityMap.getInstance().printMaze();
    }

    public void setTruckPosition(Pair<Integer, Integer> truckPosition) {
        this.truckPosition = truckPosition;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }


    public void addPackage(int indexOfPackage) {
        this.indexOfNewlyTakenPackage = indexOfPackage;
        this.packages.add(indexOfPackage);
    }
    public void removePackage(int value) {
        for (int i = 0; i < this.packages.size(); i++) {
            if (this.packages.get(i) == value)
                this.packages.remove(i);
        }
    }
    public void servePackage(int indexOfDeliveryPoint) {
        this.indexOfNewlyDeliveredPackage = indexOfDeliveryPoint;
        this.removePackage(indexOfDeliveryPoint);
        this.servedPackages.add(indexOfDeliveryPoint);
    }
    public State makeChildState() {
        State newState = this.clone();
        newState.parentState = this;
        newState.indexOfNewlyDeliveredPackage = -1;
        newState.indexOfNewlyTakenPackage = -1;
        return newState;
    }

    public boolean isFinished() {
        return this.getNumberOfDeliveredPackages() == DeliveryCompany.getInstance().cityMap.packagesDeliveryPoints.size() && this.truckPosition.equals(this.cityMap.startingPoint);
    }
    public boolean checkIfVisited() {
        return DeliveryCompany.getInstance().checkIfVisited(this);
    }
    public boolean checkIfValid(char c) {
        int x = this.truckPosition.first() + DistanceCalculator.dx.get(c);
        int y = this.truckPosition.second() + DistanceCalculator.dy.get(c);
        return !this.cityMap.checkIfOutOfBounds(x, y) && !this.cityMap.checkIfCellIsBlock(x, y);
    }
    public boolean checkIfPackageExists(int index) {
        for (int indexOfPackagesPoint : this.packages) {
            if (indexOfPackagesPoint == index)
                return true;
        }
        return false;
    }
    public boolean checkIfPackageIsServed(int index) {
        return this.servedPackages.contains(index);
    }


    public int getCostOfMovingToNewPosition() {
        return this.getCostOfParentState() + this.getParentPackagesSize() + 1;
    }


    public Pair<Integer, Integer> getTruckPosition() {
        return this.truckPosition;
    }
    public int getNumberOfDeliveredPackages() {
        return this.servedPackages.size();
    }
    public int getNumberOfPackages() {
        return this.packages.size();
    }
    public int getCost() {
        return this.cost;
    }
    public CityMap getCityMap(){
        return this.cityMap;
    }
    public List<Integer> getPackages() {
        return this.packages;
    }
    public List<Integer> getServedPackages() {
        return this.servedPackages;
    }
    public State getParentState(){
        return this.parentState;
    }
    public int getCostOfParentState() {
        if (this.parentState == null) return 0;
        else return this.parentState.cost;
    }
    public int getParentPackagesSize() {
        if (this.parentState == null) return 0;
        else return this.parentState.packages.size();
    }


    @Override
    public State clone() {
        try {
            State state = (State) super.clone();
            state.packages = new LinkedList<>();
            state.servedPackages = new LinkedList<>();
            state.packages.addAll(this.packages);
            state.servedPackages.addAll(this.servedPackages);
            state.parentState = this.parentState;
            state.truckPosition = this.truckPosition.clone();

            return state;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
