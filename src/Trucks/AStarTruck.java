package Trucks;
import Heuristics.Heuristic;
import Services.CmpAStar;
import State.State;

import java.util.PriorityQueue;

public class AStarTruck extends Truck {
    Heuristic heuristic;

    public AStarTruck(Heuristic heuristic){
        super();
        this.states = new PriorityQueue<State>(new CmpAStar());
        this.heuristic = heuristic;
    }
    //    public boolean checkAStar(State state) {
//        if (DeliveryCompany.getInstance().visitedStates.isEmpty())
//            return true;
//        if (!DeliveryCompany.getInstance().visitedStates.contains(state))
//            return true;
//        for (State st : DeliveryCompany.getInstance().visitedStates) {
//            if (state.truckPosition.equals(st.truckPosition)) {
//                return state.cost < st.cost;
//            }
//        }
//        return false;
//    }
    public void setCost(State state) {
        state.setCost(state.getCostOfMovingToNewPosition());
        state.setAStartCost(state.getCost() + this.heuristic.calc(state));
    }
}
