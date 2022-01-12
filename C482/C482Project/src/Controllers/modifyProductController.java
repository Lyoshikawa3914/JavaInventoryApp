package Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theClasses.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import theClasses.Inventory;
import theClasses.Part;


public class modifyProductController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML public TextField modifyProductID;
    @FXML public TextField modifyProductName;
    @FXML public TextField modifyProductInventory;
    @FXML public TextField modifyProductPrice;
    @FXML public TextField productSearchBar;
    @FXML public TextField modifyProductMax;
    @FXML public TextField modifyProductMin;

    @FXML public TableView<Part> partTable;
    @FXML public TableColumn<Part, Integer> partColumnID;
    @FXML public TableColumn<Part, String> partColumnName;
    @FXML public TableColumn<Part, Integer> partColumnInventory;
    @FXML public TableColumn<Part, Double> partColumnPrice;

    @FXML public Button Add;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // displays table
        partTable.setItems(Inventory.getAllParts());

        // displays the columns to the created table
        partColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partColumnInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // disables user from entering values into ID textfield
        TextField partIDTF = modifyProductID;
        partIDTF.setDisable(true);
    }

    public void productSendPart(Product product) {
        // this method is called from "mainController.java"

        
        modifyProductID.setText(String.valueOf(product.getId()));
        modifyProductName.setText(product.getName());
        modifyProductInventory.setText(String.valueOf(product.getStock()));
        modifyProductPrice.setText(String.valueOf(product.getPrice()));

        modifyProductMax.setText(String.valueOf(product.getMax()));
        modifyProductMin.setText(String.valueOf(product.getMin()));
    }

    @FXML void searchBarAction(ActionEvent actionEvent) throws IOException {
        // After typing some text in search field, table will display items with those characters

        String text = productSearchBar.getText();
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
        partTable.setItems(searchedParts);
    }

    @FXML void changeScreens(ActionEvent actionEvent, String string ) throws IOException {
        // method to return to Main menu
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new Scene(scene));
        stage.show();
    }
   
    @FXML public void productIDTF(javafx.event.ActionEvent actionEvent) {
        System.out.println("ID TExt");
    }
    @FXML public void productNameTF(javafx.event.ActionEvent actionEvent) {
        System.out.println("ID TExt");
    }
    @FXML public void productPriceTF(javafx.event.ActionEvent actionEvent) {
        System.out.println("ID TExt");
    }
    @FXML public void productInventoryTF(javafx.event.ActionEvent actionEvent) {
        System.out.println("ID TExt");
    }
    @FXML public void productMaxTF(javafx.event.ActionEvent actionEvent) {
        System.out.println("ID TExt");
    }
    @FXML public void productMinTF(javafx.event.ActionEvent actionEvent) {
        System.out.println("ID TExt");
    }

    @FXML public void addPartAction (ActionEvent actionEvent) {
        System.out.println("Adding part");
    }

    @FXML public void saveButtonAction(ActionEvent actionEvent) throws IOException {

        System.out.println("Saving...");

        int id = Integer.parseInt(modifyProductID.getText());
        String name = modifyProductName.getText();
        int stock = Integer.parseInt(modifyProductInventory.getText());
        double price = Double.parseDouble(modifyProductPrice.getText());
        int max = Integer.parseInt(modifyProductMax.getText());
        int min = Integer.parseInt(modifyProductMin.getText());


        Inventory.updateProduct(id, (new Product(id, name, price, stock, min, max)));



        this.returnToMain(actionEvent);
    }
    @FXML public void returnToMain(ActionEvent actionEvent) throws IOException {
        // method to call the "changeScreens" method
        this.changeScreens(actionEvent, "/Interface/sample.fxml");
        System.out.println("Returning to Main menu");
    }

}
