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
import java.util.Optional;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private TeachersRepository teachersRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.teachersRepository = springContext.getBean(TeachersRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Teacher> teacher = teachersRepository.findByEmail(email);

        if (teacher.get().getPassword().equals(password)){
            request.getSession(true).setAttribute("firstName", teacher.get().getFirst_name());
            request.getSession(true).setAttribute("lastName", teacher.get().getLast_name());
            request.getSession(true).setAttribute("email", email);
            request.getSession(true).setAttribute("subject", teacher.get().getSubject());
            request.getSession(true).setAttribute("avatar_id", teacher.get().getAvatar_id());

            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/sign-in");
        }
    }
}
