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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRestoMenuListController implements Initializable {

    @FXML
    private VBox menuList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onTambahMenuClicked() throws IOException {
        Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/food_menu_element.fxml"));
        AnchorPane elem = (AnchorPane) root.lookup("#menuElem");
        // simpan data masing masing restoran dengan setUserData per list element
        elem.setUserData(new FoodMenuElement("Mie Goreng", "Mie mantap", 15000, 6));

        ((Text) elem.lookup("#foodName")).setText( ((FoodMenuElement) elem.getUserData()).getName() );
        ((Text) elem.lookup("#price")).setText( String.format("Rp%d", ((FoodMenuElement) elem.getUserData()).getHarga()) );
        ((Text) elem.lookup("#description")).setText( ((FoodMenuElement) elem.getUserData()).getDescription() );

        Button incBtn = (Button) elem.lookup("#incBtn"),
               decBtn = (Button) elem.lookup("#decBtn");
        incBtn.setOnMouseClicked(event -> {
            ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())+1);
            ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
        });
        decBtn.setOnMouseClicked(event -> {
            ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())-1);
            ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
        });
        menuList.getChildren().add(elem);
    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("RestaurantList");
        FlowController.removeScene("MenuList");
    }

    public void onCheckoutOrderClicked() throws IOException {
        Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_checkout.fxml"));
        FlowController.createScene("CheckoutOrder", new Scene(root));
        FlowController.setStage("MainStage");
        FlowController.setScene("CheckoutOrder");
    }
}
