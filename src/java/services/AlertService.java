/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.AlertDAO;
import java.util.ArrayList;
import dto.Alert;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AlertService {

    private AlertDAO dao = new AlertDAO();

    public List<Alert> getAllAlert() throws SQLException {
        return dao.getAllAlert();
    }

    public List<Alert> searchAlertByTicker(String ticker) throws SQLException {
        return dao.searchByTicker(ticker);
    }

    public List<Alert> searchAlertByDirection(String direction) throws SQLException {
        return dao.searchByDirection(direction);
    }

    public List<Alert> searchAlertByStatus(String status) throws SQLException {
        return dao.searchByStatus(status);
    }

    public boolean createAlert(String userID, String ticker, float threshold, String direction) throws SQLException {
        return dao.create(userID, ticker, threshold, direction);
    }

    public boolean isCreator(int alertID, String userID) throws SQLException {
        Alert tmp = dao.getAlertByID(alertID);
        if (tmp != null) {
            if(tmp.getUserID().equalsIgnoreCase(userID)){
                return true;
            }
        }
        return false;
    }
    
    public boolean updateAlert(int alertID, String userID, float threshold, String status) throws SQLException{
        if(isCreator(alertID, userID)){
            return dao.update(alertID, threshold, status);
        }
        return false;
    }
    
    public boolean deleteAlert(int alertID, String userID) throws SQLException {
        if(isCreator(alertID, userID)){
            return dao.delete(alertID);
        }
        return false;
    }
}
