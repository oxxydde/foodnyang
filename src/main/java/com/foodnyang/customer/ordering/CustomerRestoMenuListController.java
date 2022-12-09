package com.foodnyang.customer.ordering;

import com.foodnyang.FlowController;
import com.foodnyang.MainApp;
import com.foodnyang.database.customer.ordering.CustomerRestoMenuListModel;
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
import java.util.HashMap;
import java.util.ResourceBundle;

public class CustomerRestoMenuListController implements Initializable {

    @FXML
    private Text restoNameTxt, restoCategory, restoRating;
    @FXML
    private VBox menuList;
    @FXML
    private TextField searchTxtField;

    private HashMap<Integer, FoodMenuElement> cart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cart = new HashMap<>();

        restoNameTxt.setText(
                ((RestaurantElement) FlowController.getSceneByKey("RestaurantList").getUserData()).getNama()
        );
        restoCategory.setText(
                ((RestaurantElement) FlowController.getSceneByKey("RestaurantList").getUserData()).getTipe()
        );
        restoRating.setText(
                ((RestaurantElement) FlowController.getSceneByKey("RestaurantList").getUserData()).getRating() + " / 5"
        );

        try {
            ObservableList<FoodMenuElement> menus = CustomerRestoMenuListModel.getMenus(
                    ((RestaurantElement) FlowController.getSceneByKey("RestaurantList").getUserData()).getId(),
                    ""
            );
            for (FoodMenuElement menu : menus) {
                try {
                    Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/food_menu_element.fxml"));
                    AnchorPane elem = (AnchorPane) root.lookup("#menuElem");
                    // simpan data masing masing restoran dengan setUserData per list element
                    elem.setUserData(menu);

                    ((Text) elem.lookup("#foodName")).setText( ((FoodMenuElement) elem.getUserData()).getName() );
                    ((Text) elem.lookup("#price")).setText( String.format("Rp%d", ((FoodMenuElement) elem.getUserData()).getHarga()) );
                    ((Text) elem.lookup("#description")).setText( ((FoodMenuElement) elem.getUserData()).getDescription() );

                    Button incBtn = (Button) elem.lookup("#incBtn"),
                            decBtn = (Button) elem.lookup("#decBtn");
                    incBtn.setOnMouseClicked(event -> {
                        ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())+1);
                        ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
                        if ((((FoodMenuElement) elem.getUserData()).getQty()) > 0) {
                            cart.put(
                                    ((FoodMenuElement) elem.getUserData()).getId(),
                                    (FoodMenuElement) elem.getUserData()
                            );
                        } else {
                            cart.remove(((FoodMenuElement) elem.getUserData()).getId());
                        }
                    });
                    decBtn.setOnMouseClicked(event -> {
                        ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())-1);
                        ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
                        if ((((FoodMenuElement) elem.getUserData()).getQty()) > 0) {
                            cart.put(
                                    ((FoodMenuElement) elem.getUserData()).getId(),
                                    (FoodMenuElement) elem.getUserData()
                            );
                        } else {
                            cart.remove(((FoodMenuElement) elem.getUserData()).getId());
                        }
                    });
                    menuList.getChildren().add(elem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onSearchTxtTyped() {
        menuList.getChildren().clear();
        ObservableList<FoodMenuElement> menus = null;
        try {
            menus = CustomerRestoMenuListModel.getMenus(
                    ((RestaurantElement) FlowController.getSceneByKey("RestaurantList").getUserData()).getId(),
                    searchTxtField.getText()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (FoodMenuElement menu : menus) {
            try {
                Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/food_menu_element.fxml"));
                AnchorPane elem = (AnchorPane) root.lookup("#menuElem");
                // simpan data masing masing restoran dengan setUserData per list element
                elem.setUserData(menu);
                ((FoodMenuElement) elem.getUserData()).setQty(
                        (cart.get(menu.getId()) != null) ? cart.get(menu.getId()).getQty() : 0
                );

                ((Text) elem.lookup("#foodName")).setText( ((FoodMenuElement) elem.getUserData()).getName() );
                ((Text) elem.lookup("#price")).setText( String.format("Rp%d", ((FoodMenuElement) elem.getUserData()).getHarga()) );
                ((Text) elem.lookup("#description")).setText( ((FoodMenuElement) elem.getUserData()).getDescription() );
                ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));

                Button incBtn = (Button) elem.lookup("#incBtn"),
                        decBtn = (Button) elem.lookup("#decBtn");
                incBtn.setOnMouseClicked(event -> {
                    ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())+1);
                    ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
                    if ((((FoodMenuElement) elem.getUserData()).getQty()) > 0) {
                        cart.put(
                                ((FoodMenuElement) elem.getUserData()).getId(),
                                (FoodMenuElement) elem.getUserData()
                        );
                    } else {
                        cart.remove(((FoodMenuElement) elem.getUserData()).getId());
                    }
                });
                decBtn.setOnMouseClicked(event -> {
                    ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())-1);
                    ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
                    if ((((FoodMenuElement) elem.getUserData()).getQty()) > 0) {
                        cart.put(
                                ((FoodMenuElement) elem.getUserData()).getId(),
                                (FoodMenuElement) elem.getUserData()
                        );
                    } else {
                        cart.remove(((FoodMenuElement) elem.getUserData()).getId());
                    }
                });
                menuList.getChildren().add(elem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public void onTambahMenuClicked() throws IOException {
//        Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/food_menu_element.fxml"));
//        AnchorPane elem = (AnchorPane) root.lookup("#menuElem");
//        // simpan data masing masing restoran dengan setUserData per list element
//        elem.setUserData(new FoodMenuElement("Mie Goreng", "Mie mantap", 15000, 6));
//
//        ((Text) elem.lookup("#foodName")).setText( ((FoodMenuElement) elem.getUserData()).getName() );
//        ((Text) elem.lookup("#price")).setText( String.format("Rp%d", ((FoodMenuElement) elem.getUserData()).getHarga()) );
//        ((Text) elem.lookup("#description")).setText( ((FoodMenuElement) elem.getUserData()).getDescription() );
//
//        Button incBtn = (Button) elem.lookup("#incBtn"),
//               decBtn = (Button) elem.lookup("#decBtn");
//        incBtn.setOnMouseClicked(event -> {
//            ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())+1);
//            ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
//        });
//        decBtn.setOnMouseClicked(event -> {
//            ((FoodMenuElement) elem.getUserData()).setQty((((FoodMenuElement) elem.getUserData()).getQty())-1);
//            ((Text) elem.lookup("#qtyItem")).setText(Integer.toString(((FoodMenuElement) elem.getUserData()).getQty()));
//        });
//        menuList.getChildren().add(elem);
//    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("RestaurantList");
        FlowController.getScene().setUserData(null);
        FlowController.removeScene("MenuList");
    }

    public void onCheckoutOrderClicked() throws IOException {
        FlowController.createScene("CartInfo", new Scene(new VBox()));
        FlowController.getSceneByKey("CartInfo").setUserData(cart);
        Parent root = FXMLLoader.load(MainApp.class.getResource("customer/ordering/customer_checkout.fxml"));
        FlowController.createScene("CheckoutOrder", new Scene(root));
        FlowController.setStage("MainStage");
        FlowController.setScene("CheckoutOrder");
    }
}
