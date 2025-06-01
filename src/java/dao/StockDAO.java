package dao;

import constant.Role;
import dto.Stock;
import dto.User;
import utils.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;

public class StockDAO {
    private final String GET_STOCK_BY_TINKER = "SELECT * FROM tblStocks WHERE tinker LIKE ?";
    private final String GET_ALL_STOCK = "SELECT * FROM tblStocks";
    private final String ADD_STOCK = "INSERT INTO tblStocks(ticker,name,sector,price,status) VALUES(?,?,?,?,?)";
    private final String DELETE_STOCK ="DELETE FROM tblStocks WHERE ticker=?";
    private final String SEARCH_BY_PRICE ="SELECT * FROM tblStocks WHERE price BETWEEN ? AND ?";
    private final String UPDATE = "UPDATE tblStocks SET name=?, sector=?, price=?, status=? WHERE ticker=?";
    private final String ORDER_BY_PRICE ="SELECT * FROM tblStocks ORDER BY price ";
    private final String SEARCH_BY_NAME ="SELECT * FROM tblStocks WHERE name like ? ";
    private final String SEARCH_BY_TICKER ="SELECT * FROM tblStocks WHERE ticker like ? ";
    private final String SEARCH_BY_SECTOR ="SELECT * FROM tblStocks WHERE sector like ? ";

    // check exist
    public boolean isTickerExist(String tinker) throws SQLException{
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(GET_STOCK_BY_TINKER)) {
            stm.setString(1, tinker);
            return stm.executeQuery().next();
        }
    }
    // create
    public boolean create(Stock s) throws SQLException{
        try(Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(ADD_STOCK)){
            ps.setString(1, s.getTicker());
            ps.setString(2, s.getName());
            ps.setString(3, s.getSector());
            ps.setFloat(4, s.getPrice());
            ps.setBoolean(4, s.isStatus());
            return ps.executeUpdate()>0;
        }
    }
    // delete
    public boolean delete(String ticker) throws SQLException{
        try(Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_STOCK)){
            ps.setString(1, ticker);
            return ps.executeUpdate()>0;
        }
    }
    //search by name
    public List<Stock> searchByName(String name) throws SQLException{
        return searchByKeyWord(SEARCH_BY_NAME, name);
    }
    //search by ticker
    public List<Stock> searchByTicker(String ticker) throws SQLException{
        return searchByKeyWord(SEARCH_BY_TICKER, ticker);
    }
    //search by sector
    public List<Stock> searchBySector(String sector) throws SQLException{
        return searchByKeyWord(SEARCH_BY_SECTOR, sector);
    }
    // search by keyword
    private List<Stock> searchByKeyWord(String sql,String keyword)
            throws SQLException{
        List<Stock> list = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }
    // search by price
    public List<Stock> searchbyPrice(double min,double max) throws SQLException {
        List<Stock> list = new ArrayList<>();
        try(Connection conn = DBContext.getConnection() ; PreparedStatement ps = conn.prepareStatement(SEARCH_BY_PRICE)){
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    // get all
    public List<Stock> getAllStock() throws SQLException {
        List<Stock> list = new ArrayList<Stock>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_ALL_STOCK);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

//    // sort asc
//    public List<Stock> findAllOrderByPriceAsc()
//            throws SQLException  {
//        return findByOrder("ASC");
//    }
//    // sort desc
//    public List<Stock> findAllOrderByPriceDesc()
//            throws SQLException  {
//        return findByOrder("DESC");
//    }
    //find by order
    public List<Stock> findAllOrderByPrice(String dir)
            throws SQLException  {
        List<Stock> list = new ArrayList<>();
        String sql = ORDER_BY_PRICE + dir;
        try ( Connection conn = DBContext.getConnection();  
                PreparedStatement ps = conn.prepareStatement(sql);  
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }
    // update
    public boolean update(Stock s) throws SQLException {
        try(Connection conn = DBContext.getConnection();PreparedStatement ps = conn.prepareStatement(UPDATE)){
            ps.setString(1, s.getName());
            ps.setString(2, s.getSector());
            ps.setFloat(3, s.getPrice());
            ps.setBoolean(4, s.isStatus());
            ps.setString(5, s.getTicker());
            return ps.executeUpdate() > 0;
        }
    }

    // helper
    private Stock mapRow(ResultSet rs) throws SQLException {
        return new Stock(
                rs.getString("ticker"),
                rs.getString("name"),
                rs.getString("sector"),
                rs.getFloat("price"),
                rs.getBoolean("status")
        );
    }

}
