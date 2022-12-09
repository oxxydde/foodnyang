package com.foodnyang.customer.profile;

import com.foodnyang.FlowController;
import com.foodnyang.customer.ordering.AddressElement;
import com.foodnyang.database.customer.ordering.CustomerCheckoutModel;
import com.foodnyang.database.customer.profile.CustomerEditProfileModel;
import com.foodnyang.enums.data.DataUpdate;
import com.foodnyang.login.AccountInfo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerEditProfileController implements Initializable {
    // Tab "Info Akun"
    @FXML
    private TextField nameTxtField, noTelpTextField, emailTxtField, addressTxtField, genderTxtField;

    // Tab "Alamat Anda"
    @FXML
    private TableView addressTable;
    @FXML
    private TextField namaLocTxtField, zipcodeTxtField, latTxtField, longTxtField;
    // Tab "Password"
    @FXML
    private PasswordField confirmNewPwdTxtField, newPwdTxtField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTxtField.setText( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getNama() );
        noTelpTextField.setText( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getNomorTelp() );
        emailTxtField.setText( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getEmail() );
        genderTxtField.setText(((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getJenisKelamin());

        ((TableColumn) addressTable.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ((TableColumn) addressTable.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("address"));
        ((TableColumn) addressTable.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        ((TableColumn) addressTable.getColumns().get(3)).setCellValueFactory(new PropertyValueFactory<>("latitude"));
        ((TableColumn) addressTable.getColumns().get(4)).setCellValueFactory(new PropertyValueFactory<>("longitude"));

        addressTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            namaLocTxtField.setText(((AddressElement) newValue).getName());
            addressTxtField.setText(((AddressElement) newValue).getAddress());
            zipcodeTxtField.setText(((AddressElement) newValue).getZipcode());
            latTxtField.setText(Double.toString(((AddressElement) newValue).getLatitude()));
            longTxtField.setText(Double.toString(((AddressElement) newValue).getLongitude()));
        });
        ObservableList addresses = null;
        try {
            addresses = CustomerCheckoutModel.listAlamat( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addressTable.setItems(addresses);
    }

    public void onBackBtnClicked() {
        FlowController.setStage("MainStage");
        FlowController.setScene("CustomerMenu");
        FlowController.removeScene("EditProfile");
    }

    public void onTambahClicked() {
        DataUpdate result = CustomerEditProfileModel.addAddress(
                ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId(),
                namaLocTxtField.getText(),
                addressTxtField.getText(),
                Integer.parseInt(zipcodeTxtField.getText()),
                Double.parseDouble(latTxtField.getText()),
                Double.parseDouble(longTxtField.getText())
        );
        switch (result) {
            case UPDATE_SUCCEED -> {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setContentText("Alamat berhasil ditambahkan");
                successAlert.show();
                ObservableList addresses = null;
                try {
                    addresses = CustomerCheckoutModel.listAlamat( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId() );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addressTable.setItems(addresses);
            }
            default -> {
                Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                failedAlert.setContentText("Alamat gagal ditambahkan");
                failedAlert.show();
            }
        }
    }
    public void onEditClicked() {
        if (genderTxtField.getText().equals("Pria") || genderTxtField.getText().equals("Wanita")) {
            Alert failedAlert = new Alert(Alert.AlertType.ERROR);
            failedAlert.setContentText("Masukkan Jenis Kelamin \"Pria\" atau \"Wanita\"");
            failedAlert.show();
            return;
        }
        DataUpdate result = CustomerEditProfileModel.editAddress(
                ((AddressElement) addressTable.getSelectionModel().selectedItemProperty().getValue()).getAddressId(),
                namaLocTxtField.getText(),
                addressTxtField.getText(),
                Integer.parseInt(zipcodeTxtField.getText()),
                Double.parseDouble(latTxtField.getText()),
                Double.parseDouble(longTxtField.getText())
        );
        switch (result) {
            case UPDATE_SUCCEED -> {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setContentText("Alamat berhasil diubah");
                successAlert.show();
                ObservableList addresses = null;
                try {
                    addresses = CustomerCheckoutModel.listAlamat( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId() );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addressTable.setItems(addresses);
            }
            default -> {
                Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                failedAlert.setContentText("Alamat gagal diubah");
                failedAlert.show();
            }
        }
    }
    public void onHapusClicked() {
        DataUpdate result = CustomerEditProfileModel.deleteAddress(
                ((AddressElement) addressTable.getSelectionModel().selectedItemProperty().getValue()).getAddressId()
        );
        switch (result) {
            case UPDATE_SUCCEED -> {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setContentText("Alamat berhasil dihapus");
                successAlert.show();
                ObservableList addresses = null;
                try {
                    addresses = CustomerCheckoutModel.listAlamat( ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId() );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addressTable.setItems(addresses);
            }
            default -> {
                Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                failedAlert.setContentText("Alamat gagal dihapus");
                failedAlert.show();
            }
        }
    }
    public void onUpdateProfileClicked() {
        DataUpdate result = CustomerEditProfileModel.updateProfile(
                ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId(),
                nameTxtField.getText(),
                genderTxtField.getText(),
                noTelpTextField.getText(),
                emailTxtField.getText()
        );
        switch (result) {
            case UPDATE_SUCCEED -> {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setContentText("Profil berhasil diubah");
                successAlert.show();
                ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).setNama(nameTxtField.getText());
                ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).setNomorTelp(noTelpTextField.getText());
                ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).setEmail(emailTxtField.getText());
                ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).setJenisKelamin(genderTxtField.getText());
                ((Text) FlowController.getSceneByKey("CustomerMenu").lookup("#greetTxt")).setText(
                        "Halo, " + ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getNama().split(" ")[0]
                );
            }
            default -> {
                Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                failedAlert.setContentText("Profil gagal diubah");
                failedAlert.show();
            }
        }

    }
        public void onPwdUpdateClicked() {
            if (newPwdTxtField.getText().equals(confirmNewPwdTxtField.getText())) {
                DataUpdate result = CustomerEditProfileModel.updatePassword(
                        ((AccountInfo) FlowController.getSceneByKey("AccountInfo").getUserData()).getId(),
                        confirmNewPwdTxtField.getText()
                );
                switch (result) {
                    case UPDATE_SUCCEED -> {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setContentText("Password berhasil diubah");
                        successAlert.show();
                    }
                    default -> {
                        Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                        failedAlert.setContentText("Password gagal diubah");
                        failedAlert.show();
                    }
                }
            } else {
                Alert failedAlert = new Alert(Alert.AlertType.ERROR);
                failedAlert.setContentText("Konfirmasi password baru tidak sama");
                failedAlert.show();
            }
        }
}
