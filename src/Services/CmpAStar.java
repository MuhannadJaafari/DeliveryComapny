package Services;

import State.State;

import java.util.Comparator;

public class CmpAStar implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
        return Integer.compare(o1.getAStarCost(), o2.getAStarCost());
    }
}
