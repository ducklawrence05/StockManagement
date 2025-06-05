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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import dto.User;
import java.util.ArrayList;
import services.UserService;
import utils.AuthUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    private final String CREATE = "create";
    private final String GET_USER_BY_ID = "getUserByID";
    private final String GET_ALL_USERS = "getAllUsers";
    private final String GET_USERS_BY_ID = "getUsersByID";
    private final String GET_USERS_BY_NAME = "getUsersByName";
    private final String UPDATE = "update";
    private final String DELETE = "delete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
            return;
        }
        
        String action = request.getParameter("action");
        if (action == null) action = GET_ALL_USERS;
        
        List<User> users = null;
        String url = Url.USER_LIST_PAGE;
        switch(action) {
            case CREATE: {
                url = Url.CREATE_USER_PAGE;
                break;
            }
            case UPDATE: {
                url = Url.UPDATE_USER_PAGE;
                User user = getUserByID(request, response);
                request.setAttribute("user", user);
                break;
            }
            case GET_USER_BY_ID:{
                users = new ArrayList<>();
                users.add(getUserByID(request, response));
                url = Url.UPDATE_USER_PAGE;
                break;
            }
            case GET_ALL_USERS: {
                users = getAllUsers(request, response);
                break;
            }
            case GET_USERS_BY_ID: {
                users = getUsersByID(request, response);
                break;
            }
            case GET_USERS_BY_NAME: {
                users = getUsersByName(request, response);
                break;
            }
        }
        
        if (action.equals(GET_USER_BY_ID)) {
            request.setAttribute("user", users.get(0));
        } else {
            request.setAttribute("users", users);
        }
        
        request.setAttribute("roleList", Role.values());
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "";
        String url = Url.USER_LIST_PAGE;
        try {
            switch (action) {
                case CREATE: {
                    createUser(request, response);
                    url = Url.CREATE_USER_PAGE;
                    break;
                }
                case UPDATE: {
                    updateUser(request, response);
                    url = Url.UPDATE_USER_PAGE;
                    break;
                }
                case DELETE: {
                    deleteUser(request, response);
                    break;
                }
            }
            
            request.setAttribute("users", userService.getAllUsers());
            request.setAttribute("roleList", Role.values());
            request.getRequestDispatcher(url).forward(request, response);
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
            request.getRequestDispatcher(Url.ERROR_PAGE).forward(request, response);
        }
    }
    
    private User getUserByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userID = request.getParameter("userID");
            User user = userService.getUserByID(userID);
            if (user == null) {
                user = new User();
                request.setAttribute("MSG", Message.USER_NOT_FOUND);
            } else {
                request.setAttribute("MSG", Message.USER_FOUND);
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }
    
    private List<User> getAllUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             return userService.getAllUsers();
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }
    
    private List<User> getUsersByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userID = request.getParameter("keySearch");
            return userService.getUsersByID(userID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }
    
    private List<User> getUsersByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("keySearch");
            return userService.getUsersByName(name);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }
    
    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        
        String message = userService.createUser(userID, fullName,
                Role.fromValue(roleID), password, confirmPassword);

        request.setAttribute("MSG", message);
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NumberFormatException {
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        int roleID = Integer.parseInt(request.getParameter("roleID"));

        String message = userService.updateUser(userID, fullName,
                Role.fromValue(roleID), oldPassword, password, confirmPassword);

        request.setAttribute("MSG", message);
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String userID = request.getParameter("userID");
        String message = userService.deleteUser(userID);

        request.setAttribute("MSG", message);
    }
    
    
}
