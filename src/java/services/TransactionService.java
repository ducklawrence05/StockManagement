/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import dao.TransactionDAO;
import dao.UserDAO;
import dto.Transaction;
import dto.User;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author ngogi
 */
public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();
    private UserDAO userDAO = new UserDAO();
    
    
    public boolean  createTransaction(int id, String userID, String ticker,
            String type, int quantity, float price, String status)throws SQLException, Exception{
        if(userDAO.checkUserExists(userID)){
            return false;
        }
        return transactionDAO.createTransaction(id, userID, ticker, type, quantity, price, status) == true;
    }
    
    public boolean updateTransaction(String userID, String sticker, String type,
            int quantity, float  price, String status) throws SQLException, Exception{
        
        return transactionDAO.updateTransaction(userID, sticker, type, quantity, price, status);
        
    }
    
    public boolean deleteTransaction(String userID) throws SQLException{
        return transactionDAO.deleteTransaction(userID) == true;
    }
    
    public List<Transaction> getTransactionByID(String userID) throws SQLException{
        List resultList = null;
        if(!userID.isEmpty()){
            resultList = transactionDAO.searchByUserID(userID);
        }
        return resultList;
    }
    
    public List<Transaction> getTransactionByType(String type) throws SQLException{
        List resultList = null;
        if(!type.isEmpty()){
            resultList = transactionDAO.searchByUserID(type);
        }
        return resultList;
    }
    
    public List<Transaction> getTransactionByTicker(String ticker) throws SQLException{
        List resultList = null;
        if(!ticker.isEmpty()){
            resultList = transactionDAO.searchByUserID(ticker);
        }
        return resultList;
    }
    
     public List<Transaction> getTransactionByStatus(String status) throws SQLException{
        List resultList = null;
        if(!status.isEmpty()){
            resultList = transactionDAO.searchByUserID(status);
        }
        return resultList;
    }
    
    
}
