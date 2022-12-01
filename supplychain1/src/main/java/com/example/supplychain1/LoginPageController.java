package com.example.supplychain1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;

    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
        String query=String.format("select * from user where emailId = '%s' and pass='%s'",email.getText(),password.getText());
        ResultSet res=HelloApplication.connection.executeQuery(query);
        if(res.next()){
            String userType=res.getString("userType");
            if(userType.equals("Buyer")){
                System.out.println("Logged in as Buyer");
                HelloApplication.emailId=res.getString("emailId");
                ProductPage products=new ProductPage();

                Header header=new Header();

                ListView<HBox> productList=products.showProducts();

                AnchorPane productPhane=new AnchorPane();
                productPhane.setLayoutX(50);
                productPhane.setLayoutY(100);
                productPhane.getChildren().add(productList);

                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPhane);

            }else{
                AnchorPane sellerpage= FXMLLoader.load(getClass().getResource("SellerPage.fxml"));
                HelloApplication.root.getChildren().add(sellerpage);
            }
        }else{
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Login is Failed !! please try Again");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}
