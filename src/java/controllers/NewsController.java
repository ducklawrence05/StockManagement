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
import java.util.List;
import dto.News;
import java.util.ArrayList;
import services.NewsService;
import utils.AuthUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "NewsController", urlPatterns = {"/news"})
public class NewsController extends HttpServlet {

    private NewsService newsService = new NewsService();
    
    private final String GET_ALL_NEWS = "getAllNews";
    private final String GET_NEWS_BY_TITLE = "getNewsByTitle";
    private final String CREATE = "create";
    private final String UPDATE = "update";
    private final String DELETE = "delete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // dùng khi cần check author, ko thì xoá
//        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
//            return;
//        }

        String action = request.getParameter("action");
        if (action == null) {
            action = GET_ALL_NEWS;
        }

        List<News> news = null;
        String url = Url.NEWS_LIST_PAGE;
        switch (action) {
            case CREATE: {
                url = Url.CREATE_NEWS_PAGE;
                break;
            }
            // tìm object cho trang update
            case UPDATE: {
                news = new ArrayList<>();
                news.add(getNewsByID(request, response));
                url = Url.UPDATE_NEWS_PAGE;
                break;
            }
            case GET_ALL_NEWS: {
                news = getAllNews(request, response);
                break;
            }
            // chức năng search
            case GET_NEWS_BY_TITLE: {
                news = getNewsByTitle(request, response);
                break;
            }
        }

        // nếu là tìm cho update thì trả về thằng đầu tiên, ko thì trả ra mảng bình thường
        if (action.equals(UPDATE)) {
            request.setAttribute("news", news.get(0));
        } else {
            request.setAttribute("news", news);
        }

        // lấy mảng các String là role nếu cần hiển thị
        // request.setAttribute("roleList", Role.values());
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
//            return;
//        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        String url = Url.NEWS_LIST_PAGE;
        try {
            switch (action) {
                case CREATE: {
                    createNews(request, response);
                    url = Url.CREATE_NEWS_PAGE;
                    break;
                }
                case UPDATE: {
                    updateNews(request, response);
                    url = Url.UPDATE_NEWS_PAGE;
                    // lấy lại news để hiển thị lại thông tin sau khi update 
                    News news = getNewsByID(request, response);
                    request.setAttribute("news", news);
                    break;
                }
                case DELETE: {
                    deleteNews(request, response);
                    // lấy full để sau khi delete vẫn ở trang list cũ
                    request.setAttribute("news", newsService.getAllNews());
                    break;
                }
            }

//            request.setAttribute("roleList", Role.values());
            request.getRequestDispatcher(url).forward(request, response);
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
            request.getRequestDispatcher(Url.ERROR_PAGE).forward(request, response);
        }
    }

    private News getNewsByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NumberFormatException {
        try {
            String _id = request.getParameter("newsId");
            int newsId = Integer.parseInt(_id);
            News news = newsService.getNewsById(newsId);
            if (news == null) {
                news = new News();
                request.setAttribute("MSG", Message.NEWS_NOT_FOUND);
            }
            return news;
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private List<News> getAllNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            return newsService.getAllNews();
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private List<News> getNewsByTitle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String title = request.getParameter("keySearch");
            return newsService.getNewsByTitle(title);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }

    private void createNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NumberFormatException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String ticker = request.getParameter("ticker");
        
        String message = newsService.createNews(title, content, ticker);
        
        request.setAttribute("MSG", message);
    }

    private void updateNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NumberFormatException {
        String _id = request.getParameter("newsId");
        int newsId = Integer.parseInt(_id);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        String message = newsService.updateNews(newsId, title, content);
        
        request.setAttribute("MSG", message);
    }

    private void deleteNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NumberFormatException {
        int newsId = Integer.parseInt(request.getParameter("newsId"));
        
        String message = newsService.deleteUser(newsId);

        request.setAttribute("MSG", message);
    }
}