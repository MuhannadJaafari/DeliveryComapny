package Trucks;

import java.util.PriorityQueue;
import java.util.Queue;

import Company.DeliveryCompany;
import Services.Cmp;
import State.State;

public class Truck {
    private final char[] moves;
    public Queue<State> states;

    Truck() {
        this.moves = new char[4];
        moves[0] = 'u';
        moves[1] = 'd';
        moves[2] = 'l';
        moves[3] = 'r';
        this.states = new PriorityQueue<>(new Cmp());
    }

    public void move(State currentState) {
        for (char c : this.moves) {
            if (currentState.checkIfValid(c)) {
                State newState = currentState.makeChildState();
                DeliveryCompany.getInstance().truckMover.move(newState, c);
                int indexOfDeliveryPoint = newState.getCityMap().getIndexOfDeliveryPoint(newState.getTruckPosition());
                if (indexOfDeliveryPoint != -1) {
                    if (newState.checkIfPackageExists(indexOfDeliveryPoint)) {
                        newState.servePackage(indexOfDeliveryPoint);
                    }
                } else {
                    int index = newState.getCityMap().checkIfPackageExists(newState.getTruckPosition());
                    if (index != -1 && !newState.checkIfPackageIsServed(index) && !newState.checkIfPackageExists(index)) {
                        State packageNewState = newState.clone();
                        this.setCost(packageNewState);
                        packageNewState.addPackage(index);
                        if(!packageNewState.checkIfVisited())
                            this.pushState(packageNewState);
                    }

                }
                this.setCost(newState);
                if(!newState.checkIfVisited())
                    this.pushState(newState);
            }
        }
    }


    public void pushState(State state) {
        this.states.add(state);
        DeliveryCompany.getInstance().visitedStates.add(state);
    }

    public State getNewState() {
        return this.states.poll();
    }

    public boolean hasStates() {
        return !this.states.isEmpty();
    }

    /*public List<State> getNewAddedPackages() {
        List<State> listOfStates = new LinkedList<State>();
        while (!this.states.isEmpty())
            listOfStates.add(this.states.poll());
        Collections.reverse(listOfStates);
        return listOfStates;
    }*/

    public void setCost(State state) {
        state.setCost(state.getCostOfMovingToNewPosition());
    }

}
