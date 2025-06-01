/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import constant.Message;
import constant.Role;
import constant.Url;
import dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class AuthUtils {
    
    private static final String USER_SESSION = "currentUser";
    
    public static void setUserSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_SESSION, user);
    }
    
    public static void clearUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    
    public static boolean checkAuthorization(HttpServletRequest request, HttpServletResponse response,
            Role... roles)
            throws ServletException, IOException {
        if (!AuthUtils.hasRole(request, roles)) {
            request.setAttribute("MSG", Message.UNAUTHORIZED);
            request.getRequestDispatcher(Url.ERROR_PAGE).forward(request, response);
            return false;
        }
        return true;
    }
    
    public static boolean hasRole(HttpServletRequest request, Role... roles) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;
        User currentUser = (User) session.getAttribute(USER_SESSION);
        if (currentUser == null) return false;

        for (Role role : roles) {
            if (currentUser.getRole() == role) {
                return true;
            }
        }
        return false;
    }
    
    public static void handleRememberMe(boolean isRememberMe, String userID, HttpServletResponse response) {
        if (isRememberMe == true) {
            Cookie cookie = new Cookie("userID", userID);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(7 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("userID", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}

