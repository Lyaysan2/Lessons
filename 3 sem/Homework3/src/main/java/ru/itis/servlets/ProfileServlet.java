package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(true).getAttribute("firstName") != null) {

            request.setAttribute("firstName", request.getSession().getAttribute("firstName"));
            request.setAttribute("lastName", request.getSession().getAttribute("lastName"));
            request.setAttribute("email", request.getSession().getAttribute("email"));
            request.setAttribute("subject", request.getSession().getAttribute("subject"));
            request.setAttribute("avatar_id", request.getSession().getAttribute("avatar_id"));

            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/sign-in");
        }
    }
}
