package com.foodnyang.restaurant;

import com.foodnyang.FlowController;
import com.foodnyang.database.admin.customer.CustomerListModel;
import com.foodnyang.database.admin.driver.Driver;
import com.foodnyang.database.admin.driver.DriverListModel;
import com.foodnyang.database.restaurant.ListMenuModel;
import com.foodnyang.database.restaurant.Makanan;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListMenuController implements Initializable {
    @FXML
    private TableView<Makanan> menuTable;
    @FXML
    private TableColumn<Makanan, Integer> idMenu;
    @FXML
    private TableColumn<Makanan, Integer> idResto;
    @FXML
    private TableColumn<Makanan, String> gambarMenu;
    @FXML
    private TableColumn<Makanan, String> namaMenu;
    @FXML
    private TableColumn<Makanan, String> deskripsiMenu;
    @FXML
    private TableColumn<Makanan, Integer> harga;
    @FXML
    private TableColumn<Makanan, Integer> stok;
    @FXML
    private TextField txtIdResto;
    @FXML
    private TextField txtGambarMenu;
    @FXML
    private TextField txtNamaMenu;
    @FXML
    private TextField txtDeskripsiMenu;
    @FXML
    private TextField txtHarga;
    @FXML
    private TextField txtStok;
    @FXML
    private TextField txtSearchMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idMenu.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("idMenu"));
        idResto.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("idResto"));
        gambarMenu.setCellValueFactory(new PropertyValueFactory<Makanan, String>("gambarMenu"));
        namaMenu.setCellValueFactory(new PropertyValueFactory<Makanan, String>("namaMenu"));
        deskripsiMenu.setCellValueFactory(new PropertyValueFactory<Makanan, String>("deskripsiMenu"));
        harga.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("harga"));
        stok.setCellValueFactory(new PropertyValueFactory<Makanan, Integer>("stok"));

        //Add data to columns (ide dari James_D yang dibagikan pada laman StackOverFlow,
        //berikut adalah link untuk menuju laman tersebut: https://stackoverflow.com/questions/31318713/simple-way-to-add-data-to-columns)

        //Driver Lists Query
        ListMenuModel model = new ListMenuModel();
        ObservableList menuList = model.getListMenu(txtSearchMenu.getText(), txtSearchMenu.getText());
        menuTable.setItems(menuList);

        setCellValueFromTableToTextField();
    }
    public void setCellValueFromTableToTextField() {
        menuTable.setOnMouseClicked(mouseEvent -> {
            Makanan makanan = menuTable.getItems().get(menuTable.getSelectionModel().getSelectedIndex());
            txtIdResto.setText(String.valueOf(makanan.getIdResto()));
            txtGambarMenu.setText(makanan.getGambarMenu());
            txtNamaMenu.setText(makanan.getNamaMenu());
            txtDeskripsiMenu.setText(makanan.getDeskripsiMenu());
            txtHarga.setText(String.valueOf(makanan.getHarga()));
            txtStok.setText(String.valueOf(makanan.getStok()));
        });
    }
    public void onAddBtnClicked() {
        ListMenuModel model = new ListMenuModel();
        model.addMenu(Integer.parseInt(txtIdResto.getText()), txtNamaMenu.getText(), Integer.parseInt(txtHarga.getText()), txtDeskripsiMenu.getText(), txtGambarMenu.getText(), Integer.parseInt(txtStok.getText()));
        menuTable.refresh();
        menuTable.setItems(menuTable.getItems());
    }
    public void onSearchBtnClicked() {
        System.out.println(txtSearchMenu.getText());
        //Employee Lists Query
        ListMenuModel model = new ListMenuModel();
        ObservableList menuList = model.getListMenu(txtSearchMenu.getText(), txtSearchMenu.getText());
        menuTable.refresh();
        menuTable.setItems(menuList);
    }
    public void onEditBtnClicked() {
        ListMenuModel model = new ListMenuModel();
        model.editSelectedMenu(Integer.parseInt(txtIdResto.getText()), txtNamaMenu.getText(), Integer.parseInt(txtHarga.getText()), txtDeskripsiMenu.getText(), txtGambarMenu.getText(), Integer.parseInt(txtStok.getText()));
        menuTable.refresh();
        menuTable.setItems(menuTable.getItems());
    }
    public void onDeleteBtnClicked() {
        try {
            Makanan selectedMakanan = menuTable.getSelectionModel().getSelectedItem();
            menuTable.getItems().remove(selectedMakanan);
            ListMenuModel.deleteSelectedMenu(selectedMakanan.getIdMenu());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("RestoMenu");
    }
}
