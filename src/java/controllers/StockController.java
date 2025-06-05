/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import constant.Message;
import constant.Role;
import constant.Url;
import dto.Stock;
import java.io.IOException;
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
                stocks = getAllStocks(request, response);
                break;
            }
            case ORDER_BY_PRICE: {
                stocks = findAllOrderByPrice(request, response);
                break;
            }
            case SEARCH_BY_PRICE: {
                stocks = searchbyPrice(request, response);
                break;
            }
            case SEARCH_BY_NAME: {
                stocks = searchbyName(request, response);
                break;
            }
            case SEARCH_BY_TICKER: {
                stocks = searchbyTicker(request, response);
                break;
            }
            case SEARCH_BY_SECTOR: {
                stocks = searchbySector(request, response);
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
        String action = request.getParameter("action");
        if (action == null) action = "";
        String url = Url.STOCK_LIST_PAGE;
        try {
            switch (action) {
                case CREATE: {
                    createStock(request, response);
                    url = Url.ADD_STOCK_PAGE;
                    break;
                }
                case UPDATE: {
                    updateStock(request, response);
                    List<Stock> allList = stockService.getAllStock();
                    request.setAttribute("stocks", allList);
                    url = Url.STOCK_LIST_PAGE;
                    break;
                }
                case DELETE: {
                    deleteStock(request, response);
                    break;
                }
            }
            
            request.setAttribute("stocks", stockService.getAllStock());
            request.setAttribute("roleList", Role.values());
            request.getRequestDispatcher(url).forward(request, response);
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SYSTEM_ERROR);
            request.getRequestDispatcher(Url.ERROR_PAGE).forward(request, response);
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
    
    private List<Stock> findAllOrderByPrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String order = request.getParameter("order");
            return stockService.findAllOrderByPrice(order);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.EMTY_STOCK_LIST);
        }
        return null;
    }
    private List<Stock> searchbyPrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            int min = Integer.parseInt(request.getParameter("minPrice")) ;
            int max = Integer.parseInt(request.getParameter("maxPrice"));
            return stockService.searchbyPrice(min, max);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.EMTY_STOCK_PRICE);
        }
        return null;
    }
    private List<Stock> searchbyName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            String name = (String)request.getParameter("name");
            return stockService.searchByName(name);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.STOCK_NOT_FOUND);
        }
        return null;
    }
    private List<Stock> searchbyTicker(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            String ticker = (String)request.getParameter("ticker");
            return stockService.searchByTicker(ticker);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.TICKER_NOT_FOUND);
        }
        return null;
    }
    private List<Stock> searchbySector(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            String sector = (String)request.getParameter("sector");
            return stockService.searchBySector(sector);
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("MSG", Message.SECTOR_NOT_FOUND);
        }
        return null;
    }
    private void createStock(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
        boolean status;
        String ticker = (String)request.getParameter("ticker");
        String name = (String)request.getParameter("name");
        String sector = (String)request.getParameter("sector");
        float price = (float)Integer.parseInt(request.getParameter("price"));
        int statusRaw = Integer.parseInt(request.getParameter("status")) ;
        if(statusRaw == 1){
            status=true;
        }
        else{
            status=false;
        }
        Stock stock = new Stock(ticker, name, sector, price, status);
        String message = stockService.create(stock);
        request.setAttribute("MSG", message);
    }

    private void updateStock(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, SQLException, NumberFormatException {
        // 1. Lấy param từ form
        String tickerParam = request.getParameter("ticker");
        String nameParam   = request.getParameter("name");
        String sectorParam = request.getParameter("sector");
        String priceStr    = request.getParameter("price");
        String statusStr   = request.getParameter("status");
        float priceParam = 0f;
        if (priceStr != null && !priceStr.isEmpty()) {
            priceParam = Float.parseFloat(priceStr);
        }
        boolean statusParam = "1".equals(statusStr);
        Stock updatedStock = new Stock(tickerParam, nameParam, sectorParam, priceParam, statusParam);
        String message = stockService.update(updatedStock);
        request.setAttribute("MSG", message);
    }

    private void  deleteStock(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, SQLException, NumberFormatException{
        String ticker = (String)request.getParameter("ticker");
        String message = stockService.delete(ticker);
        request.setAttribute("MSG", message);
    }
}
