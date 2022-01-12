package sample;

import Controllers.addPartController;
import Controllers.addProductController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import theClasses.InHouse;
import theClasses.Inventory;
import theClasses.Outsourced;
import theClasses.Part;
import theClasses.Product;


public class Main extends Application {
    static int idPart = 0;
    static int idProduct = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../Interface/sample.fxml"));
//        loader.load();


        Parent root = FXMLLoader.load(getClass().getResource("../Interface/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1300, 575));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // Populates table in Controller.java

        InHouse inhouse1 = new InHouse(idPart += 1, "Giga Cannon", 1000.00, 100, 100, 120, 1);
        Inventory.addPart(inhouse1);


        InHouse inhouse2 = new InHouse(idPart += 1, "Metal Claw", 500.00, 100, 100, 120, 2);
        Inventory.addPart(inhouse2);


        InHouse inhouse3 = new InHouse(idPart += 1, "Happy Bullets", 30.99, 1000, 100, 120, 3);
        Inventory.addPart(inhouse3);


        Outsourced outhouse1 = new Outsourced(idPart += 1, "T-Virus", 1000000.00, 100, 100, 120,
                "Umbrella Corps.");
        Inventory.addPart(outhouse1);

        Product product1 = new Product(idProduct += 1, "MetalGreymon", 100000.00,
        2, 2, 3);
        Inventory.addProduct(product1);

        Product product2 = new Product(idProduct += 1, "WereGarurumon", 90000.00,
                2, 1, 2);
        Inventory.addProduct(product2);

        // this method call will send "id" to addPartController.java's method "incrementID"
        addPartController.incrementIDPart(idPart);
        addProductController.incrementIDProduct((idProduct));
        launch(args);
    }
}
