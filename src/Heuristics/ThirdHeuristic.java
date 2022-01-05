package Heuristics;

import Company.CityMap;
import State.State;

public class ThirdHeuristic implements Heuristic {
    SecondHeuristic helperHeuristic;

    public ThirdHeuristic() {
        helperHeuristic = new SecondHeuristic();
    }

    @Override
    public int calc(State state) {
        int currMx = 0;
        for (int i = 0; i < CityMap.getInstance().getNumberOfPackages(); i++) {
            helperHeuristic.setPackageIndex(i);
            currMx = Math.max(currMx, helperHeuristic.calc(state));
        }
        return currMx;
    }
}
