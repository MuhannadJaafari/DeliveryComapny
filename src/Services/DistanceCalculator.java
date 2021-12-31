package Services;

import java.util.HashMap;

public class DistanceCalculator {
     public static HashMap<Character,Integer> dx;
     public static HashMap<Character,Integer> dy;
    static
    {
        dx = new HashMap<>();
        dy = new HashMap<>();
        dx.put('r',0);
        dy.put('r',1);
        dx.put('l',0);
        dy.put('l',-1);
        dx.put('u',-1);
        dy.put('u',0);
        dx.put('d',1);
        dy.put('d',0);
    }
}
