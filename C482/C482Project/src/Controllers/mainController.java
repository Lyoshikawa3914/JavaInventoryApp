package Controllers;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theClasses.InHouse;
import theClasses.Inventory;
import theClasses.Part;
import theClasses.Product;

//import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    // the table and table columns of parts from sample.fxml
    @FXML public TableView<Part> partsTable;
    @FXML public TableColumn<Part, Integer> partIDColumn;
    @FXML public TableColumn<Part, String> partNameColumn;
    @FXML public TableColumn<Part, Integer> partInventoryColumn;
    @FXML public TableColumn<Part, Double> partPriceColumn;


    @FXML public TextField mainPartTextField;

    // the table and table columns of products from sample.fxml
    @FXML public TableView<Product> productTable;
    @FXML public TableColumn<Product, Integer> productIDColumn;
    @FXML public TableColumn<Product, String> productNameColumn;
    @FXML public TableColumn<Product, Integer> productInventoryColumn;
    @FXML public TableColumn<Product, Double> productPriceColumn;

    @FXML public TextField productTextField;

    // Buttons in Inventory from sample.fxml
    @FXML public Button addPartsButton;
    @FXML public Button modifyPartButton;
    @FXML public Button deleteButton;
    @FXML public Button addProductButton;
    @FXML public Button exitButton;

    // the containers for the application that contains all contents of sample.fxml
    Stage stage;
    Parent scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // displays table
        partsTable.setItems(Inventory.getAllParts());

         // displays the columns to the created table
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // displays table
        productTable.setItems(Inventory.getAllProducts());

        // displays the columns to the created table
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
    // method to change screens after clicking "add", "modify" buttons
    @FXML void changeScreens(ActionEvent actionEvent, String string ) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML void mainPartSearchField(ActionEvent actionEvent) throws IOException {
        // After typing some text in search field, table will display items with those characters

        String text = mainPartTextField.getText();
        System.out.println("Searching...");

        ObservableList<Part> searchedParts = Inventory.lookupPart(text);

        // if the entered text is an integer, it will meet the condition for the if statement
        // then the table will show rows of the entered Id
        if (searchedParts.size() == 0) {
            int thePartID = Integer.parseInt(text);
            Part theID = Inventory.lookupPart(thePartID);
            if (theID != null) {
                searchedParts.add(theID);
            }
        }
        // if the text entered is a string, will show rows that share the same letter
        partsTable.setItems(searchedParts);
    }

    @FXML void productTextFieldOnAction(ActionEvent actionEvent) throws IOException {
        // After typing some text in search field, table will display items with those characters

        String text = productTextField.getText();
        System.out.println("Searching...");

        ObservableList<Product> searchedProduct = Inventory.lookupProduct(text);

        // if the entered text is an integer, it will meet the condition for the if statement
        // then the table will show rows of the entered Id
        if (searchedProduct.size() == 0) {
            int theProductID = Integer.parseInt(text);
            Product theID = Inventory.lookupProduct(theProductID);
            if (theID != null) {
                searchedProduct.add(theID);
            }
        }
        // if the text entered is a string, will show rows that share the same letter
        productTable.setItems(searchedProduct);
    }

    @FXML void addPartsAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Part Screen");

        // scene variable stores function that allows the actionEvent handler know that
        // the event is caused by a button.
        // getScene() + getWindow() creates a special container
        // then place (Stage) in front of statement to indicate that the
        // statement is a Stage type.
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();

        //call FXMLLoader with load method
        // getClass().getResource() plus the desired file will store it to scene
        scene = FXMLLoader.load(getClass().getResource("/Interface/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML void onActionModifyPartScreen(ActionEvent actionEvent) throws IOException {
        //this.changeScreens(actionEvent, "/Interface/modifyPart.fxml");

        try {
            FXMLLoader loader = new FXMLLoader();

            // assigns this pathway to a loader
            loader.setLocation(getClass().getResource("../Interface/modifyPart.fxml"));
            loader.load();

            // gets the controller associated with the fxml file thats stored on the loader
            modifyPartController ADMController = loader.getController();

            ADMController.sendPart(partsTable.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.showAndWait();
        }
        catch(IllegalStateException e) {

        }

    }
    @FXML void deleteButtonOnAction (ActionEvent actionEvent) throws IOException {

        if (Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem())) {
            System.out.println("delete Button clicked");
        }
        else {
            System.out.println("No deletion");
        };

    }
    @FXML void addProductAction(ActionEvent actionEvent) throws IOException {
        System.out.println("adding product");
        this.changeScreens(actionEvent, "/Interface/addProduct.fxml");

    }
    @FXML void onActionModifyProductScreen(ActionEvent actionEvent) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();

            // assigns this pathway to a loader
            loader.setLocation(getClass().getResource("../Interface/modifyProduct.fxml"));
            loader.load();

            // gets the controller associated with the fxml file thats stored on the loader
            modifyProductController ADMController = loader.getController();

            ADMController.productSendPart(productTable.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.showAndWait();
        }
        catch(IllegalStateException e) {

        }

    }
    @FXML void deleteProductOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Deleting product");
    }
    @FXML void exitAction(ActionEvent actionEvent) {
        // After clicking 'exit', app will close out
        System.out.println("exiting app");
        Platform.exit();
    }

}
