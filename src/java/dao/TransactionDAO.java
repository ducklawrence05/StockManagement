/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBContext;

/**
 *
 * @author admin
 */
public class TransactionDAO {

    private final String GET_ALL_TRANSACTION = "SELECT * FROM tblTransactions";
    private final String SEARCH_TRANSACTION_BY_USERID = "SELECT * FROM tblTransactions WHERE userID LIKE ?";
    private final String SEARCH_TRANSACTION_BY_TICKER = "SELECT * FROM tblTransactions WHERE ticker LIKE ?";
    private final String SEARCH_TRANSACTION_BY_TYPE = "SELECT * FROM tblTransactions WHERE type LIKE ?";
    private final String SEARCH_TRANSACTION_BY_STATUS = "SELECT * FROM tblTransactions WHERE status LIKE ?";
    private final String CREATE_TRANSACTION = "INSERT INTO tblTransactions"
            + "(userID, ticker, type, quantity, price, status)"
            + " VALUES(?, ?, ?, ?,?,?)";
    private final String UPDATE_TRANSACTION = "UPDATE tblTransactions SET status = ? WHERE id = ?";
    private final String DELATE_TRANSACTION = "DELETE FROM tblTransactions WHERE userID=?";

    public List<Transaction> getAllTransaction() throws SQLException {
        List<Transaction> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(GET_ALL_TRANSACTION);  ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                resultList.add(new Transaction(rs.getInt("id"),
                        rs.getString("userID"),
                        rs.getString("ticker"),
                        rs.getString("type"),
                        rs.getInt("quantity"),
                        rs.getFloat("price"),
                        rs.getString("status")));
            }
        }
        return resultList;
    }

    public ArrayList<Transaction> searchByUserID(String search) throws SQLException {
        ArrayList<Transaction> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_TRANSACTION_BY_USERID);
                stm.setString(1, search);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Transaction(rs.getInt("id"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getString("type"), rs.getInt("quantity"),
                            rs.getFloat("price"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }

    public ArrayList<Transaction> searchByTicker(String search) throws SQLException {
        ArrayList<Transaction> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_TRANSACTION_BY_TICKER);
                stm.setString(1, search);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Transaction(rs.getInt("id"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getString("type"), rs.getInt("quantity"),
                            rs.getFloat("price"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }

    public ArrayList<Transaction> searchByType(String search) throws SQLException {
        ArrayList<Transaction> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_TRANSACTION_BY_TYPE);
                stm.setString(1, search);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Transaction(rs.getInt("id"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getString("type"), rs.getInt("quantity"),
                            rs.getFloat("price"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }

    public ArrayList<Transaction> searchByStatus(String search) throws SQLException {
        ArrayList<Transaction> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(SEARCH_TRANSACTION_BY_STATUS);
                stm.setString(1, search);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    resultList.add(new Transaction(rs.getInt("id"), rs.getString("userID"), rs.getString("ticker"),
                            rs.getString("type"), rs.getInt("quantity"),
                            rs.getFloat("price"), rs.getString("status")));
                }
            }
        }
        return resultList;
    }

    public boolean create(Transaction transaction) throws Exception {
        boolean isCreated = false;
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(CREATE_TRANSACTION);
                stm.setString(1, transaction.getUserID());
                stm.setString(2, transaction.getTicker());
                stm.setString(3, transaction.getType());
                stm.setInt(4, transaction.getQuantity());
                stm.setFloat(5, transaction.getPrice());
                stm.setString(6, transaction.getStatus());
                isCreated = stm.executeUpdate() > 0;
            }
        }
        return isCreated;

    }

    public boolean update(int transactionId, String status) throws Exception {
        boolean isUpdated = false;
        try ( Connection conn = DBContext.getConnection()) {

            if (conn != null) {
                PreparedStatement stm = conn.prepareStatement(UPDATE_TRANSACTION);
                stm.setString(1, status);
                stm.setInt(2, transactionId);
                isUpdated = stm.executeUpdate() > 0;
            }
        }
        return isUpdated;
    }

    public boolean delete(String userID) throws SQLException {
        boolean isDelete = false;
        try ( Connection conn = DBContext.getConnection()) {
            if (conn != null) {

                PreparedStatement stm = conn.prepareStatement(DELATE_TRANSACTION);
                stm.setString(1, userID);
                isDelete = stm.executeUpdate() > 0;
            }
            return isDelete;
        }
    }

}
