/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import constant.Message;
import constant.Role;
import constant.Url;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import services.AlertService;
import utils.AuthUtils;
import dto.Alert;
import jakarta.websocket.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import services.UserService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AlertController", urlPatterns = {"/alert"})
public class AlertController extends HttpServlet {

    private AlertService alertService = new AlertService();

    private final String CREATE = "create";
    private final String GET_ALL_ALERTS = "getAllUsers";
    private final String GET_ALERTS_BY_TICKER = "getAlertsByTicker";
    private final String GET_ALERTS_BY_DIRECTION = "getAlertsByDirection";
    private final String GET_ALERTS_BY_STATUS = "getAlertsByStatus";
    private final String UPDATE = "update";
    private final String DELETE = "delete";
    private final String GET_ALERT_BY_ID = "getAlertById";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN, Role.STAFF)) {
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = GET_ALL_ALERTS;
        }

        List<Alert> alerts = null;
        String url = Url.ALERT_LIST_PAGE;
        switch (action) {
            case CREATE: {
                url = Url.CREATE_ALERT_PAGE;
                break;
            }
            case UPDATE: {
                url = Url.UPDATE_ALERT_PAGE;
                break;
            }
            case GET_ALERT_BY_ID: {
                alerts = new ArrayList<>();
                alerts.add(getAlertrByID(request, response));
                url = Url.UPDATE_ALERT_PAGE;
                break;
            }
            case GET_ALL_ALERTS: {
                alerts = getAllAlerts(request, response);
                break;
            }

            case GET_ALERTS_BY_TICKER: {
                alerts = getAlertsByTicker(request, response);
                break;
            }
            case GET_ALERTS_BY_DIRECTION: {
                alerts = getAlertsByDirection(request, response);
                break;
            }
            case GET_ALERTS_BY_STATUS: {
                alerts = getAlertsByStatus(request, response);
                break;
            }
        }

        if (action.equals(GET_ALERT_BY_ID)) {
            request.setAttribute("user", alerts.get(0));
        } else {
            request.setAttribute("users", alerts);
        }

        request.getRequestDispatcher(url).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN, Role.STAFF)) {
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        String url = Url.ALERT_LIST_PAGE;
        try {
            switch (action) {
                case CREATE: {
                    createAlert(request, response);
                    url = Url.CREATE_ALERT_PAGE;
                    break;
                }
                case UPDATE: {
                    updateAlert(request, response);
                    url = Url.UPDATE_ALERT_PAGE;
                    break;
                }
                case DELETE: {
                    deleteAlert(request, response);
                    break;
                }
            }

            request.setAttribute("users", alertService.getAllAlerts());
            request.getRequestDispatcher(url).forward(request, response);
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
            request.getRequestDispatcher(Url.ERROR_PAGE).forward(request, response);
        }
    }

    private Alert getAlertrByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        try {
            String userID = request.getParameter("userID");
            Alert alert = alertService.getAlertByID(Integer.parseInt(userID));
            if (alert == null) {
                alert = new Alert();
                request.setAttribute("MSG", Message.ALERT_NOT_FOUND);
            } else {
                request.setAttribute("MSG", Message.ALERT_FOUND);
            }
            return alert;
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private List<Alert> getAllAlerts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            return alertService.getAllAlerts();
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private List<Alert> getAlertsByTicker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keySearch = request.getParameter("keySearch");
            return alertService.searchAlertByTicker(keySearch);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private List<Alert> getAlertsByDirection(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keySearch = request.getParameter("keySearch");
            return alertService.searchAlertByDirection(keySearch);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private List<Alert> getAlertsByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keySearch = request.getParameter("keySearch");
            return alertService.searchAlertByStatus(keySearch);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }


    private void createAlert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        String userID = request.getParameter("userID");
        String ticker = request.getParameter("ticker");
        float threshold =  Float.parseFloat(request.getParameter("threshold"));
        String direction = request.getParameter("direction");
        String message = alertService.createAlert(userID, ticker, threshold, direction);
        
        request.setAttribute("MSG", message);
    }   
    
    private void updateAlert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        int alertID = Integer.parseInt(request.getParameter("alertID"));
        String userID = request.getParameter("userID");
        float threshold =  Float.parseFloat(request.getParameter("threshold"));
        String status = request.getParameter("status");
        String message = alertService.updateAlert(alertID, userID, threshold, status);
        
        request.setAttribute("MSG", message);
    }   
    
    private void deleteAlert(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        int alertID = Integer.parseInt(request.getParameter("alertID"));
        String userID = request.getParameter("userID");
        String message = alertService.deleteAlert(alertID, userID);
        
        request.setAttribute("MSG", message);
    }
}
