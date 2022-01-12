package Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import theClasses.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addProductController implements Initializable {
    @FXML public TextField productID;
    @FXML public TextField productName;
    @FXML public TextField productInventory;
    @FXML public TextField productPrice;
    @FXML public TextField productSearchBar;
    @FXML public TextField productMax;
    @FXML public TextField productMin;

    @FXML public TableView<Part> partTable;
    @FXML public TableColumn<Part, Integer> partColumnID;
    @FXML public TableColumn<Part, String> partColumnName;
    @FXML public TableColumn<Part, Integer> partColumnInventory;
    @FXML public TableColumn<Part, Double> partColumnPrice;

    Stage stage;
    Parent scene;
    public static int theIntProduct;

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
        TextField partIDTF = productID;
        partIDTF.setDisable(true);

    }

    @FXML void productSearchBarOnAction(ActionEvent actionEvent) throws IOException {
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
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public static int incrementIDProduct(int id) {
        // this function will increment ID by 1
        // if app is launched every time, this function will recieve "id" from Main.java
        return theIntProduct += id;

    }

    @FXML void returnToMain(ActionEvent actionEvent) throws IOException {
        this.changeScreens(actionEvent, "/Interface/sample.fxml");
    }

    @FXML void productIDTF (ActionEvent actionEvent) throws IOException {
        System.out.println("ID Line");
    }
    @FXML void productNameTF (ActionEvent actionEvent) throws IOException {
        System.out.println("Name Line");
    }
    @FXML void productInventoryTF (ActionEvent actionEvent) throws IOException {
        System.out.println("Inventory Line");
    }
    @FXML void productPriceTF (ActionEvent actionEvent) throws IOException {
        System.out.println("Price Line");
    }
    @FXML void productMaxTF (ActionEvent actionEvent) throws IOException {
        System.out.println("Max Line");
    }
    @FXML void productMinTF (ActionEvent actionEvent) throws IOException {
        System.out.println("Min Line");
    }
    @FXML void SaveButtonAction (ActionEvent actionEvent) throws IOException {
        System.out.println("Save button");

        // Need to make "addProduct" method to 'Inventory'
        try {
            String name = productName.getText();
            int stock = Integer.parseInt(productInventory.getText());
            double price = Double.parseDouble(productPrice.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());

            Inventory.addProduct(new Product(incrementIDProduct(1), name, price, stock, max, min));

            this.onActionReturnToMain(actionEvent);

        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error dialog");
            alert.setContentText("Please enter a valid value for " +
                    "each text field");
            alert.showAndWait();
        }
    }
    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        // changes from this screen to the Main menu screen
        System.out.println("Returning to Main Menu");

        this.changeScreens(actionEvent, "/Interface/sample.fxml");
    }

}
