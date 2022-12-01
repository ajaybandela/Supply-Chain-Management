package com.example.supplychain1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;

    public static String emailId;
    @Override
    public void start(Stage stage) throws Exception {
         connection=new DatabaseConnection();
        root=new Group();
        emailId="";
        Header header=new Header();

        ProductPage products=new ProductPage();
        ListView<HBox> productList=products.showProducts();

        AnchorPane productPhane=new AnchorPane();
        productPhane.setLayoutX(50);
        productPhane.setLayoutY(100);
        productPhane.getChildren().add(productList);

        root.getChildren().addAll(header.root,productPhane);
        stage.setScene(new Scene(root,500,500));
        stage.setTitle("Supply Chain");
        stage.show();

        stage.setOnCloseRequest(e ->{
            try {
                connection.con.close();
                System.out.println("Connnection is Closed");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}