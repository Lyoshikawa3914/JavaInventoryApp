package theClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Inventory{
    //private ArrayList<Part> partsTable;
    //private ArrayList<Product> allProducts;
    @FXML private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    @FXML private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public Inventory() {

    }

    public static void addPart(Part part) {
        allParts.add(part);
    }


    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);

    }
    public static Part lookupPart(int partID) {
        //ObservableList<Part> allParts = Inventory.getAllParts();
        for(int i = 0; i < allParts.size(); i++) {
            Part ID = allParts.get(i);

            if (ID.getId() == partID) {
                return ID;
            }
        }
        return null;
    }
//    public static Product lookupProduct(int productID) {
//
//    }
    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for(Part partName: allParts) {
            if (partName.getName().contains(name)) {
                namedParts.add(partName);
            }
        }
        return namedParts;
    }
    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> namedProduct = FXCollections.observableArrayList();

        for(Product productName: allProducts) {
            if (productName.getName().contains(name)) {
                namedProduct.add(productName);
            }
        }
        return namedProduct;
    }
    public static boolean updatePart(int id, Part selectedPart) {
        int index = -1;
        for (Part part: Inventory.getAllParts()) {
            index ++;
            if(part.getId() == id){
                Inventory.getAllParts().set(index, selectedPart);
                return true;
            }
        }
        return false;
    }

    public static boolean updateProduct(int id, Product newProduct) {
        int index = -1;
        for (Product product: Inventory.getAllProducts()) {
            index ++;
            if(product.getId() == id){
                Inventory.getAllProducts().set(index, newProduct);
                return true;
            }
        }
        return false;
    }

    public static boolean deletePart(Part selectedPart) {
        //return Inventory.getAllParts().remove(selectedPart);
        return false;
    }

    public static boolean deleteProduct(Product product) {
        return false;
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
