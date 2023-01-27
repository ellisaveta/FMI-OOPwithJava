package com.hw09problem3and4;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import Zad1.Product;
import Zad1.Type;
import Zad2.ListOfProducts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProductController implements javafx.fxml.Initializable {
    ListOfProducts listOfProducts;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddToList;

    @FXML
    private Button btnAveragePrice;

    @FXML
    private Button btnGroup;

    @FXML
    private Button btnShowProducts;

    @FXML
    private Button btnSortProducts;

    @FXML
    private TextArea txtAr;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    void btnAddToListOnAction(ActionEvent event) {
        if(txtDescription != null && txtPrice != null && txtCategory != null) {
            Type type = Type.valueOf(txtCategory.getText());
            double price = Double.parseDouble(txtPrice.getText());
            Product product = new Product(txtDescription.getText(), type, price);
            listOfProducts.Add(product);
        }
    }

    @FXML
    void btnAveragePriceOnAction(ActionEvent event) {
        if(listOfProducts != null && listOfProducts.getProducts() != null
                && listOfProducts.getProducts().size() != 0) {
            txtAr.setText(String.format("Average price is %.2f", listOfProducts.averagePrice()));
        }
        else {
            txtAr.setText("Average price is 0.00");
        }
    }

    @FXML
    void btnGroupOnAction(ActionEvent event) {
        Map<Type, List<Product>> map = listOfProducts.getProducts().stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        txtAr.setText(map.toString());
    }

    @FXML
    void btnShowProductsOnAction(ActionEvent event) {
        var list = listOfProducts.getProducts().stream()
                        .filter((p) -> p.getPrice() > Double.parseDouble(txtPrice.getText()))
                .toList();
        txtAr.setText(list.toString());
    }

    @FXML
    void btnSortProductsOnAction(ActionEvent event) {
        listOfProducts.getProducts().sort((p1, p2) -> (int) (p2.getPrice() - p1.getPrice()));
    }

    @FXML
    void initialize() {
        assert btnAddToList != null : "fx:id=\"btnAddToList\" was not injected: check your FXML file 'product-view.fxml'.";
        assert btnAveragePrice != null : "fx:id=\"btnAveragePrice\" was not injected: check your FXML file 'product-view.fxml'.";
        assert btnGroup != null : "fx:id=\"btnGroup\" was not injected: check your FXML file 'product-view.fxml'.";
        assert btnShowProducts != null : "fx:id=\"btnShowProducts\" was not injected: check your FXML file 'product-view.fxml'.";
        assert btnSortProducts != null : "fx:id=\"btnSortProducts\" was not injected: check your FXML file 'product-view.fxml'.";
        assert txtAr != null : "fx:id=\"txtAr\" was not injected: check your FXML file 'product-view.fxml'.";
        assert txtCategory != null : "fx:id=\"txtCategory\" was not injected: check your FXML file 'product-view.fxml'.";
        assert txtDescription != null : "fx:id=\"txtDescription\" was not injected: check your FXML file 'product-view.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'product-view.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfProducts = new ListOfProducts();
    }
}
