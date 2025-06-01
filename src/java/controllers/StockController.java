/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import constant.Message;
import constant.Role;
import constant.Url;
import dto.Stock;
import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import services.StockService;
import utils.AuthUtils;

/**
 *
 * @author Huy
 */
@WebServlet(name="StockController", urlPatterns={"/stock"})
public class StockController extends HttpServlet {
    private StockService stockService = new StockService();
    private final String CREATE = "create";
    private final String GET_ALL = "getAll";
    private final String ORDER_BY_PRICE = "orderByPrice";
    private final String SEARCH_BY_PRICE = "searchbyPrice";
    private final String SEARCH_BY_NAME = "searchByName";
    private final String SEARCH_BY_TICKER = "searchByTicker";
    private final String SEARCH_BY_SECTOR = "searchBySector";
    private final String UPDATE = "update";
    private final String DELETE = "delete";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN, Role.STAFF)) {
            return;
        }
        String action = request.getParameter("action");
        if (action == null) action = GET_ALL;
        
        List<Stock> stocks = null;
        switch(action) {
            case GET_ALL: {
                
                break;
            }
            case ORDER_BY_PRICE: {
                
                break;
            }
            case SEARCH_BY_PRICE: {
                
                break;
            }
            case SEARCH_BY_NAME: {
                
                break;
            }
            case SEARCH_BY_TICKER: {
                
                break;
            }
            case SEARCH_BY_SECTOR: {
                
                break;
            }
        }
        
        request.setAttribute("stocks", stocks);
        request.getRequestDispatcher(Url.STOCK_LIST_PAGE).forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (!AuthUtils.checkAuthorization(request, response, Role.ADMIN)) {
            return;
        }
        
    }
    private List<Stock> getAllStocks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             return stockService.getAllStock();
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
        }
        return null;
    }
//    
//    private List<Stock> findAllOrderByPrice(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            String userID = request.getParameter("userID");
//            return userService.getUsersByID(userID);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            request.setAttribute("MSG", Message.SYSTEM_ERROR);
//        }
//        return null;
//    }
}
