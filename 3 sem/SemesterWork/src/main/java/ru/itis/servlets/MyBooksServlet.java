package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.BooksRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.BookService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@MultipartConfig
@WebServlet("/myBooks")
public class MyBooksServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.bookService = springContext.getBean(BookService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        UserDto userDto = bookService.getUserByEmail(email);

        request.setAttribute("allMyBook", bookService.getBySellerId(userDto.getId()));
        request.getRequestDispatcher("/templates/myBooks.jsp").forward(request, response);
    }
}
