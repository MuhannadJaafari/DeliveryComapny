package Company;

import DataStructures.Pair;

import java.util.List;

public class CityMap {
    private int width;
    private int height;
    private static CityMap cityMap = null;
    private char[][] maze;
    public Pair<Integer, Integer> startingPoint;
    public Pair<Integer, Integer> truckPosition;

    @Override
    protected CityMap clone() throws CloneNotSupportedException {
        CityMap cityMap = (CityMap) super.clone();
        cityMap.maze = new char[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            cityMap.maze[i] = this.maze[i].clone();
        }
        return cityMap;
    }

    public List<Pair<Integer, Integer>> packagesStartingPoints;
    public List<Pair<Integer, Integer>> packagesDeliveryPoints;

    public void setDimensions(int height, int width){
        this.height = height;
        this.width = width;
        this.maze = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.maze[i][j] = '*';
            }
        }
    }

    public static CityMap getInstance() {
        if (CityMap.cityMap == null) {
            cityMap = new CityMap( );
        }
        return cityMap;
    }

    public void setStartingPoint(Pair<Integer, Integer> truckPosition) {

        this.truckPosition = truckPosition;
        this.startingPoint = truckPosition.clone();
    }

    public void setPackagesStartingPoints(List<Pair<Integer, Integer>> packagesStartingPoints) {
        this.packagesStartingPoints = packagesStartingPoints;
    }

    public void setPackagesDeliveryPoints(List<Pair<Integer, Integer>> packagesDeliveryPointsPoints) {
        this.packagesDeliveryPoints = packagesDeliveryPointsPoints;
    }

    public void printMaze() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int indexPackage = this.packagesStartingPoints.indexOf(new Pair<>(i, j));
                int indexDeliveryPackage = this.packagesDeliveryPoints.indexOf(new Pair<>(i, j));
                if (this.truckPosition.equals(new Pair<>(i, j)))
                    System.out.print("T  ");
                else if (indexPackage != -1)
                    System.out.print("P" + indexPackage + " ");
                else if (indexDeliveryPackage != -1)
                    System.out.print("D" + indexDeliveryPackage + " ");
                else System.out.print(this.maze[i][j] + "  ");
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------");
    }

    public void setRestOfMaze(List<Pair<Pair<Integer,Integer>, Character>> restOfMaze) {
        for(Pair<Pair<Integer,Integer>, Character> s : restOfMaze)
            this.set(s.second(),s.first().first(),s.first().second());
    }

    public void changeTruckPosition(int x, int y) {
        this.truckPosition.setKey(x);
        this.truckPosition.setValue(y);
    }

    public boolean checkIfOutOfBounds(int x, int y) {
        return x < 0 || y < 0 || y > this.width - 1 || x > this.height - 1;
    }

    public boolean checkIfCellIsBlock(int x, int y) {

        return this.maze[x][y] == '#';
    }

    public void setBlockCell(int x, int y) {
        this.maze[x][y] = '#';
    }

    public int getIndexOfDeliveryPoint(Pair<Integer, Integer> p) {
        return this.packagesDeliveryPoints.indexOf(p);
    }

    public int getNumberOfPackages() {
        return this.packagesStartingPoints.size();
    }

    public int checkIfPackageExists(Pair<Integer, Integer> p) {
        return this.packagesStartingPoints.indexOf(p);
    }

    public void set(char c, int row, int column) {
        this.maze[row][column] = c;
    }
}
