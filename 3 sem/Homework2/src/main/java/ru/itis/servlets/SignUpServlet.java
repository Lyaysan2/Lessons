package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.Teacher;
import ru.itis.repositories.TeachersRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private TeachersRepository teachersRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.teachersRepository = springContext.getBean(TeachersRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String subject = request.getParameter("subject");
        String password = request.getParameter("password");

        teachersRepository.save(new Teacher(firstName, lastName, subject, password));

        response.sendRedirect("/sign-in");
    }
}
