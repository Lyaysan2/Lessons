package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).removeAttribute("email");
        request.getSession(true).removeAttribute("isAuthenticated");

        Cookie cookie = new Cookie("isAuthorized", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect("/signIn");
    }
}
