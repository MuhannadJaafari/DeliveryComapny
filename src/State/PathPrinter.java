package State;

import State.State;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PathPrinter {
    public static List<State> pathList;
    public static void print(State state){
        generate(state);
        Collections.reverse(pathList);
        int index = 0;
        for(State currState : pathList){
            System.out.println(index ++);
            if (currState.indexOfNewlyTakenPackage != -1) {
                System.out.println("Package number " + (currState.indexOfNewlyTakenPackage + 1) + " has been Taken !");
                currState.indexOfNewlyTakenPackage = -1;
            } else if (currState.indexOfNewlyDeliveredPackage != -1) {
                System.out.println("Package number " + (currState.indexOfNewlyDeliveredPackage + 1) + " has been Delivered !");
                currState.indexOfNewlyDeliveredPackage = -1;
            }
            currState.printState();
        }
    }
    private static void generate(State state){
        pathList = new LinkedList<>();
        State currState = state.clone();
        while (currState != null) {
            pathList.add(currState);
            if (currState.getParentState() == null)
                break;
            currState = currState.getParentState().clone();
        }
    }
}
