package Services;

import State.State;

import java.util.Comparator;

public class Cmp implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
        return Integer.compare(o1.getCost(), o2.getCost());
    }
}
