/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBContext;
import utils.DBContext;

/**
 *
 * @author admin
 */
public class AlertDAO {

    private final String SEARCH_ALERT_BY_TICKER = "SELECT * FROM tblAlerts WHERE  ticker LIKE ?";
    private final String SEARCH_ALERT_BY_DIRECTION = "SELECT * FROM tblAlerts WHERE  direction LIKE ?";
    private final String SEARCH_ALERT_BY_STATUS = "SELECT * FROM tblAlerts WHERE  status LIKE ?";
    private final String CREATE_ALERT = "INSERT INTO tblAlerts(userID, ticker, threshold, direction) VALUES(?, ?, ?, ?)";
    private final String UPDATE_ALERT = "UPDATE tblAlerts SET threshold = ?, status = ? WHERE alertID = ? AND userID = ? AND status = 'inactive'";
    private final String DELETE_ALERT = "DELETE FROM tblAlerts WHERE alertID = ? AND userID = ? AND status = 'inactive'";

    public ArrayList<Alert> searchByTicker(String search) throws SQLException {
        ArrayList<Alert> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_ALERT_BY_TICKER);
                stm.setString(1, search );
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Alert(rs.getInt("alertID"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getFloat("threshold"), rs.getString("direction"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }
    
    public ArrayList<Alert> searchByDirection(String search) throws SQLException {
        ArrayList<Alert> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_ALERT_BY_DIRECTION);
                stm.setString(1,  search );
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Alert(rs.getInt("alertID"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getFloat("threshold"), rs.getString("direction"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }
    public ArrayList<Alert> searchByStatus(String search) throws SQLException {
        ArrayList<Alert> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_ALERT_BY_STATUS);
                stm.setString(1,  search);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Alert(rs.getInt("alertID"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getFloat("threshold"), rs.getString("direction"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }
    
    public boolean create(Alert alert) throws Exception {
        boolean isCreated = false;
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(CREATE_ALERT);
                stm.setString(1, alert.getUserID());
                stm.setString(2, alert.getTicker());
                stm.setFloat(3, alert.getThreshold());
                stm.setString(4, alert.getDirection());
                isCreated = stm.executeUpdate() > 0;
            }
        }
        return isCreated;
    }

    public boolean update(int alertID, String userID, float threshold, String status) throws Exception {

        boolean isUpdated = false;
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(UPDATE_ALERT);
                stm.setFloat(1, threshold);
                stm.setString(2, status);
                stm.setInt(3, alertID);
                stm.setString(4, userID);
                isUpdated = stm.executeUpdate() > 0;
            }
        }
        return isUpdated;
    }

    public boolean delete(int alertID, String userID) throws Exception {
        boolean isDeleted = false;
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(DELETE_ALERT);
                stm.setInt(1, alertID);
                stm.setString(2, userID);
                isDeleted = stm.executeUpdate() > 0;
            }
        }
        return isDeleted;
    }
}
