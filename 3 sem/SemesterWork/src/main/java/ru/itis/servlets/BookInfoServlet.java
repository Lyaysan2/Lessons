package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;
import ru.itis.services.BookService;
import ru.itis.services.CommentsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/bookInfo")
public class BookInfoServlet extends HttpServlet {

    private BookService bookService;
    private CommentsService commentsService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.bookService = springContext.getBean(BookService.class);
        this.commentsService = springContext.getBean(CommentsService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //выводим информацию о книге
        String bookId = request.getParameter("bookId");
        Book book = bookService.findBookById(Integer.parseInt(bookId)).orElseThrow(IllegalArgumentException::new);
        request.setAttribute("book", book);

        //выводим все коменты о книге
        request.setAttribute("comments", commentsService.getByBookId(Integer.parseInt(bookId)));

        //передаем книгу для добавления комента
        request.getSession(true).setAttribute("book", book);

        request.getRequestDispatcher("/templates/bookInfo.jsp").forward(request, response);
    }
}
