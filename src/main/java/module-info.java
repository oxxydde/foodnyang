module com.foodnyang {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.foodnyang to javafx.fxml;
    exports com.foodnyang;
    exports com.foodnyang.login;
    opens com.foodnyang.login to javafx.fxml;
    exports com.foodnyang.admin;
    opens com.foodnyang.admin to javafx.fxml;
    exports com.foodnyang.signup;
    opens com.foodnyang.signup to javafx.fxml;
    exports com.foodnyang.driver;
    opens com.foodnyang.driver to javafx.fxml;
    exports com.foodnyang.driver.order;
    opens com.foodnyang.driver.order to javafx.fxml;
    exports com.foodnyang.customer;
    opens com.foodnyang.customer to javafx.fxml;
    exports com.foodnyang.customer.ordering;
    opens com.foodnyang.customer.ordering to javafx.fxml;
    exports com.foodnyang.customer.orderlist;
    opens com.foodnyang.customer.orderlist to javafx.fxml;

}