package com.foodnyang.database.admin.employee;

import com.foodnyang.database.FoodNyangDatabaseConnection;
import com.foodnyang.enums.admin.ListStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeListModel {
    public ObservableList getEmployeeList(String nama, String email, String no_telp, String tempat_lahir, String jenis_kelamin, String pekerjaan, String departemen) {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        try {
            CallableStatement employeeQuery = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.getEmployee(?, ?, ?, ?, ?, ?, ?)}"
            );
            employeeQuery.setString(1, String.format("%%%s%%",nama));
            employeeQuery.setString(2, String.format("%%%s%%",email));
            employeeQuery.setString(3, String.format("%%%s%%",no_telp));
            employeeQuery.setString(4, String.format("%%%s%%",tempat_lahir));
            employeeQuery.setString(5, String.format("%%%s%%",jenis_kelamin));
            employeeQuery.setString(6, String.format("%%%s%%",pekerjaan));
            employeeQuery.setString(7, String.format("%%%s%%",departemen));
            ResultSet employees = employeeQuery.executeQuery();
            while (employees.next()) {
                employeeList.add(new Employee(
                        employees.getInt("id_pegawai"),
                        employees.getString("nama"),
                        employees.getString("email"),
                        employees.getString("nomor_telpon"),
                        employees.getString("tempat_lahir"),
                        employees.getString("tanggal_lahir"),
                        employees.getString("jenis_kelamin"),
                        employees.getString("tanggal_perekrutan"),
                        employees.getString("pekerjaan"),
                        employees.getString("departemen"),
                        employees.getInt("gaji")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
    public static void deleteSelectedEmployee(int pegawai_id) throws SQLException {
        PreparedStatement delete = null;
        try {
             delete = FoodNyangDatabaseConnection.connection().prepareStatement(
                    "DELETE FROM pegawai WHERE id_pegawai = ?"
        );
        delete.setInt(1,pegawai_id);
        delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (delete != null) {
                delete.close();
            }
        }
    }
    public static void addEmployee(int id, String pekerjaan, int manajer, String departemen, int gaji) {
        CallableStatement add_emp = null;
        ListStatus status = null;
        try {
            add_emp = FoodNyangDatabaseConnection.connection().prepareCall(
              "{call dbo.daftarPegawai(?, ?, ?, ?, ?)}"
            );
                add_emp.setInt(1, id);
                add_emp.setString(2, pekerjaan);
                add_emp.setInt(3, manajer);
                add_emp.setString(4, departemen);
                add_emp.setInt(5, gaji);
                add_emp.executeUpdate();
                System.out.println("Data Berhasil Ditambahkan!");
                status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (add_emp != null) {
                try {
                    add_emp.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void editSelectedEmployee(int id, String pekerjaan, String departemen, int gaji) {
        CallableStatement edit_emp = null;
        ListStatus status = null;
        try {
            edit_emp = FoodNyangDatabaseConnection.connection().prepareCall(
                    "{call dbo.editEmployee(?, ?, ?, ?)}"
            );
            edit_emp.setInt(1, id);
            edit_emp.setString(2, pekerjaan);
            edit_emp.setString(3, departemen);
            edit_emp.setInt(4, gaji);
            edit_emp.executeUpdate();
            System.out.println("Data Berhasil Diedit!");
            status = ListStatus.SUCCEED;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (edit_emp != null) {
                try {
                    edit_emp.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
