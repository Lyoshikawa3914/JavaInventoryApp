package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import theClasses.InHouse;
import theClasses.Inventory;
import theClasses.Outsourced;
import theClasses.Part;


//import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class modifyPartController implements Initializable {
    @FXML public RadioButton modifyInsourceRadio;
    @FXML public RadioButton modifyOutsourceRadio;

    Stage stage;
    Parent scene;

    @FXML public Label changeMeLabel;

    @FXML public Button modifyPartSaveButton;
    @FXML public Button modifyPartCancelButton;

    @FXML public TextField modifyPartIDTextField;
    @FXML public TextField modifyPartNameTextField;
    @FXML public TextField modifyPartInventoryTextField;
    @FXML public TextField modifyPartPriceTF;
    @FXML public TextField modifyPartSpecialTF;
    @FXML public TextField modifyPartMaxTF;
    @FXML public TextField modifyPartMinTF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextField partIDTF = modifyPartIDTextField;
        partIDTF.setDisable(true);
    }

    public void sendPart(Part part) {
        // this method is called from "mainController.java"

        modifyPartIDTextField.setText(String.valueOf(part.getId()));
        modifyPartNameTextField.setText(part.getName());
        modifyPartInventoryTextField.setText(String.valueOf(part.getStock()));
        modifyPartPriceTF.setText(String.valueOf(part.getPrice()));

        // if else statements determine if 'part' is a subclass
        if(part instanceof InHouse) {
            modifyPartSpecialTF.setText(String.valueOf(((InHouse) part).getMachineID()));
            modifyInsourceRadio.setSelected(true);
        }
        else if(part instanceof Outsourced) {
            modifyPartSpecialTF.setText(((Outsourced) part).getCompanyName());
            modifyOutsourceRadio.setSelected(true);
        }
        modifyPartMaxTF.setText(String.valueOf(part.getMax()));
        modifyPartMinTF.setText(String.valueOf(part.getMin()));
    }

    @FXML void changeScreens(ActionEvent actionEvent, String string ) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(string));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void modifyInsourceRadioAction (ActionEvent actionEvent){
        System.out.println("Clicked InHouse Radio");
        changeMeLabel.setText("Machine ID");
    }
    public void modifyOutsourceRadioAction (ActionEvent actionEvent){
        System.out.println("Clicked OutSource RAdio");
        changeMeLabel.setText("Company Name");

    }

    public void modifyPartIDTextFieldOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
    }

    public void modifyPartNameOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
        modifyPartNameTextField.setText("Hello");
    }

    public void modifyPartInventoryTFOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
    }
    public void modifyPartPriceTFOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
    }
    public void modifyPartSpecialTFOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
    }
    public void modifyPartMaxTFOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
    }
    public void modifyPartMinTFOnAction (ActionEvent actionEvent){
        System.out.println("Clicked");
    }


    public void modifyPartSaveButtonOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked Saved");

        // assigns all textfield entries to these variables
        // then creates a new row table after clicking save button
        // after clicking save button, screen will return to the Main menu

        int id = Integer.parseInt(modifyPartIDTextField.getText());
        String name = modifyPartNameTextField.getText();
        int stock = Integer.parseInt(modifyPartInventoryTextField.getText());
        double price = Double.parseDouble(modifyPartPriceTF.getText());
        int max = Integer.parseInt(modifyPartMaxTF.getText());
        int min = Integer.parseInt(modifyPartMinTF.getText());
        int machineID = 0;
        String companyName = "";

        // if the insource radio button is selected, current object will get updated
        if (modifyInsourceRadio.isSelected()) {
            machineID = Integer.parseInt(modifyPartSpecialTF.getText());
            Inventory.updatePart(id, (new InHouse(id, name, price, stock, min, max, machineID)));
        }
        // if the outsource radio button is selected, current object will get updated
        else if (modifyOutsourceRadio.isSelected()) {
            companyName = modifyPartSpecialTF.getText();

            // adds all of the stored variables to the table
            // this.incrementID(1) will call the function w/ argument 1 to increment ID
            Inventory.updatePart(id, (new Outsourced(id, name, price, stock, min, max, companyName)));

        }

        this.onActionReturnToMain(actionEvent);
    }

    public void onActionReturnToMain(ActionEvent actionEvent) throws IOException {
        // changes from this screen to the Main menu screen
        System.out.println("Returning to Main Menu");

        this.changeScreens(actionEvent, "/Interface/sample.fxml");
    }
}
