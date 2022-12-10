package com.foodnyang.customer.orderlist;

import com.foodnyang.FlowController;
import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.database.customer.orderlist.CustomerOrderDetailModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CustomerOrderDetailController implements Initializable {
    @FXML
    private Text orderID, driver, restoran, totalPrice, address, status;

    @FXML
    private TableView tableItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerOrderElement customerObj = (CustomerOrderElement) FlowController.getStageByKey("customerOrderDetail").getUserData();
        orderID.setText(Integer.toString(customerObj.getId()));
        driver.setText(customerObj.getDriver());
        restoran.setText(customerObj.getRestoran());
        address.setText(customerObj.getAddress());
        status.setText(customerObj.getStatus());

        ( (TableColumn) tableItems.getColumns().get(0)).setCellValueFactory(new PropertyValueFactory<>("name"));
        ( (TableColumn) tableItems.getColumns().get(1)).setCellValueFactory(new PropertyValueFactory<>("qty"));
        ( (TableColumn) tableItems.getColumns().get(2)).setCellValueFactory(new PropertyValueFactory<>("subtotalPrice"));

        try {
            tableItems.setItems(CustomerOrderDetailModel.getOrderDetailById(customerObj.getId()));
            totalPrice.setText(String.format("Rp %,d", customerObj.getHarga()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onPrintClicked() {
        System.out.println("Print, now jasper here!");

        try{
//            File template = new File("resources/reports/jasper/StrukUser.jrxml");
            JasperReport jr = JasperCompileManager.compileReport("src\\main\\resources\\reports\\jasper\\StrukUser.jrxml");
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("orderId", "1002");

             Connection con = FoodNyangDatabaseConnection.connection();
             JasperPrint jp = JasperFillManager.fillReport(jr, parameters, con);
             JasperViewer.viewReport(jp, false);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
