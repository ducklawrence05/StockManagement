/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import dao.TransactionDAO;
import dao.UserDAO;
import dto.Transaction;
import java.sql.SQLException;
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
        Transaction transaction = new Transaction(id, userID, ticker, type, quantity, price, status);
        return transactionDAO.create(transaction) == true;
    }
}
