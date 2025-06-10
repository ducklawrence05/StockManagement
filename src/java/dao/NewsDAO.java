/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;
import utils.DBContext;

/**
 *
 * @author admin
 */
public class NewsDAO {

    // GET
    private final String GET_ALL_NEWS = "SELECT * FROM News";
    private final String GET_NEWS_BY_ID = "SELECT * FROM News WHERE id = ?";
    private final String GET_NEWS_BY_TITLE = "SELECT * FROM News WHERE title LIKE ?";
    // CREATE
    private final String CREATE_NEWs = "INSERT INTO News (title, content, ticker) VALUES(?,?,?)";
    // UPDATE
    private final String UPDATE_NEWS = "UPDATE News SET title = ?, content = ? WHERE id = ?";
    //DELETE
    private final String DELETE_NEWS = "DELETE FROM News WHERE id = ?";

    // lấy 1 object
    public News getNewsById(int id) throws SQLException{
        try(Connection conn = DBContext.getConnection();
                PreparedStatement stm = conn.prepareStatement(GET_NEWS_BY_ID)){
            stm.setInt(1, id);
            try(ResultSet rs = stm.executeQuery()){
                while(rs.next()){
                    return map(rs);
                }
            }
        }
        return null;
    }
    
    // lấy 1 mảng
    public List<News> getNewsByTitle(String title) throws SQLException{
        List<News> resultList = new ArrayList<>();
        try(Connection conn = DBContext.getConnection();
                PreparedStatement stm = conn.prepareStatement(GET_NEWS_BY_TITLE)){
            stm.setString(1, "%" + title + "%");
            try(ResultSet rs = stm.executeQuery()){
                while(rs.next()){
                    resultList.add(map(rs));
                }
            }
        }
        return resultList;
    }
    
    // lấy 1 mảng
    public List<News> getAllNews() throws SQLException {
        List<News> resultList = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  
                PreparedStatement stm = conn.prepareStatement(GET_ALL_NEWS);) {
            try ( ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultList.add(map(rs));
                }
            }
        }
        return resultList;
    }

    public int create(String title, String content, String ticker) throws SQLException {
        try ( Connection conn = DBContext.getConnection();  
                PreparedStatement stm = conn.prepareStatement(CREATE_NEWs);) {
            stm.setString(1, title);
            stm.setString(2, content);
            stm.setString(3, ticker.toUpperCase());
            return stm.executeUpdate();
        }
    }

    public int update(int newsId, String title, String content) throws SQLException {
        try ( Connection conn = DBContext.getConnection();  
                PreparedStatement stm = conn.prepareStatement(UPDATE_NEWS);) {
            stm.setString(1, title);
            stm.setString(2, content);
            stm.setInt(3, newsId);
            return stm.executeUpdate();
        }
    }

    public int delete(int newsId) throws SQLException {
        try ( Connection conn = DBContext.getConnection();  
                PreparedStatement stm = conn.prepareStatement(DELETE_NEWS);) {
            stm.setInt(1, newsId);
            return stm.executeUpdate();
        }
    }
    
    public News map(ResultSet rs) throws SQLException{
        return new News(rs.getInt("id"), 
                         rs.getString("title"), 
                         rs.getString("content"),
                         rs.getString("ticker"));
    }
}
