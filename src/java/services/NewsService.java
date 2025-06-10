/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import constant.Message;
import dao.NewsDAO;
import dao.StockDAO;
import dto.News;
import dto.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NewsService {
    private StockDAO stockDAO = new StockDAO();
    private NewsDAO newsDAO = new NewsDAO();

    public List<News> getAllNews() throws SQLException {
        return newsDAO.getAllNews();
    }

    public News getNewsById(int id) throws SQLException {
        return newsDAO.getNewsById(id);
    }
    public List<News> getNewsByTitle(String title) throws SQLException {
        return newsDAO.getNewsByTitle(title);
    }

    public String createNews (String title, String content, String ticker) throws SQLException{
        // check empty
        if(isNullOrEmptyString(title) 
                || isNullOrEmptyString(content)
                || isNullOrEmptyString(ticker)){
            return Message.ALL_FIELDS_ARE_REQUIRED;
        }
        
        // gọi DAO của table khác nếu có khoá ngoại
        if(!stockDAO.isTickerExist(ticker)){
            return Message.STOCK_NOT_FOUND;
        }
        
        // kiểm tra số âm nếu có
        
        // tạo và trả về kết quả
        if(newsDAO.create(title, content, ticker) == 0){
            return Message.CREATE_NEWS_FAILED;
        }
        
        return Message.CREATE_NEWS_SUCCESSFULLY;
    }
    
    public String updateNews(int newsId, String title, String content) 
            throws SQLException{
        // lấy object ra
        News news = newsDAO.getNewsById(newsId);
        if(news == null){
            return Message.NEWS_NOT_FOUND;
        }
        
        // nếu rỗng hay null thì lấy value cũ
        if(isNullOrEmptyString(title)){
            title = news.getTitle();
        }
        
        if(isNullOrEmptyString(content)){
            content = news.getContent();
        }
        
        // update
        if(newsDAO.update(newsId, title, content) == 0){
            return Message.UPDATE_NEWS_FAILED;
        }
        
        return Message.UPDATE_NEWS_SUCCESSFULLY;
    }
    
    public String deleteUser(int newsId) throws SQLException{
        if(newsDAO.delete(newsId) == 0){
            return Message.NEWS_NOT_FOUND;
        }
        
        return Message.DELETE_NEWS_SUCCESSFULLY;
    }
    
    private boolean isNullOrEmptyString(String str){
        return str == null || str.isEmpty();
    }
}
