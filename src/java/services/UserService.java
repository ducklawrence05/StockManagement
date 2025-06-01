/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import constant.Role;
import dao.UserDAO;
import dto.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserService {
    private UserDAO userDAO = new UserDAO();
    
    public User login(String userID, String password)
            throws SQLException{
        return userDAO.login(userID, password);
    }
    
    public List<User> getAllUsers() throws SQLException{
        return userDAO.getAllUsers();
    }
    
    public List<User> getUsersByID(String userID) throws SQLException {
        return userDAO.getUsersByID(userID);
    }
    
    public List<User> getUsersByName(String name) throws SQLException {
        return userDAO.getUsersByID(name);
    }
    
    public boolean createUser(String userID, String fullName, Role role,
            String password, String confirmPassword) throws SQLException{
        if(userDAO.checkUserExists(userID)
                || !checkConfirmPassword(password, confirmPassword)){
            return false;
        }
        return userDAO.insertUser(userID, fullName, role, password) == 1;
    }
    
    public boolean updateUser(String userID, String fullName, Role role,
            String password, String confirmPassword) throws SQLException{
        if(!userDAO.checkUserExists(userID)
                || !checkConfirmPassword(password, confirmPassword)){
            return false;
        }
        if(password.isEmpty()){
            password = userDAO.getUserByID(userID).getPassword();
        }
        return userDAO.updateUser(userID, fullName, role, password) == 1;
    }
    
    public boolean deleteUser(String userID) throws SQLException{
        return userDAO.deleteUser(userID) == 1;
    }
    
    private boolean checkConfirmPassword(String pwd, String confirmPwd) {
        return pwd.equals(confirmPwd);
    }
}
