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
    private final String GET_ALL = "getAll";
    private final String GET_BY_ID = "getByID";
    private final String GET_BY_NAME = "getByName";
    private final String UPDATE = "update";
    private final String DELETE = "delete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
            return;
        }
        
        String action = request.getParameter("action");
        if (action == null) action = GET_ALL;
        
        List<User> users = null;
        switch(action) {
            case GET_ALL: {
                users = getAllUsers(request, response);
                break;
            }
            case GET_BY_ID: {
                users = getUsersByID(request, response);
                break;
            }
            case GET_BY_NAME: {
                users = getUsersByName(request, response);
                break;
            }
        }
        
        request.setAttribute("users", users);
        request.setAttribute("roleList", Role.values());
        request.getRequestDispatcher(Url.USER_LIST_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "";
        
        try {
            switch (action) {
                case CREATE: {
                    createUser(request, response);
                    break;
                }
                case UPDATE: {
                    updateUser(request, response);
                    break;
                }
                case DELETE: {
                    deleteUser(request, response);
                    break;
                }
            }
            
            request.setAttribute("users", userService.getAllUsers());
            request.setAttribute("roleList", Role.values());
            request.getRequestDispatcher(Url.USER_LIST_PAGE).forward(request, response);
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
            request.getRequestDispatcher(Url.ERROR_PAGE).forward(request, response);
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        int roleID = Integer.parseInt(request.getParameter("roleID"));
        
        boolean isSuccess = userService.createUser(userID, fullName,
                Role.fromValue(roleID), password, confirmPassword);

        if (isSuccess) {
            request.setAttribute("MSG", Message.CREATE_USER_SUCCESSFULLY);
        } else {
            request.setAttribute("MSG", Message.USER_ID_IS_EXISTED);
        }
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NumberFormatException {
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        int roleID = Integer.parseInt(request.getParameter("roleID"));

        boolean isSuccess = userService.updateUser(userID, fullName,
                Role.fromValue(roleID), password, confirmPassword);

        if (isSuccess) {
            request.setAttribute("MSG", Message.UPDATE_USER_SUCCESSFULLY);
        } else {
            request.setAttribute("MSG", Message.USER_NOT_FOUND);
        }
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String userID = request.getParameter("userID");
        boolean isSuccess = userService.deleteUser(userID);

        if (isSuccess) {
            request.setAttribute("MSG", Message.DELETE_USER_SUCCESSFULLY);
        } else {
            request.setAttribute("MSG", Message.USER_NOT_FOUND);
        }
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
            String userID = request.getParameter("userID");
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
            String name = request.getParameter("name");
            return userService.getUsersByName(name);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }
}
