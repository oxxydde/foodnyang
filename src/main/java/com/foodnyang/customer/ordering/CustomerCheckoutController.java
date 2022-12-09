package com.foodnyang.customer.ordering;

import com.foodnyang.FlowController;
import com.foodnyang.database.customer.ordering.CustomerCheckoutModel;
import com.foodnyang.database.customer.ordering.CustomerCreateOrderModel;
import com.foodnyang.login.AccountInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CustomerCheckoutController implements Initializable {

    @FXML
    private Text restoNameTxt, deliveryFeeTxt, grandTotalTxt;
    @FXML
    private TableView foodTable;
    @FXML
    private ComboBox addressSelector;

    private HashMap<Integer, FoodMenuElement> cart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cart = ((HashMap<Integer, FoodMenuElement>) FlowController.getSceneByKey("CartInfo").getUserData());
        restoNameTxt.setText(((RestaurantElement) FlowController.getSceneByKey("RestaurantList").getUserData()).getNama());
        ((TableColumn) foodTable.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ((TableColumn) foodTable.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("harga"));
        ((TableColumn) foodTable.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<>("qty"));
        ((TableColumn) foodTable.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory<>("totalHarga"));

        int[] grandTotalPrice = {0};
        int deliveryFee = 9000;

        cart.forEach((integer, foodMenuElement) -> {
            grandTotalPrice[0] += foodMenuElement.getTotalHarga();
            foodTable.getItems().add(foodMenuElement);
        });

        grandTotalPrice[0] += deliveryFee;

        deliveryFeeTxt.setText(String.format("Rp %,d", deliveryFee));
        grandTotalTxt.setText(String.format("Rp %,d", grandTotalPrice[0]));

        try {
            ObservableList addresses = CustomerCheckoutModel.listAlamat(
                    ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId()
            );
            addressSelector.setItems(addresses);
            addressSelector.setConverter(new StringConverter() {
                @Override
                public String toString(Object object) {
                    if (object == null) {
                        return "";
                    }
                    else {
                        return ((AddressElement) object).getName();
                    }
                }

                @Override
                public Object fromString(String string) {
                    return null;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("MenuList");
        FlowController.removeScene("CheckoutOrder");
        FlowController.removeScene("CartInfo");
    }

    public void onCheckoutNowClicked() {
        ArrayList<FoodMenuElement> items = new ArrayList<>();
        cart.forEach((integer, foodMenuElement) -> {
            items.add(foodMenuElement);
        });
        CustomerCreateOrderModel.createOrder(items);
    }
}