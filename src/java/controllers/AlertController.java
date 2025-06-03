/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import constant.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AlertService;
import utils.AuthUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="AlertController", urlPatterns={"/alert"})
public class AlertController extends HttpServlet {
   
    private AlertService alertService = new AlertService();

    private final String CREATE = "create";
    private final String GET_ALL_ALERTS = "getAllUsers";
    private final String GET_ALERTS_BY_Ticker = "getAlertByTicker";
    private final String UPDATE = "update";
    private final String DELETE = "delete";
   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN, Role.STAFF)) {
            return;
        }
        
        String action = request.getParameter("action");
        if (action == null) action = GET_ALL_ALERTS;
        
        
    } 

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
