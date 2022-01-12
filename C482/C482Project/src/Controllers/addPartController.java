package Controllers;

import theClasses.InHouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import theClasses.Inventory;
import theClasses.Outsourced;


//import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addPartController implements Initializable {
    @FXML public Label changeMeLabel;
    @FXML public RadioButton addPartInsourceRadio;
    @FXML public RadioButton addPartOutsourceRadio;


    @FXML public Button addPartCancelButton;
    @FXML public Button modifyPartCancelButton;

    @FXML public TextField addPartID;
    @FXML public TextField addPartNameTextField;
    @FXML public TextField addPartInventory;
    @FXML public TextField addPartPrice;
    @FXML public TextField addPartSpecialField;
    @FXML public TextField addPartMax;
    @FXML public TextField addPartMin;

    private static int theIntPart;
    Stage stage;
    Parent scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Disables users from typing in own ID
        TextField partIDTF = addPartID;
        partIDTF.setDisable(true);

    }

    public void addPartInsourceRadioAction(ActionEvent actionEvent) {
        // changes the text after pressing "Insource" radio button

        //assigns the text to say "Machine ID"
        changeMeLabel.setText("Machine ID");
        System.out.println("Clicked");
    }

    public void addPartOutsourceRadioAction(ActionEvent actionEvent) {
        // changes the text after pressing "Outsource" radio button

        //assigns the text to say "Company Name"
        changeMeLabel.setText("Company Name");
        System.out.println("Clicked");
    }

//    public boolean search(int id) {
//        for(Part inhouse: Inventory.getAllParts()) {
//            if (inhouse.getId() == id) {
//                return true;
//            }
//        return false;
//        }
//    }

    @FXML void changeScreens(ActionEvent actionEvent, String string ) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public static int incrementIDPart(int id) {
        // this function will increment ID by 1
        // if app is launched every time, this function will recieve "id" from Main.java
        return theIntPart += id;

    }

    public void savePartAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked Saved");

        // assigns all textfield entries to these variables
        // then creates a new row table after clicking save button
        // after clicking save button, screen will return to the Main menu

        //int id = Integer.parseInt(addPartID.getText());

        try {
            String name = addPartNameTextField.getText();
            int stock = Integer.parseInt(addPartInventory.getText());
            double price = Double.parseDouble(addPartPrice.getText());
            int max = Integer.parseInt(addPartMax.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int machineID = 0;
            String companyName = "";

            if (addPartInsourceRadio.isSelected()) {
                machineID = Integer.parseInt(addPartSpecialField.getText());

                // adds all of the stored variables to the table
                // this.incrementID(1) will call the function w/ argument 1 to increment ID
                Inventory.addPart(new InHouse(this.incrementIDPart(1), name, price, stock, min, max, machineID));
            }
            else if (addPartOutsourceRadio.isSelected()) {
                companyName = addPartSpecialField.getText();

                // adds all of the stored variables to the table
                // this.incrementID(1) will call the function w/ argument 1 to increment ID
                Inventory.addPart(new Outsourced(this.incrementIDPart(1), name, price, stock, min, max, companyName));

            }

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
