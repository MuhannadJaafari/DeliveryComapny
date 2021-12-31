package Services;

import DataStructures.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileReader {
    private final Pair<Integer, Integer> dimensions;
    private final Pair<Integer, Integer> startingPoint;
    private final Set<Pair<Pair<Integer,Integer>, Integer>> listOfPackagesStartingPoint;
    private final Set<Pair<Pair<Integer,Integer>, Integer>> listOfPackagesDeliveringPoint;
    private final List<Pair<Pair<Integer, Integer>, Character>> restOfMaze;
    public FileReader() {
        this.dimensions = new Pair<Integer, Integer>();
        this.startingPoint = new Pair<Integer, Integer>();
        this.restOfMaze = new LinkedList<>();
        this.listOfPackagesStartingPoint = new TreeSet<>(new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
                return Integer.compare(o1.second(),o2.second());
            }
        });
        this.listOfPackagesDeliveringPoint = new TreeSet<>(new Comparator<Pair<Pair<Integer, Integer>, Integer>>() {
            @Override
            public int compare(Pair<Pair<Integer, Integer>, Integer> o1, Pair<Pair<Integer, Integer>, Integer> o2) {
                return Integer.compare(o1.second(),o2.second());
            }
        });
    }

    public void readInput(String path) throws IOException {
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
        String read;
        int row = 0;
        String dimensions = bufferedReader.readLine();
        List<String> items = Arrays.asList(dimensions.split("\\s*,\\s*"));
        this.dimensions.setKey(Integer.parseInt(items.get(0)));
        this.dimensions.setValue(Integer.parseInt(items.get(1)));
        while ((read = bufferedReader.readLine()) != null) {
            items = Arrays.asList(read.split("\\s* \\s*"));
            int column = 0;
            for (String s : items) {
                if (s.charAt(0) == 'P') {
                    this.listOfPackagesStartingPoint.add(new Pair<>(new Pair<>(row,column), s.charAt(1) - '0'));
                } else if (s.charAt(0) == 'T') {
                    this.startingPoint.setKey(row);
                    this.startingPoint.setValue(column);
                } else if (s.charAt(0) == 'D') {
                    listOfPackagesDeliveringPoint.add(new Pair<>(new Pair<>(row,column), s.charAt(1) - '0'));
                } else {
                    this.restOfMaze.add(new Pair<>(new Pair<>(row, column), s.charAt(0)));
                }
                column++;
            }
            row++;
        }
    }

    public Pair<Integer, Integer> getDimensions() {
        return this.dimensions;
    }

    public Pair<Integer, Integer> getStartingPoint() {
        return this.startingPoint;
    }

    public List<Pair<Integer, Integer>> getListOfPackagesStartingPoint() {
        List<Pair<Integer,Integer>> tmp = new LinkedList<>();
        for(Pair<Pair<Integer, Integer>, Integer> st : this.listOfPackagesStartingPoint){
            tmp.add(st.first());
        }
        return tmp;
    }

    public List<Pair<Integer, Integer>> getListOfPackagesDeliveringPoint() {
        List<Pair<Integer,Integer>> tmp = new LinkedList<>();
        for(Pair<Pair<Integer, Integer>, Integer> st : this.listOfPackagesDeliveringPoint){
            tmp.add(st.first());
        }
        return tmp;
    }

    public List<Pair<Pair<Integer, Integer>, Character>> getRestOfMaze() {
        return this.restOfMaze;
    }
}
