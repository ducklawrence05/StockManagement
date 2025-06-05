/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import constant.Message;
import dao.AlertDAO;
import dto.Alert;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class AlertService {

    private AlertDAO dao = new AlertDAO();

    public List<Alert> getAllAlerts() throws SQLException {
        return dao.getAllAlert();
    }
    public Alert getAlertByID(int id) throws SQLException{
        return dao.getAlertByID(id);
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

    public String createAlert(String userID, String ticker, float threshold, String direction) throws SQLException {
        if( dao.create(userID, ticker, threshold, direction)){
            return Message.CREATE_ALERT_SUCCESSFULLY;
        }
        return Message.CREATE_ALERT_FAILED;
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
    
    public String updateAlert(int alertID, String userID, float threshold, String status) throws SQLException{
        if(isCreator(alertID, userID)){
            if(isInactive(alertID)){
                if(dao.update(alertID, threshold, status)){
                    return Message.UPDATE_ALERT_SUCCESSFULLY;
                }
                return Message.UPDATE_ALERT_FAILED;
            }
            return Message.ALERT_STATUS_IS_ACTIVE;
        }
        return Message.IS_NOT_CREATOR;
    }
    
    public String deleteAlert(int alertID, String userID) throws SQLException {
        if(isCreator(alertID, userID)){
            if(isInactive(alertID)){
                if(dao.delete(alertID)){
                    return Message.DELETE_ALERT_SUCCESSFULLY;
                }
                return Message.DELETE_ALERT_FAILED;
            }
            return Message.ALERT_STATUS_IS_ACTIVE;
        }
        return Message.IS_NOT_CREATOR;
    }
    
    public boolean isInactive(int alertID) throws SQLException{
        Alert tmp = dao.getAlertByID(alertID);
        if(tmp.getDirection().equalsIgnoreCase("inactive")){
            return true;
        }
        return false;
    }
}
