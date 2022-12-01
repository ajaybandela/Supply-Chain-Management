package com.example.supplychain1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage {
    ListView<HBox> products;

    ListView<HBox> searchproductbyName(String search) throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("select * from product");
        products=new ListView<>();

        Label Name=new Label();
        Label Price=new Label();
        Label Id=new Label();

        Name.setMinWidth(50);
        Id.setMinWidth(80);
        Price.setMinWidth(60);

        HBox Details=new HBox();

        Name.setText(" Name ");
        Price.setText(" Price ");
        Id.setText(" ProductID");

        Details.getChildren().addAll(Id,Name,Price);
        productList.add(Details);




        while(res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label ProductName = new Label();
                Label ProductPrice = new Label();
                Label ProductID = new Label();

                ProductName.setMinWidth(50);
                ProductID.setMinWidth(50);
                ProductPrice.setMinWidth(50);
                Button buy = new Button();
                HBox productDetails = new HBox();
                buy.setText("Buy");

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login First Before Place Order");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {

                            try {
                                Orders place = new Orders();
                                place.placeOrder(ProductID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("You clicked on Buy Button");
                        }

                    }
                });


                ProductName.setText(res.getString("productName"));
                ProductPrice.setText(res.getString("price"));
                ProductID.setText("" + res.getInt("productID"));

                productDetails.getChildren().addAll(ProductID, ProductName, ProductPrice, buy);
                productList.add(productDetails);
            }
        }
        if(productList.size()==1){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No Product Available For Your Search!!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }





    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery("select * from product");
        products=new ListView<>();

        Label Name=new Label();
        Label Price=new Label();
        Label Id=new Label();

        Name.setMinWidth(50);
        Id.setMinWidth(50);
        Price.setMinWidth(50);

        HBox Details=new HBox();

        Name.setText(" Name ");
        Price.setText(" Price ");
        Id.setText(" ProductID");

       Details.getChildren().addAll(Id,Name,Price);
        productList.add(Details);




        while(res.next()){
            Label ProductName=new Label();
            Label ProductPrice=new Label();
            Label ProductID=new Label();

            ProductName.setMinWidth(50);
            ProductID.setMinWidth(50);
            ProductPrice.setMinWidth(50);
            Button buy=new Button();
            HBox productDetails=new HBox();
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (HelloApplication.emailId.equals("")){
                        Dialog<String> dialog=new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login First Before Place Order");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();
                    }
                    else{

                        try {
                            Orders place=new Orders();
                            place.placeOrder(ProductID.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("You clicked on Buy Button");
                    }

                }
            });


            ProductName.setText(res.getString("productName"));
            ProductPrice.setText(res.getString("price"));
            ProductID.setText(""+res.getInt("productID"));

            productDetails.getChildren().addAll(ProductID,ProductName,ProductPrice,buy);
            productList.add(productDetails);
        }
        products.setItems(productList);
        return products;
    }
}
