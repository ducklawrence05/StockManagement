/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.AlertDAO;
import java.util.ArrayList;
import dto.Alert;
/**
 *
 * @author ADMIN
 */
public class AlertService {
    public static void main(String[] args) {
        AlertDAO dao = new AlertDAO();
        try {
            boolean a = dao.delete(7, "user003");
            System.out.println(a);
        } catch (Exception e) {
            System.out.println("Error");
        }
        
    }
}
