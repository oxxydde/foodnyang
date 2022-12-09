package com.foodnyang.customer.ordering;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.customer.ordering.CustomerRestaurantListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerRestaurantListController implements Initializable {
    @FXML
    private VBox restoList;
    @FXML
    private TextField searchTxtField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<RestaurantElement> restos = null;
        try {
            restos = CustomerRestaurantListModel.getRestaurants("", "", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (RestaurantElement resto : restos) {
            try {
                Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/restaurant_element.fxml"));
                AnchorPane elem = (AnchorPane) root.lookup("#restoElem");
                // simpan data masing masing restoran dengan setUserData per list element
                // elem.setUserData();

                // input all text infos
                ((Text) elem.lookup("#restoName")).setText(resto.getNama());
                ((Text) elem.lookup("#restoType")).setText(resto.getTipe());
                ((Text) elem.lookup("#restoRating")).setText(String.format("Rating %.1f / 5", resto.getRating()));

                Button openMenuBtn = (Button) elem.lookup("#openMenuBtn");
                openMenuBtn.setOnMouseClicked(event -> {
                    try {
                        FlowController.getSceneByKey("RestaurantList").setUserData(resto);
                        FlowController.createScene("MenuList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_restaurant_menu_list.fxml"))));
                        FlowController.setStage("MainStage");
                        FlowController.setScene("MenuList");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                restoList.getChildren().add(elem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void onSearchTxtTyped() throws SQLException {
        // Query order lists
        int orderIdInput = -1;
        try {
            orderIdInput = Integer.parseInt(searchTxtField.getText());
        } catch (NumberFormatException e) {
            orderIdInput = 0;
        }

        ObservableList<RestaurantElement> restos = null;
        try {
            restos = CustomerRestaurantListModel.getRestaurants(
                    searchTxtField.getText(),
                    searchTxtField.getText(),
                    "Aktif"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        restoList.getChildren().clear();
        for (RestaurantElement resto : restos) {
            try {
                Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/restaurant_element.fxml"));
                AnchorPane elem = (AnchorPane) root.lookup("#restoElem");
                // simpan data masing masing restoran dengan setUserData per list element
                // elem.setUserData();

                // input all text infos
                ((Text) elem.lookup("#restoName")).setText(resto.getNama());
                ((Text) elem.lookup("#restoType")).setText(resto.getTipe());
                ((Text) elem.lookup("#restoRating")).setText(String.format("Rating %.1f / 5", resto.getRating()));

                Button openMenuBtn = (Button) elem.lookup("#openMenuBtn");
                openMenuBtn.setOnMouseClicked(event -> {
                    try {
                        FlowController.getSceneByKey("RestaurantList").setUserData(resto);
                        FlowController.createScene("MenuList", new Scene(FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_restaurant_menu_list.fxml"))));
                        FlowController.setStage("MainStage");
                        FlowController.setScene("MenuList");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                restoList.getChildren().add(elem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerMenu");
        FlowController.removeScene("RestaurantList");
    }
}
