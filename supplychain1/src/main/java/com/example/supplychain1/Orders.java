package com.example.supplychain1;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Orders {
    void placeOrder(String productID) throws SQLException{
        ResultSet res=HelloApplication.connection.executeQuery("select max(orderID) from orders");
        int orderID=0;
        if(res.next())
            orderID=res.getInt("max(orderID)") + 1;
        String query=String.format("Insert Into orders values(%s,%s,'%s')",orderID,productID,HelloApplication.emailId);
        int responce=HelloApplication.connection.executeUpdate(query);
        if(responce>0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("You Order is Successfully Placed");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
            System.out.println("Order is Placed");
        }else{
            System.out.println("Your Order is Not Placed");
        }
    }
}
