package Trucks;
import Heuristics.Heuristic;
import State.State;

public class AStarTruck extends Truck {
    Heuristic heuristic;
    public AStarTruck(Heuristic heuristic){
        super();
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
        state.setCost(state.getCostOfMovingToNewPosition() + this.heuristic.calc(state));
    }
}
