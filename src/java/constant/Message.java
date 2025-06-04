/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 *
 * @author Admin
 */
public class Message {
    // System
    public static final String SYSTEM_ERROR = "System error";
    public static final String UNAUTHORIZED = "You do not have permission to access this function.";
    public static final String ACTION_NOT_FOUND = "Action not found.";
    
    // User
    public static final String PASSWORD_NOT_MATCH_CONFIRM_PASSWORD = "Password not match confirm password!";
    public static final String PASSWORD_AND_CONFIRM_PASSWORD_ARE_REQUIRED = "Password and confirm password are required";
    public static final String OLD_PASSWORD_ARE_REQUIRED = "Old password is required";
    public static final String CREATE_USER_SUCCESSFULLY = "Create user successfully";
    public static final String CREATE_USER_FAILED = "Create user failed";
    public static final String USER_ID_IS_EXISTED = "User ID is existed";
    public static final String UPDATE_USER_SUCCESSFULLY = "Update user successfully";
    public static final String UPDATE_USER_FAILED = "Update user failed";
    public static final String DELETE_USER_SUCCESSFULLY = "Delete user successfully";
    public static final String USER_FOUND = "User found";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVALID_USER_ID_OR_PASSWORD = "Invalid user ID or password.";

    // Stock
    public static final String EMTY_STOCK_LIST = "Not found any stock.";
    public static final String EMTY_STOCK_PRICE = "Not found any stock in range.";
    public static final String STOCK_NOT_FOUND = "Stock not found";
    public static final String TICKER_NOT_FOUND = "Ticker not found";
    public static final String SECTOR_NOT_FOUND = "Sector not found";
    
    // Transaction
    public static final String TRANSACTION_ID_IS_EXISTED = "Transaction ID is existed";
    public static final String CREATE_TRANSACTION_SUCCESSFULLY = "Create Transaction successfully";
    public static final String CREATE_TRANSACTION_FAILED = "Create Transaction failed";
    public static final String UPDATE_TRANSACTION_SUCCESSFULLY = "UPDATE Transaction successfully";
    public static final String UPDATE_TRANSACTION_FAILED = "UPDATE Transaction failed";
    public static final String DELETE_TRANSACTION_SUCCESSFULLY = "UPDATE Transaction successfully";
    public static final String TRANSACTION_NOT_FOUND = "TRANSACTION not found";
    
    
    // Alert
    public static final String CREATE_ALERT_SUCCESSFULLY = "Create alert successfully";
    public static final String CREATE_ALERT_FAILED = "Create alert failed";
    public static final String UPDATE_ALERT_SUCCESSFULLY = "Update alert successfully";
    public static final String UPDATE_ALERT_FAILED = "Update alert failed";
    public static final String DELETE_ALERT_SUCCESSFULLY = "Delete alert successfully";
    public static final String DELETE_ALERT_FAILED = "Delete alert failed";
    public static final String ALERT_NOT_FOUND = "Alert not found";
    public static final String ALERT_FOUND = "Alert found";
    public static final String IS_NOT_CREATOR = "You are not the creator of this alert";
    public static final String THRESHOLD_CAN_NOT_BE_NEGATIVE = "Thrshold can not be negative";
    public static final String ALERT_STATUS_IS_ACTIVE = "Alert status is active, can not update or delete";
    
}
