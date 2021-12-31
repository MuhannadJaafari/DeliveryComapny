

import java.io.*;

import Company.DeliveryCompany;
import Input.AlgorithmChooser;
import Services.FileReader;

public class main {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.readInput("C:\\Users\\mo_ma\\Desktop\\test.txt");
        DeliveryCompany deliveryCompany = DeliveryCompany.getInstance();
        deliveryCompany.cityMap.setDimensions(fileReader.getDimensions().first(),fileReader.getDimensions().second());
        deliveryCompany.setStartingPoint(fileReader.getStartingPoint());
        deliveryCompany.setPackagesStartingPoints(fileReader.getListOfPackagesStartingPoint());
        deliveryCompany.setPackagesDeliveryPoints(fileReader.getListOfPackagesDeliveringPoint());
        deliveryCompany.setRestOfMaze(fileReader.getRestOfMaze());
        deliveryCompany.setTruckType(AlgorithmChooser.get());
        deliveryCompany.start();
    }
}
