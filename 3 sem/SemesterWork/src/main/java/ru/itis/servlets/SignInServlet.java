package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.SignInForm;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private final static String COOKIE_STATUS_NAME = "isAuthorized";
    private final static int COOKIE_STATUS_MAX_AGE = 60 * 60 * 24 * 365;

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.signInService = applicationContext.getBean(SignInService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInForm form = SignInForm.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        if (signInService.signIn(form)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("isAuthenticated", true);
            session.setAttribute("email", form.getEmail());

            Cookie cookie = new Cookie(COOKIE_STATUS_NAME, form.getEmail());
            cookie.setMaxAge(COOKIE_STATUS_MAX_AGE);
            response.addCookie(cookie);

            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/signIn?error");
        }
    }

}
