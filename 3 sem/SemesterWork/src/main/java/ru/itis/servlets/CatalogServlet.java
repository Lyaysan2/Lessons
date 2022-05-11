package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.repositories.BooksRepository;
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

@WebServlet("/catalog")
@MultipartConfig
public class CatalogServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.bookService = springContext.getBean(BookService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("allBook", bookService.findAllBook());
        request.getRequestDispatcher("/templates/catalog.jsp").forward(request, response);
    }
}
