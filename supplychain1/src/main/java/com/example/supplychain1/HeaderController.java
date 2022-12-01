package com.example.supplychain1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button loginButton;

    @FXML
    Label email;

    @FXML
    TextField searchText;

    @FXML
    Button logoutButton;

    @FXML
    public void initialize(){
        if(!HelloApplication.emailId.equals("")){
            loginButton.setOpacity(0);
            email.setText(HelloApplication.emailId);
        }
    }

    @FXML
    public void login(MouseEvent event) throws IOException {
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    public void Search(MouseEvent event) throws IOException, SQLException {
        Header header=new Header();
        ProductPage products=new ProductPage();
        AnchorPane productphane=new AnchorPane();
        productphane.getChildren().add(products.searchproductbyName(searchText.getText()));
        productphane.setLayoutX(150);
        productphane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productphane);
    }
    @FXML
    public void logOut(MouseEvent event) throws IOException,SQLException{
        if(logoutButton.getOpacity()==0){
            logoutButton.setOpacity(1);
            logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailId="";
                    logoutButton.setOpacity(0);
                    try {
                        Header header=new Header();
                        HelloApplication.root.getChildren().add(header.root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }else{
            logoutButton.setOpacity(0);
        }
    }
}
