package com.foodnyang.customer.ordering;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRestaurantListController implements Initializable {
    @FXML
    private VBox restoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onTambahRestoranClicked() throws IOException {
        Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/restaurant_element.fxml"));
        AnchorPane elem = (AnchorPane) root.lookup("#restoElem");
        // simpan data masing masing restoran dengan setUserData per list element
//        elem.setUserData();
        Button openMenuBtn = (Button) elem.lookup("#openMenuBtn");
        openMenuBtn.setOnMouseClicked(event -> {
            try {
                FlowController.createScene("MenuList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_restaurant_menu_list.fxml"))));
                FlowController.setStage("MainStage");
                FlowController.setScene("MenuList");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
        restoList.getChildren().add(elem);
    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerMenu");
        FlowController.removeScene("RestaurantList");
    }
}
