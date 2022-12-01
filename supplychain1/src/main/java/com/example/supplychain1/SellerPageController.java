package com.example.supplychain1;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name;
    @FXML
    TextField price;
    @FXML
    TextField email;

    @FXML
    public void Add(MouseEvent event) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("select max(productID) from product");
        int productId=0;
        if(res.next())
            productId=res.getInt("max(productID)") + 1;
        String query=String.format("Insert Into product values(%s,'%s',%s,'%s')",productId,name.getText(),price.getText(),email.getText());
        int responce=HelloApplication.connection.executeUpdate(query);
        Dialog<String> dialog=new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(responce>0){
            dialog.setContentText("A New Product is Added");
        }else{
            dialog.setContentText("The product is Not Added");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
