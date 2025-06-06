/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import constant.Message;
import constant.Role;
import constant.Url;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import dto.User;
import services.UserService;
import utils.AuthUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name="AuthController", urlPatterns={"/auth"})
public class AuthController extends HttpServlet {
    private UserService userService = new UserService();
    private final String LOGIN = "login";
    private final String LOGOUT = "logout";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case LOGIN: {
                login(request, response);
                break;
            }
            case LOGOUT: {
                logout(request, response);
                break;
            }
        }
    }
    
    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        
        try {
            User user = userService.login(userID, password);
            if(user == null){
                request.setAttribute("MSG", Message.INVALID_USER_ID_OR_PASSWORD);
                request.getRequestDispatcher(Url.LOGIN_PAGE).forward(request, response);
                return;
            } 
            
            //cookie
            AuthUtils.handleRememberMe(rememberMe != null, userID, response);
            
            //session
            AuthUtils.clearUserSession(request);
            AuthUtils.setUserSession(request, user);
            
            String url = user.getRole() == Role.ADMIN ? Url.ADMIN_PAGE : Url.WELCOME_PAGE;
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex){
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
            request.getRequestDispatcher(Url.LOGIN_PAGE).forward(request, response);
        }
    }
    
    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        AuthUtils.clearUserSession(request);
        response.sendRedirect(request.getContextPath() + Url.LOGIN_PAGE);
    }
}
