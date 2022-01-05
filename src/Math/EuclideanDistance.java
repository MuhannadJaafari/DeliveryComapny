package Math;

import DataStructures.Pair;

public class EuclideanDistance {
    public int get(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2){
         return Math.abs(p1.first() - p2.first()) +
                Math.abs(p1.second() - p2.second());
    }
}
