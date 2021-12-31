package Math;

import DataStructures.Pair;

public class EuclideanDistance {
    public int get(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2){
         return (int) Math.sqrt((Math.pow(Math.abs(p1.first() - p2.first()), 2) +
                Math.pow(Math.abs(p1.second() - p2.second()), 2)));
    }
}
