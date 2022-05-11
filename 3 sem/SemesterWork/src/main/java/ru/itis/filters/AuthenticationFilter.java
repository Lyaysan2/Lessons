package ru.itis.filters;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.UserDto;
import ru.itis.repositories.BooksRepository;
import ru.itis.services.SignInService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getRequestURI().startsWith("/front") || request.getRequestURI().startsWith("/signIn") || request.getRequestURI().startsWith("/signUp")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        boolean isAuthenticated = false;

        //если есть сессия
        if (session != null){
            isAuthenticated = session.getAttribute("isAuthenticated") != null;
        }

        if(!isAuthenticated) {
            Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies())
                    .filter(item -> item.getName().equals("isAuthorized"))
                    .findFirst();
            if(optionalCookie.isPresent()) {
                String email = optionalCookie.get().getValue();
                session = request.getSession(true);
                session.setAttribute("email", email);
                isAuthenticated = true;
            }
        }

        if (isAuthenticated){
            filterChain.doFilter(request, response);
        } else{
            response.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {}
}
